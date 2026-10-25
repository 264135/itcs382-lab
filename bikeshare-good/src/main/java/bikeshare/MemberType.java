package bikeshare;

public enum MemberType {
    STUDENT(2),
    STAFF(3);

    private final int bahtPerMinute;

    MemberType(int bahtPerMinute) {
        this.bahtPerMinute = bahtPerMinute;
    }

    public int fee(int minutes) {
        return bahtPerMinute * minutes;
    }

    public static MemberType parse(String text) {
        if ("student".equals(text)) {
            return STUDENT;
        }
        if ("staff".equals(text)) {
            return STAFF;
        }
        throw new IllegalArgumentException("Unknown member type");
    }
}
