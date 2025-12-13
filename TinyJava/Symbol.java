package TinyJava;

public class Symbol {
    String type;
    String name;


    public Symbol(String t, String n){
        type = t;
        name = n;
    }

    public String toString(){
        return "Type: " + type + "Name: " + name;
    }


}
