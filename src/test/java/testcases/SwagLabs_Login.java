package testcases;

import Pages.LoginPage;
import helpers.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SwagLabs_Login extends Base {

    ArrayList<String> usernames = new ArrayList<String>();
    LoginPage lp;
    public SwagLabs_Login() throws FileNotFoundException {
        super();
    }

    @BeforeTest
    public void openBrowser() throws IOException, InterruptedException {
        prop.load(input);      // loading the config.properties file.
        TimeUnit.SECONDS.sleep(2);
        setup(prop.getProperty("browser"));  // opening the browser.
        TimeUnit.SECONDS.sleep(2);
        gotoUrl(prop.getProperty("url"));    // launching the url.
    }

    @Test
    public void validate_Browser_Title()  {
       // navigateUrl();
        String title_expected = driver.findElement(By.cssSelector(".login_logo")).getText();
        String title_actual = "Swag Labs";
        Assert.assertEquals(title_actual,title_expected);
        System.out.println("Successfully validated");

    }

    @Test(priority = 1)
    public void loginApp() throws InterruptedException {
        lp = new LoginPage(driver);

        usernames.add(prop.getProperty("user_s"));
        usernames.add(prop.getProperty("user_l"));
       // usernames.add(prop.getProperty("user_p"));
        usernames.add(prop.getProperty("user_per"));
        System.out.println(usernames);


        for(String username:usernames) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement user = driver.findElement(By.xpath("//input[contains(@id,'user-name')]"));
            wait.until(ExpectedConditions.visibilityOf(user));
            WebElement pass = driver.findElement(By.xpath("//input[@name='password']"));
            wait.until(ExpectedConditions.visibilityOf(pass));

               if (username.equals(prop.getProperty("user_s"))) {
                   lp.enterCredentials(prop.getProperty("user_s"),prop.getProperty("password"));
                }
                if (username.equals(prop.getProperty("user_l"))) {
                   lp.enterCredentials(prop.getProperty("user_l"),prop.getProperty("password"));
                    // user.sendKeys(prop.getProperty("user_l"));
                }
                if (username.equals(prop.getProperty("user_p"))) {
                   // lp.enterCredentials(prop.getProperty("user_p"),prop.getProperty("password"));
                    //lp.clearPassword();
                   //user.sendKeys(prop.getProperty("user_p"));
                   // pass.clear();

                } else if (username.equals(prop.getProperty("user_per"))) {
                    lp.enterCredentials(prop.getProperty("user_per"),prop.getProperty("password"));
                   // user.sendKeys(prop.getProperty("user_per"));
                }
                //pass.sendKeys(prop.getProperty("password"));
                //user.clear();
                //pass.clear();
               //TimeUnit.SECONDS.sleep(4);
              // WebElement login = driver.findElement(By.xpath("//input[@id='login-button']"));
             //  login.click();
               lp.loginButton();
               if(username.equals(prop.getProperty("user_l"))){
                 //  TimeUnit.SECONDS.sleep(2);
                lp.clearCredentials();
                  // user.clear();
                   //pass.clear();
                  System.out.println("username and password cleared");
               }
               if (username.equals(prop.getProperty("user_s"))||username.equals(prop.getProperty("user_p"))){
                TimeUnit.SECONDS.sleep(2);
                driver.navigate().back();
            }
        }

    }

    @Test(priority = 2,dependsOnMethods = "loginApp")
    public void select_Item() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
       WebElement sel = driver.findElement(By.cssSelector(".inventory_item_name"));
       sel.click();
    }

    @Test(priority = 3,dependsOnMethods = "select_Item")
    public void addToCart() throws InterruptedException {

      TimeUnit.SECONDS.sleep(3);
      WebElement add = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
      add.click();
      /* TimeUnit.SECONDS.sleep(3);
        WebElement add = driver.findElement(By.xpath("//div/following-sibling::button[contains(text(),'Add to cart')][1]"));
        add.click();*/
        System.out.println("add to cart clicked");
    }

    @Test(priority = 4,dependsOnMethods = "select_Item")
    public void view_Cart() throws InterruptedException {
       TimeUnit.SECONDS.sleep(3);
        WebElement view = driver.findElement(By.cssSelector(".shopping_cart_link"));
        view.click();
        System.out.println("view cart clicked");
    }

    @Test(priority = 5,dependsOnMethods = "select_Item")
    public void check_Out() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        WebElement checkout = driver.findElement(By.xpath("//button/following-sibling::button"));
        checkout.click();
    }

    @Test(priority = 6)
    public void details() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("ABC");
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("DEF");
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("7668");
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.xpath("//input[@id='continue']")).click();
    }

    @Test(priority = 7)
    public void complete_purchase() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.xpath("//button[@id='finish']")).click();
    }

    @Test(priority = 8)
    public void back_To_Products() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.xpath("//button[@id='back-to-products']")).click();
    }

    @AfterClass
    public void closeBrowser() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
       closeWeb();
    }
}

