package com.demo.demo.service;

import com.demo.demo.model.Agreement;
import com.demo.demo.model.CheckOut;

public interface CheckOutService {
    Agreement processCheckout(CheckOut checkout);
}
