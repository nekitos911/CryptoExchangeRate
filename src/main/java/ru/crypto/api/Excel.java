package ru.crypto.api;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import ru.crypto.enums.Crypto;
import ru.crypto.enums.Currency;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Excel {
    private File file = new File("Data.xls");

    public Excel() {
        try {
            if(file.createNewFile()) {
                create();
            }
        } catch (Exception ex) {}
    }

    private void create() {
        try {
            String filename = file.getName() ;
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");

            HSSFRow rowhead = sheet.createRow((short)0);

            for (int i = 0; i < Crypto.values().length; i++) {
                    rowhead.createCell(i).setCellValue(Crypto.values()[i].toString());
            }
            for (int i = 0; i < Currency.values().length; i++) {
                rowhead.createCell(i + Crypto.values().length).setCellValue(Currency.values()[i].toString());
            }

            rowhead.createCell(Crypto.values().length + Currency.values().length).setCellValue("Date");


            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();

        } catch ( Exception ex ) {

        }
    }

    public void update(String crypto, String currency, double cryptoValue, double currencyValue) {
        try {
            if(file.createNewFile()) {
                create();
            }
            FileInputStream inputStream = new FileInputStream(file);
            HSSFWorkbook wb = new HSSFWorkbook(inputStream);
            HSSFSheet worksheet = wb.getSheetAt(0);
            HSSFRow mainRow = worksheet.getRow(0);
            HSSFRow row = worksheet.createRow(worksheet.getLastRowNum() + 1);

            for (int i = 0; i < mainRow.getLastCellNum(); i++) {
                if(crypto.equals(mainRow.getCell(i).getStringCellValue())) {
                    row.createCell(i).setCellValue(cryptoValue);
                } else if(currency.equals(mainRow.getCell(i).getStringCellValue())) {
                    row.createCell(i).setCellValue(currencyValue);
                    worksheet.autoSizeColumn(i);
                }
            }

            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
            row.createCell(mainRow.getLastCellNum() - 1).setCellValue(formatForDateNow.format(dateNow));
            worksheet.autoSizeColumn(mainRow.getLastCellNum() - 1);

            inputStream.close();
            FileOutputStream output = new FileOutputStream(file);
            wb.write(output);
            output.close();

        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
    }
}
