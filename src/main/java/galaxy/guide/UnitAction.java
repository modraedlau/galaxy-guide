package galaxy.guide;

import galaxy.guide.number.Numeral;
import galaxy.guide.repl.Action;
import galaxy.guide.repl.Token;
import galaxy.guide.unit.Unit;

import java.util.List;

/**
 * @author Modraed Lau
 */
public class UnitAction implements Action {
    private Context context;

    UnitAction(Context context) {
        this.context = context;
    }

    @Override
    public boolean perform(List<Token> tokens) {
        Numeral numeral = context.getNumeral(tokens.get(0).getContents());
        if (numeral == null) {
            return false;
        }
        String name = tokens.get(1).getContent().toString();
        int value = (Integer) tokens.get(3).getContent();
        Unit unit = new Unit(name, value / numeral.getValue());
        context.addUnit(name, unit);
        return true;
    }
}
