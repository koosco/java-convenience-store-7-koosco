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
}
