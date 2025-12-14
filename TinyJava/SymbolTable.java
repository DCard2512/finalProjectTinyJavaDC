package TinyJava;

import java.util.HashMap;
import java.util.Map;


public class SymbolTable {
    private Map<String,Symbol> table = new HashMap();

    public void insert(String type, String name){
        table.put(name, new Symbol(name, type));
    }
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
