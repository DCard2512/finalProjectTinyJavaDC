package TinyJava;

import java.util.HashMap;
import java.util.Map;


public class SymbolTable {
    private Map<String,Symbol> table = new HashMap();

    //Hashmap used for key and value of type and name 
    public void insert(String type, String name){
        table.put(name, new Symbol(name, type));
    }

    //Finds the key value "name" in our symbol table 
    public Symbol find(String name){
        return table.get(name);
    }

    public String toString(){
        System.out.println("Symbol Table\n");
        for(Symbol s: table.values())
            System.out.println(s.name + ": " + s.type + "\n");
        return "";

    }
}
