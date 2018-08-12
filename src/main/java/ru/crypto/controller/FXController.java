package ru.crypto.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.crypto.enums.Crypto;
import ru.crypto.enums.Currency;

public class FXController {
    private MainController controller = new MainController();

    @FXML
    public void initialize() {
        cBoxCrypto.setValue(Crypto.BTC.name());
        cBoxCurrency.setValue(Currency.RUB.name());
        String[] items = new String[Crypto.values().length];
        for (int i = 0; i < items.length; i++) {
            items[i] = Crypto.values()[i].toString();
        }

        ObservableList<String> options = FXCollections.observableArrayList(items);
        cBoxCrypto.setItems(options);

        items = new String[Currency.values().length];
        for (int i = 0; i < items.length; i++) {
            items[i] = Currency.values()[i].toString();
        }

        options = FXCollections.observableArrayList(items);
        cBoxCurrency.setItems(options);
    }

    @FXML
    private ComboBox<String> cBoxCrypto;

    @FXML
    private ComboBox<String> cBoxCurrency;

    @FXML
    private TextField tFieldCrypto;

    @FXML
    private TextField tFieldCurrency;

    public void onClickCryptoComboBox() {
        onEditCryptoValue();
    }

    public void onClickCurrencyComboBox() {
        onEditCurrencyValue();
    }

    public void onEditCryptoValue() {
       editField(tFieldCrypto, tFieldCurrency);
    }

    public void onEditCurrencyValue() {
        editField(tFieldCurrency, tFieldCrypto);
    }

    public void OnClickSaveDataButton() {
        String cryptovalue = tFieldCrypto.getText();
        String currencyValue = tFieldCurrency.getText();
        if(cryptovalue.isEmpty() || currencyValue.isEmpty()) return;

        if(cryptovalue.contains(","))
        {
            cryptovalue = cryptovalue.replace(",",".");
        }
        if(currencyValue.contains(","))
        {
            currencyValue = currencyValue.replace(",",".");
        }

        double currency = Double.parseDouble(currencyValue);
        double crypto = Double.parseDouble(cryptovalue);

        controller.saveData(cBoxCrypto.getValue(), cBoxCurrency.getValue(), crypto, currency);
    }

    private void editField(TextField... field) {
        if(!field[0].getText().isEmpty()) {
            String value = field[0].getText();
            if(value.contains(","))
            {
                value = value.replace(",",".");
            }
            double valueCount = Double.parseDouble(value);
            double price = Double.parseDouble(controller.getConverted(cBoxCrypto.getValue(), cBoxCurrency.getValue()));
            if(field[1] == tFieldCurrency) {
                field[1].setText(String.format("%.2f", valueCount * price));
            } else if(field[1] == tFieldCrypto) {
                field[1].setText(String.format("%.2f", valueCount / price));
            }
        }
    }
}
