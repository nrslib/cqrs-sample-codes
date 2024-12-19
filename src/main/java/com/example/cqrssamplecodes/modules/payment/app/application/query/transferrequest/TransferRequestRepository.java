package com.example.cqrssamplecodes.modules.payment.app.application.query.transferrequest;

import com.example.cqrssamplecodes.modules.payment.app.application.query.transferrequest.datamodel.TransferRequestDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRequestRepository extends JpaRepository<TransferRequestDataModel, String> {
}
