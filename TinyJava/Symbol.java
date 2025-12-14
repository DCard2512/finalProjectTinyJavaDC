package TinyJava;

public class Symbol {
    String type;
    String name;


    public Symbol(String n, String t){
        name = n;
        type = t;
    }

    public String toString(){
        return "Type: " + type + "Name: " + name;
    }


}
