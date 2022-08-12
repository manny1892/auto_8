package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private SelenideElement notification = $("[data-test-id=error-notification]");


    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public void validVerify(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
    }

    public void getVerificationNotification() {
        notification.shouldHave(Condition.text("Ошибка! Неверно указан код! Попробуйте ещё раз.")).shouldBe(Condition.visible);
    }

    public void getBlockedVerificationNotification() {
        notification.shouldBe(Condition.visible);
    }

}