package TinyJava;

public class javaFinal {
    public static void main(String[] args) {

        String program = """
            int x, y;
            double z;

            x = 3 + 5 * 2;

            if (x < 20) {
                z = x * 1.0;
            }
        """;

        System.out.println("=== TOKEN TRAIN ===");
        Scanner lexer1 = new Scanner(program);
        token t;
        do {
            t = lexer1.next();
            System.out.println(t);
        } while (t.type != GrammarToken.ENDOFFILE);

        System.out.println("\n=== PARSER OUTPUT ===");
        Scanner lexer2 = new Scanner(program);
        Parse parser = new Parse(lexer2);
        parser.parseProgram();
    }
}
