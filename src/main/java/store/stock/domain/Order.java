package store.stock.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import store.stock.app.dto.FreeProductDto;
import store.stock.app.dto.ProductDto;

public class Order {

    private final List<ProductDto> productDtos = new ArrayList<>();
    private final List<FreeProductDto> freeProductDtos = new ArrayList<>();
    private int membershipDiscountPrice;

    public Order() {
        this.membershipDiscountPrice = 0;
    }

    public List<ProductDto> getProductDtos() {
        return productDtos.stream()
            .sorted(Comparator.comparingInt(ProductDto::price))
            .toList();
    }

    public List<FreeProductDto> getFreeProductDtos() {
        return freeProductDtos.stream()
            .sorted(Comparator.comparingInt(FreeProductDto::price))
            .toList();
    }

    public int getTotalAmount() {
        return productDtos.stream().map(ProductDto::amount).mapToInt(Integer::intValue).sum();
    }

    public int getTotalPrice() {
        return productDtos.stream().map(dto -> dto.price() * dto.amount()).mapToInt(Integer::intValue).sum();
    }

    public int getPromotionDiscountPrice() {
        return freeProductDtos.stream().map(dto -> dto.price() * dto.amount()).mapToInt(Integer::intValue).sum();
    }

    public int getMembershipDiscountPrice() {
        return membershipDiscountPrice;
    }

    public int getResultPrice() {
        return getTotalPrice() - getPromotionDiscountPrice() - getMembershipDiscountPrice();
    }

    public void applyMembershipDiscount(int price) {
        membershipDiscountPrice += price;
    }

    public void addProduct(ProductDto dto) {
        productDtos.add(dto);
    }

    public void addFreeProduct(FreeProductDto dto) {
        freeProductDtos.add(dto);
    }
}
