package TinyJava;

public class token {
    public GrammarToken type;
    public String lex;


    //Tokens that we use from Grammar token of types and lexer
    public token(GrammarToken type, String lex){
        this.type = type;
        this.lex = lex;
    }


    //Outputs string 
    public String toString(){
        return "Type: " + type + ", lex: " + lex;
    }
}
