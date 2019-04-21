package galaxy.guide;

import galaxy.guide.repl.Descriptor;
import galaxy.guide.repl.Parser;
import galaxy.guide.repl.Token;

import java.util.Scanner;

import static galaxy.guide.repl.Token.TokenType;

/**
 * @author Modraed Lau
 */
public class Application {

    private static Parser buildParser(Context context) {
        Parser parser = new Parser();

        AliasAction aliasAction = new AliasAction(context);
        Descriptor aliasDescriptor = new Descriptor(aliasAction);
        aliasDescriptor.addToken(new Token(TokenType.ALIAS));
        aliasDescriptor.addToken(new Token(TokenType.KEYWORD, "is"));
        aliasDescriptor.addToken(new Token(TokenType.SYMBOL));

        UnitAction unitAction = new UnitAction(context);
        Descriptor unitDescriptor = new Descriptor(unitAction);
        unitDescriptor.addToken(new Token(TokenType.ALIAS, true));
        unitDescriptor.addToken(new Token(TokenType.UNIT));
        unitDescriptor.addToken(new Token(TokenType.KEYWORD, "is"));
        unitDescriptor.addToken(new Token(TokenType.VALUE));
        unitDescriptor.addToken(new Token(TokenType.KEYWORD, "Credits"));

        ValueAction valueAction = new ValueAction(context);
        Descriptor valueDescriptor = new Descriptor(valueAction);
        valueDescriptor.addToken(new Token(TokenType.KEYWORD, "how"));
        valueDescriptor.addToken(new Token(TokenType.KEYWORD, "much"));
        valueDescriptor.addToken(new Token(TokenType.KEYWORD, "is"));
        valueDescriptor.addToken(new Token(TokenType.ALIAS, true));
        valueDescriptor.addToken(new Token(TokenType.KEYWORD, "?"));

        CreditsAction creditsAction = new CreditsAction(context);
        Descriptor creditsDescriptor = new Descriptor(creditsAction);
        creditsDescriptor.addToken(new Token(TokenType.KEYWORD, "how"));
        creditsDescriptor.addToken(new Token(TokenType.KEYWORD, "many"));
        creditsDescriptor.addToken(new Token(TokenType.KEYWORD, "Credits"));
        creditsDescriptor.addToken(new Token(TokenType.KEYWORD, "is"));
        creditsDescriptor.addToken(new Token(TokenType.ALIAS, true));
        creditsDescriptor.addToken(new Token(TokenType.UNIT));
        creditsDescriptor.addToken(new Token(TokenType.KEYWORD, "?"));

        parser.add(aliasDescriptor);
        parser.add(unitDescriptor);
        parser.add(valueDescriptor);
        parser.add(creditsDescriptor);
        return parser;
    }

    public static void main(String[] args) {
        Context context = new Context();
        Parser parser = buildParser(context);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine().trim();
            if ("quit".equals(command)) {
                System.out.println("Bye!");
                break;
            }
            parser.process(command);
        }
    }
}
