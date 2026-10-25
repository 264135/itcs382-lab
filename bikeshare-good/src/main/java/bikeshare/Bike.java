package bikeshare;

public class Bike {
    private final String id;
    private String memberId;
    private MemberType memberType;

    public Bike(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isFree() {
        return memberId == null;
    }

    public boolean isRentedBy(String memberId) {
        return this.memberId != null && this.memberId.equals(memberId);
    }

    public void rentTo(String memberId, MemberType memberType) {
        this.memberId = memberId;
        this.memberType = memberType;
    }

    public MemberType release() {
        MemberType type = memberType;
        memberId = null;
        memberType = null;
        return type;
    }
}
