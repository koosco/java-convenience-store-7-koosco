package store.stock.app;

import java.util.List;
import store.common.exception.CustomException;
import store.common.exception.ErrorMessage;
import store.stock.app.dto.AdditionalMessage;
import store.stock.app.dto.OrderRequest;
import store.stock.domain.Order;
import store.stock.domain.Stock;
import store.stock.repository.StockRepository;

public class StockCommandService {

    private final StockQueryService queryService;
    private final StockRepository stockRepository;

    public StockCommandService(StockQueryService queryService, StockRepository stockRepository) {
        this.queryService = queryService;
        this.stockRepository = stockRepository;
    }

    public AdditionalMessage checkPromotion(OrderRequest dto) {
        Stock stock = queryService.findStock(dto.productName());
        return stock.checkPromotion(dto.amount());
    }

    public Order purchaseAll(List<Stock> stocks, List<OrderRequest> dtos) {
        Order order = new Order();
        for (OrderRequest dto : dtos) {
            purchase(stocks, order, dto);
        }

        return order;
    }

    private void purchase(List<Stock> stocks, Order order, OrderRequest dto) {
        Stock findStock = stocks.stream()
            .filter(stock -> stock.getName().equals(dto.productName()))
            .findFirst().orElseThrow(() -> new CustomException(ErrorMessage.NOT_FOUND_PRODUCT));
        findStock.purchase(order, dto.amount(), dto.applyPromotion());
        save(stocks);
    }

    public void save(List<Stock> stocks) {
        stockRepository.save(stocks);
    }
}
