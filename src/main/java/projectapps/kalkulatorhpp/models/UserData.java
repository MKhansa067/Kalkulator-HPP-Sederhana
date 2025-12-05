package projectapps.kalkulatorhpp.models;

import java.util.ArrayList;
import java.util.List;

//Anggota 5: Keira
public class UserData {
    public String username;
    public String password; // Untuk demo: disimpan plain (tidak aman). Ganti hashing di produksi.
    public List<Ingredient> ingredients = new ArrayList<>();
    public List<Product> products = new ArrayList<>();
    public List<SaleRecord> sales = new ArrayList<>();

    public UserData() {}

    public UserData(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
