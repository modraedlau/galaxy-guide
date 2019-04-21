package galaxy.guide;

import galaxy.guide.number.Numeral;
import galaxy.guide.repl.Action;
import galaxy.guide.repl.Token;
import galaxy.guide.utils.StringUtils;

import java.util.List;

/**
 * @author Modraed Lau
 */
public class ValueAction implements Action {
    private Context context;

    ValueAction(Context context) {
        this.context = context;
    }

    @Override
    public boolean perform(List<Token> tokens) {
        List<Object> aliases = tokens.get(3).getContents();
        Numeral numeral = context.getNumeral(aliases);
        if (numeral == null) {
            return false;
        }
        StringBuilder builder = new StringBuilder();
        for (Object alias : aliases) {
            builder.append(alias.toString());
            builder.append(" ");
        }
        builder.append("is ");
        builder.append(numeral.getValue());
        System.out.println(builder.toString());
        return true;
    }
}
