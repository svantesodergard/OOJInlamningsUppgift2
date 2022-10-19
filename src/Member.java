import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Member {
    private String name;
    private String personId;
    private LocalDate lastRenewed;

    public String getPersonId() {
        return personId;
    }

    public LocalDate getLastRenewed() {
        return lastRenewed;
    }

    public String getName() {
        return name;
    }

    public Member(String personId, String name, LocalDate lastRenewed) {
       this.name = name;
       this.personId = personId;
       this.lastRenewed = lastRenewed;
    }

    public boolean isMembershipActive() {
        LocalDate currentDate = LocalDate.now();
        Period periodSinceLastRenewed = Period.between(lastRenewed, currentDate);
        return periodSinceLastRenewed.getYears() < 1;
    }

    public String memberShipStatusString() {
        if (this.isMembershipActive()) {
            return this.name + "har ett aktivt medlemskap";
        } else {
            return this.name + "har varit medlem innan men har ett utgånget medlemskap";
        }
    }
}
