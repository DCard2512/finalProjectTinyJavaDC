package TinyJava;

public class lexical {
    private String i;
    private int position = 0;
    
    public lexical(String i) {
        this.i = i;
    }

    public String toString(){
        return "Input: " + i + ", position: " + position;
    }

}