package com.combats.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;

import static com.codeborne.selenide.Selenide.*;
import static com.combats.BaseBot.waiting;
import static java.lang.Double.parseDouble;

public class GoToBattlePage {

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
        $("[value='Поединки']").click();
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
        return page(BattlePage.class);
    }

    private void enterToChaos() {
        while (refreshBtn.isDisplayed()) {
            if (confirm.isDisplayed()) {
                int number = chooseRadio();
                $$(goCombat).get(number).click();
                confirm.click();
            } else if (applicationChaos.isDisplayed()) {
                applicationChaos.click();
                $("[name=startime2]").selectOptionByValue("300");
                $("[name=levellogin1]").selectOptionByValue("3");
                open.click();
            } else
                refreshGoToBattlePage();
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
        waiting(3, 5);
    }

    private void enterToSingle() {
        open.click();
    }

    /*
     * P3
     * переписать логику выбора, сейчас сортируется порядковый номер, а надо сортировать время до боя
     */
    private int chooseRadio() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int iterator = 0;
        try {
            for (SelenideElement element : $$("[action='zayavka.pl'] > .dsc > i > b")) {
                double time = parseDouble(element.getText());
                if (time <= 5)
                    list.add(iterator);
                iterator++;
            }
            Collections.reverse(list);
            return list.get(0);
        } catch (NumberFormatException e) {
            e.getStackTrace();
            return 0;
        }
    }

}
