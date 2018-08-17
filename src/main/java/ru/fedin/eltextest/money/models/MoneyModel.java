package ru.fedin.eltextest.money.models;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import ru.fedin.eltextest.net.HTTPResponser;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public final class MoneyModel {

    private static final Logger log = Logger.getLogger(MoneyModel.class);
    private static final String moneyApiURL = "http://free.currencyconverterapi.com/api/v5/convert";
    private static final List<String> currencies = Arrays.asList("EUR_RUB","USD_RUB");

    private HttpResponse<String> doMoneyRequest(List<String> currency) throws UnirestException {
        String reqCurrencies = currency.stream().collect(Collectors.joining(","));
        Map<String, String> headers = Collections.unmodifiableMap(Stream.of(
                new AbstractMap.SimpleEntry<>("accept", "application/json"))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
        Map<String, Object> params = Collections.unmodifiableMap(Stream.of(
                new AbstractMap.SimpleEntry<>("q", reqCurrencies),
                new AbstractMap.SimpleEntry<>("compact", "y"))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));

        return HTTPResponser.doRequest(moneyApiURL,headers,params);
    }
    private Map<String, Double> parseCurrencies(String response){
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(response);
        try{
            Map<String, Double> currencyStorage = new HashMap<>();
            for (Map.Entry<String,JsonElement> currentCurrency:
                    element.getAsJsonObject().entrySet()) {
                String currencyName = currentCurrency.getKey();
                Double currencyValue = currentCurrency.getValue().getAsJsonObject().get("val").getAsDouble();
                currencyStorage.put(currencyName,currencyValue);
            }
            return currencyStorage;
        }catch (Exception e){
            log.error(e);
        }
        return null;
    }

    public Map<String, Double> getAllCurrency(){
        try {
            HttpResponse<String> response = doMoneyRequest(currencies);
            log.info(String.format("Currency rResponse status: %s, body: %s", response.getStatus(),response.getBody()));
            if(response.getStatus() == 200)
                return parseCurrencies(response.getBody());
        }catch (UnirestException e) {
            log.error(e);
        }
        return null;
    }

}
