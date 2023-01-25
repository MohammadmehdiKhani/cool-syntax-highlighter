import java.io.*;
%%

%class Scanner
%public
%unicode
%function nextToken
%type Symbol
%line
%column
%implements Lexical

//number
DecimalDigits = [0-9]
Decimal = 0|[1-9]{DecimalDigits}*
HexDigit = [{DecimalDigits}a-fA-F]
Hex = [0][xX]{HexDigit}+
Real = {DecimalDigits}+\.{DecimalDigits}*
Scientific = {Decimal}|{Real}"E"(\+|\-)?{Decimal}

//char and word
Letter = [a-zA-Z]
SpecialChar = \\[trn\'\"\\]
Identifier = {Letter} ({Letter}|{DecimalDigits}|"_")*
Character = [^\r\n]
Space = [ ]
Tab = \t
Enter = \r|\n|\r\n
InputCharacter = [^\r\n]
Reserved = "let"|"void"|"int"|"real"|"bool"|"string"|"static"|"class"|"for"|"len"|"loop"|"print"|"while"|"break"|"continue"|"if"|"range"|"else"|"new"|"func"|"return"|"inputStr"|"inputInt"|"in"

//comment
OneLineComment = "//" {Character}*
MultipleLinesComment = "/*" ~ "*/"
Comment = {MultipleLinesComment} | {OneLineComment}

//operator and punc
Operator = "+"|"-"|"*"|"/"|"+="|"-="|"*="|"/="|"++"|"--"|"<"|"<="|">"|">="|"!="|"=="|"="|"%"|"&&"|"||"|"&"|"|"|"^"|"!"|"."
Punc = ","|";"|"["|"]"|"("|")"|"{"|"}"

%state STRING

%%

<YYINITIAL> {
    \"                  {yybegin(STRING); return new Symbol(SymbolType.STRING, yytext(), yyline, yycolumn);}
    {Reserved}          {return new Symbol(SymbolType.RESERVED, yytext(), yyline, yycolumn);}
    {Identifier}        {return new Symbol(SymbolType.IDENTIFIER, yytext(), yyline, yycolumn);}
    {Decimal}           {return new Symbol(SymbolType.INTEGER, yytext(), yyline, yycolumn);}
    {Hex}               {return new Symbol(SymbolType.INTEGER, yytext(), yyline, yycolumn);}
    {Real}              {return new Symbol(SymbolType.REAL, yytext(), yyline, yycolumn);}
    {Scientific}        {return new Symbol(SymbolType.REAL, yytext(), yyline, yycolumn);}
    {Comment}           {return new Symbol(SymbolType.COMMENT, yytext(), yyline, yycolumn);}
    {Operator}          {return new Symbol(SymbolType.OPERATOR, yytext(), yyline, yycolumn);}
    {Space}             {return new Symbol(SymbolType.SPACE, yytext(), yyline, yycolumn);}
    {Tab}               {return new Symbol(SymbolType.TAB, yytext(), yyline, yycolumn);}
    {Enter}             {return new Symbol(SymbolType.ENTER, "\n", yyline, yycolumn);}
    {Punc}              {return new Symbol(SymbolType.PUNC, yytext(), yyline, yycolumn);}
    [^]                 {return new Symbol(SymbolType.UNDEFINED, yytext(), yyline, yycolumn);}
}

<STRING> {
    \"                  {yybegin(YYINITIAL); return new Symbol(SymbolType.STRING, yytext(), yyline, yycolumn);}
    {SpecialChar}       {return new Symbol(SymbolType.SPECIAL_CHAR, yytext(), yyline, yycolumn);}
    [^]                 {return new Symbol(SymbolType.STRING, yytext(), yyline, yycolumn);}
}