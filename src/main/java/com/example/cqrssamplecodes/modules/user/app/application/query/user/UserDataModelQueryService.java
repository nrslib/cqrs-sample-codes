package com.example.cqrssamplecodes.modules.user.app.application.query.user;


import com.example.cqrssamplecodes.modules.user.app.application.query.user.queries.FindUserDataModel;
import com.example.cqrssamplecodes.modules.user.app.application.query.user.queries.FindUserDataModelResult;
import com.example.cqrssamplecodes.modules.user.app.application.query.user.queries.GetUserDataModel;
import com.example.cqrssamplecodes.modules.user.app.application.query.user.queries.GetUserDataModelResult;
import com.example.cqrssamplecodes.modules.user.app.infrastructure.jpa.user.UserDataModel;
import com.example.cqrssamplecodes.modules.user.app.infrastructure.jpa.user.UserDataRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record UserDataModelQueryService(UserDataRepository userDataRepository) {
    @QueryHandler
    public GetUserDataModelResult handle(GetUserDataModel query) {
        return userDataRepository.findById(query.userId().toString())
                .map(it -> new GetUserDataModelResult(Optional.of(it)))
                .orElseGet(() -> new GetUserDataModelResult(Optional.empty()));
    }

    @QueryHandler
    public FindUserDataModelResult handle(FindUserDataModel query) {
        var spec = Specification.where(
                equalUserName(query.userName())
        );
        var users = userDataRepository.findAll(spec, query.pageable());

        return new FindUserDataModelResult(users);
    }

    private Specification<UserDataModel> equalUserName(String userName) {
        if (userName == null) {
            return null;
        }
        return (root, _, cb) ->
                cb.equal(root.get("name"), userName);

    }
}
