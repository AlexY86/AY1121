package com.demo.demo.utils;

import java.text.NumberFormat;
import java.util.Locale;

import com.demo.demo.exception.ExceptionHandler;

public class NumberFormatUtils {

    public static String formatDoubleCurrency(double dollarAmount) {
        NumberFormat numberFormat = null;
        try {
            Locale currentLocale = new Locale("en", "US");
            numberFormat = NumberFormat.getCurrencyInstance(currentLocale);
            numberFormat.setMaximumFractionDigits(2);
            return numberFormat.format(dollarAmount);

        } catch (Exception e) {
            throw new ExceptionHandler("Invalid dollar amount");

        }
    }

    public static String formatPercentage(int percentageAmount) {
        String formattedPercentage = "";
        try {
            formattedPercentage = String.format("%d%%", percentageAmount);
        } catch (Exception e) {
            throw new ExceptionHandler("Invalid dollar amount");
        }
        return formattedPercentage;

    }
}
