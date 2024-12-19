package com.example.cqrssamplecodes.modules.user.app.infrastructure.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<UserDataModel, String>, JpaSpecificationExecutor<UserDataModel> {
}
