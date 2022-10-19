import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IOHandlerTest {
    Path fileIn = Paths.get("test/costumersTest.txt");
    IOHandler ioHandler = new IOHandler(true);

    @Test
    void readDataFromFileTest() throws IOException {
        List<Member> costumers = ioHandler.readDataFromFile(fileIn);
        assert (costumers.size() == 14);
        assertEquals (costumers.get(0).getName(), "Alhambra Aromes");
        assertEquals (costumers.get(0).getPersonId(), "7703021234");
        assertEquals (costumers.get(0).getLastRenewed(),
                LocalDate.parse("2022-07-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Test
    void getPathToFileForMemberTest() {
        ioHandler = new IOHandler(false);
        Member member = new Member("9912124591", "Axel knäskål", LocalDate.now());
        Path expectedPath = Paths.get("costumers/9912124591.txt");
        Path actualPath = ioHandler.getPathToFileForMember(member);

        assertEquals(expectedPath, actualPath);
    }

    @Test
    void printVisitToFileTest() {
        Path path = Paths.get("test/testCostumer.txt");
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Member m = new Member("123", "Jonas", LocalDate.now());
        try {
            ioHandler.printVisitToFile(m);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert (Files.exists(path));
    }

}