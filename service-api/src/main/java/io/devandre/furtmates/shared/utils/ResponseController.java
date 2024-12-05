package io.devandre.furtmates.shared.utils;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class ResponseController {

    protected <T> ResponseEntity<ResponseApi<T>> ok(T data) {
        return ResponseEntity.ok(ResponseApi.success(data));
    }

    protected <T> ResponseEntity<ResponseApi<T>> ok(T data, String message) {
        return ResponseEntity.ok(ResponseApi.success(data, message));
    }

    // For POST requests to return void
    protected ResponseEntity<ResponseApi<Void>> created() {
        return ResponseEntity.status(201).body(ResponseApi.created());
    }

    protected <T> ResponseEntity<ResponseApi<T>> created(T data) {
        return ResponseEntity.status(201).body(ResponseApi.created(data));
    }

    protected ResponseEntity<ResponseApi<Void>> deleted() {
        return ResponseEntity.status(204).body(ResponseApi.deleted());
    }

    // For paginated responses
    protected <T> ResponseEntity<ResponseApi<List<T>>> withPagination(Page<T> page) {
        return ResponseEntity.ok(ResponseApi.withPagination(
                page.getContent(),
                PaginationInfo.of(page)
        ));
    }

    // For non-paginated list responses
    protected <T> ResponseEntity<ResponseApi<List<T>>> list(List<T> items) {
        return ResponseEntity.ok(ResponseApi.success(items, "Retrieved successfully"));
    }
}
