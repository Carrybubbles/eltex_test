package ru.fedin.money.views;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.log4j.Logger;
import ru.fedin.money.controllers.MoneyController;
import ru.fedin.money.models.MoneyModel;

public class MoneyUI extends CustomComponent {
    private static final Logger log = Logger.getLogger(MoneyUI.class);

    private Label labelUsd;
    private Label labelEur;
    private Label labelName;
    private Button updateButton;
    private MoneyModel moneyModel;

    public MoneyUI(MoneyModel moneyModel) {
        this.moneyModel = moneyModel;

        VerticalLayout vl = new VerticalLayout();

        initLabels();
        initButtons();

        vl.addComponents(labelName,labelUsd,labelEur,updateButton);

        setCompositionRoot(vl);

        vl.setSizeUndefined();
        setSizeFull();
    }

    private void initLabels(){
        labelUsd = new Label("USD: 0");
        labelUsd.setStyleName(ValoTheme.LABEL_LARGE);
        labelEur = new Label("EUR: 0");
        labelEur.setStyleName(ValoTheme.LABEL_LARGE);
        labelName = new Label("Курс валют");
        labelName.setStyleName(ValoTheme.LABEL_HUGE);
    }

    private void initButtons(){
        updateButton = new Button("Обновить");
    }

    public  void addUpdateButtonEvent(Button.ClickListener event){
        updateButton.addClickListener(event);
    }

    public void setNameToUsdLabel(String name){
        labelUsd.setValue(name);
    }
    public void setNameToEurLabel(String name){
        labelEur.setValue(name);
    }

    public void clickOnUpdateButton(){
        updateButton.click();
    }

}
