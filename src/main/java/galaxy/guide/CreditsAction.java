package galaxy.guide;

import galaxy.guide.number.Numeral;
import galaxy.guide.repl.Action;
import galaxy.guide.repl.Token;
import galaxy.guide.unit.Unit;
import galaxy.guide.utils.StringUtils;

import java.util.List;

/**
 * @author Modraed Lau
 */
public class CreditsAction implements Action {
    private Context context;

    CreditsAction(Context context) {
        this.context = context;
    }

    @Override
    public boolean perform(List<Token> tokens) {
        List<Object> aliases = tokens.get(4).getContents();
        Numeral numeral = context.getNumeral(aliases);
        if (numeral == null) {
            return false;
        }
        String name = tokens.get(5).getContent().toString();
        Unit unit = context.getUnit(name);
        if (unit == null) {
            return false;
        }
        StringBuilder builder = new StringBuilder();
        for (Object alias : aliases) {
            builder.append(alias.toString());
            builder.append(" ");
        }
        builder.append(name);
        builder.append(" is ");
        builder.append(numeral.getValue() * unit.getCredits());
        builder.append(" Credits");
        System.out.println(builder.toString());
        return true;
    }
}
