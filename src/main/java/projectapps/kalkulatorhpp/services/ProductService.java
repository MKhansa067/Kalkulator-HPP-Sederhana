package projectapps.kalkulatorhpp.services;

import projectapps.kalkulatorhpp.models.Product;
import projectapps.kalkulatorhpp.models.RecipeItem;
import projectapps.kalkulatorhpp.models.UserData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

//Anggota 2: Ibnu (Product)
public class ProductService {
    private final UserData userData;

    public ProductService(UserData userData) {
        this.userData = userData;
    }

    public Product addProduct(String name, double yieldPerBatch, int timePerBatchMinutes, List<RecipeItem> recipe) {
        String id = UUID.randomUUID().toString().substring(0,8);
        Product p = new Product(id, name, yieldPerBatch, timePerBatchMinutes);
        p.recipe = recipe;
        userData.products.add(p);
        return p;
    }

    public boolean editProduct(String id, String name, double yieldPerBatch, int timePerBatchMinutes, List<RecipeItem> recipe) {
        Optional<Product> o = userData.products.stream().filter(p -> p.id.equals(id)).findFirst();
        if (o.isEmpty()) return false;
        Product p = o.get();
        p.name = name;
        p.yieldPerBatch = yieldPerBatch;
        p.timePerBatchMinutes = timePerBatchMinutes;
        p.recipe = recipe;
        return true;
    }

    public List<Product> listProducts() {
        return userData.products;
    }

    public List<Product> searchProducts(String q) {
        String lower = q.toLowerCase();
        return userData.products.stream()
                .filter(p -> p.name.toLowerCase().contains(lower) || p.id.equals(q))
                .collect(Collectors.toList());
    }

    public Product findById(String id) {
        return userData.products.stream().filter(p -> p.id.equals(id)).findFirst().orElse(null);
    }
}
