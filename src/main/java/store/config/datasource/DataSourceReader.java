package store.config.datasource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import store.stock.domain.Product;
import store.stock.domain.Promotion;

public class DataSourceReader {

    private static final int SKIP_HEADER_LINE_COUNT = 1;

    public List<Promotion> loadPromotions() {
        try {
            return readFromFile(DataSourceConst.PROMOTION_FILE_PATH.get()).stream()
                .skip(SKIP_HEADER_LINE_COUNT)
                .map(line -> line.split(DataSourceConst.FILE_DELIMITER.get()))
                .map(Promotion::new).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> loadProducts() {
        try {
            return readFromFile(DataSourceConst.PRODUCT_FILE_PATH.get()).stream()
                .skip(SKIP_HEADER_LINE_COUNT)
                .map(line -> line.split(DataSourceConst.FILE_DELIMITER.get()))
                .map(Product::new).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> readFromFile(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName));
    }
}
