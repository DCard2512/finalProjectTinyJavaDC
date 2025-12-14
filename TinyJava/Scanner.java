package TinyJava;


public class Scanner {
    private String in;
    private int position = 0;
    
    public Scanner(String in) {
        this.in = in;
    }

    public String toString(){
        return "Input: " + in + ", position: " + position;
    }

    private char top(){
        if(position > in.length())
            return ' ';
        else
            return in.charAt(position);
    }

    private char inc(){
        return in.charAt(position++);
    }

    private void incWhiteSpace(){
        while(Character.isWhitespace(top()))
            inc();
    }

    public token next(){
        String input = "";
        char i = top();
        incWhiteSpace();
        if(Character.isLetter(i)){
            while (Character.isLetterOrDigit(top())) {
                input += inc();
            }

            switch(input){
            case "int":
                return new token(GrammarToken.INTEGER, input);
            case "double":
                return new token(GrammarToken.DOUBLE, input);
            case "if":
                return new token(GrammarToken.IF, input);
            default: 
                input="";
            }
        }

        

        if(Character.isDigit(i)){
            
            while(Character.isDigit(i)){
                input += inc();
            }

            if(top() == '.'){
                input += inc();
                while(Character.isDigit(top())){
                    input += inc();
                }

                return new token(GrammarToken.Num_Double, input);
            }
            else{
                return new token(GrammarToken.Num_Int, input);
            }
        }

        input = "";
        switch(inc()){
            case '+':
                return new token(GrammarToken.ADD, "+");
            case '-':
                return new token(GrammarToken.SUB, "+");
            case '*':
                return new token(GrammarToken.MULT, "*");
            case '/':
                return new token(GrammarToken.DIV, "/");
            case '=':
                if(top() == '='){
                    inc();
                    return new token(GrammarToken.EQUAL, "==");
                }
            case '!':
                if (top() == '=') {
                    inc();
                    return new token(GrammarToken.NET, "!=");
                }
                break;
            case '<':
                if (top() == '=') {
                    inc();
                    return new token(GrammarToken.LTET, "<=");
                }
                return new token(GrammarToken.LT, "<");
            case '>':
                if (top() == '=') {
                    inc();
                    return new token(GrammarToken.GTET, ">=");
                }
                return new token(GrammarToken.GT, ">");
        }

        throw new RuntimeException("Not valid");
    }
}
