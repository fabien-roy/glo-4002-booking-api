package ca.ulaval.glo4002.booking.domain.oxygen;

import java.time.LocalDate;

public class OxygenHistoryItem {

    private LocalDate date; // TODO : Is date really useful?
    private Integer qtyOxygenTankBought;
    private Integer qtyWaterUsed;
    private Integer qtyCandlesUsed;
    private Integer qtyOxygenTankMade;

    public OxygenHistoryItem(LocalDate date) {
        this.date = date;
        this.qtyOxygenTankBought = 0;
        this.qtyWaterUsed = 0;
        this.qtyCandlesUsed = 0;
        this.qtyOxygenTankMade = 0;
    }

    public LocalDate getDate() {
        return  this.date;
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
