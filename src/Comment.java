public class Comment
{
    public String translateSpecialChars(String comment)
    {
        String translatedString = "";
        for (int i = 0; i < comment.length(); i++)
        {
            char ch = comment.charAt(i);
            switch (ch)
            {
                case ' ':
                    translatedString += "&nbsp;";
                    break;

                case '\n':
                    translatedString += "<br>";
                    break;

                case '\t':
                    translatedString += "&nbsp;&nbsp;&nbsp;";
                    break;

                default:
                    translatedString += ch;
            }
        }
        return translatedString;
    }
}