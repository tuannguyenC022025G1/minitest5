import java.time.LocalDate;
import java.time.Period;

public class CrispyFlour extends Material {
    private int quantity;

    public CrispyFlour(String id, String name, LocalDate manufacturingDate, int cost, int quantity) {
        super(id, name, manufacturingDate, cost);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double getAmount() {
        return quantity * getCost();
    }

    @Override
    public LocalDate getExpiryDate() {
        return getManufacturingDate().plusYears(1);
    }

    @Override
    public double getRealMoney() {
        LocalDate today = LocalDate.now();
        Period period = Period.between(today, getExpiryDate());
        int monthsRemaining = period.getYears() * 12 + period.getMonths();

        double amount = getAmount();
        if (monthsRemaining <= 2) {
            return amount * 0.6;
        } else if (monthsRemaining <= 4) {
            return amount * 0.8;
        } else {
            return amount * 0.95;
        }
    }
}