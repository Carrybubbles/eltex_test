package ru.fedin.eltextest.weather.exceptions;

public class WeatherResponseException extends Exception {
    public WeatherResponseException() { super(); }
    public WeatherResponseException(String message) { super(message); }
    public WeatherResponseException(String message, Throwable cause) { super(message, cause); }
    public WeatherResponseException(Throwable cause) { super(cause); }
}
