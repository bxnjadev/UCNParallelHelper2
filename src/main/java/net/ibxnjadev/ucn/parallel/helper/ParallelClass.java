package net.ibxnjadev.ucn.parallel.helper;

import net.ibxnjadev.ucn.parallel.helper.read.ClassDefined;

import java.util.HashSet;
import java.util.Set;

public class ParallelClass {

    public static final String LIMITER = ",";

    private final String semester;
    private final String identifier;
    private final String name;
    private final String nrc;
    private final String section;
    private final String membersLimit;
    private final String membersJoined;
    private final String membersAvailable;
    private final String credits;
    private final ParallelClassType type;
    private final String lastNameTeacher;
    private final String nameTeacher;
    private final Set<ClassDefined> classes;

    public ParallelClass(String semester,
                         String identifier,
                         String name,
                         String nrc,
                         String section,
                         String membersLimit,
                         String membersJoined,
                         String membersAvailable,
                         String credits,
                         ParallelClassType type,
                         String lastNameTeacher,
                         String nameTeacher,
                         Set<ClassDefined> classes) {

        this.semester = semester;
        this.identifier = identifier;
        this.name = name;
        this.nrc = nrc;
        this.section = section;
        this.membersLimit = membersLimit;
        this.membersJoined = membersJoined;
        this.credits = credits;
        this.type = type;
        this.lastNameTeacher = lastNameTeacher;
        this.membersAvailable = membersAvailable;
        this.nameTeacher = nameTeacher;
        this.classes = classes;
    }

    public String getSemester() {
        return semester;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getNrc() {
        return nrc;
    }

    public String getSection() {
        return section;
    }

    public String getMembersLimit() {
        return membersLimit;
    }

    public String getMembersJoined() {
        return membersJoined;
    }

    public String getMembersAvailable() {
        return membersAvailable;
    }

    public String getCredits() {
        return credits;
    }

    public void addClasses(Set<ClassDefined> classes) {
        this.classes.addAll(classes);
    }

    public String getLastNameTeacher() {
        return lastNameTeacher;
    }

    public String getNameTeacher() {
        return nameTeacher;
    }

    public ParallelClassType getType() {
        return type;
    }

    public Set<ClassDefined> getClasses() {
        return classes;
    }

    public String serializeClasses() {
        StringBuilder stringBuilder = new StringBuilder();

        for (ClassDefined classDefined : classes) {
            stringBuilder.append(classDefined.serialize()).append(LIMITER);
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    public void addClass(ClassDefined classDefined) {
        classes.add(classDefined);
    }

    public static class Builder {

        private String semester;
        private String identifier;
        private String name;
        private String nrc;
        private String section;
        private String membersLimit;
        private String membersJoined;
        private String membersAvailable;
        private String credits;
        private ParallelClassType type;
        private String lastNameTeacher;
        private String nameTeacher;
        private final Set<ClassDefined> classes = new HashSet<>();

        public Builder semester(String semester) {
            this.semester = semester;
            return self();
        }

        public Builder identifier(String identifier) {
            this.identifier = identifier;
            return self();
        }

        public Builder name(String name) {
            this.name = name;
            return self();
        }

        public Builder nrc(String nrc) {
            this.nrc = nrc;
            return self();
        }

        public Builder section(String section) {
            this.section = section;
            return self();
        }

        public Builder membersLimit(String membersLimit) {
            this.membersLimit = membersLimit;
            return self();
        }

        public Builder membersJoined(String membersJoined) {
            this.membersJoined = membersJoined;
            return self();
        }

        public Builder membersAvailable(String membersAvailable) {
            this.membersAvailable = membersAvailable;
            return self();
        }

        public Builder credits(String credits) {
            this.credits = credits;
            return self();
        }

        public Builder type(ParallelClassType type) {
            this.type = type;
            return self();
        }

        public Builder lastNameTeacher(String lastNameTeacher) {
            this.lastNameTeacher = lastNameTeacher;
            return self();
        }

        public Builder nameTeacher(String nameTeacher) {
            this.nameTeacher = nameTeacher;
            return self();
        }

        public Builder addClass(ClassDefined classDefined) {
            classes.add(classDefined);
            return self();
        }

        public Builder addClasses(Set<ClassDefined> classes) {
            this.classes.addAll(classes);
            return self();
        }

        private Builder self() {
            return this;
        }

        public ParallelClass build() {
            return new ParallelClass(semester, identifier, name, nrc, section, membersLimit,
                    membersJoined, membersAvailable, credits, type, lastNameTeacher, nameTeacher, classes);
        }

    }

}
