package store.view.input;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import store.common.exception.CustomException;
import store.common.exception.ErrorMessage;
import store.stock.app.dto.OrderRequest;
import store.stock.domain.Stock;

public class ProductInputView {

    private static final Pattern INPUT_PATTERN = Pattern.compile("\\[[^]]+-\\d+](,\\[[^]]+-\\d+])*");

    private final Converter converter = new Converter();
    private final Validator validator = new Validator();

    public List<OrderRequest> purchaseProducts(List<Stock> stocks) {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String products = Console.readLine();
        validator.validateFormat(products);
        return convertToRequest(stocks, products);
    }

    private List<OrderRequest> convertToRequest(List<Stock> stocks, String products) {
        List<OrderRequest> dtos = converter.toRequest(products);
        validator.validateProducts(stocks, dtos);
        return dtos;
    }

    private static class Validator {

        public void validateFormat(String command) {
            if (!INPUT_PATTERN.matcher(command).matches()) {
                throw new CustomException(ErrorMessage.INVALID_FORMAT_ERROR);
            }
        }

        public void validateProducts(List<Stock> stocks, List<OrderRequest> dtos) {
            for (OrderRequest dto : dtos) {
                Stock findStock = validateName(stocks, dto);
                validateQuantity(dto, findStock);
            }
        }

        private static Stock validateName(List<Stock> stocks, OrderRequest dto) {
            return stocks.stream().filter(stock -> stock.getName().equals(dto.productName())).findFirst()
                .orElseThrow(() -> new CustomException(ErrorMessage.PRODUCT_NOT_FOUND_ERROR));
        }

        private static void validateQuantity(OrderRequest dto, Stock findStock) {
            if (findStock.getQuantity() < dto.amount()) {
                throw new CustomException(ErrorMessage.INVALID_QUANTITY_ERROR);
            }
        }
    }

    private static class Converter {

        private final static String PRODUCT_AMOUNT_DELIMITER = "-";
        private final static String PRODUCT_TOKEN_DELIMITER = ",";
        private final static int PRODUCT_INPUT_PREFIX_INDEX = 1;
        private final static int PRODUCT_INPUT_SUFFIX_INDEX = 1;


        public List<OrderRequest> toRequest(String products) {
            return Arrays.stream(products.split(PRODUCT_TOKEN_DELIMITER))
                .map(item -> item.substring(PRODUCT_INPUT_PREFIX_INDEX, item.length() - PRODUCT_INPUT_SUFFIX_INDEX))
                .map(item -> {
                    String[] parts = item.split(PRODUCT_AMOUNT_DELIMITER);
                    // 상품 이름, 상품 개수
                    return new OrderRequest(parts[0], Integer.parseInt(parts[1]), false);
                }).toList();
        }
    }
}
