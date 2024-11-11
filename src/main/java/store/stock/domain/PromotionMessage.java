package store.stock.domain;

public enum PromotionMessage {

    CAN_ADD_ADDITIONAL_PRODUCT("현재 {%s}은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)"),
    CAN_NOT_GET_PROMOTION("현재 {%s} {%d}개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)"),
    NO_ADDITIONAL_MESSAGE("");

    private final String prompt;

    PromotionMessage(String prompt) {
        this.prompt = prompt;
    }

    public String format(String productName, int amount) {
        return String.format(prompt, productName, amount);
    }
}
