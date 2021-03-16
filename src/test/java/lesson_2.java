import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Keys;
import java.io.File;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

public class lesson_2  {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void selenideSearchTest() {
        //----open and validation page
        open("https://demoqa.com/automation-practice-form");
        $(By.cssSelector(".main-header")).shouldHave(text("Practice"));
        //----

        String first_name = "Andru";
        String last_name = "Katyushin";
        String gender = "Male";
        String email = "a.katyushin@gmail.com";
        String phone = "3759999999";
        String birth = "12/04/1985";
        String birth_check ="04 December,1985";
        String subjects = "English";
        String hob = "Sports";
        String photo_name = "photo_2021-03-10_20-56-18.jpg";
        String address = "Suite 12 2nd Floor,\n" + "Queens House,\n" +"180 Tottenham Court Road,\n" + "London W1T 7PD";
        String state = "Haryana";
        String city = "Karnal";

        $(By.id("firstName")).setValue(first_name);
        $(By.id("lastName")).setValue(last_name);
        $(By.id("userEmail")).setValue(email);
        $(byXpath("//*[@id='gender-radio-1']")).doubleClick();
        $(By.id("userNumber")).setValue(phone);

        //----Date of Birth
        $(By.id("dateOfBirthInput")).click();
        $(By.id("dateOfBirthInput")).sendKeys(Keys.CONTROL + "A");
        sleep(500);
        $(By.id("dateOfBirthInput")).sendKeys(birth);
        $(By.id("dateOfBirthInput")).pressEnter();
        //----

        //----Subjects
        $(By.id("subjectsInput")).setValue(subjects);
        $(By.id("subjectsInput")).pressEnter();
        //----

        $("[for='hobbies-checkbox-1']").click();

        //----load_file
        File file = $(By.id("uploadPicture")).uploadFile(new File("src/test/java/../resources/"+photo_name));
        //----

        $(By.id("currentAddress")).setValue(address);

       //----drop-down
        $(By.id("react-select-3-input")).setValue(state).pressEnter();
        $(By.id("react-select-4-input")).setValue(city).pressEnter();
        //----

        $(By.id("submit")).click();

        //----assetrs
        $(By.id("example-modal-sizes-title-lg")).shouldHave(text("Thanks for submitting the form"));
//----знаю что селекторы кривые,но по другому к ячейкам таблицы не нашел как обратиться
        $(byXpath("//tr[1]/td[2]")).shouldHave(text(first_name+" "+last_name));
        $(byXpath("//tr[2]/td[2]")).shouldHave(text(email));
        $(byXpath("//tr[3]/td[2]")).shouldHave(text(gender));
        $(byXpath("//tr[4]/td[2]")).shouldHave(text(phone));
        $(byXpath("//tr[5]/td[2]")).shouldHave(text(birth_check));
        $(byXpath("//tr[6]/td[2]")).shouldHave(text(subjects));
        $(byXpath("//tr[7]/td[2]")).shouldHave(text(hob));
        $(byXpath("//tr[8]/td[2]")).shouldHave(text(photo_name));
        $(byXpath("//tr[9]/td[2]")).shouldHave(text(address));
        $(byXpath("//tr[10]/td[2]")).shouldHave(text(state+" "+city));

    }
}
