package store.config.datasource;

public enum DataSourceConst {

    FILE_DELIMITER(","),
    PRODUCT_FILE_PATH("src/main/resources/products.md"),
    PROMOTION_FILE_PATH("/src/main/resources/promotions.md");

    private final String name;

    DataSourceConst(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}
