package MercuryTours;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.openqa.selenium.By.*;

public class Test {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.gecko.driver","C:\\Users\\smirza\\Downloads\\geckodriver.exe");
        FirefoxDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/test/newtours/");

        //accept terms and conditions
        Thread.sleep(2000);
        driver.switchTo().frame("gdpr-consent-notice");
        driver.findElement(id("save")).click();
        driver.switchTo().defaultContent();
        System.out.println("Closed terms and conditions");

        //login with valid credentials
        Thread.sleep(2000);
        WebElement username = driver.findElement(name("userName"));
        WebElement password = driver.findElement(name("password"));
        WebElement loginButton = driver.findElement(name("submit"));
        username.sendKeys("mirza.suba@gmail.com");
        password.sendKeys("myPassword1");
        Thread.sleep(1000);
        loginButton.click();
        String successfulLoginURL = "https://demo.guru99.com/test/newtours/login_sucess.php";
        if(successfulLoginURL.contentEquals(driver.getCurrentUrl()))
            System.out.println("Test passed. Login successful");
        else
            System.out.println("Test failed. Login failed");

        //check tour tips on home page
        Thread.sleep(2000);
        driver.findElement(linkText("Home")).click();
        String homePageURL = "https://demo.guru99.com/test/newtours/index.php";
        if(!homePageURL.contentEquals(driver.getCurrentUrl()))
            driver.navigate().to(homePageURL);
        //search alt tag of tour tips picture
        if(driver.findElement(xpath("//img[contains(@alt, 'Tour Tips')]")).isDisplayed())
            System.out.println("Test passed. Tour Tips are displayed");
        else
            System.out.println("Test failed. Tour Tips are not displayed");

        //navigate to destination
        Thread.sleep(2000);
        driver.findElement(linkText("Destinations")).click();
        String destinationURL ="https://demo.guru99.com/test/newtours/support.php";
        if(driver.getCurrentUrl().contentEquals(destinationURL))
            System.out.println("Test passed. Navigated to destinations");
        else
            System.out.println("Test failed. Didnt find destination url");

        //print url
        System.out.println("Current url: " + driver.getCurrentUrl());

        //navigate to home page
        Thread.sleep(2000);
        driver.findElement(linkText("Home")).click();
        if(driver.getCurrentUrl().contentEquals(homePageURL))
            System.out.println("Test passed. Navigated to home");
        else
            System.out.println("Test failed. Didnt find home page");

        //sign out
        Thread.sleep(2000);
        try {
            driver.findElement(linkText("SIGN-OFF")).click();
            System.out.println("Test passed. Signed out");
        }
        catch (NoSuchElementException e) {
            //Already logged out
            System.out.println("Test failed. Could not log out");
        }

        driver.close();
    }
}
