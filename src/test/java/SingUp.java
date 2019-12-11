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

public class SingUp{
    private WebDriver driver;
    private WebDriverWait wait;

    int prodQuantity, stickerQuantity;
    WebElement productUnit;
    List<WebElement> prodList, stickerList;
    String firstName = "Mihas";
    String lastName = "Ivanov";
    String adress = "Chkalov str. 15";
    String postCode = "220070";
    String city = "Minsk";
    String email = "misha_khlus@mail.ru";
    String phone = "+375333341424";
    String pasword = "12345";

    @BeforeMethod
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,5);
    }

    @Test
    public void mySingUp() throws InterruptedException {
        //открыть главную страницу магазина
        driver.get("http://localhost/litecart/");
        //ожидание загрузки страницы
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product.column.shadow.hover-light")));
        //перейти по ссылке New customer click here
        driver.findElement(By.xpath("//tr[5]/td/a")).click();
        //заполняем обязательные поля
        driver.findElement(By.cssSelector("[name=firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("[name=lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("[name=address1")).sendKeys(adress);
        driver.findElement(By.cssSelector("[name=postcode")).sendKeys(postCode);
        driver.findElement(By.cssSelector("[name=city")).sendKeys(city);
        driver.findElement(By.cssSelector("[name=email")).sendKeys(email);
        driver.findElement(By.cssSelector("[name=phone")).sendKeys(phone);
        driver.findElement(By.cssSelector("[name=password")).sendKeys(pasword);
        driver.findElement(By.cssSelector("[name=confirmed_password")).sendKeys(pasword);

        Thread.sleep(5000);

//        //сохраняем количество товаров
//        prodQuantity = prodList.size();
//
//        //проходим по списку товаров
//        for (int i = 0; i < prodQuantity; i++) {
//            prodList = driver.findElements(By.cssSelector("li.product"));
//            productUnit = prodList.get(i);
//            //определение списка стикеров (полосок) у товара
//            stickerList = productUnit.findElements(By.cssSelector(".sticker"));
//            //определение количества стикеров у товара
//            stickerQuantity = stickerList.size();
//            //проверка на наличие у товара одного стикера
//            Assert.assertTrue(stickerQuantity == 1);
//        }
    }

    @AfterMethod
    public void stop(){
        driver.quit();
        driver = null;
    }
}
