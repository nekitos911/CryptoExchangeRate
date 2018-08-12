package ru.crypto.controller;

import ru.crypto.api.API;
import ru.crypto.api.Excel;

public class MainController extends AbstractController {
    private API api = new API();
    private Excel excel = new Excel();

    public String getConverted(String crypto, String currency) {
        return api.getCurrency(crypto,currency);
    }

    public void saveData(String crypto, String currency, double cryptoValue, double currencyValue) {
        excel.update(crypto, currency, cryptoValue, currencyValue);
    }
}
