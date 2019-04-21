package galaxy.guide.repl;

import java.util.List;

/**
 * @author Modraed Lau
 */
@FunctionalInterface
public interface Action {

    boolean perform(List<Token> tokens);
}