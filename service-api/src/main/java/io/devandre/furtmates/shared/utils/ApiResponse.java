package io.devandre.furtmates.shared.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private HttpStatus status;
    private String message;
    private T data;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime timestamp;
    private PaginationInfo pagination;

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>()
                .setStatus(HttpStatus.OK)
                .setMessage("Success")
                .setData(data);
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<T>()
                .setStatus(HttpStatus.OK)
                .setMessage(message)
                .setData(data);
    }

    public static <T> ApiResponse<T> created() {
        return new ApiResponse<T>()
                .setStatus(HttpStatus.CREATED)
                .setMessage("Created successfully");
    }

    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<T>()
                .setStatus(HttpStatus.CREATED)
                .setMessage("Created successfully")
                .setData(data);
    }

    public static ApiResponse<Void> deleted() {
        return new ApiResponse<Void>()
                .setStatus(HttpStatus.NO_CONTENT)
                .setMessage("Deleted successfully");
    }

    public static <T> ApiResponse<T> withPagination(T data, PaginationInfo pagination) {
        return new ApiResponse<T>()
                .setStatus(HttpStatus.OK)
                .setMessage("Success")
                .setData(data)
                .setPagination(pagination);
    }

}
