package store.stock.domain;

import java.time.LocalDate;

public class Promotion {

    private final String name;
    private final int buy;
    private final int get;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String name, int buy, int get, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Promotion(String[] parts) {
        this(parts[0],
            Integer.parseInt(parts[1]),
            Integer.parseInt(parts[2]),
            LocalDate.parse(parts[3]),
            LocalDate.parse(parts[4]));
    }

    public String getName() {
        return name;
    }
}
