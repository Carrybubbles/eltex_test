package ru.fedin.weather.views;


import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.log4j.Logger;
import ru.fedin.weather.controllers.WeatherController;
import ru.fedin.weather.models.WeatherModel;

import java.util.Arrays;
import java.util.List;

public class WeatherUI extends CustomComponent {

    private static final Logger log = Logger.getLogger(WeatherUI.class);

    private WeatherModel weatherModel;

    private Label labelWeather;
    private Label labelTomorrowTemp;
    private Label labelTodayTemp;
    private ComboBox<String> select;
    private Button updateButton;


    public WeatherUI(WeatherModel weatherModel) {
        this.weatherModel = weatherModel;

        VerticalLayout vl = new VerticalLayout();

        initLabels();
        initButtons();
        initComboBoxes();

        vl.addComponents(labelWeather,select, labelTodayTemp,labelTomorrowTemp,updateButton);
        setCompositionRoot(vl);
        vl.setSizeUndefined();
        setSizeFull();
    }

    private void initLabels(){
        labelWeather = new Label("Погода");
        labelTodayTemp = new Label("Темпертаруа текущая: %s C");
        labelTomorrowTemp = new Label("Температура на завтра: %s C");

    }

    private void initButtons(){
        updateButton = new Button("Обновить");
    }

    private void initComboBoxes(){
        final List<String> cities = Arrays.asList("Novosibirsk", "Moscow", "St. Petersburg");
        select = new ComboBox<>("Выберите город");
        select.setItems(cities);
        select.setSelectedItem(cities.get(0));
        select.setEmptySelectionAllowed(false);
    }

    public void addUpdateButtonEvent(Button.ClickListener event){
        updateButton.addClickListener(event);
    }

    public String getComboxBoxValue(){
        return select.getValue();
    }

    public void updateTomorrowTempLabel(String name){
        labelTomorrowTemp.setValue(name);
    }

    public void updateTodayTempLabel(String name){
        labelTodayTemp.setValue(name);
    }

    public void clickOnUpdateButton(){
        updateButton.click();
    }
}
