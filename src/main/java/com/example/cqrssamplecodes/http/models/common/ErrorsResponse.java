package com.example.cqrssamplecodes.http.models.common;

import java.util.ArrayList;
import java.util.List;

public record ErrorsResponse(List<ErrorModel> errors) {
    public static ErrorsResponse apply(Object code, String message) {
        var errors = new ArrayList<ErrorModel>();
        errors.add(new ErrorModel(code, message));

        return new ErrorsResponse(
                errors
        );
    }
}
