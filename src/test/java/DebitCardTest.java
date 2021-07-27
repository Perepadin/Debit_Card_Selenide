import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class DebitCardTest {

    @Test
    void shouldTestPassed() {
        open("http://localhost:7777");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иван Васильевич Царь");
        form.$("[data-test-id=phone] input").setValue("+79270000001");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}
