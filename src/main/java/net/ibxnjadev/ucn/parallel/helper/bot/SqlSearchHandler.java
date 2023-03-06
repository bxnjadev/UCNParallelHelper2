package net.ibxnjadev.ucn.parallel.helper.bot;

import net.ibxnjadev.ucn.parallel.helper.ParallelClass;
import net.ibxnjadev.ucn.parallel.helper.ParallelClassType;
import net.ibxnjadev.ucn.parallel.helper.read.ClassDefined;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class SqlSearchHandler {

    private final Connection connection;

    public SqlSearchHandler(Connection connection) {
        this.connection = connection;
    }

    public Set<ParallelClass> getCompoundParallelByName(String name) {

        Set<ParallelClass> parallelClasses = new HashSet<>();

        String table = "classes";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM " + table + " WHERE name = ?"
            );

            preparedStatement.setString(1, name);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                parallelClasses.add(
                        parseAsParallelClass(resultSet)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parallelClasses;
    }

    public ParallelClass parseAsParallelClass(ResultSet resultSet) throws SQLException {

        Set<ClassDefined> classesCompound = new HashSet<>();

        String classes = resultSet.getString("classes");
        String[] classesParts = classes.split(ParallelClass.LIMITER);

        for (String classDefinedSerialized : classesParts) {
            classesCompound.add(
                    ClassDefined.of(
                            classDefinedSerialized
                    )
            );
        }

        return new ParallelClass.Builder()
                .name(resultSet.getString("name"))
                .lastNameTeacher(resultSet.getString("lastNameTeacher"))
                .credits(resultSet.getString("credits"))
                .membersAvailable(resultSet.getString("membersAvailable"))
                .membersLimit(resultSet.getString("membersLimit"))
                .membersJoined(resultSet.getString("membersJoined"))
                .identifier(resultSet.getString("identifier"))
                .nrc(resultSet.getString("nrc"))
                .type(ParallelClassType.valueOf(resultSet.getString("type")))
                .section(resultSet.getString("section"))
                .nameTeacher(resultSet.getString("nameTeacher"))
                .semester(resultSet.getString("semester"))
                .addClasses(classesCompound)
                .build();
    }

}
