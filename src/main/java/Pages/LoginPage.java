package Pages;

import helpers.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LoginPage extends Base {
    WebDriver driver;
    @FindBy(id="user-name")
    WebElement username;

    @FindBy(name="password")
    WebElement password;

    @FindBy(id="login-button")
    WebElement login;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    public void enterCredentials(String user,String pass) throws InterruptedException {

        TimeUnit.SECONDS.sleep(2);
        username.sendKeys(user);
        TimeUnit.SECONDS.sleep(2);
        password.sendKeys(pass);
    }
    public void loginButton() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        login.click();
    }
    public void clearCredentials() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        username.clear();
        TimeUnit.SECONDS.sleep(2);
        password.clear();
    }
    public void clearPassword() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        password.clear();
    }

}
