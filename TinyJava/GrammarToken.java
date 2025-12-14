package TinyJava;

public enum GrammarToken {
    //Variable Types
    INTEGER, DOUBLE, 
    
    //Numbers
    Num_Int, Num_Double,

    //Arith. Operator
    ADD, SUB, MULT, DIV, MOD, INC, DEC,

    //Relational Operator
    ET, NET, LT, GT, LTET, GTET,

    //Assignment/if Statements
    EQUAL, IF, ASSIGN
}
