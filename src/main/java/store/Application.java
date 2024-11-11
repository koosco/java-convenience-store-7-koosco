package store;

import store.config.datasource.AppConfig;

public class Application {
    public static void main(String[] args) {
        AppConfig config = new AppConfig();
        new Client(config.view().inputView(),
            config.view().outputView(),
            config.stock().stockController(),
            config.membership().membershipController(),
            config.stock().additionalMessageHandler()).run();
    }
}
