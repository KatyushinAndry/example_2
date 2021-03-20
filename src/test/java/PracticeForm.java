import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeForm {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
        }

    @Test
    void selenideSearchTest() {
        String firstName = "Andru";
        String lastName = "Katyushin";
        String gender = "Male";
        String email = "a.katyushin@gmail.com";
        String phone = "3759999999";
        String birthDay = "4";
        String birthMonth = "December";
        String birthYear = "1985";
        //String birth = "12/04/1985";
        String birthСheck ="04 December,1985";
        String subjects = "English";
        String hobby = "Sports";
        String photoName = "photo_2021-03-10_20-56-18.jpg";
        String address = "Suite 12 2nd Floor,\n" + "Queens House,\n" +"180 Tottenham Court Road,\n" + "London W1T 7PD";
        String state = "Haryana";
        String city = "Karnal";

        //----OpeningAndValidationPage
        open("https://demoqa.com/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));
        //----

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#gender-radio-1").doubleClick();
        $("#userNumber").setValue(phone);

        //----DateOfBirth
        /*
        $(By.id("dateOfBirthInput")).click();
        $(By.id("dateOfBirthInput")).sendKeys(Keys.CONTROL + "A");
        sleep(500);
        $(By.id("dateOfBirthInput")).sendKeys(birth);
        $(By.id("dateOfBirthInput")).pressEnter();
        */
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(birthMonth);
        $(".react-datepicker__year-select").selectOption(birthYear);
        $(String.format("[aria-label='Choose Wednesday, %s %sth, %s']", birthMonth, birthDay, birthYear)).click();
        //----

        //----Subjects
        $("#subjectsInput").setValue(subjects);
        $("#subjectsInput").pressEnter();
        //----

        $("[for='hobbies-checkbox-1']").click();

        //----LoadFile
        $("#uploadPicture").uploadFile(new File("./src/test/java/../resources/"+ photoName));
        //----

        $("#currentAddress").setValue(address);

       //----DropDown
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
        //----

        $("#submit").click();

        //----assetrs
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName), text(lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(email));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(phone));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text(birthСheck));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(subjects));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(hobby));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text(photoName));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(address));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text(state+" "+city));


/* → для истории жесткачок
        $(By.id("example-modal-sizes-title-lg")).shouldHave(text("Thanks for submitting the form"));
//----знаю что селекторы кривые,но по другому к ячейкам таблицы не нашел как обратиться
        $(byXpath("//tr[1]/td[2]")).shouldHave(text(firstName +" "+ lastName));
        $(byXpath("//tr[2]/td[2]")).shouldHave(text(email));
        $(byXpath("//tr[3]/td[2]")).shouldHave(text(gender));
        $(byXpath("//tr[4]/td[2]")).shouldHave(text(phone));
        $(byXpath("//tr[5]/td[2]")).shouldHave(text(birthСheck));
        $(byXpath("//tr[6]/td[2]")).shouldHave(text(subjects));
        $(byXpath("//tr[7]/td[2]")).shouldHave(text(hobby));
        $(byXpath("//tr[8]/td[2]")).shouldHave(text(photoName));
        $(byXpath("//tr[9]/td[2]")).shouldHave(text(address));
        $(byXpath("//tr[10]/td[2]")).shouldHave(text(state+" "+city));
//---- можно так но мне не нравится
        $(".modal-content").shouldHave(
                text(firstName +" "+ lastName),
                text(email),
                text(gender),
                text(phone),
                text(birthСheck),
                text(subjects),
                text(hobby),
                text(photoName),
                text(address),
                text(state+" "+city));
*/
    }

}
