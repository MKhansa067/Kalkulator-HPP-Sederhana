package projectapps.kalkulatorhpp.models;

import java.util.ArrayList;
import java.util.List;

//Anggota 2: Ibnu
public class Product {
    public String id;
    public String name;
    public double yieldPerBatch; // jumlah unit yang dihasilkan per batch
    public int timePerBatchMinutes; // menit / batch
    public List<RecipeItem> recipe = new ArrayList<>(); // daftar bahan dan jumlahnya
    public int totalSold = 0; // jumlah sold (statis)

    public Product() {}

    public Product(String id, String name, double yieldPerBatch, int timePerBatchMinutes) {
        this.id = id;
        this.name = name;
        this.yieldPerBatch = yieldPerBatch;
        this.timePerBatchMinutes = timePerBatchMinutes;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | yield/batch=%.2f | time/batch=%d min | sold=%d",
                id, name, yieldPerBatch, timePerBatchMinutes, totalSold);
    }
}
