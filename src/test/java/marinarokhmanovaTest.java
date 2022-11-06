import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.stream.Location;
import java.util.List;

public class marinarokhmanovaTest {

    // TC_1_1  - Тест кейс:
    //1. Открыть страницу https://openweathermap.org/
    //2. Набрать в строке поиска город Paris
    //3. Нажать пункт меню Search
    //4. Из выпадающего списка выбрать Paris, FR
    //5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\marin\\Documents\\ChromDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id= 'weather-widget']//input[@ placeholder='Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();

        Thread.sleep(5000);

        WebElement parisFRChoiceINDropdownMenu = driver.findElement(
                By.xpath("//ul[@class ='search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceINDropdownMenu.click();

        WebElement h2CityCountryHeard = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeard.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

//    TC_11_01
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню Guide
//3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide и что title этой страницы
//    OpenWeatherMap API guide - OpenWeatherMap

    @Test
    public void testOpenWeatherMapGuide() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\marin\\Documents\\ChromDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement guideSearchField = driver.findElement(
                By.xpath("//a[@href][text()='Guide']")
        );
        guideSearchField.click();
        Thread.sleep(2000);

        String confirmPageWithLink = driver.getTitle();

        String actualResult1 = driver.getCurrentUrl();
        String actualResult2 = confirmPageWithLink.intern();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);

        driver.quit();
    }

//    TC_11_02
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//3.  Подтвердить, что температура для города показана в Фарингейтах

    @Test
    public void testUnitsImperialCtoF() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\marin\\Documents\\ChromDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String name = "Imperial";
        String expectedResult = "°F";

        driver.get(url);
        Thread.sleep(5000);

        WebElement unitsImperialF = driver.findElement(
                By.xpath("//div[@class ='option'][text()='Imperial: °F, mph']")
        );

        unitsImperialF.click();
        Thread.sleep(2000);

        WebElement unitsImperialFCity = driver.findElement(
                By.xpath("//div[@class ='current-temp']/span")
        );
        unitsImperialFCity.click();
        Thread.sleep(2000);

        boolean actualResult = unitsImperialFCity.getText().contains("F");

        Assert.assertTrue(actualResult);

        driver.quit();
    }

//    TC_11_03
//1.  Открыть базовую ссылку
//2.  Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site to work.
//    We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can
//    allow all cookies or manage them individually.”
//    3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”

    @Test
    public void testConfirmPanelWithTextAtBottom() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\marin\\Documents\\ChromDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        String expectedResult1 = "We use cookies which are essential for the site to work. We also use non-essential " +
                "cookies to help us improve our services. Any data collected is anonymised. You can allow all " +
                "cookies or manage them individually.";
        String expectedResult2 = "Allow all";
        String expectedResult3 = "Manage cookies";

        driver.get(url);
        Thread.sleep(3000);

        WebElement panelWithText = driver.findElement(
                By.xpath("//div[@class = 'stick-footer-panel__container']" +
                        "/p[@class = 'stick-footer-panel__description']")
        );

        WebElement panelWithAllowAll = driver.findElement(
                By.xpath("//div[@class ='stick-footer-panel__btn-container']/button[@type = 'button']")
        );

        WebElement panelWithManageCookies = driver.findElement(
                By.xpath("//div[@class ='stick-footer-panel__btn-container']" +
                        "/a[@class = 'stick-footer-panel__link']")
        );

        String actualResult1 = panelWithText.getText();
        String actualResult2 = panelWithAllowAll.getText();
        String actualResult3 = panelWithManageCookies.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);

        driver.quit();
    }

//    TC_11_04
//1.  Открыть базовую ссылку
//2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”

    @Test
    public void testMenuSupport_FAQHowToStastAskQuestion() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\marin\\Documents\\ChromDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        String expectedResult1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement panelSupport = driver.findElement(
                By.xpath("//li[@class = 'with-dropdown']/div[@id = 'support-dropdown']")
        );
        panelSupport.click();
        Thread.sleep(5000);

        WebElement panelFAQ = driver.findElement(
                By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']/li/a[@href][text() = 'FAQ']")
        );

        WebElement panelHowToStart = driver.findElement(
                By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']/li/a[@href][text() = 'How to start']")
        );


        WebElement panelAskQuestion = driver.findElement(
                By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']/li/a[@href][text() = 'Ask a question']")
        );

        String actualResult1 = panelFAQ.getText();
        String actualResult2 = panelHowToStart.getText();
        String actualResult3 = panelAskQuestion.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);

        driver.quit();
    }

