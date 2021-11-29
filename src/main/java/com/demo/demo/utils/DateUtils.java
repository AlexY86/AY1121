package com.demo.demo.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import com.demo.demo.exception.ExceptionHandler;

public class DateUtils {

    public static LocalDate parseInputDate(String date) {
        LocalDate formattedDate = null;
        try {
            DateTimeFormatter formatterSingleDigits = DateTimeFormatter.ofPattern("M/d/yy")
                    .withLocale(Locale.US);
            DateTimeFormatter formatterDoubleDigits = DateTimeFormatter.ofPattern("MM/dd/yy")
                    .withLocale(Locale.US);

            DateTimeFormatter[] formats = { formatterSingleDigits,
                    formatterDoubleDigits };
            boolean invalidFormat = false;

            for (DateTimeFormatter format : formats) {
                try {
                    formattedDate = LocalDate.parse(date, format);
                    invalidFormat = false;
                    break;
                } catch (DateTimeParseException e) {
                    invalidFormat = true;
                    throw new DateTimeParseException("Failed to parse date [" + date + "] with format [" + format + "]",
                            date, 0);
                }
            }

            if (invalidFormat) {
                throw new Exception("Failed to parse date [" + date + "]");
            }
            return formattedDate;

        } catch (Exception e) {
            throw new ExceptionHandler("Date is invalid format");

        }
    }

    public static String convertDateToUSStandardFormat(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        Date date = null;
        try {
            date = (Date) formatter.parse(dateStr);
        } catch (ParseException e) {
            throw new ExceptionHandler("Failed to parse date");
        } catch (Exception e) {
            throw new ExceptionHandler("Invalid input, Failed to parse date");
        }
        return formatter.format(date);
    }

    public static String calculateDueDate(String checkoutDate, int numberOfDaysRented) {
        LocalDate dueDate = null;
        String formattedDate = "";
        String convertedDate = "";

        String dateRegex = "\\d{1}/\\d{2}/\\d{2}";

        if (checkoutDate.matches(dateRegex)) {
            convertedDate = DateUtils.convertDateToUSStandardFormat(checkoutDate);

        } else {
            convertedDate = checkoutDate;
        }

        try {
            dueDate = parseInputDate(convertedDate).plusDays(numberOfDaysRented);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
            formattedDate = dueDate.format(formatter);
        } catch (Exception e) {
            throw new ExceptionHandler("Invalid due date");

        }
        return formattedDate;
    }
}
