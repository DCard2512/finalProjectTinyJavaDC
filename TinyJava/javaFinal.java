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

        Scanner s1 = new Scanner(program);
        token t;

        do {
            t = s1.next();
            System.out.println(t.type + ": " + t.lex);
        } while (t.type != GrammarToken.ENDOFFILE);

        Scanner s2 = new Scanner(program);
        Parse p = new Parse(s2);

        try {
            p.parsingFile();

           
            System.out.println(p.toString());

     
            p.printInterCode();


        } catch (RuntimeException e) {
            System.out.println("Parsing failed: " + e.getMessage());

          
            System.out.println(p.toString());

            System.out.println("\nIntermediate Code so far:");
            p.printInterCode();
        }
    }
}
