package tests;

import base.TestBase;
import org.testng.annotations.Test;

public class TestExample extends TestBase {

    @Test
    public void testGoogle() {


        System.out.println(getDriver().getTitle()
        );
    }
}
