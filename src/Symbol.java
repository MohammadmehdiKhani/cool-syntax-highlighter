public class Symbol {
    SymbolType symbolType;
    String content;
    int line;
    int column;

    public Symbol(SymbolType symbolType, String content, int line, int column) {
        this.symbolType = symbolType;
        this.content = content;
        this.line = line;
        this.column = column;
    }
}
