package testcases;

import helpers.Base;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;


public class TCase1 extends Base {
    public TCase1() throws FileNotFoundException {
    }

    @BeforeClass
    private void startBrowser(){
  /*setDriver_private();
    System.out.println("Opening in private mode in firefox");*/
   /* setDriver_headless_firefox();
    System.out.println("Opening in headless mode in firefox");*/
   /* setDriver_incognito();
    System.out.println("Opening in incognito mode in Chrome");*/
   /* setDriver_headless_chrome();
    System.out.println("Opening in headless mode in Chrome");*/
       setup("Chrome");
    }

    
    @Test
    private void checkUrl(){

    gotoUrl("https://www.saucedemo.com/");

    }
    @Test
    private void validateTitle()  {
        checkUrl();
        String title_expected = "Swag Labs";
        String text_actual = driver.findElement(By.cssSelector(".login_logo")).getText();
        Assert.assertEquals(text_actual,title_expected);
        System.out.println("Assertion success:"+text_actual+" "+"equals"+" "+title_expected);
    }

    @AfterClass
    private void closeBrowser(){

      // closeWeb();
    }


}
