package galaxy.guide.number;

import java.util.LinkedList;

/**
 * @author Modraed Lau
 */
public final class Numeral {

    private static final int TEN = 10;

    private LinkedList<Symbol> source;
    private LinkedList<Symbol> stack;

    private String letters;
    private int value = -1;

    public Numeral(String letters) {
        if (letters == null || letters.isEmpty()) {
            throw new IllegalArgumentException("The given Roman letter cannot be empty!");
        }
        for (char c : letters.toCharArray()) {
            if (!Symbol.isValidCharacter(c)) {
                throw new IllegalArgumentException("Illegal letter: " + c);
            }
        }

        source = new LinkedList<>();

        this.letters = letters;
        for (char c : letters.toCharArray()) {
            source.add(Symbol.fromCharacter(c));
        }
    }

    public Numeral(int value) {
        if (value < 1) {
            throw new IllegalArgumentException("The given value must be greater than zero!");
        }
        this.value = value;
    }

    public int getValue() {
        if (value < 0) {
            stack = new LinkedList<>();
            return calculate();
        }
        return value;
    }

    private int calculate() {
        int sum = 0;
        Symbol symbol;
        while ((symbol = source.poll()) != null) {
            stack.push(symbol);
            if (stack.size() == 2) {
                if (stack.get(1).compareTo(stack.get(0)) >= 0) {
                    source.addFirst(stack.pop());
                    sum += stack.pop().getValue();
                } else {
                    sum += stack.pop().getValue() - stack.pop().getValue();
                }
            }
        }
        if (!stack.isEmpty()) {
            sum += stack.pop().getValue();
        }
        return sum;
    }

    public String getLetters() {
        if (letters == null) {
            stack = new LinkedList<>();
            int number = value;
            int counter = 0;
            while (number > 0) {
                int digit = number % TEN;
                if (digit != 0) {
                    digit *= Math.pow(TEN, counter);
                    Symbol symbol = Symbol.fromValue(digit);
                    if (symbol != null) {
                        stack.push(symbol);
                    } else {
                        findSymbols(digit);
                    }
                }
                number = number / TEN;
                counter++;
            }
            StringBuilder builder = new StringBuilder();
            while (!stack.isEmpty()) {
                builder.append(stack.pop());
            }
            letters = builder.toString();
        }
        return letters;
    }

    private void findSymbols(int digit) {
        for (Symbol current : Symbol.values()) {
            if (current.getValue() > digit) {
                for (Symbol smaller : Symbol.values()) {
                    if (smaller == current) {
                        break;
                    }
                    if (smaller == Symbol.V || smaller == Symbol.L || smaller == Symbol.D) {
                        continue;
                    }
                    if (smaller == Symbol.I) {
                        if (current != Symbol.V && current != Symbol.X) {
                            continue;
                        }
                    }
                    if (smaller == Symbol.X) {
                        if (current != Symbol.L && current != Symbol.C) {
                            continue;
                        }
                    }
                    if (smaller == Symbol.C) {
                        if (current != Symbol.D && current != Symbol.M) {
                            continue;
                        }
                    }
                    if (current.getValue() - smaller.getValue() == digit) {
                        stack.push(current);
                        stack.push(smaller);
                        break;
                    }
                }
                break;
            } else {
                if (current == Symbol.I || current == Symbol.X || current == Symbol.C || current == Symbol.M) {
                    if (current.getValue() * 2 == digit) {
                        stack.push(current);
                        stack.push(current);
                    } else if (current.getValue() * 3 == digit) {
                        stack.push(current);
                        stack.push(current);
                        stack.push(current);
                    }
                } else {
                    for (Symbol smaller : Symbol.values()) {
                        if (smaller == current) {
                            break;
                        }
                        if (current.getValue() + smaller.getValue() == digit) {
                            stack.push(smaller);
                            stack.push(current);
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Numeral{" +
            "letters='" + letters + '\'' +
            ", value=" + value +
            '}';
    }
}
