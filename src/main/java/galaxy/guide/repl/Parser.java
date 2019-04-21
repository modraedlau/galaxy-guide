package galaxy.guide.repl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Modraed Lau
 */
public class Parser {
    public static final String DEFAULT_PROMPT = "I have no idea what you are talking about";

    private final List<Descriptor> descriptors = new ArrayList<>();

    public void add(Descriptor descriptor) {
        Objects.requireNonNull(descriptor, "The input command descriptor is null.");

        if (descriptor.size() == 0) {
            return;
        }

        descriptors.add(descriptor);
    }

    public void process(String command) {
        for (Descriptor descriptor : descriptors) {
            if (descriptor.parse(command)) {
                return;
            }
        }
        System.out.println(DEFAULT_PROMPT);
    }
}
