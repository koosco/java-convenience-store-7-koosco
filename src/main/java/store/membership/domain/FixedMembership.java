package store.membership.domain;

public class FixedMembership implements Membership {

    private final static int DISCOUNT_RATE = 30;
    private final static int MAX_DISCOUNT_AMOUNT = 8000;

    public int discount(int price) {
        return Math.min(price * DISCOUNT_RATE / 100, MAX_DISCOUNT_AMOUNT);
    }
}
