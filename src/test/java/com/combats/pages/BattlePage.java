package com.combats.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalTime;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.combats.BaseTest.getRandomInt;
import static com.combats.BaseTest.waiting;

public class BattlePage {

    @FindBy(css = "[action=commit]")
    private SelenideElement commitBtn;

    @FindBy(css = ".UserBattleKick")
    private SelenideElement battleKick;

    @FindBy(css = "[action=gameover]")
    private SelenideElement gameover;

    @FindBy(css = ".UserBattleMethod")
    private SelenideElement battleMethod;

    @FindBy(css = ".UserBattleAttack button.UserBattleRadio")
    private List<SelenideElement> attackRadios;

    @FindBy(css = ".UserBattleDefend button.UserBattleRadio")
    private List<SelenideElement> defendRadios;

    public BattlePage() {
    }

    /*
     * P2
     * добавить исключение выбора питомца в приёмах
     */

    public void fight() {
        switchTo().defaultContent();
        commitBtn.waitUntil(visible, 5000);
        while (commitBtn.isDisplayed() || battleKick.isDisplayed()) {
            if (battleKick.isDisplayed()) {
                battleKick.click();
                waiting(3, 5);
            } else {
                if (battleMethod.isDisplayed()) {
                    $$(".UserBattleMethod").get(0).click();
                }
                if (attackRadios.get(0).isDisplayed() && defendRadios.get(0).isDisplayed()) {
                    attackRadios.get(getRandomInt(0, 5)).shouldBe(visible).click();
                    defendRadios.get(getRandomInt(0, 5)).click();
                    commitBtn.pressEnter();
                    waiting(1, 2);
                }
            }
        }
        getMessage();
        exitBattle();
    }

    /*
     * P4
     * уведомление в телегу об окончании боя
     */

    private void getMessage() {
        String message = $("td.UserBattleError").getText();
        System.out.println(LocalTime.now() + " " + message);
    }

    private void exitBattle() {
        getMessage();
        gameover.waitUntil(visible, 5000).click();
    }

}
