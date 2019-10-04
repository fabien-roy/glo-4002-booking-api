package ca.ulaval.glo4002.booking.domainobjects.report;

public class InventoryItem {

    protected Long id;
    private Long quantity;
    private Long oxygenCategoryId;

    public InventoryItem(Long id, Long quantity, Long oxygenCategoryId) {
        this.id = id;
        this.quantity = quantity;
        this.oxygenCategoryId = oxygenCategoryId;
    }

    public Long getId() {
        return id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long getOxygenCategoryId() {
        return oxygenCategoryId;
    }
}
