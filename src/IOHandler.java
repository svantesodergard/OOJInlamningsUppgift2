import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IOHandler {
    private boolean test;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public IOHandler() {}
    public IOHandler(boolean test) {
        this.test = test;
    }
    public List<Member> readDataFromFile(Path fileIn) throws IOException {
        try (BufferedReader bufferedReader = Files.newBufferedReader(fileIn)){
            List<Member> members = new ArrayList<>();
            String firstLine, secondLine;

            while ((firstLine = bufferedReader.readLine()) != null && (secondLine = bufferedReader.readLine()) != null) {
                String[] dataParts = Arrays.copyOf(firstLine.split(",", 2), 3);
                dataParts[2] = secondLine;
                for (int i = 0; i < dataParts.length; i++) {
                    dataParts[i] = dataParts[i].trim();
                }
                LocalDate lastRenewed = LocalDate.parse(dataParts[2], dateTimeFormatter);
                members.add(new Member(dataParts[0], dataParts[1], lastRenewed));
            }
            return members;
        }
    }

    public void printVisitToFile(Member member) throws IOException {
        Path filePath = getPathToFileForMember(member);
        System.out.println(filePath);
        if (!Files.exists(filePath)) {
            createFileForMember(member);
        }
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(
                filePath, StandardCharsets.UTF_8, StandardOpenOption.APPEND)){
            bufferedWriter.newLine();
            bufferedWriter.write(LocalDate.now().format(dateTimeFormatter));
        }
    }
    public Path getPathToFileForMember(Member member) {
        if (test) return Paths.get("test/testCostumer.txt");
        String pathString = "costumers/" + member.getPersonId() + ".txt";
        return Paths.get(pathString);
    }
    public void createFileForMember(Member member) throws IOException{
        Path filePath = getPathToFileForMember(member);
        StringBuilder toPrint = new StringBuilder();
        Files.createFile(filePath);
        toPrint.append(member.getName().toUpperCase());
        toPrint.append("\n"+member.getPersonId());
        toPrint.append("\nRegistrerade Besök:");
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(
                filePath, StandardCharsets.UTF_8, StandardOpenOption.APPEND)){
            bufferedWriter.write(toPrint.toString());
        }
    }
}
