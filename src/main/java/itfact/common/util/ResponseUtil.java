package itfact.common.util;

import itfact.common.response.dto.ResponseDTO;
import itfact.common.response.enums.ResponseCode;
import itfact.common.response.enums.ResponseStatus;

public class ResponseUtil {

    public static <T> ResponseDTO<T> SUCCESS(ResponseCode responseCode, T data) {
        return new ResponseDTO<>(ResponseStatus.SUCCESS, responseCode.getMessage(), responseCode.getCode(), data);
    }

    public static <T> ResponseDTO<T> SUCCESS(ResponseCode responseCode) {
        return new ResponseDTO<>(ResponseStatus.SUCCESS, responseCode.getMessage(), responseCode.getCode(), null);
    }

    public static <T> ResponseDTO<T> SUCCESS(String message, T data) {
        return new ResponseDTO<>(ResponseStatus.SUCCESS, message,null ,data);
    }

    public static <T> ResponseDTO<T> SUCCESS(String message) {
        return new ResponseDTO<>(ResponseStatus.SUCCESS, message,null, null);
    }

    public static <T> ResponseDTO<T> ERROR(ResponseCode responseCode) {
        return new ResponseDTO<>(ResponseStatus.ERROR, responseCode.getMessage(), responseCode.getCode(), null);
    }

    public static <T> ResponseDTO<T> ERROR(ResponseCode responseCode, T data) {
        return new ResponseDTO<>(ResponseStatus.ERROR, responseCode.getMessage(), responseCode.getCode(), data);
    }

    public static <T> ResponseDTO<T> ERROR(String message, T data) {
        return new ResponseDTO<>(ResponseStatus.ERROR, message,null, data);
    }

    public static <T> ResponseDTO<T> ERROR(String message) {
        return new ResponseDTO<>(ResponseStatus.ERROR, message,null, null);
    }

    public static <T> ResponseDTO<T> FAIL(ResponseCode responseCode) {
        return new ResponseDTO<>(ResponseStatus.FAIL, responseCode.getMessage(),responseCode.getCode(), null);
    }

    public static <T> ResponseDTO<T> FAIL(ResponseCode responseCode, T data) {
        return new ResponseDTO<>(ResponseStatus.FAIL, responseCode.getMessage(),responseCode.getCode(), data);
    }

    public static <T> ResponseDTO<T> FAIL(String message, T data) {
        return new ResponseDTO<>(ResponseStatus.FAIL, message,null, data);
    }

    public static <T> ResponseDTO<T> FAIL(String message) {
        return new ResponseDTO<>(ResponseStatus.FAIL, message,null, null);
    }
}
