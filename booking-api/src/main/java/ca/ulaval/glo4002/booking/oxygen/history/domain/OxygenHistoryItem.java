package ca.ulaval.glo4002.booking.oxygen.history.domain;

public class OxygenHistoryItem {

    private Integer qtyOxygenTankBought;
    private Integer qtyOxygenTankMade;
    private Double qtyWaterUsed; // TODO : Check why this is a Double
    private Integer qtyCandlesUsed;

    public OxygenHistoryItem() {
        this.qtyOxygenTankBought = 0;
        this.qtyOxygenTankMade = 0;
        this.qtyWaterUsed = 0D;
        this.qtyCandlesUsed = 0;
    }

    public Integer getQtyOxygenTankBought() {
        return qtyOxygenTankBought;
    }

    public Integer getQtyOxygenTankMade() {
        return qtyOxygenTankMade;
    }

    public Double getQtyWaterUsed() {
        return qtyWaterUsed;
    }

    public Integer getQtyCandlesUsed() {
        return qtyCandlesUsed;
    }

    public void addTanksBought(Integer qtyOxygenTankBought) {
        this.qtyOxygenTankBought += qtyOxygenTankBought;
    }

    public void addTanksMade(Integer qtyOxygenTankMade) {
        this.qtyOxygenTankMade += qtyOxygenTankMade;
    }

    public void addWaterUsed(Double qtyWaterUsed) {
        this.qtyWaterUsed += qtyWaterUsed;
    }

    public void addCandleUsed(Integer qtyCandlesUsed) {
        this.qtyCandlesUsed += qtyCandlesUsed;
    }
}
