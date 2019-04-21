package galaxy.guide.number;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Modraed Lau
 */
public enum Symbol {

    I(1),
    V(5),
    X(10),
    L(50),
    C(100),
    D(500),
    M(1000);

    private int value;

    private static final Map<Character, Symbol> CHARACTER_SYMBOL_MAP = new HashMap<>(8);
    private static final Map<Integer, Symbol> VALUE_SYMBOL_MAP = new HashMap<>(8);

    static {
        for (Symbol symbol : values()) {
            CHARACTER_SYMBOL_MAP.put(symbol.getCharacter(), symbol);
            VALUE_SYMBOL_MAP.put(symbol.getValue(), symbol);
        }
    }

    Symbol(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Character getCharacter() {
        return toString().toCharArray()[0];
    }

    public static Symbol fromCharacter(Character character) {
        return CHARACTER_SYMBOL_MAP.get(character);
    }

    public static Symbol fromValue(Integer value) {
        return VALUE_SYMBOL_MAP.get(value);
    }

    public static boolean isValidCharacter(Character character) {
        return CHARACTER_SYMBOL_MAP.containsKey(character);
    }
}
