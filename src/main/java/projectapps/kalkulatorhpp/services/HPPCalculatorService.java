package projectapps.kalkulatorhpp.services;

import projectapps.kalkulatorhpp.models.Ingredient;
import projectapps.kalkulatorhpp.models.Product;
import projectapps.kalkulatorhpp.models.RecipeItem;

import java.util.HashMap;
import java.util.Map;

//Anggota 4 (HPP Calculator)
public class HPPCalculatorService {

    // hitung total biaya bahan per batch
    public static double costPerBatch(Product p, InventoryService inv) {
        double sum = 0;
        for (RecipeItem r : p.recipe) {
            Ingredient ing = inv.findById(r.ingredientId);
            if (ing == null) continue;
            sum += r.amount * ing.pricePerUnit;
        }
        return sum;
    }

    // cost bahan per unit = costPerBatch / yieldPerBatch
    public static double costPerUnitFromBahan(Product p, InventoryService inv) {
        double cpb = costPerBatch(p, inv);
        if (p.yieldPerBatch == 0) return 0;
        return cpb / p.yieldPerBatch;
    }

    // biaya pekerja per unit
    public static double workerCostPerUnit(double workerRpPerHour, double timePerUnitMinutes) {
        return workerRpPerHour * (timePerUnitMinutes / 60.0);
    }

    // total HPP per unit
    public static double totalHPPPerUnit(Product p, InventoryService inv, double workerRpPerHour, double timePerUnitMinutes) {
        double bahan = costPerUnitFromBahan(p, inv);
        double worker = workerCostPerUnit(workerRpPerHour, timePerUnitMinutes);
        return bahan + worker;
    }

    // price with margin
    public static double priceWithMargin(double hppPerUnit, double marginPercent) {
        return hppPerUnit * (1 + marginPercent / 100.0);
    }

    // breakdownDetails
    public static Map<String, Double> breakdown(Product p, InventoryService inv, double workerRpPerHour, double timePerUnitMinutes, double marginPercent) {
        Map<String, Double> m = new HashMap<>();
        double bahan = costPerUnitFromBahan(p, inv);
        double worker = workerCostPerUnit(workerRpPerHour, timePerUnitMinutes);
        double total = bahan + worker;
        double price = priceWithMargin(total, marginPercent);
        m.put("bahan_per_unit", bahan);
        m.put("worker_per_unit", worker);
        m.put("total_hpp_per_unit", total);
        m.put("price_with_margin", price);
        return m;
    }
}
