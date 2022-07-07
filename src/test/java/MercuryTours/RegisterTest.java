package MercuryTours;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import static org.openqa.selenium.By.*;

public class RegisterTest {
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

        Thread.sleep(2000);
        //go to register
        driver.findElement(linkText("REGISTER")).click();

        Thread.sleep(2000);
        //username and email should have inverted IDs
        WebElement firstName = driver.findElement(name("firstName"));
        WebElement lastName = driver.findElement(name("lastName"));
        WebElement phone = driver.findElement(name("phone"));
        WebElement mail = driver.findElement(id("userName"));
        WebElement address = driver.findElement(name("address1"));
        WebElement city = driver.findElement(name("city"));
        WebElement province = driver.findElement(name("state"));
        WebElement postalCode = driver.findElement(name("postalCode"));
        WebElement country = driver.findElement(name("country"));
        WebElement userName = driver.findElement(id("email"));
        WebElement password = driver.findElement(name("password"));
        WebElement confirmPassword = driver.findElement(name("confirmPassword"));
        WebElement submit = driver.findElement(name("submit"));


        firstName.sendKeys("Mirza");
        lastName.sendKeys("Subasic");
        phone.sendKeys("061123123");
        mail.sendKeys("test@gmail.com");

        address.sendKeys("myAddress");
        city.sendKeys("Sarajevo");
        province.sendKeys("Sarajevo");
        postalCode.sendKeys("50000");
        Select select= new Select(country);
        select.selectByValue("BOSNIA AND HERZEGOVINA");

        userName.sendKeys("UserName123");
        password.sendKeys("password");
        confirmPassword.sendKeys("password");
        Thread.sleep(500);
        submit.click();

        Thread.sleep(2500);
        //check if register is successful
        String successfulRegisterUrk = "https://demo.guru99.com/test/newtours/register_sucess.php";
        if(successfulRegisterUrk.contentEquals(driver.getCurrentUrl())){
            System.out.println("Test passed. Register successful");
            Thread.sleep(2000);
            driver.findElement(linkText("SIGN-OFF")).click();
            System.out.println("Signed out");
            String homePageUrl = "https://demo.guru99.com/test/newtours/index.php";
            if(!homePageUrl.contentEquals(driver.getCurrentUrl()))
                driver.navigate().to(homePageUrl);
            System.out.println("Returned to home page");
        }
        else
            System.out.println("Test failed. User not registered");
        Thread.sleep(2000);


        //register when all fields are empty
        driver.findElement(linkText("REGISTER")).click();
        WebElement submit1 = driver.findElement(name("submit"));
        Thread.sleep(2000);
        //all fields are empty by default, click submit button
        submit1.click();
        String registerURL = "https://demo.guru99.com/test/newtours/register.php";
        if(!registerURL.contentEquals(driver.getCurrentUrl()))
            System.out.println("Test failed. User registered");
        else
            System.out.println("Test passed. User didnt register");

        driver.close();
    }
}
