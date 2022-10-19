import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    @Test
    void isMembershipActiveTest() {

        LocalDate currentDate = LocalDate.now();
        LocalDate expiredMembership = currentDate.minusYears(1).minusDays(1);
        LocalDate expiredMembership2 = currentDate.minusYears(2);
        LocalDate activeMembership = currentDate.minusYears(1).plusDays(1);

        Member member = new Member("9912123810", "John. F. Kennedy", expiredMembership);
        assertFalse(member.isMembershipActive());
        member = new Member("9912123810", "John. F. Kennedy", activeMembership);
        assertTrue(member.isMembershipActive());
        member = new Member("9912123810", "John. F. Kennedy", expiredMembership2);
        assertFalse(member.isMembershipActive());


    }
}