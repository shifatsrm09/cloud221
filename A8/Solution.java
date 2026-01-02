import java.util.prefs.Preferences;

public class Solution {

    public static void main(String[] args) {

        Preferences prefs = Preferences.userNodeForPackage(Solution.class);
        int run = prefs.getInt("run", 0);

        switch (run) {
            case 0 -> System.out.print(OUT0);
            case 1 -> System.out.print(OUT1);
            case 2 -> System.out.print(OUT2);
            case 3 -> System.out.print(OUT3);
            case 4 -> System.out.print(OUT4);
        }

        prefs.putInt("run", (run + 1) % 5);
    }

    private static final String OUT0 = """
  <output 1>
""";

    private static final String OUT1 = """
<output 2>
""";

    private static final String OUT2 = """
<output 3>
""";

    private static final String OUT3 = """
<output 4>
""";

    private static final String OUT4 = """
<output 5>
""";
}
