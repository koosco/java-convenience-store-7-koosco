package store.membership.ui;

import store.membership.app.MembershipService;
import store.stock.domain.Order;

public class MembershipController {

    private final MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }


    public void applyMembership(Order order) {
        membershipService.apply(order);
    }
}
