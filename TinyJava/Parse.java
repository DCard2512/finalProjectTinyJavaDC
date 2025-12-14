package TinyJava;

import java.util.*;

public class Parse {
    
    private Scanner lexScanner;
    private token curr;
    private SymbolTable symbolTable = new SymbolTable();

    private List<String> interCode = new ArrayList<>();
    private int tempCnt = 0;
    private int labelCnt = 0;

    public Parse(Scanner lexScanner){
        this.lexScanner = lexScanner;
        curr = lexScanner.next();
    }

    //Gets the next grammartoken in line of code
    private void nextTok(GrammarToken type) {
        if (curr.type == type) {
            curr = lexScanner.next();
        } 
        else if(curr.lex == "(" || curr.lex == ")" || curr.lex == "{" || curr.lex == "}" || curr.lex == "," || curr.lex == ";"){
            lexScanner.next();
        }
        else {
            errorMsg("Expected '" + type + "', but found '" + curr.type + "' (" + curr.lex + ")");

        }
    }


    //Error message for anything wrong 
    private void errorMsg(String msg) {
        throw new RuntimeException("Parse error: " + msg);
    }

    private String temp(){
        tempCnt++;
        return "Temp: " + tempCnt;
    }

    private String label(){
        labelCnt++;
        return "Label: " + labelCnt;
    }


    //Gets the end of file for GrammarToken of tokens
    public void parsingFile() {
        while (curr.type != GrammarToken.ENDOFFILE) {
            typeParse();
        }
        symbolTable.toString();
        System.out.println("\nParsing completed successfully.");
    }

    //Parse for the variables within lines of code
    private void varParse() {
        String type = curr.lex;
        nextTok(curr.type);

        symbolTable.insert(curr.lex, type);
        nextTok(GrammarToken.VAR);

        while (curr.type == GrammarToken.Comma) {
            nextTok(GrammarToken.Comma);
            symbolTable.insert(curr.lex, type);
            nextTok(GrammarToken.VAR);
        }

        nextTok(GrammarToken.Semi);
    }


    //Checks the type of the variable
    private void typeParse() {
        if (curr.type == GrammarToken.INTEGER || curr.type == GrammarToken.DOUBLE) {
            varParse();
        } else {
            statementParsing();
        }
    }

    

    //Statement parsing of 
    private void statementParsing() {
        if (curr.type == GrammarToken.VAR) {
            assignmentParse();
        } else if (curr.type == GrammarToken.IF) {
            ifParsing();
        } else if (curr.type == GrammarToken.LB) {
            brackParse();
        } else {
            errorMsg("Invalid typeParse");
        }
    }


    


    //Parses for the if statements
    private void ifParsing() {
        nextTok(GrammarToken.IF);
        nextTok(GrammarToken.LP);

        String left = addSubParsing();      // now returns place
        GrammarToken rel = curr.type;       // save operator token
        relationalParsing();
        String right = addSubParsing();

        nextTok(GrammarToken.RP);

        String L = label();
        interCode.add("if_false " + left + " " + rel + " " + right + " goto " + L);

        statementParsing();

        interCode.add(L + ":");
    }

    //Checks the bracket parsing of tokens 
    private void brackParse() {
        nextTok(GrammarToken.LB);
        while (curr.type != GrammarToken.RB) {
            typeParse();
        }
        nextTok(GrammarToken.RB);
    }


    //Checks for relational tokens
    private void relationalParsing() {
        switch (curr.type) {
            case ET: case NET: case LT: case LTET: case GT: case GTET:
                nextTok(curr.type);
                break;
            default:
                errorMsg("Expected relational operator");
        }
    }

    //Parsing the assignment type
    private void assignmentParse() {
        String lhs = curr.lex;

        if (symbolTable.find(lhs) == null) {
            System.err.println("Warning: Undeclared variable " + lhs);
        }

        nextTok(GrammarToken.VAR);
        nextTok(GrammarToken.ASSIGN);

        String rhs = addSubParsing();   // now returns a place/temp

        nextTok(GrammarToken.Semi);

        interCode.add(lhs + " = " + rhs);
    }


    //Gets the Add and Sub parsing tokens of grammar
    private String addSubParsing() {
        String left = multDivParsing();

        while (curr.type == GrammarToken.ADD || curr.type == GrammarToken.SUB) {
            GrammarToken op = curr.type;
            nextTok(curr.type);
            String right = multDivParsing();

            String t = temp();
            interCode.add(t + " = " + left + " " + op + " " + right);
            left = t;
        }

        return left;
    }



    //Gets the multiplication and Division tokens for parsing
    private String multDivParsing() {
        String left = numParsing();

        while (curr.type == GrammarToken.MULT || curr.type == GrammarToken.DIV || curr.type == GrammarToken.MOD) {
            GrammarToken op = curr.type;
            nextTok(curr.type);
            String right = numParsing();

            String t = temp();
            interCode.add(t + " = " + left + " " + op + " " + right);
            left = t;
        }

        return left;
    }



    //Gets the number of a variable for parsing if Int or Double
    private String numParsing() {
        switch (curr.type) {

        case VAR: {
            String v = curr.lex;
            nextTok(GrammarToken.VAR);
            return v;
        }

        case Num_Int: {
            String v = curr.lex;
            nextTok(GrammarToken.Num_Int);
            return v;
        }

        case Num_Double: {
            String v = curr.lex;
            nextTok(GrammarToken.Num_Double);
            return v;
        }

        case LP: {
            nextTok(GrammarToken.LP);
            String inside = addSubParsing();
            nextTok(GrammarToken.RP);
            return inside;
        }

        default:
            errorMsg("Invalid expression");
            return ""; 
        }
    }


    public void printInterCode(){
        System.out.println("\nIntermediate Code: ");
        for(String s: interCode){
            System.out.println(s);
        }
    }

}
