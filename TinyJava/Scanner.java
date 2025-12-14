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
        char input;

        incWhiteSpace();
        
        
        
    }
}
