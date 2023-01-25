import java.io.IOException;
import java.io.*;

public class SyntaxHighlighter
{
    FileWriter fileWriter = new FileWriter("src/out/out.html");
    Comment comment = new Comment();

    public SyntaxHighlighter() throws IOException
    {
        fileWriter.write("<!DOCTYPE html>\n<html>\n<head>\n<title>output</title>\n</head>\n<body style=\"background-color:#222; font-family:Verdana;\">\n<div style=\" margin-left:6%; margin-top:2%; padding:3%;\">");
    }

    public void addTag(Symbol current) throws IOException
    {
        if (current.column == 0)
            fileWriter.write("<span style=\"color:#A9A9A9; padding-right:2%\">" + current.line + "</span>");

        switch (current.symbolType)
        {
            case RESERVED:
                fileWriter.write("<span style=\"color:#B667F1\">" + current.content + "</span>");
                break;

            case IDENTIFIER:
                fileWriter.write("<span style=\"color:#FFFFFF\">" + current.content + "</span>");
                break;

            case INTEGER:
                fileWriter.write("<span style=\"color:#FFC300\n\">" + current.content + "</span>");
                break;

            case REAL:
                fileWriter.write("<span style=\"color:#FFC300\"><i>" + current.content + "</i></span>");
                break;

            case STRING:
                fileWriter.write("<span style=\"color:#00C897\">" + current.content + "</span>");
                break;

            case SPECIAL_CHAR:
                fileWriter.write("<span style=\"color:#B8FFF9\"><i>" + current.content + "</i></span>");
                break;

            case COMMENT:
                fileWriter.write("<span style=\"color:#69676C\">" + comment.translateSpecialChars(current.content) + "</span>");
                break;

            case OPERATOR:
            case PUNC:
                fileWriter.write("<span style=\"color:#90E0EF\">" + current.content + "</span>");
                break;

            case UNDEFINED:
                fileWriter.write("<span style=\"color:#FF1818\">" + current.content + "</span>");
                break;

            case SPACE:
                fileWriter.write("&nbsp;");
                break;

            case TAB:
                fileWriter.write("&nbsp;&nbsp;&nbsp;");
                break;

            case ENTER:
                fileWriter.write("<br>\n\n");
                break;
        }
    }

    public void endingTagOfHtml() throws IOException
    {
        fileWriter.write("</div>\n</body>\n</html>\n");
        fileWriter.flush();
        fileWriter.close();
    }
}

