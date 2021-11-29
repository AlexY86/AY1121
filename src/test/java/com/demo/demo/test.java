package com.demo.demo;

import com.demo.demo.exception.ExceptionHandler;
import com.demo.demo.model.Agreement;
import com.demo.demo.model.CheckOut;
import com.demo.demo.model.Tools;
import com.demo.demo.service.CheckOutService;
import com.demo.demo.service.CheckOutServiceImpl;
import com.demo.demo.utils.DateUtils;
import com.demo.demo.utils.NumberFormatUtils;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(value = BlockJUnit4ClassRunner.class)

public class test extends TestCase {

    private CheckOutService checkoutService;

    @Before
    public void setUp() {
        checkoutService = new CheckOutServiceImpl();
    }

    @Test(expected = ExceptionHandler.class)
    public void test1() {
        System.out.println("***** Test 1 *****");

        Tools jackhammer = Tools.JAKR;

        CheckOut checkout = CheckOut.Builder.newInstance()
                .setToolCode(jackhammer.getToolCode().getValue())
                .setRentalDayCount(5)
                .setDiscountPercent(101)
                .setCheckoutDate("1/1/21")
                .build();

        assertEquals("JAKR", checkout.getToolCode());
        assertEquals("01/01/21", DateUtils.convertDateToUSStandardFormat(checkout.getCheckoutDate()));
        assertEquals(5, checkout.getRentalDayCount());
        assertEquals("101%", NumberFormatUtils.formatPercentage(checkout.getDiscountPercent()));

    }

