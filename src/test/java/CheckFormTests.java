import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.datatransfer.StringSelection;

public class CheckFormTests {

    String firstName = "Ivan";
    String lastName = "Ivanov";
    String mail = "ivan@mail.com";
    String phoneNumber = "8911321127";
    String date = "18";
    String month = "3";
    String year = "1985";
    String subjects = "English, History";
    String currentAddress = "Russia, Saint-Petersburg, Mira st., 18";
    String state = "Uttar Pradesh";
    String city = "Agra";
    String fileName = "2020.11.13_001.png";

    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    @Test
    @Order(0)
    public void fillForm() throws AWTException {
        open("https://demoqa.com/automation-practice-form");
        $x("//input[@id='firstName']").setValue(firstName);
        $x("//input[@id='lastName']").setValue(lastName);
        $x("//input[@id='userEmail']").setValue(mail);
        $x("//label[@for='gender-radio-1']").click();
        $x("//input[@id='userNumber']").setValue(phoneNumber);
        $x("//input[@id='dateOfBirthInput']").click();
        $x("//select[@class='react-datepicker__year-select']").click();
        $x("//select[@class='react-datepicker__year-select']/option[@value='" + year + "']").click();
        $x("//select[@class='react-datepicker__month-select']").click();
        $x("//select[@class='react-datepicker__month-select']/option[@value='" + month + "']").click();
        $x("//div[@class='react-datepicker__month']//div[contains(text(),'" + date + "')]").click();
        $x("//input[@id='subjectsInput']").setValue(subjects);
        $x("//label[@for='hobbies-checkbox-2']").click();
        $x("//label[@for='hobbies-checkbox-3']").click();

//        Тут еще нужно файл выбрать и видимо запомнить его имя для проверки
//        $x("//input[@id='uploadPicture']").sendKeys("C:/Users/UserDesktop/Временная/2020.11.13/2020.11.13_001.png");

        $x("//label[@for='uploadPicture']").click();
        setClipboardData("C:\\Users\\User\\Desktop\\Временная\\2020.11.13\\" + fileName);
        //native key strokes for CTRL, V and ENTER keys
        Robot robot = new Robot();
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        $x("//textarea[@id='currentAddress']").setValue(currentAddress);
        $x("//div[@id='state']//input").setValue(state).pressEnter();
        $x("//div[@id='city']//input").setValue(city).pressEnter();
        $x("//button[@id='submit']").click();
        $x("//div[@id='example-modal-sizes-title-lg']").shouldHave(Condition.text("Thanks for submitting the form"));

    }

    @Test
    @Order(1)
    public void checkName() {
        $x("//tbody//td[contains(text(),'Student Name')]/../td[2]").shouldHave(Condition.text(firstName));
        $x("//tbody//td[contains(text(),'Student Name')]/../td[2]").shouldHave(Condition.text(lastName));
    }

    @Test
    @Order(2)
    public void checkMail() {
        $x("//tbody//td[contains(text(),'Student Email')]/../td[2]").shouldHave(Condition.text(mail));
    }

    @Test
    @Order(3)
    public void CheckGender() {
        $x("//tbody//td[contains(text(),'Gender')]/../td[2]").shouldHave(Condition.text("Male"));
    }

    @Test
    @Order(4)
    public void checkPhone() {
        $x("//tbody//td[contains(text(),'Mobile')]/../td[2]").shouldHave(Condition.text(phoneNumber));
    }

    @Test
    @Order(5)
    public void checkBirth() {
        $x("//tbody//td[contains(text(),'Date of Birth')]/../td[2]").shouldHave(Condition.text(date));
        $x("//tbody//td[contains(text(),'Date of Birth')]/../td[2]").shouldHave(Condition.text("April"));
        $x("//tbody//td[contains(text(),'Date of Birth')]/../td[2]").shouldHave(Condition.text(year));
    }

    @Test
    @Order(6)
    public void checkSubjects() {
        $x("//tbody//td[contains(text(),'Subjects')]/../td[2]").shouldHave(Condition.text(subjects));
    }

    @Test
    @Order(7)
    public void checkHobbies() {
        $x("//tbody//td[contains(text(),'Hobbies')]/../td[2]").shouldHave(Condition.text("Reading"));
        $x("//tbody//td[contains(text(),'Hobbies')]/../td[2]").shouldHave(Condition.text("Music"));
    }

//    Тут еще нужна проверка на наличие имени прикрепленного файла
    @Test
    @Order(8)
    public void checkAddress() {
        $x("//tbody//td[contains(text(),'Address')]/../td[2]").shouldHave(Condition.text(currentAddress));
    }

    @Test
    @Order(8)
    public void checkFileName() {
        $x("//tbody//td[contains(text(),'Picture')]/../td[2]").shouldHave(Condition.text(fileName));
    }

    @Test
    @Order(9)
    public void checkStateAndCity() {
        $x("//tbody//td[contains(text(),'State and City')]/../td[2]").shouldHave(Condition.text(state));
        $x("//tbody//td[contains(text(),'State and City')]/../td[2]").shouldHave(Condition.text(city));
    }

}
