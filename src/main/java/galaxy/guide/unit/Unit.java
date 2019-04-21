package galaxy.guide.unit;

/**
 * @author Modraed Lau
 */
public class Unit {

    private String name;

    private int credits;

    public Unit(String name, int credits) {
        this.name = name;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Unit{" +
            "name='" + name + '\'' +
            ", credits=" + credits +
            '}';
    }
}
