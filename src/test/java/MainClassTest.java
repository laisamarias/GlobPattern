import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class MainClassTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @AfterEach
    void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    private String runMain(String input, String... args) {
        System.setIn(new ByteArrayInputStream(
                input.getBytes(StandardCharsets.UTF_8)));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Main.main(args);

        return output.toString()
                .replace("\r\n", "\n");
    }

    @Test
    void printsOneForMatchingLines() {
        assertEquals("1\n1\n", runMain("cat\nbat\n", "?at"));
    }

    @Test
    void printsZeroForNonMatchingLines() {
        assertEquals("0\n0\n", runMain("at\nboat\n", "?at"));
    }

    @Test
    void printsUsageWithNoArguments() {
        assertEquals(
                "Usage: $(COMMAND) [PATTERN]\n",
                runMain("", new String[0]));
    }

    @Test
    void printsUsageWithTooManyArguments() {
        assertEquals(
                "Usage: $(COMMAND) [PATTERN]\n",
                runMain("", "?at", "extra"));
    }
}
