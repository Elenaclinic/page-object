package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
  private final ElementsCollection cards = $$(".list__item div");
  private final SelenideElement heading = $("[data-test-id='dashboard']");
  private final String balanceStart = "баланс: ";
  private final String balanceFinish = " р.";

  public DashboardPage() {
    heading.shouldBe(visible);
  }

  public int getCardBalance(DataHelper.CardInfo cardInfo) {
    var text= cards.findBy(attribute("data-test-id",cardInfo.getTestId())).getText();
    return extractBalance(text);
  }

  public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
    cards.findBy(text(cardInfo.getNumberCard().substring(15))).$("button").click();
    return new TransferPage();
  }

  private int extractBalance(String text) {
    val start = text.indexOf(balanceStart);
    val finish = text.indexOf(balanceFinish);
    val value = text.substring(start + balanceStart.length(), finish);
    return Integer.parseInt(value);
  }
}


