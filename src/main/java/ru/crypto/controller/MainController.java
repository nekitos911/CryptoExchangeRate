package ru.crypto.controller;

import ru.crypto.api.API;

public class MainController extends AbstractController {
    private API api = new API();


    public String getConverted(String crypto, String currency) {
        return api.getCurrency(crypto,currency);
    }
}
