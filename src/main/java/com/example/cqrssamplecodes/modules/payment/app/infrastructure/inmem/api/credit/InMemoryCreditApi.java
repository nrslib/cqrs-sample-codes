package com.example.cqrssamplecodes.modules.payment.app.infrastructure.inmem.api.credit;

import com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.credit.CreditApi;
import com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.credit.makepayment.CreditMakePaymentRequest;
import com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.credit.makepayment.CreditMakePaymentResponse;

public class InMemoryCreditApi implements CreditApi {
    @Override
    public CreditMakePaymentResponse makePayment(CreditMakePaymentRequest request) {
        return new CreditMakePaymentResponse(true, 200);
    }
}
