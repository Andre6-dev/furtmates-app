package io.devandre.furtmates.users.boundary.response;

import lombok.Builder;

@Builder
public record ActivationCodeResponse(
        String message
) {
}
