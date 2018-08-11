package ru.crypto.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.crypto.enums.Crypto;

public class API {
    private final String MAIN_URL = "https://api.coinmarketcap.com/v2/";

    public String getCurrency(String crypto, String currency) {
        String url;
        url = "ticker/" + getCryptoId(Crypto.valueOf(crypto).name()) + "/?convert=" + currency;
        String input = Net.GET(MAIN_URL + url);

        try {
            JSONObject mainObject = new JSONObject(input);
            JSONObject dataObject = mainObject.getJSONObject("data");

            String price = dataObject.getJSONObject("quotes").
                    getJSONObject(currency).getString("price");
            return price;

        } catch (JSONException e) {

        }
        return null;
    }

    private String getCryptoId(String crypto) {
        String url = "listings/";
        String inputJSON = Net.GET(MAIN_URL + url);

        try {
            JSONObject mainObject = new JSONObject(inputJSON);
            JSONArray array = mainObject.getJSONArray("data");

            for (int i = 0; i < array.length(); i++)
            {
                String name = array.getJSONObject(i).getString("symbol");

                if(name.toLowerCase().equals(crypto.toLowerCase())) {
                    return array.getJSONObject(i).getString("id");
                }
            }

        } catch (JSONException e) {

        }
        return "-1";
    }
}
