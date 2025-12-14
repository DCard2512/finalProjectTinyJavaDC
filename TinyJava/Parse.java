package TinyJava;

public class Parse {
    
    private Scanner lexScanner;
    private token curr;
    private SymbolTable symbolTable = new SymbolTable();

    public Parse(Scanner lexScanner){
        this.lexScanner = lexScanner;
        curr = lexScanner.next();
    }

        private void nextTok(GrammarToken type) {
        if (curr.type == type) {
            curr = lexScanner.next();
        } 
        else if(curr.lex == "(" || curr.lex == ")" || curr.lex == "{" || curr.lex == "}" || curr.lex == "," || curr.lex == ";"){
            lexScanner.next();
        }
        else {
            error("Expected " + type + ", found " + curr.type);
        }
    }

    private void error(String msg) {
        throw new RuntimeException("Parse error: " + msg);
    }

    public void parseProgram() {
        while (curr.type != GrammarToken.ENDOFFILE) {
            parseDeclOrStmt();
        }
        symbolTable.toString();
        System.out.println("\nParsing completed successfully.");
    }

    private void parseDeclOrStmt() {
        if (curr.type == GrammarToken.INTEGER || curr.type == GrammarToken.DOUBLE) {
            parseVarDecl();
        } else {
            parseStmt();
        }
    }

    private void parseVarDecl() {
        String type = curr.lex;
        nextTok(curr.type);

        symbolTable.insert(curr.lex, type);
        nextTok(GrammarToken.Indent);

        while (curr.type == GrammarToken.Comma) {
            nextTok(GrammarToken.Comma);
            symbolTable.insert(curr.lex, type);
            nextTok(GrammarToken.Indent);
        }

        nextTok(GrammarToken.Semi);
    }

    private void parseStmt() {
        if (curr.type == GrammarToken.Indent) {
            parseAssign();
        } else if (curr.type == GrammarToken.IF) {
            parseIf();
        } else if (curr.type == GrammarToken.LB) {
            parseBlock();
        } else {
            error("Invalid statement");
        }
    }

    private void parseAssign() {
        if (symbolTable.find(curr.lex) == null) {
            System.err.println("Warning: Undeclared variable " + curr.lex);
        }
        nextTok(GrammarToken.Indent);
        nextTok(GrammarToken.ASSIGN);
        parseExpr();
        nextTok(GrammarToken.Semi);
    }

    private void parseIf() {
        nextTok(GrammarToken.IF);
        nextTok(GrammarToken.LP);
        parseExpr();
        parseRelOp();
        parseExpr();
        nextTok(GrammarToken.RP);
        parseStmt();

    }

    private void parseBlock() {
        nextTok(GrammarToken.LB);
        while (curr.type != GrammarToken.RB) {
            parseDeclOrStmt();
        }
        nextTok(GrammarToken.RB);
    }

    private void parseRelOp() {
        switch (curr.type) {
            case ET: case NET: case LT: case LTET: case GT: case GTET:
                nextTok(curr.type);
                break;
            default:
                error("Expected relational operator");
        }
    }

    private void parseExpr() {
        parseTerm();
        while (curr.type == GrammarToken.ADD || curr.type == GrammarToken.SUB) {
            nextTok(curr.type);
            parseTerm();
        }
    }

    private void parseTerm() {
        parseFactor();
        while (curr.type == GrammarToken.MULT || curr.type == GrammarToken.DIV) {
            nextTok(curr.type);
            parseFactor();
        }
    }

    private void parseFactor() {
        switch (curr.type) {
            case Num_Int:
                nextTok(GrammarToken.Num_Int);
                break;
            case Num_Double:
                nextTok(GrammarToken.Num_Double);
                break;
            case LP:
                nextTok(GrammarToken.LP);
                parseExpr();
                nextTok(GrammarToken.RP);
                break;
            default:
                error("Invalid expression");
        }
    }

}
