package com.example.cqrssamplecodes.modules.payment.config.infrastructure;

import com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.account.AccountApi;
import com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.account.AccountApiImpl;
import com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.credit.CreditApi;
import com.example.cqrssamplecodes.modules.payment.app.infrastructure.inmem.api.credit.InMemoryCreditApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class LocalConfiguration {
    @Bean
    public AccountApi accountApi() {
        return new AccountApiImpl();
    }

    @Bean
    public CreditApi creditApi() {
        return new InMemoryCreditApi();
    }
}
