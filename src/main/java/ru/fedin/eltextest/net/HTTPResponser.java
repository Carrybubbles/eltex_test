package ru.fedin.eltextest.net;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.Logger;

import java.util.Map;

public class HTTPResponser {
    private static final Logger log = Logger.getLogger(HTTPResponser.class);

    public static HttpResponse<String> doRequest(String url, Map<String,String> headers, Map<String,Object> parametrs) throws UnirestException {
        HttpResponse<String> jsonResponse = Unirest.get(url)
                .headers(headers)
                .queryString(parametrs)
                .asString();
        return jsonResponse;
    }
}
