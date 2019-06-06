package com.combats.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.*;

public class CityPage {

    private void switchToGameFrame() {
        switchTo().frame($("[onload='top.User.Framework.MainOnLoad( )']"));
    }

    private void exitToBattle() {
        if ($(".UserBattleEnd").isDisplayed()) {
            $(".UserBattleEnd").click();
            refresh();
        }
    }

    public GoToBattlePage moveInTheCity() {
        exitToBattle();
        switchToGameFrame();
        if ($("#dailypopup").isDisplayed())
            $x("//*[.='Взять задание']").click();
        return Selenide.page(GoToBattlePage.class);
    }

}
