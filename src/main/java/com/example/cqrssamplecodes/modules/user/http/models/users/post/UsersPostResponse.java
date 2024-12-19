package com.example.cqrssamplecodes.modules.user.http.models.users.post;

import java.util.UUID;

public record UsersPostResponse(
        UUID id,
        String name
) {
}
