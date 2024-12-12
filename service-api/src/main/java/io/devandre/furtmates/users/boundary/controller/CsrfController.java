package io.devandre.furtmates.users.boundary.controller;

import io.devandre.furtmates.shared.utils.ResponseApi;
import io.devandre.furtmates.shared.utils.ResponseController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsrfController extends ResponseController {

    @GetMapping("/api/v1/csrf-token")
    public ResponseEntity<ResponseApi<CsrfToken>> csrf(HttpServletRequest request) {
        return ok((CsrfToken) request.getAttribute(CsrfToken.class.getName()));
    }
}
