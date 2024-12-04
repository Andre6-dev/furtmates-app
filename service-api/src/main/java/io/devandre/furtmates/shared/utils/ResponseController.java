package io.devandre.furtmates.shared.utils;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class ResponseController {

    protected <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    protected <T> ResponseEntity<ApiResponse<T>> ok(T data, String message) {
        return ResponseEntity.ok(ApiResponse.success(data, message));
    }

    // For POST requests to return void
    protected ResponseEntity<ApiResponse<Void>> created() {
        return ResponseEntity.status(201).body(ApiResponse.created());
    }

    protected <T> ResponseEntity<ApiResponse<T>> created(T data) {
        return ResponseEntity.status(201).body(ApiResponse.created(data));
    }

    protected ResponseEntity<ApiResponse<Void>> deleted() {
        return ResponseEntity.status(204).body(ApiResponse.deleted());
    }

    // For paginated responses
    protected <T> ResponseEntity<ApiResponse<List<T>>> withPagination(Page<T> page) {
        return ResponseEntity.ok(ApiResponse.withPagination(
                page.getContent(),
                PaginationInfo.of(page)
        ));
    }

    // For non-paginated list responses
    protected <T> ResponseEntity<ApiResponse<List<T>>> list(List<T> items) {
        return ResponseEntity.ok(ApiResponse.success(items, "Retrieved successfully"));
    }
}
