package tests.gui;


import base.BaseGui;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceholderTest extends BaseGui {

    @Test
    public void placeholderTest() {
        pages.getHomePage().get();
        Assert.assertTrue(true);
    }
}