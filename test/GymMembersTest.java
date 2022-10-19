import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class GymMembersTest {
    IOHandler ioHandler = new IOHandler();
    Path fileIn = Paths.get("test/costumersTest.txt");

    @Test
    void findMemberTest() throws IOException, Exception {
        GymMembers gymMembers = new GymMembers();
        gymMembers.setMembers(ioHandler.readDataFromFile(fileIn));

        Member member = gymMembers.findMemberByPersonalId("7605021234");
        assertEquals (member.getName(), "Elmer Ekorrsson");

        member = gymMembers.findMemberByName("Elmer Ekorrsson");
        assertEquals(member.getPersonId(), "7605021234");
    }
}