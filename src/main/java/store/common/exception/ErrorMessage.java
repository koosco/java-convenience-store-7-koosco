package store.common.exception;

public enum ErrorMessage {

    INVALID_FORMAT_ERROR("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    INVALID_QUANTITY_ERROR("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    PRODUCT_NOT_FOUND_ERROR("존재하지 않는 상품입니다. 다시 입력해 주세요."),
    GENERAL_INPUT_ERROR("잘못된 입력입니다. 다시 입력해 주세요."),
    NOT_FOUND_PRODUCT("상품을 찾을 수 없습니다"),
    FILE_NOT_FOUND("파일을 찾을 수 없습니다");


    private static final String PREFIX = "[ERROR] ";

    private final String message;

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }

    public String get() {
        return message;
    }
}
