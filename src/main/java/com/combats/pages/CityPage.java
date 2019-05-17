package com.combats.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.*;

public class CityPage {

    @FindBy(css = "[value='Поединки']")
    private SelenideElement battles;

    private void switchToGameFrame() {
        switchTo().frame($("[onload='top.User.Framework.MainOnLoad( )']"));
    }

    private void whereAmI() {
        if ($(".UserBattleEnd").isDisplayed())
            $(".UserBattleEnd").click();
        if(!battles.isDisplayed())
            refresh();
    }

    public GoToBattlePage moveInTheCity() {
        whereAmI();
        switchToGameFrame();
        if ($("#dailypopup").isDisplayed())
            $x("//*[.='Взять задание']").click();
        return Selenide.page(GoToBattlePage.class);
    }

}
