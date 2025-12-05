package projectapps.kalkulatorhpp.models;

//Anggota 4: Alghi
public class RecipeItem {
    public String ingredientId;
    public double amount; // amount in the ingredient's unit

    public RecipeItem() {}

    public RecipeItem(String ingredientId, double amount) {
        this.ingredientId = ingredientId;
        this.amount = amount;
    }
}
