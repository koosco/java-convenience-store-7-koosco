package store.config.membership;

import store.membership.app.MembershipService;
import store.membership.domain.FixedMembership;
import store.membership.domain.Membership;
import store.membership.ui.MembershipController;

public class MembershipConfig {

    public MembershipController membershipController() {
        return new MembershipController(membershipService());
    }

    public MembershipService membershipService() {
        return new MembershipService(membership());
    }

    public Membership membership() {
        return new FixedMembership();
    }
}
