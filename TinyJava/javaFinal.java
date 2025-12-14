package TinyJava;

public class javaFinal {
    public static void main(String[] args) {

        String program = """
            int y;
            double x;

            y = 12 % 3;
            x = 5 * 4;

            if( x == y)
            x = 5 * y;

        """;

        System.out.println("Token");
        Scanner tokScanner = new Scanner(program);
        token t;
        do {
            t = tokScanner.next();
            System.out.println(t);
        } while (t.type != GrammarToken.ENDOFFILE);

        System.out.println("\nParse Output");
        Scanner parseScanner = new Scanner(program);
        Parse parser = new Parse(parseScanner);
        parser.parsingFile();
    }
}
