import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.io.File;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CheckFormTests {

    String firstName = "Ivan";
    String lastName = "Ivanov";
    String mail = "ivan@mail.com";
    String gender = "Male";
    String phoneNumber = "8911321127";
    String date = "18";
    String month = "April";
    String year = "1985";
    String subject1 = "English";
    String subject2 = "History";
    String hobbi1 = "Sports";
    String hobbi2 = "Reading";
    String hobbi3 = "Music";
    String currentAddress = "Russia, Saint-Petersburg, Mira st., 18";
    String state = "Uttar Pradesh";
    String city = "Agra";
    String fileName = "1.png";

    @Test
    public void fillForm() throws AWTException {
//        Заполнение и сохранение формы
        Configuration.startMaximized = true;
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(mail);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(phoneNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month-select").selectOption(month);
        $x("//div[@class='react-datepicker__month']//div[contains(text(),'" + date + "')]").click();
        $("#subjectsInput").setValue(subject1).pressEnter();
        $("#subjectsInput").setValue(subject2).pressEnter();
        $("#hobbiesWrapper").$(byText(hobbi1)).click();
        $("#hobbiesWrapper").$(byText(hobbi2)).click();
        $("#hobbiesWrapper").$(byText(hobbi3)).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/" + fileName));
        $("#currentAddress").setValue(currentAddress);
        $x("//div[@id='state']//input").setValue(state).pressEnter();
        $x("//div[@id='city']//input").setValue(city).pressEnter();
        $("#submit").click();
        $x("//div[@id='example-modal-sizes-title-lg']").shouldHave(Condition.text("Thanks for submitting the form"));
//      Проверка имени и фамилии
        $x("//tbody//td[contains(text(),'Student Name')]/../td[2]").shouldHave(Condition.text(firstName));
        $x("//tbody//td[contains(text(),'Student Name')]/../td[2]").shouldHave(Condition.text(lastName));
//      Проверка почты
        $x("//tbody//td[contains(text(),'Student Email')]/../td[2]").shouldHave(Condition.text(mail));
//      Проверка пола
        $x("//tbody//td[contains(text(),'Gender')]/../td[2]").shouldHave(Condition.text("Male"));
//        Проверка номера телефона
        $x("//tbody//td[contains(text(),'Mobile')]/../td[2]").shouldHave(Condition.text(phoneNumber));
//        Проверка даты рождения
        $x("//tbody//td[contains(text(),'Date of Birth')]/../td[2]").shouldHave(Condition.text(date));
        $x("//tbody//td[contains(text(),'Date of Birth')]/../td[2]").shouldHave(Condition.text(month));
        $x("//tbody//td[contains(text(),'Date of Birth')]/../td[2]").shouldHave(Condition.text(year));
//        Проверка предметов
        $x("//tbody//td[contains(text(),'Subjects')]/../td[2]").shouldHave(Condition.text(subject1));
        $x("//tbody//td[contains(text(),'Subjects')]/../td[2]").shouldHave(Condition.text(subject2));
//        Проверка выбранных хобби
        $x("//tbody//td[contains(text(),'Hobbies')]/../td[2]").shouldHave(Condition.text(hobbi1));
        $x("//tbody//td[contains(text(),'Hobbies')]/../td[2]").shouldHave(Condition.text(hobbi2));
        $x("//tbody//td[contains(text(),'Hobbies')]/../td[2]").shouldHave(Condition.text(hobbi3));
//        Проверка на наличие имени прикрепленного файла
        $x("//tbody//td[contains(text(),'Picture')]/../td[2]").shouldHave(Condition.text(fileName));
//        Проверка адреса
        $x("//tbody//td[contains(text(),'Address')]/../td[2]").shouldHave(Condition.text(currentAddress));
//        Проверка штата и города
        $x("//tbody//td[contains(text(),'State and City')]/../td[2]").shouldHave(Condition.text(state));
        $x("//tbody//td[contains(text(),'State and City')]/../td[2]").shouldHave(Condition.text(city));
    }

}
