package com.combats.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalTime;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.combats.BaseBot.getRandomInt;
import static com.combats.BaseBot.waiting;

public class BattlePage {

    @FindBy(css = "[action=commit]")
    private SelenideElement commitBtn;

    @FindBy(css = ".UserBattleKick")
    private SelenideElement battleKick;

    @FindBy(css = "[action=gameover]")
    private SelenideElement gameover;

    @FindBy(css = ".UserBattleMethod")
    private List<SelenideElement> battleMethods;

    @FindBy(css = ".UserBattleAttack button.UserBattleRadio")
    private List<SelenideElement> attackRadios;

    @FindBy(css = ".UserBattleDefend button.UserBattleRadio")
    private List<SelenideElement> defendRadios;

    @FindBy(css = "td.UserBattleError")
    private SelenideElement text;

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
            if (commitBtn.isDisplayed()) {
                if ($(".UserBattleMethod").isDisplayed()) {
                    battleMethods.get(0).click();
                    waiting(0, 1);
                }
                if (attackRadios.get(1).isDisplayed() && defendRadios.get(1).isDisplayed()) {
                    attackRadios.get(getRandomInt(0, 5)).click();
                    defendRadios.get(getRandomInt(0, 5)).click();
                    commitBtn.pressEnter();
                    waiting(1, 2);
                }
            } else if (battleKick.isDisplayed()) {
                battleKick.click();
                waiting(3, 5);
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
        if (text.isDisplayed()) {
            String message = text.getText();
            System.out.println(LocalTime.now() + " " + message);
        }
    }

    private void exitBattle() {
        getMessage();
        if (gameover.isDisplayed())
            gameover.click();
    }

}
