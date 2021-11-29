package com.demo.demo.model;

public class CheckOut {

    private String toolCode;
    private int rentalDayCount;
    private int discountPercent;
    private String checkoutDate;

    private CheckOut(Builder builder) {
        this.toolCode = builder.toolCode;
        this.rentalDayCount = builder.rentalDayCount;
        this.checkoutDate = builder.checkoutDate;
        this.discountPercent = builder.discountPercent;
    }

    public static class Builder {

        private String toolCode;
        private int rentalDayCount;
        private int discountPercent;
        private String checkoutDate;

        public static Builder newInstance() {
            return new Builder();
        }

        private Builder() {
        }

        public Builder setToolCode(String toolCode) {
            this.toolCode = toolCode;
            return this;
        }

        public Builder setRentalDayCount(int rentalDayCount) {
            if (rentalDayCount < 1)
                throw new IllegalArgumentException("You must rent the tool for a minimum of 1 day."
                        + " How many days you would like to rent the tool? ");
            this.rentalDayCount = rentalDayCount;
            return this;
        }

        public Builder setDiscountPercent(int discountPercent) {
            if (discountPercent < 0 || discountPercent > 100)
                throw new IllegalArgumentException(
                        "Discount percent must be between 0 and 100."
                                + "Enter the discount percent for the tool rental.");
            this.discountPercent = discountPercent;
            return this;
        }

        public Builder setCheckoutDate(String checkoutDate) {
            this.checkoutDate = checkoutDate;
            return this;
        }

        public CheckOut build() {
            return new CheckOut(this);
        }
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public String getToolCode() {
        return toolCode;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public int getRentalDayCount() {
        return rentalDayCount;
    }

}
