package tests;

import base.BaseTest;
import managers.Context;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/*
* This class is example of solution.
* Please notice that 2 solutions could be completely different but both could be valuable*/

public class ChallengingDomTests extends BaseTest {

   private static final String EXPECTED_VALUE_IN_THIRD_ROW = "Consequuntur2";
   private static final String EXPECTED_VALUE_IN_SECOND_ROW = "Apeirian1";
   private static final String EXPECTED_VALUE_IN_FIRST_ROW = "Adipisci0";
   private static final String DICERET_VALUE = "Phaedrum4";
   private static final String EXPECTED_BUTTON_NAME = "baz";
   private static final String EXPECTED_URL_PART = "edit";

   private String notValidButtonName = "quz";
   private String anotherNotValidButtonName = "bar";

    @BeforeClass
    public static void initialize() {
        BaseTest.context = new Context();
        BaseTest.pages = context.pages;
        context.driverManager.initDriver();
        pages.getChallengingDomPage().get();
    }

    @AfterClass
    public static void ceaseTestCase() {
        context.driverManager.quit();
    }

    @Test
    public void checkValuesFromTableTest() {
        assertEquals(EXPECTED_VALUE_IN_THIRD_ROW, pages.getChallengingDomPage().getValueFromSelectedField(3, 5));
        assertEquals(EXPECTED_VALUE_IN_SECOND_ROW, pages.getChallengingDomPage().getValueFromSelectedField(2, 2));
        assertEquals(EXPECTED_VALUE_IN_FIRST_ROW, pages.getChallengingDomPage().getValueFromSelectedField(1, 3));
    }

    @Test
    public void clickOnEditButtonTest() {
        pages.getChallengingDomPage().clickOnEditButtonNearBySelectedSixthColumnValue(DICERET_VALUE);
        assertTrue(pages.getChallengingDomPage().getCurrentUrl().contains(EXPECTED_URL_PART));
    }

    @Test
    public void buttonNameTest() {
        String buttonName = pages.getChallengingDomPage().getAlertButtonText();
        assertNotEquals(notValidButtonName, buttonName);
        assertNotEquals(anotherNotValidButtonName, buttonName);
        assertEquals(EXPECTED_BUTTON_NAME, buttonName);
    }
}
