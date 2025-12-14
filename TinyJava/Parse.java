package TinyJava;

public class Parse {
    
    private Scanner lexScanner;
    private token curr;
    private SymbolTable symbolTable = new SymbolTable();

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
        addSubParsing();
        relationalParsing();
        addSubParsing();
        nextTok(GrammarToken.RP);
        statementParsing();

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
        if (symbolTable.find(curr.lex) == null) {
            System.err.println("Warning: Undeclared variable " + curr.lex);
        }
        nextTok(GrammarToken.VAR);
        nextTok(GrammarToken.ASSIGN);
        addSubParsing();
        nextTok(GrammarToken.Semi);
    }


    //Gets the Add and Sub parsing tokens of grammar
    private void addSubParsing() {
        multDivParsing();
        while (curr.type == GrammarToken.ADD || curr.type == GrammarToken.SUB) {
            nextTok(curr.type);
            multDivParsing();
        }
    }



    //Gets the multiplication and Division tokens for parsing
    private void multDivParsing() {
        numParsing();
        while (curr.type == GrammarToken.MULT || curr.type == GrammarToken.DIV) {
            nextTok(curr.type);
            numParsing();
        }
    }



    //Gets the number of a variable for parsing if Int or Double
    private void numParsing() {
        switch (curr.type) {
            case Num_Int:
                nextTok(GrammarToken.Num_Int);
                break;
            case Num_Double:
                nextTok(GrammarToken.Num_Double);
                break;
            case LP:
                nextTok(GrammarToken.LP);
                addSubParsing();
                nextTok(GrammarToken.RP);
                break;
            default:
                errorMsg("Invalid expression");
        }
    }

}
