package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class Base {
    public WebDriver driver;
    public InputStream input ;  // InputStream obj created to read the config.properties file.
    public Properties prop = new Properties(); //prop object created to fetch properties of the config.properties file.
    public Base()  {
        try{
            this.input= new FileInputStream("src/main/resources/config.properties");
        }
        catch (FileNotFoundException e){
            System.out.println("--->"+e);
        }

    }
    public void setup(String browser) {

        if (browser.contains("Chrome")) {
            System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
            driver = new ChromeDriver();
        } else if(browser.contains("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.contains("Edge")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public void setDriver_headless_firefox() {
        FirefoxOptions options = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        options.addArguments("--headless");
        driver=new FirefoxDriver(options);
    }
    public void setDriver_private() {
        FirefoxOptions options = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        options.addArguments("-private");
        driver=new FirefoxDriver(options);
    }
    public void setDriver_incognito() {
        System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
    }
    public void setDriver_headless_chrome() {
        System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }
    public void gotoUrl(String url){

        driver.get(url);

       /* if(driver.getTitle().contains("Swag Labs"))
        {
            System.out.println("Successfully navigated to:"+url);
        }
        else
            System.out.println("Navigation failed to:"+url);*/
    }
    public void closeWeb(){
        driver.close();
    }
}
