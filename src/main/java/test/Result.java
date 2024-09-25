package test;


import java.util.Map;

/**
 * Result
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>11月 28, 2023</pre>
 */
public class Result<T> {

    private int code;

    private boolean success;

    private T data;

    private Map<String, String> extData;

    private String traceId;


    private Result(IResultCode resultCode, T data, Map<String, String> extData) {
        this.code = resultCode.getCode();
        this.data = data;
        this.extData = extData;
        this.success = this.code == 200;
        this.traceId = null;
    }

    public static <T> Result<T> success() {
        return Result.success(null);
    }

    public static <T> Result<T> success(T data) {
        return Result.success(data, null);
    }

    public static <T> Result<T> success(T data, Map<String, String> extData) {
        return new Result<>(ResultCode.SUCCESS, data, extData);
    }

    public static <T> Result<T> failedResult(IResultCode resultCode, T data) {
        return Result.failedResult(resultCode, data, null);

    }

    public static <T> Result<T> failedResult(IResultCode resultCode) {
        return Result.failedResult(resultCode, null, null);
    }

    public static <T> Result<T> failedResult(IResultCode resultCode, T data, Map<String, String> extData) {
        return new Result<>(resultCode, data, extData);
    }


    public interface IResultCode {

        int getCode();

        String getMessage();

    }

    public enum ResultCode implements IResultCode {

        SUCCESS(200, "SUCCESS"),

        FAIL(201, "FAIL");

        private int code;

        private String message;

        ResultCode(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return this.code;
        }

        public String getMessage() {
            return this.message;
        }


    }

    public static void main(String[] args) {
        String courseIds = "3300,3301,3302,3303,3304,3305,3306,3307,3308,3309,3310,3311,3312,3313,3314,3315";
        String[] split = courseIds.split(",");
        System.out.println(split.length);

    }






}
