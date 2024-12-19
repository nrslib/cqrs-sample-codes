package com.example.cqrssamplecodes.modules.payment.http.models.users.post;

import java.util.UUID;

public record UsersPostResponse(
        UUID id,
        String name
) {
}
