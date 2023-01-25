import java.io.FileReader;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        FileReader fileReader = new FileReader("src/in/input.cool");
        Scanner scanner = new Scanner(fileReader);
        SyntaxHighlighter syntaxHighlighter = new SyntaxHighlighter();
        Symbol current = scanner.nextToken();

        while (!scanner.yyatEOF())
        {
            syntaxHighlighter.addTag(current);
            current = scanner.nextToken();
        }

        syntaxHighlighter.endingTagOfHtml();
        scanner.yyclose();
    }
}
