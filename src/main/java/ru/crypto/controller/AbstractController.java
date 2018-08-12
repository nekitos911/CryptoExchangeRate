package ru.crypto.controller;

public abstract class AbstractController {

    public abstract String getConverted(String crypto, String currency);

    public abstract void saveData(String crypto, String currency, double cryptoValue, double currencyValue);
}
