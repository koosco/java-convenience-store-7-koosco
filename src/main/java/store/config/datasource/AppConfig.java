package store.config.datasource;

import store.config.membership.MembershipConfig;
import store.config.stock.StockConfig;
import store.config.view.ViewConfig;

public class AppConfig {

    private final DataSourceConfig dataSourceConfig;
    private final MembershipConfig membershipConfig;
    private final StockConfig stockConfig;
    private final ViewConfig viewConfig;

    public AppConfig() {
        this.dataSourceConfig = new DataSourceConfig();
        this.membershipConfig = new MembershipConfig();
        this.viewConfig = new ViewConfig();
        this.stockConfig = new StockConfig(dataSource(), view());
    }

    public DataSourceConfig dataSource() {
        return dataSourceConfig;
    }

    public MembershipConfig membership() {
        return membershipConfig;
    }

    public StockConfig stock() {
        return stockConfig;
    }

    public ViewConfig view() {
        return viewConfig;
    }
}
