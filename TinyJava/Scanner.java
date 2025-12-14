package TinyJava;


public class Scanner {
    private String in;
    private int position = 0;
    
    //
    public Scanner(String in) {
        this.in = in;
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

    //Gets token depending on the grammar
    public token next(){
        String input = "";
        char i = top();
        incWhiteSpace();



        //Used if text is String
        if(Character.isLetter(i)){

            //Checks if digit for Variable type 
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

        
        //If digit then checks if Interger or Double Numbers
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

        //Gets the Arith and Relational Operators 
        switch(inc()){
            case '+':
                return new token(GrammarToken.ADD, "+");
            case '-':
                return new token(GrammarToken.SUB, "+");
            case '*':
                return new token(GrammarToken.MULT, "*");
            case '/':
                return new token(GrammarToken.DIV, "/");
            case '(': 
                return new token(GrammarToken.LP, "(");
            case ')': 
                return new token(GrammarToken.RP, ")");
            case '{': 
                return new token(GrammarToken.LB, "{");
            case '}': 
                return new token(GrammarToken.RB, "}");
            case ',': 
                return new token(GrammarToken.Comma, ",");
            case ';': 
                return new token(GrammarToken.Semi, ";");
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
        throw new RuntimeException("Not valid" + i);
    }



    //String output
    public String toString(){
        return "Input: " + in + ", position: " + position;
    }
}
