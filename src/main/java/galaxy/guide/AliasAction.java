package galaxy.guide;

/**
 * @author Modraed Lau
 */

import galaxy.guide.number.Symbol;
import galaxy.guide.repl.Action;
import galaxy.guide.repl.Token;

import java.util.List;

public final class AliasAction implements Action {
    private Context context;

    AliasAction(Context context) {
        this.context = context;
    }

    @Override
    public boolean perform(List<Token> tokens) {
        String alias = tokens.get(0).getContent().toString();
        String letter = tokens.get(2).getContent().toString();
        Symbol symbol = Symbol.valueOf(letter.toUpperCase());
        context.addAlias(alias, symbol);
        return true;
    }
}
