import java.util.List;

public class GymMembers {
    private List<Member> members;

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Member findMember(String personalIdOrName) throws IllegalArgumentException {
        char firstChar = personalIdOrName.charAt(0);
        if (Character.isDigit(firstChar)) {
            return findMemberByPersonalId(personalIdOrName);
        }
        return findMemberByName(personalIdOrName);
    }
    public Member findMemberByPersonalId(String personalId) throws IllegalArgumentException {
        for (Member member : members) {
            if (member.getPersonId().equals(personalId)) {
                return member;
            }
        }
        throw new IllegalArgumentException("Medlemmen finns inte i databasen");
    }

    public Member findMemberByName(String name) throws IllegalArgumentException {
        for (Member member : members) {
            if (member.getName().equals(name)) return member;
        }
        throw new IllegalArgumentException("Medlemmen finns inte i databasen");
    }
}
