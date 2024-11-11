package store.view.output;

import java.util.List;
import store.stock.domain.Stock;

public class ProductOutputView {

    public void printGreeting() {
        System.out.println("안녕하세요. W편의점입니다");
    }

    public void printProducts(List<Stock> stocks) {
        System.out.println("현재 보유하고 있는 상품입니다\n");

        stocks.forEach(stock -> {
            printIfPromotionExists(stock);
            printIfPromotionAbsent(stock);
        });
        System.out.println();
    }

    private static void printIfPromotionExists(Stock stock) {
        if (stock.hasPromotion()) {
            System.out.printf("- %s %,d원 %s %s%n", stock.getName(), stock.getPrice(),
                getQuantity(stock.getPromotionQuantity()), stock.getPromotionName());
            System.out.printf("- %s %,d원 %s%n", stock.getName(), stock.getPrice(),
                getQuantity(stock.getNoPromotionQuantity()));
        }
    }

    private static void printIfPromotionAbsent(Stock stock) {
        if (!stock.hasPromotion()) {
            System.out.printf("- %s %,d원 %s%n", stock.getName(), stock.getPrice(),
                getQuantity(stock.getNoPromotionQuantity()));
        }
    }

    private static String getQuantity(int quantity) {
        if (quantity != 0) {
            return String.valueOf(quantity) + "개";
        }
        return "재고 없음";
    }
}
