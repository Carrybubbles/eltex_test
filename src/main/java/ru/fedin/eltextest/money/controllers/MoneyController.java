package ru.fedin.eltextest.money.controllers;

import com.vaadin.ui.Button;
import org.apache.log4j.Logger;
import ru.fedin.eltextest.money.models.MoneyModel;
import ru.fedin.eltextest.money.views.MoneyUI;

import java.util.Map;

public class MoneyController {
    private static final Logger log = Logger.getLogger(MoneyController.class);

    private MoneyModel moneyModel;
    private MoneyUI moneyUI;

    public MoneyController(MoneyModel moneyModel, MoneyUI moneyUI) {
        this.moneyModel = moneyModel;
        this.moneyUI = moneyUI;

        moneyUI.addUpdateButtonEvent((Button.ClickListener) event -> updateCurrency());
    }

    private void updateCurrency(){
        log.info("Click on update currency button");
        Map<String,Double> currencies = moneyModel.getAllCurrency();
        Double eurVal = null;
        Double usdVal = null;
        if(currencies != null) {
            eurVal = currencies.get("EUR_RUB");
            usdVal = currencies.get("USD_RUB");
        }
        moneyUI.setNameToEurLabel(currencies != null ? String.format("EUR: %s", eurVal) : "EUR: Ошибка");
        moneyUI.setNameToUsdLabel(currencies != null ? String.format("USD: %s", usdVal) : "USD: Ошибка");
    }
}
