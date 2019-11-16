package ca.ulaval.glo4002.booking.profits;

public class ProfitsDto {

    private Float in;
    private Float out;
    private Float profit;

    public ProfitsDto(float in, float out, float profit) {
        this.in = in;
        this.out = out;
        this.profit = profit;
    }

    public float getIn() {
        return in;
    }

    public float getOut() {
        return out;
    }

    public float getProfit() {
        return profit;
    }
}
