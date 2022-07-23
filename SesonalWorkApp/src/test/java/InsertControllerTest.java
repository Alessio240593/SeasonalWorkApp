import Controller.InsertController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;

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
    @DisplayName("Check name with literal")
    public void checkNameLiteralTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkName("Ziopino"), true);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check name with number")
    public void checkNameNumberTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkName("123"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check name with other expression")
    public void checkNameOtherExpTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkName("??%%&&$£"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check name with space")
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
        Assert.assertEquals(testModel.checkSurname("Ziopino"), true);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check surname with number")
    public void checkSurnameNumberTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkSurname("123"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check surname with other expression")
    public void checkSurnameOtherExpTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkSurname("??%%&&$£"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check surname with space")
    public void checkSurnameSpaceTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkSurname(" "), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check surname with nothing")
    public void checkSurnameNothingTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkSurname(""), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check cell with number")
    public void checkCellNumberTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkCell("3489189321"), true);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check cell with literal")
    public void checkCellLiteralTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkCell("questo è un test"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check cell with other expression")
    public void checkCellOtherExpTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkCell("??%%&&$£"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check cell with space")
    public void checkCellSpaceTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkCell(" "), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check cell with nothing")
    public void checkCellNothingTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkCell(""), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check cell length")
    public void checkCellLengthTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkCell("345"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check email with number")
    public void checkEmailRegularTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkEmail("prova.test@gmail.com"), true);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check email with literal")
    public void checkEmailLiteralTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkEmail("questo è un test"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check email with number")
    public void checkEmailNumberTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkEmail("12345678"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check email with other expression")
    public void checkEmailOtherExpTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkEmail("??%%&&$£"), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check email with space")
    public void checkEmailSpaceTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkEmail(" "), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check email with nothing")
    public void checkEmailNothingTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkEmail(""), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check legal date")
    public void checkDateLegalTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkDate( LocalDate.of(1993, 10, 27)), true);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check legal date")
    public void checkDateIllegalMinTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkDate( LocalDate.of(1899, 10, 27)), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check legal date")
    public void checkDateIllegalMaxTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkDate( LocalDate.of(2023, 10, 27)), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    @Test
    @DisplayName("Check legal date")
    public void checkDateIllegalAgeTest() {
        start(new Throwable().getStackTrace()[0].getMethodName());
        Assert.assertEquals(testModel.checkDate( LocalDate.of(2006, 10, 27)), false);
        checkRes( new Throwable().getStackTrace()[0].getMethodName());
    }

    public void checkRes(String methodName) {
        System.out.println("Finish " + methodName + " → \033[32mPASSED \033[0m\n");
    }
}
