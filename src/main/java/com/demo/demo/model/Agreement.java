package com.demo.demo.model;

import com.demo.demo.utils.DateUtils;
import com.demo.demo.utils.NumberFormatUtils;

public class Agreement {

    private String toolCode;

    private String toolType;

    private final String toolBrand;

    private int numberOfDaysRented;

    private String checkoutDate;

    private String dueDate;

    private double dailyRentalCharge;

    private int chargeableDaysCount;

    private double preDiscountCharge;

    private int discountPercent;

    private double discountAmount;

    private double finalCharge;

    private Agreement(Builder builder) {
        this.toolCode = builder.toolCode;
        this.toolType = builder.toolType;
        this.toolBrand = builder.toolBrand;
        this.numberOfDaysRented = builder.numberOfDaysRented;
        this.checkoutDate = builder.checkoutDate;
        this.dueDate = builder.dueDate;
        this.dailyRentalCharge = builder.dailyRentalCharge;
        this.chargeableDaysCount = builder.chargeableDaysCount;
        this.preDiscountCharge = builder.preDiscountCharge;
        this.discountPercent = builder.discountPercent;
        this.discountAmount = builder.discountAmount;
        this.finalCharge = builder.finalCharge;
    }

    public static class Builder {

        private String toolCode;

        private String toolType;

        private String toolBrand;

        private int numberOfDaysRented;

        private String checkoutDate;

        private String dueDate;

        private double dailyRentalCharge;

        private int chargeableDaysCount;

        private double preDiscountCharge;

        private int discountPercent;

        private double discountAmount;

        private double finalCharge;

        public static Builder newInstance() {
            return new Builder();
        }

        private Builder() {
        }

        public Builder setToolCode(String toolCode) {
            this.toolCode = toolCode;
            return this;
        }

        public Builder setToolType(String toolType) {
            this.toolType = toolType;
            return this;
        }

        public Builder setToolBrand(String toolBrand) {
            this.toolBrand = toolBrand;
            return this;
        }

        public Builder setNumberOfDaysRented(int numberOfDaysRented) {
            this.numberOfDaysRented = numberOfDaysRented;
            return this;
        }

        public Builder setCheckoutDate(String checkoutDate) {
            this.checkoutDate = checkoutDate;
            return this;
        }

        public Builder setDueDate(String dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder setDailyRentalCharge(double dailyRentalCharge) {
            this.dailyRentalCharge = dailyRentalCharge;
            return this;
        }

        public Builder setChargeableDaysCount(int chargeableDaysCount) {
            this.chargeableDaysCount = chargeableDaysCount;
            return this;
        }

        public Builder setPreDiscountCharge(double preDiscountCharge) {
            this.preDiscountCharge = preDiscountCharge;
            return this;
        }

        public Builder setDiscountPercent(int discountPercent) {
            this.discountPercent = discountPercent;
            return this;
        }

        public Builder setDiscountAmount(double discountAmount) {
            this.discountAmount = discountAmount;
            return this;
        }

        public Builder setFinalCharge(double finalCharge) {
            this.finalCharge = finalCharge;
            return this;
        }

        public Agreement build() {
            return new Agreement(this);
        }
    }

    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public int getNumberOfDaysRented() {
        return numberOfDaysRented;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public int getChargeableDaysCount() {
        return chargeableDaysCount;
    }

    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getFinalCharge() {
        return finalCharge;
    }

    public void printRentalAgreementDetails() throws Exception {
        System.out.println("Tool code: " + getToolCode() + "\n" +
                "Tool type: " + getToolType() + "\n" +
                "Tool brand: " + getToolBrand() + "\n" +
                "Rental days: " + getNumberOfDaysRented() + "\n" +
                "Checkout date: " + DateUtils.convertDateToUSStandardFormat(getCheckoutDate()) + "\n" +
                "Due date: " + DateUtils.convertDateToUSStandardFormat(getDueDate()) + "\n" +
                "Daily charge: " + NumberFormatUtils.formatDoubleCurrency(getDailyRentalCharge()) + "\n" +
                "Charge days: " + getChargeableDaysCount() + "\n" +
                "Pre-discount charge: " + NumberFormatUtils.formatDoubleCurrency(getPreDiscountCharge()) + "\n" +
                "Discount %: " + NumberFormatUtils.formatPercentage(getDiscountPercent()) + "\n" +
                "Discount amount: " + NumberFormatUtils.formatDoubleCurrency(getDiscountAmount()) + "\n" +
                "Final charge: " + NumberFormatUtils.formatDoubleCurrency(getFinalCharge()) + "\n");
    }
}