//    TC_11_05
//1. Открыть базовую ссылку
//2. Нажать пункт меню Support → Ask a question
//3. Заполнить поля Email, Subject, Message
//4. Не подтвердив CAPTCHA, нажать кнопку Submit
//5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”

    @Test
    public void testMenuSupportAskQuestion_TryAgain() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\marin\\Documents\\ChromDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String email = "tester@test.com";
        String text = "Hello";

        String expectedResult1 = "reCAPTCHA verification failed, please try again.";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(4000);

        WebElement panelSupport = driver.findElement(
                By.xpath("//li[@class = 'with-dropdown']/div[@id = 'support-dropdown']")
        );
        panelSupport.click();
        Thread.sleep(500);

        WebElement panelSupportAskQuestion = driver.findElement(
                By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']/li/a[@href][text() = 'Ask a question']")
        );
        panelSupportAskQuestion.click();
        Thread.sleep(5000);

        driver.get("https://home.openweathermap.org/questions");
        Thread.sleep(3000);

        WebElement panelEmail = driver.findElement(By.id("question_form_email"));

        panelEmail.click();
        panelEmail.sendKeys(email);
        Thread.sleep(2000);

        WebElement panelSubject = driver.findElement(
                By.xpath("//select[@class = 'form-control select required']")
        );
        panelSubject.click();
        Thread.sleep(2000);

        WebElement panelSubjectOther = driver.findElement(
                By.xpath("//select[@class = 'form-control select required']/option[@value= 'Other']")
        );
        panelSubjectOther.click();
        Thread.sleep(2000);

        WebElement message = driver.findElement(
                By.xpath("//div[@class ='col-sm-8']/textarea[@name = 'question_form[message]']")
        );
        message.click();
        message.sendKeys(text);
        Thread.sleep(2000);

        WebElement submit = driver.findElement(
                By.xpath("//div[@class = 'col-sm-8']/input[@type = 'submit']")
        );
        submit.click();
        Thread.sleep(5000);

        WebElement reCAPTCHA = driver.findElement(
                By.xpath("//div[@class][text() ='reCAPTCHA verification failed, please try again.']")
        );
        Thread.sleep(2000);

        String actualResult = reCAPTCHA.getText();

        Assert.assertEquals(actualResult, expectedResult1);

        driver.quit();
    }

//    TC_11_06
//1.  Открыть базовую ссылку
//2.  Нажать пункт меню Support → Ask a question
//3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
//4.  Оставить пустым поле Email
//5.  Заполнить поля  Subject, Message
//6.  Подтвердить CAPTCHA
//7.  Нажать кнопку Submit
//8.  Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”

    @Test
    public void testMenuSupportAskQuestion_NoEmail() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\marin\\Documents\\ChromDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String subject = "Other";
        String text = "Hello";

        String expectedResult = "can't be blank";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(4000);

        WebElement panelSupport = driver.findElement(
                By.xpath("//div[@id = 'support-dropdown']")
        );
        panelSupport.click();

        String originalWindow = driver.getWindowHandle();
        Thread.sleep(4000);
        WebElement selectSubmenu_AskAQuestion = driver.findElement(By.xpath(
                "//ul[@id='support-dropdown-menu']//a[@href='https://home.openweathermap.org/questions']"));
        selectSubmenu_AskAQuestion.click();

        Thread.sleep(4500);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Thread.sleep(3000);

        WebElement enterSubject = driver.findElement(By.xpath(
                "//select[@class='form-control select required']"));

        enterSubject.click();

        enterSubject.sendKeys(subject);

        Thread.sleep(4000);

        WebElement enterMessage = driver.findElement(By.xpath(
                "//textarea[@class='form-control text required']"));
        enterMessage.click();
        enterMessage.sendKeys(text);

        Thread.sleep(5000);

        String window2 = driver.getWindowHandle();

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']")));

        WebElement enterCaptcha = driver.findElement(By.xpath(
                "//span[@class='recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked "
                        + "rc-anchor-checkbox']"));
        enterCaptcha.click();

        Thread.sleep(10000);

        driver.switchTo().window(window2);

        WebElement pressSubmit = driver.findElement(By.xpath(
                "//input[@data-disable-with='Create Question form']"));
        pressSubmit.click();

        WebElement confirmErrorEmail = driver.findElement(By.xpath("//span[@class='help-block']"));

        String actualResult = confirmErrorEmail.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

