import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class DebitCardTest {

    @Test
    void shouldTestPassedV1() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иван Васильевич Царь");
        form.$("[data-test-id=phone] input").setValue("+79270000001");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestPassedV2() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иван Васильевич Царь-Русский");
        form.$("[data-test-id=phone] input").setValue("+79270000001");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestFieldNameFailedWithEnglish() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Ivan");
        form.$("[data-test-id=phone] input").setValue("+79270000001");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestFieldNameFailed() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иван 12");
        form.$("[data-test-id=phone] input").setValue("+79270000001");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestFieldNameEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79270000001");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestFieldPhoneFailed() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иван Васильевич Царь");
        form.$("[data-test-id=phone] input").setValue("+7927000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestFieldPhoneEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иван Васильевич Царь");
        form.$("[data-test-id=phone] input").setValue("5");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestNoClickAgreement() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иван Васильевич Царь");
        form.$("[data-test-id=phone] input").setValue("+79270000001");
        form.$("[role=button]").click();
        $("[data-test-id='agreement'].input_invalid").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}
