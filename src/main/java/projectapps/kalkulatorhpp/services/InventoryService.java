package projectapps.kalkulatorhpp.services;

import projectapps.kalkulatorhpp.models.Ingredient;
import projectapps.kalkulatorhpp.models.UserData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

//Anggota 1 (Inventory)
public class InventoryService {
    private final UserData userData;

    public InventoryService(UserData userData) {
        this.userData = userData;
    }

    public Ingredient addIngredient(String name, String unit, double pricePerUnit, double stock) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        Ingredient ing = new Ingredient(id, name, unit, pricePerUnit, stock);
        userData.ingredients.add(ing);
        return ing;
    }

    public boolean editIngredient(String id, String name, String unit, double pricePerUnit, double stock) {
        Optional<Ingredient> o = userData.ingredients.stream().filter(i -> i.id.equals(id)).findFirst();
        if (o.isEmpty()) return false;
        Ingredient ing = o.get();
        ing.name = name;
        ing.unit = unit;
        ing.pricePerUnit = pricePerUnit;
        ing.stock = stock;
        return true;
    }

    public List<Ingredient> listIngredients() {
        return userData.ingredients;
    }

    public List<Ingredient> searchIngredients(String q) {
        String lower = q.toLowerCase();
        return userData.ingredients.stream()
                .filter(i -> i.name.toLowerCase().contains(lower) || i.id.equals(q))
                .collect(Collectors.toList());
    }

    public Ingredient findById(String id) {
        return userData.ingredients.stream().filter(i -> i.id.equals(id)).findFirst().orElse(null);
    }
}
