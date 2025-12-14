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
        boolean isDouble = false;
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
                isDouble = true;
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


        switch(top(){
            
        })
        
        
    }
}
