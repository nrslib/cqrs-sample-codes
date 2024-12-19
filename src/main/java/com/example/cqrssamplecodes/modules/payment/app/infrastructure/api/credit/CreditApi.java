package com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.credit;

import com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.credit.makepayment.CreditMakePaymentRequest;
import com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.credit.makepayment.CreditMakePaymentResponse;

public interface CreditApi {
    CreditMakePaymentResponse makePayment(CreditMakePaymentRequest request);
}
