package TinyJava;

public class token {
    public GrammarToken type;
    public String lex;

    public token(GrammarToken type, String lex){
        this.type = type;
        this.lex = lex;
    }

    public String toString(){
        return "Type: " + type + ", lex: " + lex;
    }
}
