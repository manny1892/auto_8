package test;

import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import page.DashboardPage;
import page.LoginPage;
import lombok.val;
import page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

class Test {
    @BeforeEach
    void login() {
        open("http://localhost:9999");
    }


    @org.junit.jupiter.api.Test
    void shouldLoginFirstRegisteredUser() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfoFirstUser();
        loginPage.validLogin(authInfo);
        var verificationPage = new VerificationPage();
        verificationPage.validVerify(DataHelper.getVerificationCodeFor());
        val dashboardPage = new DashboardPage();

    }

    @org.junit.jupiter.api.Test
    void shouldLoginSecondRegisteredUser() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfoSecondUser();
        loginPage.validLogin(authInfo);
        var verificationPage = new VerificationPage();
        verificationPage.validVerify(DataHelper.getVerificationCodeFor());
        val dashboardPage = new DashboardPage();
    }

    @org.junit.jupiter.api.Test
    void NotShouldWithWrongLogin() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfoWrongLogin();
        loginPage.validLogin(authInfo);
        loginPage.getLoginNotification();

    }

    @org.junit.jupiter.api.Test
    void NotShouldWithWrongPassword() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfoWrongPassword();
        loginPage.validLogin(authInfo);
        loginPage.getLoginNotification();

    }

    @org.junit.jupiter.api.Test
    void shouldBlockedVerificationPage() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfoFirstUser();
        loginPage.validLogin(authInfo);
        var verificationPage = new VerificationPage();
        verificationPage.validVerify(DataHelper.getWrongCode());
        loginPage.validLogin(authInfo);
        verificationPage.validVerify(DataHelper.getWrongCode());
        loginPage.validLogin(authInfo);
        verificationPage.validVerify(DataHelper.getWrongCode());
        verificationPage.getBlockedVerificationNotification();
    }

}

