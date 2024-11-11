package store.stock.app;

import store.stock.domain.Order;
import store.stock.domain.membership.Membership;

public class MembershipService {

    private final Membership membership;

    public MembershipService(Membership membership) {
        this.membership = membership;
    }

    public void apply(Order order) {
        order.getProductDtos().stream()
            .filter(dto -> !dto.applyPromotion())
            .forEach(dto -> order.applyMembershipDiscount(getDiscount(dto.price() * dto.amount())));
    }

    public int getDiscount(int price) {
        return membership.discount(price);
    }
}
