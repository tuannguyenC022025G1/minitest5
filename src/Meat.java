import java.time.LocalDate;
import java.time.Period;

public class Meat extends Material {
    private double weight;

    public Meat(String id, String name, LocalDate manufacturingDate, int cost, double weight) {
        super(id, name, manufacturingDate, cost);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getAmount() {
        return getCost() * weight;
    }

    @Override
    public LocalDate getExpiryDate() {
        return getManufacturingDate().plusDays(7);
    }

    @Override
    public double getRealMoney() {
        LocalDate today = LocalDate.now();
        Period period = Period.between(today, getExpiryDate());
        int daysRemaining = period.getDays();

        double amount = getAmount();
        if (daysRemaining <= 5) {
            return amount * 0.7;
        } else {
            return amount * 0.9;
        }
    }
}