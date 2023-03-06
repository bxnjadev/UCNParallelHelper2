package net.ibxnjadev.ucn.parallel.helper;

import net.ibxnjadev.ucn.parallel.helper.read.ClassesReader;
import net.ibxnjadev.ucn.parallel.helper.read.CompoundParallel;
import net.ibxnjadev.ucn.parallel.helper.read.XmlsClassesReader;
import net.ibxnjadev.ucn.parallel.helper.read.XmlsReaderSettings;
import net.ibxnjadev.ucn.parallel.helper.read.sql.SqlClassesHandler;

import java.sql.SQLException;

public class Main2 {

    public static void main(String[] args) {

        XmlsReaderSettings xmlsReaderSettings = new XmlsReaderSettings(
                4, 0, 13,
                14, 15,
                16, 17, 18,
                19,
                "oferta.xlsx"
        );

        CompoundParallel compoundParallel = new CompoundParallel();

        ClassesReader classesReader = new XmlsClassesReader(
                xmlsReaderSettings, compoundParallel
        );

        classesReader.read();


        SqlConnection sqlConnection = new SqlConnection(
                "localhost", 3306, "root", ""
        );

        sqlConnection.connect();

        SqlClassesHandler sqlClassesHandler = new SqlClassesHandler(
                sqlConnection.get()
        );

        sqlClassesHandler.createTable();

        compoundParallel.values().forEach(parallelClass -> {
            try {
                sqlClassesHandler.update(parallelClass);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }

}
