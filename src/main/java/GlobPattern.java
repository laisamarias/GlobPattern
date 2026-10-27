
public class GlobPattern {
    private final String pattern;

    public GlobPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean match(String text) {
        if (text.length() != pattern.length()) {
            return false;
        }

        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) != '?' && pattern.charAt(i) != text.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
