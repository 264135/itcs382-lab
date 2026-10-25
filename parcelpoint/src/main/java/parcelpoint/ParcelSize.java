package parcelpoint;

public enum ParcelSize {
    SMALL(1),
    MEDIUM(2),
    LARGE(3);

    private final int code;

    ParcelSize(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ParcelSize fromCode(int code) {
        for (ParcelSize size : values()) {
            if (size.code == code) {
                return size;
            }
        }
        throw new IllegalArgumentException("Unknown size code: " + code);
    }
}