    @Test
    public void test2() {
        System.out.println("***** Test 2 *****");
        Tools ladder = Tools.LADW;
        CheckOut checkout = CheckOut.Builder.newInstance()
                .setToolCode(ladder.getToolCode().getValue())
                .setRentalDayCount(3)
                .setDiscountPercent(10)
                .setCheckoutDate("1/1/21")
                .build();

        Agreement rentalAgreement = checkoutService.processCheckout(checkout);

        assertEquals("LADW", checkout.getToolCode());
        assertEquals("01/02/21", DateUtils.convertDateToUSStandardFormat(checkout.getCheckoutDate()));
        assertEquals(3, checkout.getRentalDayCount());
        assertEquals("10%", NumberFormatUtils.formatPercentage(checkout.getDiscountPercent()));

        assertEquals("01/05/21",
                DateUtils.calculateDueDate(rentalAgreement.getCheckoutDate(), rentalAgreement.getNumberOfDaysRented()));
        assertEquals("$1.99", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDailyRentalCharge()));
        assertEquals(2, rentalAgreement.getChargeableDaysCount());
        assertEquals("$3.98", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getPreDiscountCharge()));
        assertEquals("10%", NumberFormatUtils.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$0.40", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDiscountAmount()));
        assertEquals("$3.58", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getFinalCharge()));
    }

    @Test
    public void test3() {
        System.out.println("***** Test 3 *****");
        Tools chainsaw = Tools.CHNS;

        CheckOut checkout = CheckOut.Builder.newInstance()
                .setToolCode(chainsaw.getToolCode().getValue())
                .setRentalDayCount(5)
                .setDiscountPercent(25)
                .setCheckoutDate("1/2/21")
                .build();

        Agreement rentalAgreement = checkoutService.processCheckout(checkout);

        assertEquals("CHNS", checkout.getToolCode());
        assertEquals("01/02/21", DateUtils.convertDateToUSStandardFormat(checkout.getCheckoutDate()));
        assertEquals(5, checkout.getRentalDayCount());
        assertEquals("25%", NumberFormatUtils.formatPercentage(checkout.getDiscountPercent()));

        assertEquals("01/07/21",
                DateUtils.calculateDueDate(rentalAgreement.getCheckoutDate(), rentalAgreement.getNumberOfDaysRented()));
        assertEquals("$1.49", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDailyRentalCharge()));
        assertEquals(3, rentalAgreement.getChargeableDaysCount());
        assertEquals("$4.47", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getPreDiscountCharge()));
        assertEquals("25%", NumberFormatUtils.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$1.12", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDiscountAmount()));
        assertEquals("$3.35", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getFinalCharge()));
    }

    @Test
    public void test4() {
        System.out.println("***** Test 4 *****");
        Tools jackhammer = Tools.JAKD;

        CheckOut checkout = CheckOut.Builder.newInstance()
                .setToolCode(jackhammer.getToolCode().getValue())
                .setRentalDayCount(6)
                .setDiscountPercent(0)
                .setCheckoutDate("1/3/21")
                .build();

        Agreement rentalAgreement = checkoutService.processCheckout(checkout);

        assertEquals("JAKD", checkout.getToolCode());
        assertEquals("01/03/21", DateUtils.convertDateToUSStandardFormat(checkout.getCheckoutDate()));
        assertEquals(6, checkout.getRentalDayCount());
        assertEquals("0%", NumberFormatUtils.formatPercentage(checkout.getDiscountPercent()));

        assertEquals("01/09/21",
                DateUtils.calculateDueDate(rentalAgreement.getCheckoutDate(), rentalAgreement.getNumberOfDaysRented()));
        assertEquals("$2.99", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDailyRentalCharge()));
        assertEquals(3, rentalAgreement.getChargeableDaysCount());
        assertEquals("$8.97", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getPreDiscountCharge()));
        assertEquals("0%", NumberFormatUtils.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$0.00", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDiscountAmount()));
        assertEquals("$8.97", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getFinalCharge()));

    }

    @Test
    public void test5() {
        System.out.println("***** Test 5 *****");

        Tools jackhammer = Tools.JAKR;
        CheckOut checkout = CheckOut.Builder.newInstance()
                .setToolCode(jackhammer.getToolCode().getValue())
                .setRentalDayCount(9)
                .setDiscountPercent(0)
                .setCheckoutDate("1/2/21")
                .build();

        Agreement rentalAgreement = checkoutService.processCheckout(checkout);

        assertEquals("JAKR", checkout.getToolCode());
        assertEquals("01/02/21", DateUtils.convertDateToUSStandardFormat(checkout.getCheckoutDate()));
        assertEquals(9, checkout.getRentalDayCount());
        assertEquals("0%", NumberFormatUtils.formatPercentage(checkout.getDiscountPercent()));

        assertEquals("01/11/21",
                DateUtils.calculateDueDate(rentalAgreement.getCheckoutDate(), rentalAgreement.getNumberOfDaysRented()));
        assertEquals("$2.99", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDailyRentalCharge()));
        assertEquals(5, rentalAgreement.getChargeableDaysCount());
        assertEquals("$14.95", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getPreDiscountCharge()));
        assertEquals("0%", NumberFormatUtils.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$0.00", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDiscountAmount()));
        assertEquals("$14.95", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getFinalCharge()));
    }

    @Test
    public void test6() {
        System.out.println("***** Test 6 *****");

        Tools jackhammer = Tools.JAKR;

        CheckOut checkout = CheckOut.Builder.newInstance()
                .setToolCode(jackhammer.getToolCode().getValue())
                .setRentalDayCount(4)
                .setDiscountPercent(50)
                .setCheckoutDate("1/2/21")
                .build();

        Agreement rentalAgreement = checkoutService.processCheckout(checkout);

        assertEquals("JAKR", checkout.getToolCode());
        assertEquals("01/02/21", DateUtils.convertDateToUSStandardFormat(checkout.getCheckoutDate()));
        assertEquals(4, checkout.getRentalDayCount());
        assertEquals("50%", NumberFormatUtils.formatPercentage(checkout.getDiscountPercent()));

        assertEquals("01/06/21",
                DateUtils.calculateDueDate(rentalAgreement.getCheckoutDate(), rentalAgreement.getNumberOfDaysRented()));
        assertEquals("$2.99", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDailyRentalCharge()));
        assertEquals(1, rentalAgreement.getChargeableDaysCount());
        assertEquals("$2.99", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getPreDiscountCharge()));
        assertEquals("50%", NumberFormatUtils.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$1.50", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getDiscountAmount()));
        assertEquals("$1.49", NumberFormatUtils.formatDoubleCurrency(rentalAgreement.getFinalCharge()));
    }

}
