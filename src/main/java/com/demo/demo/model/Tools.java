package com.demo.demo.model;

import com.demo.demo.common.ToolConstant;

public enum Tools {

    LADW(ToolCode.LADW, ToolType.LADDER, ToolBrand.WERNER),
    CHNS(ToolCode.CHNS, ToolType.CHAINSAW, ToolBrand.STIHL),
    JAKR(ToolCode.JAKR, ToolType.JACKHAMMER, ToolBrand.RIDGID),
    JAKD(ToolCode.JAKD, ToolType.JACKHAMMER, ToolBrand.DEWALT);

    private ToolCode toolCode;

    private ToolType toolType;

    private ToolBrand toolBrand;

    Tools(ToolCode toolCode, ToolType toolType, ToolBrand toolBrand) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
    }

    public ToolCode getToolCode() {
        return toolCode;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public ToolBrand getToolBrand() {
        return toolBrand;
    }

    public static Tools getTool(String code) {
        switch (code) {
            case "LADW":
                return LADW;
            case "CHNS":
                return CHNS;
            case "JAKR":
                return JAKR;
            case "JAKD":
                return JAKD;
            default:
                return null;
        }
    }

    public enum ToolBrand {

        WERNER(ToolConstant.WERNER),
        STIHL(ToolConstant.STIHL),
        RIDGID(ToolConstant.RIDGID),
        DEWALT(ToolConstant.DEWALT);

        private String value;

        public String getValue() {
            return value;
        }

        ToolBrand(String value) {
            this.value = value;
        }
    }

    public enum ToolCode {

        LADW(ToolConstant.LADW),
        CHNS(ToolConstant.CHNS),
        JAKR(ToolConstant.JAKR),
        JAKD(ToolConstant.JAKD);

        private String value;

        public String getValue() {
            return value;
        }

        ToolCode(String value) {
            this.value = value;
        }
    }

    public enum ToolType {

        LADDER(ToolConstant.LADDER, ToolConstant.RENTAL_CHARGE_LADDER, ToolConstant.YES_CHARGE,
                ToolConstant.YES_CHARGE, ToolConstant.NO_CHARGE),
        CHAINSAW(ToolConstant.CHAINSAW, ToolConstant.RENTAL_CHARGE_CHAINSAW, ToolConstant.YES_CHARGE,
                ToolConstant.NO_CHARGE, ToolConstant.YES_CHARGE),
        JACKHAMMER(ToolConstant.JACKHAMMER, ToolConstant.RENTAL_CHARGE_JACKHAMMER, ToolConstant.YES_CHARGE,
                ToolConstant.NO_CHARGE, ToolConstant.NO_CHARGE);

        private String value;

        private double dailyCharge;

        private String weekdayCharge;

        private String weekendCharge;

        private String holidayCharge;

        public String getValue() {
            return value;
        }

        public double getDailyCharge() {
            return dailyCharge;
        }

        public String isWeekdayCharge() {
            return weekdayCharge;
        }

        public String isWeekendCharge() {
            return weekendCharge;
        }

        public String isHolidayCharge() {
            return holidayCharge;
        }

        ToolType(String value, double dailyCharge, String weekdayCharge, String weekendCharge, String holidayCharge) {
            this.value = value;
            this.dailyCharge = dailyCharge;
            this.weekdayCharge = weekdayCharge;
            this.weekendCharge = weekendCharge;
            this.holidayCharge = holidayCharge;
        }
    }

}
