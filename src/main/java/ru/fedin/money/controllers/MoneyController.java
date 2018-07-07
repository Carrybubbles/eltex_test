package ru.fedin.money.controllers;

import com.vaadin.ui.Button;
import org.apache.log4j.Logger;
import ru.fedin.money.models.MoneyModel;
import ru.fedin.money.views.MoneyUI;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    public MoneyModel getMoneyModel() {
        return moneyModel;
    }

    public void setMoneyModel(MoneyModel moneyModel) {
        this.moneyModel = moneyModel;
    }

    public MoneyUI getMoneyUI() {
        return moneyUI;
    }

    public void setMoneyUI(MoneyUI moneyUI) {
        this.moneyUI = moneyUI;
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
