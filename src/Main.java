import javax.swing.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        IOHandler ioHandler = new IOHandler();
        Path costumersPath = Paths.get("src/costumers.txt");

        GymMembers gymMembers = new GymMembers();
        try {
            gymMembers.setMembers(ioHandler.readDataFromFile(costumersPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            String memberToFind = JOptionPane.showInputDialog(null, "Medlemmens namn/personnummer");
            if (memberToFind == null) {
                break;
            }
            try {
                Member member = gymMembers.findMember(memberToFind);
                JOptionPane.showMessageDialog(null, member.memberShipStatusString());
                if (member.isMembershipActive()) {
                    ioHandler.printVisitToFile(member);
                }
            } catch (IllegalArgumentException | IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
}