package com.combats.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.*;
import static com.combats.BaseCombatsBot.waiting;
import static java.lang.Double.parseDouble;

public class GoToBattlePage {

    @FindBy(css = "[value='Поединки']")
    private SelenideElement battles;

    @FindBy(css = "[value='Обновить']")
    private SelenideElement refreshBtn;

    @FindBy(css = "[name=confirm1]")
    private SelenideElement confirm;

    @FindBy(xpath = "//*[.='Подать заявку на хаотичный бой']")
    private SelenideElement applicationChaos;

    @FindBy(css = "[name=open]")
    private SelenideElement open;

    private By goCombat = By.cssSelector("[name=gocombat]");

    public BattlePage chooseBattle(String typeOfBattle) {
        if (battles.isDisplayed()) {
            battles.click();
            if ("chaos".equals(typeOfBattle) || "".equals(typeOfBattle)) {
                $x("//*[.='Хаотичные']").click();
                enterToChaos();
            }
            if ("group".equals(typeOfBattle)) {
                $x("//*[.='Групповые']").click();
                enterToGroup();
            }
            if ("single".equals(typeOfBattle)) {
                $x("//*[.='1 на 1']").click();
                enterToSingle();
            }
        }
        return page(BattlePage.class);
    }

    private void enterToChaos() {
        while (refreshBtn.isDisplayed()) {
            if (confirm.isDisplayed()) {
                int number = chooseRadioWithMinTime();
                if (number >= 0) {
                    $$(goCombat).get(number).click();
                    confirm.click();
                }
            }
            if (applicationChaos.isDisplayed()) {
                applicationChaos.click();
                $("[name=startime2]").selectOptionByValue("300");
                $("[name=levellogin1]").selectOptionByValue("3");
                open.click();
            }
            refreshGoToBattlePage();
            waiting(10, 15);
        }
    }

    private void enterToGroup() {
        while (refreshBtn.isDisplayed()) {
            if (open.isDisplayed()) {
                open.click();
                $("[name=nlogin1]").setValue("3");
                $("[name=levellogin1]").selectOptionByValue("3");
                $("[name=nlogin2]").setValue("3");
                $("[name=levellogin2]").selectOptionByValue("3");
                open.click();
            }
            refreshGoToBattlePage();
        }
    }

    private void refreshGoToBattlePage() {
        if (refreshBtn.isDisplayed())
            refreshBtn.click();
    }

    private void enterToSingle() {
        open.click();
    }

    private int chooseRadioWithMinTime() {
        int minTime = -1;
        int iterator = 0;
        double tmpTime = 5;
        try {
            for (SelenideElement element : $$("[action='zayavka.pl'] > .dsc > i > b")) {
                double time = parseDouble(element.getText());
                if (time <= tmpTime) {
                    tmpTime = time;
                    minTime = iterator;
                }
                iterator++;
            }
            return minTime;
        } catch (NumberFormatException e) {
            e.getStackTrace();
            return -1;
        }
    }

}
