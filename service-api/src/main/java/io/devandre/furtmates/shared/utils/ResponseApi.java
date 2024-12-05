package io.devandre.furtmates.shared.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Standard response API")
public class ResponseApi<T> {

    @Schema(description = "HTTP status code", example = "200")
    private HttpStatus status;

    @Schema(description = "Response message", example = "Success")
    private String message;

    @Schema(description = "Response data")
    private T data;

    @Schema(description = "Timestamp of the response", example = "2021-09-01 12:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime timestamp;

    @Schema(description = "Pagination information")
    private PaginationInfo pagination;

    public ResponseApi() {
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ResponseApi<T> success(T data) {
        return new ResponseApi<T>()
                .setStatus(HttpStatus.OK)
                .setMessage("Success")
                .setData(data);
    }

    public static <T> ResponseApi<T> success(T data, String message) {
        return new ResponseApi<T>()
                .setStatus(HttpStatus.OK)
                .setMessage(message)
                .setData(data);
    }

    public static <T> ResponseApi<T> created() {
        return new ResponseApi<T>()
                .setStatus(HttpStatus.CREATED)
                .setMessage("Created successfully");
    }

    public static <T> ResponseApi<T> created(T data) {
        return new ResponseApi<T>()
                .setStatus(HttpStatus.CREATED)
                .setMessage("Created successfully")
                .setData(data);
    }

    public static ResponseApi<Void> deleted() {
        return new ResponseApi<Void>()
                .setStatus(HttpStatus.NO_CONTENT)
                .setMessage("Deleted successfully");
    }

    public static <T> ResponseApi<T> withPagination(T data, PaginationInfo pagination) {
        return new ResponseApi<T>()
                .setStatus(HttpStatus.OK)
                .setMessage("Success")
                .setData(data)
                .setPagination(pagination);
    }

}
