package TinyJava;

public class Symbol {
    String type;
    String name;

    //Parent object
    public Symbol(String n, String t){
        name = n;
        type = t;
    }

    //This will overload and print out if needed
    public String toString(){
        return "Type: " + type + "Name: " + name;
    }


}
