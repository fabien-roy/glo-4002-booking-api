package ca.ulaval.glo4002.booking.domain.oxygen;

public class OxygenHistoryItem {

    private Integer qtyOxygenTankBought;
    private Integer qtyWaterUsed;
    private Integer qtyCandlesUsed;
    private Integer qtyOxygenTankMade;

    public OxygenHistoryItem() {
        this.qtyOxygenTankBought = 0;
        this.qtyWaterUsed = 0;
        this.qtyCandlesUsed = 0;
        this.qtyOxygenTankMade = 0;
    }

    public Integer getQtyOxygenTankBought() {
        return qtyOxygenTankBought;
    }

    public Integer getQtyWaterUsed() {
        return qtyWaterUsed;
    }

    public Integer getQtyCandlesUsed() {
        return qtyCandlesUsed;
    }

    public Integer getQtyOxygenTankMade() {
        return qtyOxygenTankMade;
    }

    public void addTankBought(Integer qtyOxygenTankBought) {
        this.qtyOxygenTankBought += qtyOxygenTankBought;
    }

    public void addWaterUsed(Integer qtyWaterUsed) {
        this.qtyWaterUsed += qtyWaterUsed;
    }

    public void addCandleUsed(Integer qtyCandlesUsed) {
        this.qtyCandlesUsed += qtyCandlesUsed;
    }

    public void addTankMade(Integer qtyOxygenTankMade) {
        this.qtyOxygenTankMade += qtyOxygenTankMade;
    }

}
