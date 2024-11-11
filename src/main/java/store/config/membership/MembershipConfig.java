package store.config.membership;

import store.membership.app.MembershipService;
import store.membership.domain.FixedMembership;
import store.membership.domain.Membership;

public class MembershipConfig {

    public MembershipService membershipService() {
        return new MembershipService(membership());
    }

    public Membership membership() {
        return new FixedMembership();
    }
}
