package com.example.cqrssamplecodes.modules.payment.config.infrastructure;

import com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.account.AccountApi;
import com.example.cqrssamplecodes.modules.payment.app.infrastructure.api.credit.CreditApi;
import com.example.cqrssamplecodes.modules.payment.app.infrastructure.inmem.api.account.InMemoryAccountApi;
import com.example.cqrssamplecodes.modules.payment.app.infrastructure.inmem.api.credit.InMemoryCreditApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("inmem")
public class InMemoryConfiguration {
    @Bean
    public AccountApi accountApi() {
        return new InMemoryAccountApi();
    }

    @Bean
    public CreditApi creditApi() {
        return new InMemoryCreditApi();
    }
}
