import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GlobPatternTest {

    @Test
    void matchesAnySingleCharacter() {
        GlobPattern pattern = new GlobPattern("?at");

        assertTrue(pattern.match("Cat"));
        assertTrue(pattern.match("cat"));
        assertTrue(pattern.match("bat"));
    }

    @Test
    void rejectsDifferentLengths() {
        GlobPattern pattern = new GlobPattern("?at");

        assertFalse(pattern.match("at"));
        assertFalse(pattern.match("boat"));
        assertFalse(pattern.match("cats"));
    }

    @Test
    void matchesLiteralCharactersExactly() {
        GlobPattern pattern = new GlobPattern("cat");

        assertTrue(pattern.match("cat"));
        assertFalse(pattern.match("bat"));
        assertFalse(pattern.match("car"));
    }

    @Test
    void supportsSeveralWildcards() {
        GlobPattern pattern = new GlobPattern("???");

        assertTrue(pattern.match("abc"));
        assertTrue(pattern.match("123"));
        assertFalse(pattern.match("ab"));
        assertFalse(pattern.match("abcd"));
    }

    @Test
    void handlesEmptyPattern() {
        GlobPattern pattern = new GlobPattern("");

        assertTrue(pattern.match(""));
        assertFalse(pattern.match("a"));
    }

    @Test
    void matchesWildcardBetweenLiteralCharacters() {
        GlobPattern pattern = new GlobPattern("a?c");

        assertTrue(pattern.match("abc"));
        assertTrue(pattern.match("a1c"));
        assertFalse(pattern.match("ac"));
        assertFalse(pattern.match("abbc"));
    }
}
