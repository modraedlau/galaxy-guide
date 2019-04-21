package galaxy.guide.repl;

import galaxy.guide.number.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Modraed Lau
 */
public class Token {

    public enum TokenType {
        ALIAS,
        UNIT,
        SYMBOL,
        VALUE,
        KEYWORD,
    }

    private final TokenType tokenType;
    private final String datum;
    private final boolean isMultiple;

    private Object content;
    private List<Object> contents;

    public Token(TokenType tokenType) {
        this(tokenType, null, false);
    }

    public Token(TokenType tokenType, String datum) {
        this(tokenType, datum, false);
    }

    public Token(TokenType tokenType, boolean isMultiple) {
        this(tokenType, null, isMultiple);
    }

    public Token(TokenType tokenType, String datum, boolean isMultiple) {
        Objects.requireNonNull(tokenType, "Input token type is null.");

        if (tokenType == TokenType.KEYWORD && datum == null) {
            throw new IllegalArgumentException("A keyword string is null for a keyword token.");
        }

        this.tokenType = tokenType;
        this.datum = datum;
        this.isMultiple = isMultiple;
        if (isMultiple) {
            contents = new ArrayList<>();
        }
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getDatum() {
        return datum;
    }

    public boolean isMultiple() {
        return isMultiple;
    }

    public Object getContent() {
        return content;
    }

    public List<Object> getContents() {
        return contents;
    }

    boolean matches(String part) {
        switch (tokenType) {
            case KEYWORD: {
                if (part.equalsIgnoreCase(datum)) {
                    content = part;
                    return true;
                }
                return false;
            }
            case SYMBOL: {
                for (Symbol symbol : Symbol.values()) {
                    if (symbol.toString().equalsIgnoreCase(part)) {
                        content = symbol;
                        return true;
                    }
                }
                return false;
            }
            case VALUE: {
                try {
                    content = Integer.parseInt(part);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            case UNIT: {
                content = part;
                return true;
            }
            case ALIAS: {
                if (isMultiple) {
                    contents.add(part);
                    return true;
                }
                content = part;
                return true;
            }
        }
        return false;
    }

    public void reset() {
        content = null;
        if (contents != null) {
            contents.clear();
        }
    }

    @Override
    public String toString() {
        return "Token{" +
            "tokenType=" + tokenType +
            ", datum='" + datum + '\'' +
            ", isMultiple=" + isMultiple +
            ", content=" + content +
            ", contents=" + contents +
            '}';
    }
}
