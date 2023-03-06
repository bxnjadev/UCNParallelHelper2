package net.ibxnjadev.ucn.parallel.helper.read;

public class XmlsReaderSettings {

    private final int firstRow;
    private final int sheet;
    private final int columnMonday;
    private final int columnTuesday;
    private final int columnWednesday;
    private final int columnThursday;
    private final int columnFriday;
    private final int columnSaturday;
    private final int columnSunday;
    private final String path;

    public XmlsReaderSettings(int firstRow, int sheet, int columnMonday,
                              int columnTuesday, int columnWednesday,
                              int columnThursday, int columnFriday,
                              int columnSaturday, int columnSunday,
                              String path) {
        this.firstRow = firstRow;
        this.sheet = sheet;
        this.columnMonday = columnMonday;
        this.columnFriday = columnFriday;
        this.columnSunday = columnSunday;
        this.columnTuesday = columnTuesday;
        this.columnWednesday = columnWednesday;
        this.columnThursday = columnThursday;
        this.columnSaturday = columnSaturday;
        this.path = path;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public int getColumnMonday() {
        return columnMonday;
    }

    public int getColumnTuesday() {
        return columnTuesday;
    }

    public int getColumnFriday() {
        return columnFriday;
    }

    public int getColumnSunday() {
        return columnSunday;
    }

    public int getColumnWednesday() {
        return columnWednesday;
    }

    public int getColumnThursday() {
        return columnThursday;
    }

    public int getColumnSaturday() {
        return columnSaturday;
    }

    public int getSheet() {
        return sheet;
    }

    public String getPath() {
        return path;
    }

}
