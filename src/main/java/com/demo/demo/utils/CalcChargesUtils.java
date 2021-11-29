package com.demo.demo.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;

import com.demo.demo.common.ToolConstant;
import com.demo.demo.model.CheckOut;
import com.demo.demo.model.Tools;

public class CalcChargesUtils {

    public static int calculateChargeableDaysCount(Tools tool, CheckOut checkout) throws Exception {

        int chargeableDaysCount = 0;

        try {
            LocalDate date = DateUtils.parseInputDate(checkout.getCheckoutDate()).plusDays(1);
            LocalDate dueDate = DateUtils.parseInputDate(checkout.getCheckoutDate())
                    .plusDays(checkout.getRentalDayCount());

            while (!date.isEqual(dueDate.plusDays(1))) {
                if (tool.getToolType().isHolidayCharge().equals(ToolConstant.NO_CHARGE)
                        && tool.getToolType().isWeekendCharge().equals(ToolConstant.NO_CHARGE)) {
                    if (date.getMonthValue() == 7 && date.getDayOfMonth() == 4) {
                        if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                            chargeableDaysCount--;
                        }
                    }

                    else if (date.getMonthValue() == 9 && date.getDayOfWeek() == DayOfWeek.MONDAY
                            && date.getDayOfMonth() < 8) {

                    }

                    else if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {

                    } else {
                        chargeableDaysCount++;
                    }
                }

                else if (tool.getToolType().isWeekendCharge().equals(ToolConstant.NO_CHARGE)
                        && tool.getToolType().isHolidayCharge().equals(ToolConstant.YES_CHARGE)) {
                    if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    } else {
                        chargeableDaysCount++;
                    }

                }

                else if (tool.getToolType().isHolidayCharge().equals(ToolConstant.NO_CHARGE)
                        && tool.getToolType().isWeekendCharge().equals(ToolConstant.YES_CHARGE)) {
                    if (date.getMonthValue() == 7 && date.getDayOfMonth() == 4) {
                        if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                            if (DateUtils.parseInputDate(checkout.getCheckoutDate()).isEqual(date.minusDays(1))) {
                                chargeableDaysCount++;
                            }
                        } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                            if (LocalDate.parse(DateUtils.calculateDueDate(checkout.getCheckoutDate(),
                                    checkout.getRentalDayCount())).isEqual(date)) {
                                chargeableDaysCount++;
                            }
                        }
                    }

                    else if (date.getMonthValue() == 9 && date.getDayOfWeek() == DayOfWeek.MONDAY
                            && date.getDayOfMonth() < 8) {
                    }

                    else {
                        chargeableDaysCount++;
                    }
                }
                date = date.plusDays(1);
            }
            return chargeableDaysCount;
        } catch (Exception e) {
            throw new Exception("Failed to count number of days charged");
        }
    }

    public static double calculatePreDiscountCharge(int chargeableDaysCount, double dailyRentalCharge) {
        double preDiscountCharge = new BigDecimal(chargeableDaysCount * dailyRentalCharge)
                .setScale(2, RoundingMode.HALF_DOWN).doubleValue();
        return preDiscountCharge;
    }

    public static double calculateDiscountAmount(int discountPercent, double preDiscountCharge) {
        double discountAmount = new BigDecimal(discountPercent * 0.01f * preDiscountCharge)
                .setScale(2, RoundingMode.HALF_UP).doubleValue();
        return discountAmount;
    }

    public static double calculateFinalCharge(double preDiscountCharge, double discountAmount) throws Exception {
        double finalCharge = 0;
        try {
            finalCharge = preDiscountCharge - discountAmount;
        } catch (Exception e) {
            throw new Exception("Failed to calculate final charge");
        }
        return finalCharge;

    }
}
