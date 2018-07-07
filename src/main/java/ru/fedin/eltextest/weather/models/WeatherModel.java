package ru.fedin.eltextest.weather.models;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import ru.fedin.eltextest.net.HTTPResponser;
import org.apache.log4j.Logger;
import ru.fedin.eltextest.weather.exceptions.WeatherResponseException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class WeatherModel {
    private static final Logger log = Logger.getLogger(WeatherModel.class);

    private static final String WEATHER_API_URL = "http://api.worldweatheronline.com/premium/v1/weather.ashx";
    //need place it to db and encrypt it
    private static final String API_KEY = "97e5664bdf9e478a86d115938180507";

    private HttpResponse<String> doWeatherRequest(String city) throws UnirestException {
        HTTPResponser responser = new HTTPResponser();
        Map<String, Object> params = Collections.unmodifiableMap(Stream.of(
                new AbstractMap.SimpleEntry<>("q", city),
                new AbstractMap.SimpleEntry<>("format", "json"),
                new AbstractMap.SimpleEntry<>("date", "tomorrow"),
                new AbstractMap.SimpleEntry<>("key", API_KEY))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));

        return responser.doRequest(WEATHER_API_URL,null,params);
    }

    private Map<String,Integer> parseWeatherResponse(String response) throws WeatherResponseException {
        Integer todayTemp = getTodayTemp(response);
        if(todayTemp == null){
            throw new WeatherResponseException("today temperature is null ");
        }
        Integer tomorrowTemp = getTomorrowTemp(response);
        if(tomorrowTemp == null){
            throw new WeatherResponseException("tomorrow temperature is null ");
        }
        return Collections.unmodifiableMap(Stream.of(
                new AbstractMap.SimpleEntry<>("tomorrowTemp", tomorrowTemp),
                new AbstractMap.SimpleEntry<>("todayTemp", todayTemp))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
    }

    private Integer getTodayTemp(String  response){
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(response);
        try{
            return element.getAsJsonObject().get("data").getAsJsonObject().get("current_condition").getAsJsonArray().get(0).getAsJsonObject().get("temp_C").getAsInt();
        }catch (Exception e){
            log.error(e);
            return null;
        }
    }

    private Integer getTomorrowTemp(String response){
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(response);
        try{
            int max = element.getAsJsonObject().get("data").getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("maxtempC").getAsInt();
            int min = element.getAsJsonObject().get("data").getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("mintempC").getAsInt();
            return  (max + min) / 2;
        }catch (Exception e){
            log.error(e);
        }
        return null;

    }

    public Map<String,Integer> getWeather(String city) {
        try {
            HttpResponse<String> response = doWeatherRequest(city);
            log.info(String.format("Weather response status: %s, body: %s", response.getStatus(),response.getBody()));
            if(response.getStatus() == 200){
                return parseWeatherResponse(response.getBody());
            }
        } catch (UnirestException | WeatherResponseException e) {
            log.error(e);
        }
        return null;
    }

}
