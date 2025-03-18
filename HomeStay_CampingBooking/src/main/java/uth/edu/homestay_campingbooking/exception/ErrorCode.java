package uth.edu.homestay_campingbooking.exception;

public enum ErrorCode {
    ID_OR_NAME_NOT_EXISTED(2000, "not existed or empty"),
    EXISTED(2001, "existed"),
    PRICE_INVALID(1005, "Price must be greater than 0"),
    INVALID_KEY(8888, "Invalid message key"),
    USERNAME_INVALID(1002, "Username must be at least 4 character"),
    PASSWORD_INVALID(1003, "Password must be at least 8 character"),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception"),
    USER_EXISTED(1001, "User existed");
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
