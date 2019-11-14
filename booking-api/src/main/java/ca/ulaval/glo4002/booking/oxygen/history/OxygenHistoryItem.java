package ca.ulaval.glo4002.booking.oxygen.history;

public class OxygenHistoryItem {

    private Integer qtyOxygenTankBought;
    private Integer qtyOxygenTankMade;
    private Integer qtyWaterUsed;
    private Integer qtyCandlesUsed;

    public OxygenHistoryItem() {
        this.qtyOxygenTankBought = 0;
        this.qtyOxygenTankMade = 0;
        this.qtyWaterUsed = 0;
        this.qtyCandlesUsed = 0;
    }

    public Integer getQtyOxygenTankBought() {
        return qtyOxygenTankBought;
    }

    public Integer getQtyOxygenTankMade() {
        return qtyOxygenTankMade;
    }

    public Integer getQtyWaterUsed() {
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

    public void addWaterUsed(Integer qtyWaterUsed) {
        this.qtyWaterUsed += qtyWaterUsed;
    }

    public void addCandleUsed(Integer qtyCandlesUsed) {
        this.qtyCandlesUsed += qtyCandlesUsed;
    }
}
