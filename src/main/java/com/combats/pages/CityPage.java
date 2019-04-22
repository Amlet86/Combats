package combats.pages;

import static com.codeborne.selenide.Selenide.*;

public class CityPage {

    private void switchToGameFrame() {
        switchTo().frame($("[onload='top.User.Framework.MainOnLoad( )']"));
    }

    private void whereAmI() {
        if ($(".UserBattleEnd").isDisplayed())
            $(".UserBattleEnd").click();
    }

    public GoToBattlePage moveInCity() {
        whereAmI();
        switchToGameFrame();
        if ($("#dailypopup").isDisplayed())
            $x("//*[.='Взять задание']").click();
        return page(GoToBattlePage.class);
    }

}
