package net.ibxnjadev.ucn.parallel.helper.read;

import net.ibxnjadev.ucn.parallel.helper.ParallelClass;
import net.ibxnjadev.ucn.parallel.helper.ParallelClassType;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class XmlsClassesReader implements ClassesReader {

    private static final String WHITE_LINE = " ";
    private final XmlsReaderSettings settings;
    private final CompoundParallel compoundParallel;

    public XmlsClassesReader(XmlsReaderSettings settings,
                             CompoundParallel compoundParallel) {
        this.settings = settings;
        this.compoundParallel = compoundParallel;
    }

    @Override
    public void read() {
        File file = new File(
                settings.getPath()
        );

        try (FileInputStream fileInputStream = new FileInputStream(file)) {

            Workbook workbook =
                    WorkbookFactory.create(fileInputStream);

            Sheet sheet = workbook.getSheetAt(settings.getSheet());

            int rowIndex = settings.getFirstRow();
            Row row = sheet.getRow(rowIndex);

            while (row != null) {
                iterateColumn(row, rowIndex);

                rowIndex = rowIndex + 1;
                row = sheet.getRow(rowIndex);
            }

        } catch (IOException e) {
            e.printStackTrace();

        }


    }

    private int iterateColumn(Row row, int rowIndex) {

        int indexColumn = 0;
        Cell cell = row.getCell(indexColumn);
        List<String> elements = new ArrayList<>();

        Cell cellId = row.getCell(1);
        String id = cellId.getStringCellValue();

        Cell cellBlock = row.getCell(10);
        String block = cellBlock.getStringCellValue();

        ParallelClass parallelClass = compoundParallel.find(id);

        if (parallelClass != null) {
            readAgainColumn(block, row, parallelClass);
            rowIndex = rowIndex + 1;

            return rowIndex;
        }

        while (cell != null) {

            if (cell.getCellType() == CellType.BLANK) {
                System.out.println("Add white");
                elements.add(WHITE_LINE);
                cell = row.getCell(indexColumn++);

                continue;
            }

            String result = cell.getCellType() == CellType.NUMERIC ?
                    String.valueOf(cell.getNumericCellValue()) : cell.getStringCellValue();

            System.out.println(result);
            elements.add(result);

            indexColumn = indexColumn + 1;
            cell = row.getCell(indexColumn);
        }

        Set<ClassDefined> allClasses = getAllClasses(block, elements);

        parallelClass = new ParallelClass.Builder()
                .semester(elements.get(0))
                .identifier(elements.get(1))
                .name(elements.get(2))
                .nrc(elements.get(3))
                .section(elements.get(4))
                .membersLimit(elements.get(5))
                .membersJoined(elements.get(6))
                .membersAvailable(elements.get(7))
                .credits(elements.get(8))
                .type(ParallelClassType.valueOf(elements.get(9)))
                .addClasses(allClasses)
                .lastNameTeacher(elements.get(19))
                .nameTeacher(elements.get(20))
                .build();

        compoundParallel.add(parallelClass);

        rowIndex = rowIndex + 1;

        return rowIndex;
    }

    private void readAgainColumn(String block, Row row, ParallelClass parallelClass) {

        Set<ClassDefined> classes = new HashSet<>();

        for (int i = 13; i < 21; i++) {
            Cell cell = row.getCell(i);

            String classroom = cell.getStringCellValue();
            String day = getDayForColumn(i);

            ClassDefined classDefined = new
                    ClassDefined(block, day, classroom);

            classes.add(classDefined);

        }

        parallelClass.addClasses(classes);
    }

    private Set<ClassDefined> getAllClasses(String block, List<String> elements) {

        Set<ClassDefined> classes = new HashSet<>();

        for (int i = 13; i < 21; i++) {
            String classroom = elements.get(i);

            if (classroom.equals(WHITE_LINE)) {
                continue;
            }

            String day = getDayForColumn(i);

            ClassDefined classDefined = new ClassDefined(block, day, classroom);
            classes.add(classDefined);

        }

        return classes;
    }

    private String getDayForColumn(int column) {
        return switch (column) {
            case 13 -> "Lunes";
            case 14 -> "Martes";
            case 15 -> "Miercoles";
            case 16 -> "Jueves";
            case 17 -> "Viernes";
            case 18 -> "Sabado";
            default -> "Domingo";
        };
    }


}
