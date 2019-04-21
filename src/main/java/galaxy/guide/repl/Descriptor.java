package galaxy.guide.repl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Modraed Lau
 */
public class Descriptor {
    private final List<Token> tokens = new ArrayList<>();
    private final Action action;

    public Descriptor(Action action) {
        this.action = action;
    }

    public void addToken(Token token) {
        Objects.requireNonNull(token, "The input token is null.");
        tokens.add(token);
    }

    public int size() {
        return tokens.size();
    }

    public Token getToken(int index) {
        return tokens.get(index);
    }

    public boolean parse(String command) {
        String[] parts = command.trim().split("\\s+");

        if (parts.length < tokens.size()) {
            return false;
        }

        int multipleLength = parts.length - tokens.size() + 1;
        int index = 0;
        for (Token token : tokens) {
            if (token.isMultiple()) {
                int length = index + multipleLength;
                for (int i = index; i < length; i++) {
                    if (!token.matches(parts[index])) {
                        return false;
                    }
                    index++;
                }
            } else {
                if (!token.matches(parts[index])) {
                    return false;
                }
                index++;
            }
        }

        if (action != null) {
            action.perform(tokens);

            // reset
            tokens.forEach(Token::reset);
        }

        return true;
    }
}
