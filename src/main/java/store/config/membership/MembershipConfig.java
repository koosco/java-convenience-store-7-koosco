package store.config.membership;

import store.stock.app.MembershipService;
import store.stock.domain.membership.FixedMembership;
import store.stock.domain.membership.Membership;

public class MembershipConfig {

    public MembershipService membershipService() {
        return new MembershipService(membership());
    }

    public Membership membership() {
        return new FixedMembership();
    }
}
