package galaxy.guide;

import galaxy.guide.number.Numeral;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * @author Modraed Lau
 */
public class NumeralTest {

    @Test
    public void testGetValue() {
        Pattern pattern = Pattern.compile("^[a-z_].*");
        Matcher matcher = pattern.matcher("abc_1");
        System.out.println(matcher.matches());

        Numeral numeral = new Numeral("MMVI");
        assertEquals(2006, numeral.getValue());

        numeral = new Numeral("MCMXLIV");
        assertEquals(1944, numeral.getValue());

        numeral = new Numeral("MCMIII");
        assertEquals(1903, numeral.getValue());
    }

    @Test
    public void testGetLetters() {
        Numeral numeral = new Numeral(58);
        assertEquals("LVIII", numeral.getLetters());

        numeral = new Numeral(60);
        assertEquals("LX", numeral.getLetters());

        numeral = new Numeral(2006);
        assertEquals("MMVI", numeral.getLetters());

        numeral = new Numeral(1944);
        assertEquals("MCMXLIV", numeral.getLetters());

        numeral = new Numeral(1903);
        assertEquals("MCMIII", numeral.getLetters());
    }
}
