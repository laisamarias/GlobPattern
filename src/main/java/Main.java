
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        if (args.length != 1) {
            System.out.println("Usage: $(COMMAND) [PATTERN]");
            return;
        }

        GlobPattern pattern = new GlobPattern(args[0]);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(pattern.match(line) ? "1" : "0");
        }
    }
}
