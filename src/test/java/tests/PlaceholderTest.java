package tests;


import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceholderTest extends BaseTest {

    @Test
    public void placeholderTest() {
        pages.getHomePage().get();
        Assert.assertTrue(true);
    }
}