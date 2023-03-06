package net.ibxnjadev.ucn.parallel.helper.read;

public class ClassDefined {

    private static final String LIMITER = ":";
    private static final String SERIALIZE_STRUCTURE = "{block}" + LIMITER + "{day}" + LIMITER + "{classroom}";

    private final String block;
    private final String classroom;
    private final String day;

    public ClassDefined(String block, String day,
                        String classroom) {
        this.block = block;
        this.day = day;
        this.classroom = classroom;
    }

    public String getBlock() {
        return block;
    }

    public String getDay() {
        return day;
    }

    public String getClassroom() {
        return classroom;
    }

    public String serialize() {
        return SERIALIZE_STRUCTURE.replace("block", getBlock())
                .replace("day", getDay())
                .replace("classroom", getClassroom());
    }

    public static ClassDefined of(String componentSerialized) {
        String[] componentSeparated = componentSerialized.split(LIMITER);

        return new ClassDefined(componentSeparated[0], componentSeparated[1], componentSeparated[2]);
    }

}
