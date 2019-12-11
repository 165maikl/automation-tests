import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CheckCorrectPage {
    private WebDriver driver;
    private WebDriverWait wait;

    float size1, size2;
    String[] valueOldPrice, valueNewPrice, formatOldPrice, formatNewPrice;
    WebElement firstItem;


    @BeforeMethod
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,5);
    }

    @Test
    public void myCheckCorrectPage() {
        //открыть главную страницу магазина
        driver.get("http://localhost/litecart/");
        //ожидание загрузки страницы
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product.column.shadow.hover-light")));

        //инициализируем строковые массивы для хранения названия товара, размера старой и новой цены на домашней странце
        //и странице товара
        valueOldPrice = new String[3];
        valueNewPrice = new String[3];

        //находим первый товар в блоке "Campaigns"
        firstItem = driver.findElement(By.cssSelector("#box-campaigns li:first-child"));
        //сохраняем название товара
        valueOldPrice[0] = firstItem.findElement(By.cssSelector(".name")).getText();
        //сохраняем старую цену товара, указанную на главной странице
        valueOldPrice[1] = firstItem.findElement(By.cssSelector(".regular-price")).getText();
        //сохраняем новую цену товара, указанную на главной странице
        valueOldPrice[2] = firstItem.findElement(By.cssSelector(".campaign-price")).getText();

        //переходим на страницу товара
        driver.findElement(By.cssSelector("#box-campaigns li:first-child")).click();
        //сохраняем название товара, указанное на странице товара
        valueNewPrice[0] = driver.findElement(By.cssSelector("#box-product .title")).getText();
        //сохраняем старую цену товара, указанную на странице товара
        valueNewPrice[1] = driver.findElement(By.cssSelector(".regular-price")).getText();
        //сохраняем новую цену товара, указанную на странице товара
        valueNewPrice[2] = driver.findElement(By.cssSelector(".campaign-price")).getText();

        //сравниваем название товара, значение старой и новой цены на домашней странице и странице товара
        for (int i=0; i<3; i++){
            Assert.assertTrue(valueOldPrice[i].compareTo(valueNewPrice[i])==0);
        }

        //инициализируем строковые массивы для хранения параметров оформления старой и новой цены
        formatOldPrice = new String[4];
        formatNewPrice = new String[4];
        //сохраняем размер шрифта старой цены
        formatOldPrice[0] = driver.findElement(By.cssSelector(".regular-price")).getCssValue("font-size");
        //конвертируем в число размер шрифта
        size1 = Float.parseFloat(formatOldPrice[0].replaceAll("px",""));
        //сохраняем цвет шрифта старой цены
        formatOldPrice[1] = driver.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        //сохраняем толщину шрифта старой цены (обычный или полужирный)
        formatOldPrice[2] = driver.findElement(By.cssSelector(".regular-price")).getCssValue("font-weight");
        //сохраняем написание шрифта старой цены (простой или зачеркнутый)
        formatOldPrice[3] = driver.findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration");

        //сохраняем размер шрифта новой цены
        formatNewPrice[0] = driver.findElement(By.cssSelector(".campaign-price")).getCssValue("font-size");
        //конвертируем в число размер шрифта
        size2 = Float.parseFloat(formatNewPrice[0].replaceAll("px",""));
        //сохраняем цвет шрифта новой цены
        formatNewPrice[1] = driver.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        //сохраняем толщину шрифта новой цены (обычный или полужирный)
        formatNewPrice[2] = driver.findElement(By.cssSelector(".campaign-price")).getCssValue("font-weight");
        //сохраняем написание шрифта новой цены (простой или зачеркнутый)
        formatNewPrice[3] = driver.findElement(By.cssSelector(".campaign-price")).getCssValue("text-decoration");

        //сравниваем, что размер шрифта старой цены меньше новой
        Assert.assertTrue(size1<size2);
        //проверяем, что цвет старой цены серый
        Assert.assertEquals(formatOldPrice[1], "rgba(102, 102, 102, 1)");
        //проверяем, что шрифт старой цены не является полужирным
        Assert.assertEquals(formatOldPrice[2], "400");
        //проверяем, что шрифт старой цены зачеркнутый
        Assert.assertEquals(formatOldPrice[3], "line-through solid rgb(102, 102, 102)");
    }

    @AfterMethod
    public void stop(){
        driver.quit();
        driver = null;
    }
}
