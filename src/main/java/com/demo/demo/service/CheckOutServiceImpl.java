package com.demo.demo.service;

import com.demo.demo.exception.ExceptionHandler;
import com.demo.demo.model.Agreement;
import com.demo.demo.model.CheckOut;
import com.demo.demo.model.Tools;
import com.demo.demo.utils.DateUtils;
import com.demo.demo.utils.CalcChargesUtils;

public class CheckOutServiceImpl implements CheckOutService {

        @Override

        public Agreement processCheckout(CheckOut checkout) {

                Agreement rentalAgreement = null;

                try {
                        Tools tool = Tools.getTool(checkout.getToolCode());
                        rentalAgreement = Agreement.Builder.newInstance()
                                        .setToolCode(checkout.getToolCode())
                                        .setToolType(tool.getToolType().getValue())
                                        .setToolBrand(tool.getToolBrand().getValue())
                                        .setNumberOfDaysRented(checkout.getRentalDayCount())
                                        .setCheckoutDate(checkout.getCheckoutDate())
                                        .setDueDate(DateUtils.calculateDueDate(checkout.getCheckoutDate(),
                                                        checkout.getRentalDayCount()))
                                        .setDailyRentalCharge(tool.getToolType().getDailyCharge())
                                        .setChargeableDaysCount(
                                                        CalcChargesUtils.calculateChargeableDaysCount(tool, checkout))
                                        .setPreDiscountCharge(CalcChargesUtils.calculatePreDiscountCharge(
                                                        CalcChargesUtils.calculateChargeableDaysCount(tool,
                                                                        checkout),
                                                        tool.getToolType().getDailyCharge()))
                                        .setDiscountPercent(checkout.getDiscountPercent())
                                        .setDiscountAmount(CalcChargesUtils.calculateDiscountAmount(
                                                        checkout.getDiscountPercent(),
                                                        CalcChargesUtils.calculatePreDiscountCharge(
                                                                        CalcChargesUtils
                                                                                        .calculateChargeableDaysCount(
                                                                                                        tool, checkout),
                                                                        tool.getToolType().getDailyCharge())))
                                        .setFinalCharge(CalcChargesUtils.calculateFinalCharge(
                                                        CalcChargesUtils.calculatePreDiscountCharge(
                                                                        CalcChargesUtils
                                                                                        .calculateChargeableDaysCount(
                                                                                                        tool, checkout),
                                                                        tool.getToolType().getDailyCharge()),
                                                        CalcChargesUtils.calculateDiscountAmount(
                                                                        checkout.getDiscountPercent(),
                                                                        CalcChargesUtils
                                                                                        .calculatePreDiscountCharge(
                                                                                                        CalcChargesUtils
                                                                                                                        .calculateChargeableDaysCount(
                                                                                                                                        tool,
                                                                                                                                        checkout),
                                                                                                        tool.getToolType()
                                                                                                                        .getDailyCharge()))))
                                        .build();
                        rentalAgreement.printRentalAgreementDetails();
                } catch (Exception e) {
                        throw new ExceptionHandler("Unable to create Agreement instance");

                }
                return rentalAgreement;

        }
}