//    TC_11_07
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//3.  Нажать на единицы измерения Metric: °C, m/s
//4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С

    @Test
    public void testImperialMetric_FtoC() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\marin\\Documents\\ChromDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        String expectedResult = "°C";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(4000);

        WebElement panelImperial = driver.findElement(
                By.xpath("//div[@class ='option'][text()='Imperial: °F, mph']")
        );
        panelImperial.click();
        Thread.sleep(1000);

        WebElement panelMetric = driver.findElement(
                By.xpath("//div[@class ='option'][text()='Metric: °C, m/s']")
        );
        panelMetric.click();
        Thread.sleep(500);

        WebElement panelTemperatureC = driver.findElement(
                By.xpath("//div[@class = 'controls']//div[@class ='option']")
        );

        boolean actualResult = panelTemperatureC.getText().contains("°C");

        Assert.assertTrue(actualResult);

        driver.quit();
    }

//    TC_11_08
//1.  Открыть базовую ссылку
//2.  Нажать на лого компании
//3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась

    @Test
    public void testLogoComp_OpenWeather() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\marin\\Documents\\ChromDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        String expectedResult = "https://openweathermap.org/";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(4000);

        WebElement logoCompOpenWeather = driver.findElement(
                By.xpath("//ul[@id = 'first-level-nav']//img[@src='/themes/openweathermap/assets/" +
                        "img/logo_white_cropped.png']"));

        logoCompOpenWeather.click();
        Thread.sleep(4000);

        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }

//    TC_11_09
// 1.  Открыть базовую ссылку
// 2.  В строке поиска в навигационной панели набрать “Rome”
// 3.  Нажать клавишу Enter
// 4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
// 5.  Подтвердить, что в строке поиска на новой странице вписано слово “Rome”

    @Test
    public void testNavigationBar_Rome() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\marin\\Documents\\ChromDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Rome";

        String expectedResult1 = "https://openweathermap.org/find?q=Rome";
        String expectedResult2 = "Rome";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(4000);

        WebElement navigationBar = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//input[@type = 'text']"));

        navigationBar.click();
        navigationBar.sendKeys(cityName);
        navigationBar.sendKeys(Keys.ENTER);

        String actualResult1 = driver.getCurrentUrl();

        WebElement weatherYourCityRome = driver.findElement
                (By.xpath("//div[@class='form-group']/input[@id ='search_str']"));

        String actualResult2 = weatherYourCityRome.getAttribute("value");

        Assert.assertEquals(actualResult1,expectedResult1);
        Assert.assertEquals(actualResult2,expectedResult2);
        driver.quit();
    }

//    TC_11_10
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню API
//3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок

    @Test
    public void testAPI_orangeButtons() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\marin\\Documents\\ChromDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        String expectedResult = "30";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(4000);

        WebElement clickAPI= driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']/ul/li[2]/a")
        );
        clickAPI.click();

        List<WebElement>listOfElements1 = driver.findElements
                (By.xpath("//a[@class = 'btn_block orange round']"));
        List<WebElement>listOfElements2 = driver.findElements
                (By.xpath("//a[@class = 'ow-btn round btn-orange']"));

        String actualResult = String.valueOf(listOfElements1.size() + listOfElements2.size());

        Assert.assertEquals(actualResult,expectedResult);
        driver.quit();
    }
}
