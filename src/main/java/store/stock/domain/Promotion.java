package store.stock.domain;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import store.stock.app.dto.AdditionalMessage;

public class Promotion {

    private static final int END_DATE_INCLUSIVE = 1;

    private final String name;
    private final int buy;
    private final int get;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String name, int buy, int get, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Promotion(String[] parts) {
        this(parts[0],
            Integer.parseInt(parts[1]),
            Integer.parseInt(parts[2]),
            LocalDate.parse(parts[3]),
            LocalDate.parse(parts[4]));
    }

    public boolean isApplicable(int promotionQuantity) {
        LocalDateTime now = DateTimes.now();
        return !now.isBefore(startDate.atStartOfDay())
            && !now.isAfter(endDate.atTime(LocalTime.MAX))
            && promotionQuantity >= buy + get;
    }

    public String getName() {
        return name;
    }

    public AdditionalMessage apply(String name, int promotionStock, int promotionAmount, int normalAmount) {
        int promotionCount = promotionAmount / (buy + get);
        int restAmount = promotionAmount % (buy + get);
        if (isPromotionStockLeft(promotionStock, promotionAmount) && satisfyPromotionCondition(restAmount)) {
            return new AdditionalMessage(name, null, PromotionMessage.CAN_ADD_ADDITIONAL_PRODUCT, true);
        }

        if (restAmount == 0) {
            return new AdditionalMessage(name, null, PromotionMessage.NO_ADDITIONAL_MESSAGE, true);
        }
        if (promotionCount != 0) {
            return new AdditionalMessage(name, restAmount + normalAmount, PromotionMessage.CAN_NOT_GET_PROMOTION, true);
        }
        return new AdditionalMessage(name, restAmount + normalAmount, PromotionMessage.CAN_NOT_GET_PROMOTION, false);
    }

    private static boolean isPromotionStockLeft(int promotionStock, int promotionAmount) {
        // 프로모션을 적용할 수 있는 프로모션 재고가 남은 경우
        return promotionStock >= promotionAmount + 1;
    }


    private boolean satisfyPromotionCondition(int restAmount) {
        // 구매 개수가 프로모션 조건을 만족하는 경우
        return restAmount == buy;
    }

    public int getPromotion(int amount) {
        return amount / (buy + get);
    }
}
