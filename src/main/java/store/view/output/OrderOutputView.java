package store.view.output;


import store.stock.domain.Order;

public class OrderOutputView {

    public void printReceipt(Order order) {
        System.out.println("\n===========W 편의점=============");
        printProducts(order);
        printPromotionProducts(order);
    }

    private static void printProducts(Order order) {
        System.out.printf("%-13s%-8s%-9s\n", "상품명", "수량", "금액");
        order.getProductDtos()
            .forEach(dto -> System.out.printf("%-14s%-9d%,-9d\n", dto.name(), dto.amount(), dto.price()));
    }

    private static void printPromotionProducts(Order order) {
        System.out.println("===========증   정=============");
        order.getFreeProductDtos()
            .forEach(dto -> System.out.printf("%-11s%4d\n", dto.name(), dto.amount()));
    }
}
