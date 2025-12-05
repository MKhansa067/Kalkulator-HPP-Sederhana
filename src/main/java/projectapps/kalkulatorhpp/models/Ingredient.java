package projectapps.kalkulatorhpp.models;

//Anggota 1
public class Ingredient {
    public String id;
    public String name;
    public String unit; // g, kg, ml, l, pcs, pack
    public double pricePerUnit; // harga per unit (sesuai unit)
    public double stock; // jumlah stok dalam unit yang sama

    public Ingredient() {}

    public Ingredient(String id, String name, String unit, double pricePerUnit, double stock) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | unit=%s | price/unit=%.2f | stock=%.2f",
                id, name, unit, pricePerUnit, stock);
    }
}
