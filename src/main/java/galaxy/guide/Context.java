package galaxy.guide;

import galaxy.guide.number.Numeral;
import galaxy.guide.number.Symbol;
import galaxy.guide.unit.Unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Modraed Lau
 */
public class Context {

    Map<String, Symbol> aliasSymbolMap = new HashMap<>(8);
    Map<String, Unit> nameUnitMap = new HashMap<>(4);

    public void addAlias(String alias, Symbol symbol) {
        aliasSymbolMap.put(alias, symbol);
    }

    public void addUnit(String name, Unit unit) {
        nameUnitMap.put(name, unit);
    }

    public Symbol getAlias(String alias) {
        return aliasSymbolMap.get(alias);
    }

    public Unit getUnit(String name) {
        return nameUnitMap.get(name);
    }

    public Numeral getNumeral(List<Object> aliases) {
        StringBuilder builder = new StringBuilder();
        for (Object alias : aliases) {
            Symbol symbol = this.getAlias(alias.toString());
            if (symbol == null) {
                return null;
            }
            builder.append(symbol.toString());
        }
        try {
            return new Numeral(builder.toString());
        } catch (Exception e) {
            return null;
        }
    }
}
