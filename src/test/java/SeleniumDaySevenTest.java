import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.Collectors;

public class SeleniumDaySevenTest {

    public static ChromeDriver driver;

    @BeforeAll
    public static void setupDriver()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void checkamazon()
    {
        driver.get("https://www.Ajio.com/");
        WebElement webElement = driver.findElement(By.cssSelector("#twotabsearchtextbox"));

        String s_search = "Oppo";
        webElement.sendKeys(s_search, Keys.ENTER);

        List<String> actualItems = driver.findElements(By.cssSelector("[data-component-type='s-search-result'] h2 .a-link-normal"))
                .stream()
                .map(element -> element.getText().toLowerCase() + element.getAttribute("href").toLowerCase())
                .collect(Collectors.toList());
/*
        List<String> expectedItems = actualItems.stream()
                .filter(item -> item.contains("invalid search phrase"))
                .collect(Collectors.toList());*/
        List<String> expectedItems = actualItems.stream()
                .filter(item -> item.contains(s_search))
                .collect(Collectors.toList());
        Assertions.assertEquals(expectedItems, actualItems);
    }

    @AfterAll
    public static void stopdownDriver()
    {
        driver.quit();
    }
}
