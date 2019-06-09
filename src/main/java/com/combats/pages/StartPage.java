package com.combats.pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.*;

public class StartPage extends BasePage {

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

    public DungeonsPage moveInTheDungeon(){
        switchToGameFrame();
        return Selenide.page(DungeonsPage.class);
    }

}
