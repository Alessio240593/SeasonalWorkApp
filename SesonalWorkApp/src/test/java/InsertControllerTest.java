import Controller.InsertController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class InsertControllerTest {
    public InsertController testModel;

    @Before
    public void setUp() {
        testModel = new InsertController();
    }

    public void start(String methodName) {
        System.out.println("Start " + methodName +  "...");
    }

    @Test
    @DisplayName("Check surname with literal")
    public void checkNameLiteralTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkName("Ziopino"), true);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check surname with number")
    public void checkNameNumberTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkName("123"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check surname with other expression")
    public void checkNameOtherExpTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkName("??%%&&$£"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check surname with space")
    public void checkNameSpaceTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkName(" "), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check surname with nothing")
    public void checkNameNothingTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkName(""), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }



    @Test
    @DisplayName("Check surname with literal")
    public void checkSurnameLiteralTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkSurname("Ziopino", "^[a-zA-Z]+"), true);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check surname with number")
    public void checkSurnameNumberTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkSurname("123", "^[a-zA-Z]+"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check surname with other expression")
    public void checkSurnameOtherExpTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkSurname("??%%&&$£", "^[a-zA-Z]+"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check surname with space")
    public void checkSurnameSpaceTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkSurname(" ", "^[a-zA-Z]+"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check surname with nothing")
    public void checkSurnameNothingTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkSurname("", "^[a-zA-Z]+"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check cell with number")
    public void checkCellNumberTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkCell("3489189321", "^[0-9]{10}$"), true);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check cell with literal")
    public void checkCellLiteralTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkCell("questo è un test", "^[0-9]{10}$"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check cell with other expression")
    public void checkCellOtherExpTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkCell("??%%&&$£", "^[a-zA-Z]+"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check cell with space")
    public void checkCellSpaceTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkCell(" ", "^[a-zA-Z]+"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check cell with nothing")
    public void checkCellNothingTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkCell("", "^[a-zA-Z]+"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check cell length")
    public void checkCellLengthTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkCell("345", "^[a-zA-Z]+"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check email with number")
    public void checkEmailRegularTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkEmail("prova.test@gmail.com", "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$"), true);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check email with literal")
    public void checkEmailLiteralTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkEmail("questo è un test", "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check email with number")
    public void checkEmailNumberTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkEmail("12345678", "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check email with other expression")
    public void checkEmailOtherExpTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkEmail("??%%&&$£", "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check email with space")
    public void checkEmailSpaceTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkEmail(" ", "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check email with nothing")
    public void checkEmailNothingTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkEmail("", "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    public void checkRes(String methodName) {
        System.out.println("Finish " + methodName + " → \033[32mPASSED \033[0m\n");
    }
}
