package store.view.output;


import store.stock.domain.Order;

public class OrderOutputView {

    public void printReceipt(Order order) {
        System.out.println("\n===========W 편의점=============");
        printProducts(order);
        printPromotionProducts(order);
        printResult(order);
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

    private static void printResult(Order order) {
        System.out.println("==============================");
        System.out.printf("%-14s%-6d%,8d\n", "총구매액", order.getTotalAmount(), order.getTotalPrice());
        System.out.printf("%-20s %7s\n", "행사할인", "-" + String.format("%,d", order.getPromotionDiscountPrice()));
        System.out.printf("%-20s %6s\n", "멤버십할인", "-" + String.format("%,d", order.getMembershipDiscountPrice()));
        System.out.printf("%-20s %7s\n", "내실돈", String.format("%,d", order.getResultPrice()));
    }
}
