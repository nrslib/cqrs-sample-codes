package com.example.cqrssamplecodes.modules.payment.app.application.query.transferrequest;

import com.example.cqrssamplecodes.modules.payment.app.application.domain.models.transferrequest.events.TransferRequestStarted;
import com.example.cqrssamplecodes.modules.payment.app.application.query.transferrequest.datamodel.TransferRequestDataModel;
import com.example.cqrssamplecodes.modules.payment.app.application.query.transferrequest.datamodel.TransferRequestStatus;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.springframework.stereotype.Component;

@Component
public record TransferRequestProjection(TransferRequestRepository repository) {
    @ResetHandler
    public void reset() {
        repository.deleteAll();
    }

    @EventHandler
    public void on(TransferRequestStarted event) {
        var data = new TransferRequestDataModel();
        data.setTransferRequestId(event.transferRequestId().asString());
        data.setStatus(TransferRequestStatus.CREATED);

        repository.save(data);
    }
}
