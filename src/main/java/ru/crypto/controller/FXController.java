package ru.crypto.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.crypto.enums.Crypto;
import ru.crypto.enums.Currency;

public class FXController {
    private MainController controller = new MainController();

    @FXML
    public void initialize() {
        cBox1.setValue(Crypto.BTC.name());
        cBox2.setValue(Currency.RUB.name());

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        Crypto.BTC.toString(),
                        Crypto.ETH.toString(),
                        Crypto.LTC.toString()
                );
        cBox1.setItems(options);

        options =
                FXCollections.observableArrayList(
                        Currency.RUB.toString(),
                        Currency.EUR.toString(),
                        Currency.USD.toString()
                );
        cBox2.setItems(options);
    }

    @FXML
    private ComboBox<String> cBox2;

    @FXML
    private ComboBox<String> cBox1;

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

    private void editField(TextField... field) {
        if(!field[0].getText().isEmpty()) {
            String value = field[0].getText();
            if(value.contains(","))
            {
                value = value.replace(",",".");
            }
            double valueCount = Double.parseDouble(value);
            double price = Double.parseDouble(controller.getConverted(cBox1.getValue(), cBox2.getValue()));
            if(field[1] == tFieldCurrency) {
                field[1].setText(String.format("%.2f", valueCount * price));
            } else if(field[1] == tFieldCrypto) {
                field[1].setText(String.format("%.2f", valueCount / price));
            }
        }
    }
}
