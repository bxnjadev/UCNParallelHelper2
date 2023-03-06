package net.ibxnjadev.ucn.parallel.helper.read.sql;

import net.ibxnjadev.ucn.parallel.helper.ParallelClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlClassesHandler {

    private final Connection connection;

    public SqlClassesHandler(Connection connection) {
        this.connection = connection;
    }

    public void createTable() {
        try {
            connection.prepareStatement("CREATE TABLE classes (semester varchar(150), identifier varchar (150), name varchar (150), nrc varchar (150), section VARCHAR (150), membersList VARCHAR (150), membersJoined VARCHAR (150), membersAvailable VARCHAR (150), credits VARCHAR (150), type VARCHAR (150), lastNameTeacher VARCHAR (150), nameTeacher VARCHAR (150), classes VARCHAR (150)) ")
                    .executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ParallelClass parallelClass) throws SQLException {

        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO classes (semester, identifer, name, nrc, section, membersLimit, membersJoined, memberAvailable, credits, type, lastNameTeacher, nameTeacher, classes) VALUES (" +
                        "?,?,?,?,?,?,?,?,?,?,?,?,?)");

        preparedStatement.setString(1, parallelClass.getSemester());
        preparedStatement.setString(2, parallelClass.getIdentifier());
        preparedStatement.setString(3, parallelClass.getName());
        preparedStatement.setString(4, parallelClass.getNrc());
        preparedStatement.setString(5, parallelClass.getSection());
        preparedStatement.setString(6, parallelClass.getMembersLimit());
        preparedStatement.setString(7, parallelClass.getMembersJoined());
        preparedStatement.setString(8, parallelClass.getMembersAvailable());
        preparedStatement.setString(9, parallelClass.getCredits());
        preparedStatement.setString(10, parallelClass.getType().toString());
        preparedStatement.setString(11, parallelClass.getLastNameTeacher());
        preparedStatement.setString(12, parallelClass.getNameTeacher());
        preparedStatement.setString(13, parallelClass.serializeClasses());


    }

}
