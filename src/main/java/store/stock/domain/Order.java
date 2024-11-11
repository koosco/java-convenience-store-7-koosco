package store.stock.domain;

import java.util.ArrayList;
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
        return productDtos;
    }

    public List<FreeProductDto> getFreeProductDtos() {
        return freeProductDtos;
    }

    public int getMembershipDiscountPrice() {
        return membershipDiscountPrice;
    }

    public int getTotalAmount() {
        return productDtos.stream().map(ProductDto::amount).mapToInt(Integer::intValue).sum();
    }

    public int getTotalPrice() {
        return productDtos.stream().map(ProductDto::price).mapToInt(Integer::intValue).sum();
    }

    public int getPromotionDiscountPrice() {
        return freeProductDtos.stream().map(dto -> dto.price() * dto.amount()).mapToInt(Integer::intValue).sum();
    }

    public int getMembershipDiscount() {
        return membershipDiscountPrice;
    }
}
