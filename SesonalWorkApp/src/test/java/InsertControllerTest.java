import Controller.InsertController;
import org.junit.Assert;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.params.*;

public class InsertControllerTest {

    @ParameterizedTest
    @ValueSource (strings = "Alessio")
    public void checkNameTest(String name) {
        Assert.assertTrue(InsertController.checkName(name, "^[a-zA-Z]+"));
    }
}
