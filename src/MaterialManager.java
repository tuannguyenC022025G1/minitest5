import java.time.LocalDate;

public class MaterialManager {
    private Material[] materials;
    private int size;
    private static final int maxSize = 10;

    public MaterialManager() {
        materials = new Material[maxSize];
        size = 0;
        initializeMaterials();
    }

    private void initializeMaterials() {
        addMaterial(new CrispyFlour("CF1", "Flour A", LocalDate.of(2024, 10, 1), 100, 10));
        addMaterial(new CrispyFlour("CF2", "Flour B", LocalDate.of(2024, 12, 1), 120, 8));
        addMaterial(new CrispyFlour("CF3", "Flour C", LocalDate.of(2025, 1, 1), 90, 15));
        addMaterial(new CrispyFlour("CF4", "Flour D", LocalDate.of(2025, 2, 1), 110, 12));
        addMaterial(new CrispyFlour("CF5", "Flour E", LocalDate.of(2025, 3, 1), 130, 5));

        addMaterial(new Meat("M1", "Beef", LocalDate.of(2025, 4, 10), 200, 2.5));
        addMaterial(new Meat("M2", "Pork", LocalDate.of(2025, 4, 11), 180, 3.0));
        addMaterial(new Meat("M3", "Chicken", LocalDate.of(2025, 4, 12), 150, 1.5));
        addMaterial(new Meat("M4", "Lamb", LocalDate.of(2025, 4, 13), 220, 2.0));
        addMaterial(new Meat("M5", "Fish", LocalDate.of(2025, 4, 14), 170, 1.8));
    }

    private void resize() {
        Material[] newMaterials = new Material[materials.length * 2];
        for (int i = 0; i < size; i++) {
            newMaterials[i] = materials[i];
        }
        materials = newMaterials;
    }

    public void addMaterial(Material material) {
        if (size == materials.length) {
            resize();
        }
        materials[size] = material;
        size++;
    }

    public void updateMaterial(String id, Material updatedMaterial) {
        for (int i = 0; i < size; i++) {
            if (materials[i].getId().equals(id)) {
                materials[i] = updatedMaterial;
                return;
            }
        }
    }

    public void deleteMaterial(String id) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (materials[i].getId().equals(id)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < size - 1; i++) {
                materials[i] = materials[i + 1];
            }
            materials[size - 1] = null;
            size--;
        }
    }

    public double getTotalAmount() {
        double total = 0;
        for (int i = 0; i < size; i++) {
            total += materials[i].getAmount();
        }
        return total;
    }

  
    public double getTotalRealMoney() {
        double total = 0;
        for (int i = 0; i < size; i++) {
            total += materials[i].getRealMoney();
        }
        return total;
    }


    public double getDiscountDifference() {
        return getTotalAmount() - getTotalRealMoney();
    }


    public void printMaterials() {
        for (int i = 0; i < size; i++) {
            System.out.println("ID: " + materials[i].getId() + ", Name: " + materials[i].getName() +
                    ", Amount: " + materials[i].getAmount() + ", Real Money: " + materials[i].getRealMoney() +
                    ", Expiry Date: " + materials[i].getExpiryDate());
        }
    }

    public static void main(String[] args) {
        MaterialManager manager = new MaterialManager();
        System.out.println("Materials list:");
        manager.printMaterials();

        System.out.println("\nTotal cost without discount: " + manager.getTotalAmount());
        System.out.println("Total cost with discount: " + manager.getTotalRealMoney());
        System.out.println("Discount difference: " + manager.getDiscountDifference());
    }
}