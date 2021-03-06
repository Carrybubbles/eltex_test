package ru.fedin.eltextest.weather.controllers;

import com.vaadin.ui.Button;
import org.apache.log4j.Logger;
import ru.fedin.eltextest.weather.models.WeatherModel;
import ru.fedin.eltextest.weather.views.WeatherUI;

import java.util.Map;

public class WeatherController {
    private static final Logger log = Logger.getLogger(WeatherController.class);

    private WeatherUI weatherUI;
    private WeatherModel weatherModel;

    public WeatherController(WeatherUI weatherUI, WeatherModel weatherModel) {
        this.weatherUI = weatherUI;
        this.weatherModel = weatherModel;
        weatherUI.addUpdateButtonEvent((Button.ClickListener) event -> updateWeather());
    }

    public WeatherUI getWeatherUI() {
        return weatherUI;
    }

    public void setWeatherUI(WeatherUI weatherUI) {
        this.weatherUI = weatherUI;
    }

    public WeatherModel getWeatherModel() {
        return weatherModel;
    }

    public void setWeatherModel(WeatherModel weatherModel) {
        this.weatherModel = weatherModel;
    }

    private void updateWeather(){

        log.info("Click on weather update button");
        Map<String,Integer> temperature = weatherModel.getWeather(weatherUI.getComboxBoxValue());
        weatherUI.updateTodayTempLabel( temperature != null ? String.format("Темпертаруа текущая: %s C",temperature.get("todayTemp")) : "Темпертаруа текущая: error");
        weatherUI.updateTomorrowTempLabel( temperature != null ? String.format("Температура на завтра: %s C",temperature.get("tomorrowTemp")) : "Температура на завтра: error");


    }
}
