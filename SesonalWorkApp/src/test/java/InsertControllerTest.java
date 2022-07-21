import Controller.InsertController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.params.*;

public class InsertControllerTest {

    @Test
    public void checkNameTest() {
        Assert.assertTrue(InsertController.checkName("Alessio", "^[a-zA-Z]+"));
    }

    @Test
    public void checkSurnameLiteralTest() {
        Assert.assertTrue(InsertController.checkSurname("Ziopino", "^[a-zA-Z]+"));
    }

    @Test
    public void checkSurnameNumberTest() {
        Assert.assertFalse(InsertController.checkSurname("123", "^[a-zA-Z]+"));
    }

    @Test
    public void checkSurnameOtherExpTest() {
        Assert.assertFalse(InsertController.checkSurname("??%%&&$£", "^[a-zA-Z]+"));
    }
}
