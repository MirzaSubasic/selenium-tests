package MercuryTours;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import static org.openqa.selenium.By.*;

public class demoTest {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\smirza\\Downloads\\chromedriver.exe");

        //new chrome driver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/test/newtours/");

        //verify title
        String expectedTitle = "Welcome: Mercury Tours";
        String actualTitle = driver.getTitle();
        if (actualTitle.contentEquals(expectedTitle))
            System.out.println("Test passed. Title is correct");
        else
            System.out.println("Test failed. Title is incorrect");

        //accept terms and conditions
        Thread.sleep(2000);
        driver.switchTo().frame("gdpr-consent-notice");
        driver.findElement(By.id("save")).click();
        driver.switchTo().defaultContent();
        System.out.println("Closed terms and conditions");

        Thread.sleep(2000);
        //sign in with empty username and password
        driver.findElement(name("submit")).click();
        String successfulRegisterURL = "https://demo.guru99.com/test/newtours/login_sucess.php";
        if(!successfulRegisterURL.contentEquals(driver.getCurrentUrl()))
            System.out.println("Test passed. Didnt log in");
        else
            System.out.println("Test failed. User managed to log in");

        Thread.sleep(2000);
        try {
            driver.findElement(linkText("SIGN-OFF")).click();
            String expected = "https://demo.guru99.com/test/newtours/index.php";
            //when signed off user should return to home page
            if(expected.contentEquals(driver.getCurrentUrl()))
                throw new NoSuchElementException("");
            System.out.println("Test passed. Sign off worked correctly");
        }
        catch (NoSuchElementException e) {
            System.out.println("There is no sign off button");
        }

        driver.close();
    }
}
