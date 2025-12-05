package projectapps.kalkulatorhpp;

import projectapps.kalkulatorhpp.auth.AuthService;
import projectapps.kalkulatorhpp.models.*;
import projectapps.kalkulatorhpp.storage.StorageService;
import projectapps.kalkulatorhpp.services.*;
import projectapps.kalkulatorhpp.util.ConsoleUtil;

import java.util.ArrayList;
import java.util.List;

//Anggota 7: Khansa (Main flow)
public class KalkulatorHPP {
    private final StorageService storage = new StorageService();
    private final AuthService auth = new AuthService(storage);
    private UserData currentUser = null;

    public static void main(String[] args) {
        KalkulatorHPP app = new KalkulatorHPP();
        app.run();
    }

    public void run() {
        while (true) {
            if (currentUser == null) {
                showHome();
            } else {
                showDashboardMenu();
            }
        }
    }

    private void showHome() {
        System.out.println("\n== Kalkulator HPP - Beranda ==");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        String choice = ConsoleUtil.readLine("Pilih> ");
        switch (choice) {
            case "1" -> doLogin();
            case "2" -> doRegister();
            case "3" -> {
                System.out.println("Keluar. Sampai jumpa!");
                System.exit(0);
            }
            default -> System.out.println("Pilihan tidak valid");
        }
    }

    private void doRegister() {
        String username = ConsoleUtil.readLine("Username: ");
        String password = ConsoleUtil.readLine("Password: ");
        if (auth.register(username, password)) {
            System.out.println("Registrasi sukses. Silakan login.");
        } else {
            System.out.println("Username sudah ada.");
        }
    }

    private void doLogin() {
        String username = ConsoleUtil.readLine("Username: ");
        String password = ConsoleUtil.readLine("Password: ");
        UserData ud = auth.login(username, password);
        if (ud == null) {
            System.out.println("Login gagal.");
        } else {
            currentUser = ud;
            System.out.println("Login sukses. Selamat datang, " + currentUser.username);
        }
    }

    private void showDashboardMenu() {
        InventoryService inv = new InventoryService(currentUser);
        ProductService ps = new ProductService(currentUser);
        DashboardService ds = new DashboardService(currentUser);

        System.out.println("\n== Dashboard ==");
        System.out.println("1. Lihat ringkasan dashboard");
        System.out.println("2. Kelola Bahan Baku");
        System.out.println("3. Kelola Produk");
        System.out.println("4. Kalkulator HPP");
        System.out.println("5. Penjualan (tambah record)");
        System.out.println("6. Logout");
        System.out.println("7. Simpan & Exit");
        String c = ConsoleUtil.readLine("Pilih> ");
        switch (c) {
            case "1" -> showSummary(ds, ps);
            case "2" -> manageIngredients(inv);
            case "3" -> manageProducts(ps, inv);
            case "4" -> runHppCalculator(ps, inv);
            case "5" -> addSale(ds, ps);
            case "6" -> doLogout();
            case "7" -> { auth.persist(currentUser); System.out.println("Simpan selesai. Keluar."); System.exit(0); }
            default -> System.out.println("Pilihan tidak valid");
        }
    }

    private void showSummary(DashboardService ds, ProductService ps) {
        System.out.println("Total produk: " + ds.totalProducts());
        System.out.println("Total pendapatan: Rp " + String.format("%.2f", ds.totalRevenue()));
        System.out.println("Rata-rata harga (per transaksi): Rp " + String.format("%.2f", ds.averagePrice()));
        Product best = ds.bestSellingProduct();
        System.out.println("Produk terlaris: " + (best != null ? best.name + " (sold=" + best.totalSold + ")" : "-"));
        System.out.println("\nDaftar produk:");
        ps.listProducts().forEach(p -> System.out.println(p.toString()));
    }

    private void manageIngredients(InventoryService inv) {
        while (true) {
            System.out.println("\n== Kelola Bahan Baku ==");
            System.out.println("1. Tambah bahan baku");
            System.out.println("2. Edit bahan baku");
            System.out.println("3. Tampilkan semua");
            System.out.println("4. Cari bahan baku");
            System.out.println("5. Kembali");
            String v = ConsoleUtil.readLine("Pilih> ");
            switch (v) {
                case "1" -> {
                    String name = ConsoleUtil.readLine("Nama bahan: ");
                    String unit = ConsoleUtil.readLine("Satuan (g,kg,ml,l,pcs,pack): ");
                    double price = ConsoleUtil.readDouble("Harga per satuan (Rp): ");
                    double stock = ConsoleUtil.readDouble("Jumlah stok (satuan): ");
                    inv.addIngredient(name, unit, price, stock);
                    auth.persist(currentUser);
                    System.out.println("Bahan ditambahkan.");
                }
                case "2" -> {
                    String id = ConsoleUtil.readLine("ID bahan yang akan diedit: ");
                    Ingredient ing = inv.findById(id);
                    if (ing == null) { System.out.println("Tidak ditemukan."); break; }
                    String name = ConsoleUtil.readLine("Nama bahan ("+ing.name+"): ");
                    String unit = ConsoleUtil.readLine("Satuan ("+ing.unit+"): ");
                    double price = ConsoleUtil.readDouble("Harga per satuan (Rp) ("+ing.pricePerUnit+"): ");
                    double stock = ConsoleUtil.readDouble("Jumlah stok ("+ing.stock+"): ");
                    inv.editIngredient(id, name.isEmpty()?ing.name:name, unit.isEmpty()?ing.unit:unit, price, stock);
                    auth.persist(currentUser);
                    System.out.println("Bahan diupdate.");
                }
                case "3" -> inv.listIngredients().forEach(i -> System.out.println(i.toString()));
                case "4" -> {
                    String q = ConsoleUtil.readLine("Masukkan nama atau ID: ");
                    inv.searchIngredients(q).forEach(i -> System.out.println(i.toString()));
                }
                case "5" -> { return; }
                default -> System.out.println("Pilihan tidak valid");
            }
        }
    }

    private void manageProducts(ProductService ps, InventoryService inv) {
        while (true) {
            System.out.println("\n== Kelola Produk ==");
            System.out.println("1. Tambah produk");
            System.out.println("2. Edit produk");
            System.out.println("3. Tampilkan semua");
            System.out.println("4. Cari produk");
            System.out.println("5. Kembali");
            String v = ConsoleUtil.readLine("Pilih> ");
            switch (v) {
                case "1" -> {
                    String name = ConsoleUtil.readLine("Nama produk: ");
                    double yield = ConsoleUtil.readDouble("Yield per batch (unit): ");
                    int time = ConsoleUtil.readInt("Waktu kerja per batch (menit): ");
                    List<RecipeItem> recipe = inputRecipe(inv);
                    ps.addProduct(name, yield, time, recipe);
                    auth.persist(currentUser);
                    System.out.println("Produk ditambahkan.");
                }
                case "2" -> {
                    String id = ConsoleUtil.readLine("ID produk yang akan diedit: ");
                    Product p = ps.findById(id);
                    if (p == null) { System.out.println("Tidak ditemukan."); break; }
                    String name = ConsoleUtil.readLine("Nama produk ("+p.name+"): ");
                    double yield = ConsoleUtil.readDouble("Yield per batch ("+p.yieldPerBatch+"): ");
                    int time = ConsoleUtil.readInt("Waktu kerja per batch (menit) ("+p.timePerBatchMinutes+"): ");
                    System.out.println("Masukkan resep baru:");
                    List<RecipeItem> recipe = inputRecipe(inv);
                    ps.editProduct(id, name.isEmpty()?p.name:name, yield, time, recipe);
                    auth.persist(currentUser);
                    System.out.println("Produk diupdate.");
                }
                case "3" -> ps.listProducts().forEach(pr -> {
                    System.out.println(pr.toString());
                    if (!pr.recipe.isEmpty()) {
                        System.out.println("  Resep:");
                        pr.recipe.forEach(r -> {
                            Ingredient ing = inv.findById(r.ingredientId);
                            String inName = ing == null ? "UNKNOWN" : ing.name + " (" + ing.unit + ")";
                            System.out.println("   - " + inName + " : " + r.amount);
                        });
                    }
                });
                case "4" -> {
                    String q = ConsoleUtil.readLine("Nama atau ID: ");
                    ps.searchProducts(q).forEach(p -> System.out.println(p.toString()));
                }
                case "5" -> { return; }
                default -> System.out.println("Pilihan tidak valid");
            }
        }
    }

    private List<RecipeItem> inputRecipe(InventoryService inv) {
        List<RecipeItem> recipe = new ArrayList<>();
        while (true) {
            System.out.println("Tambahkan item resep? (y/n)");
            String yn = ConsoleUtil.readLine("> ");
            if (!yn.equalsIgnoreCase("y")) break;
            System.out.println("Daftar bahan tersedia:");
            inv.listIngredients().forEach(i -> System.out.println(i.toString()));
            String ingId = ConsoleUtil.readLine("Masukkan ID bahan: ");
            Ingredient ing = inv.findById(ingId);
            if (ing == null) {
                System.out.println("Bahan tidak ditemukan.");
                continue;
            }
            double amount = ConsoleUtil.readDouble("Jumlah (satuan " + ing.unit + "): ");
            recipe.add(new RecipeItem(ingId, amount));
            System.out.println("Item resep ditambahkan.");
        }
        return recipe;
    }

    private void runHppCalculator(ProductService ps, InventoryService inv) {
        System.out.println("\n== Kalkulator HPP ==");
        ps.listProducts().forEach(p -> System.out.println(p.toString()));
        String pid = ConsoleUtil.readLine("Pilih ID produk: ");
        Product p = ps.findById(pid);
        if (p == null) { System.out.println("Produk tidak ditemukan."); return; }
        double margin = ConsoleUtil.readDouble("Target margin (%) 10-100: ");
        double timePerUnit = ConsoleUtil.readDouble("Waktu kerja per unit (menit): ");
        double workerRpPerHour = ConsoleUtil.readDouble("Biaya pekerja per jam (Rp): ");
        int estPerMonth = ConsoleUtil.readInt("Estimasi produksi per bulan (unit): ");

        double bahanPerUnit = HPPCalculatorService.costPerUnitFromBahan(p, inv);
        double workerPerUnit = HPPCalculatorService.workerCostPerUnit(workerRpPerHour, timePerUnit);
        double totalHpp = bahanPerUnit + workerPerUnit;
        double price = HPPCalculatorService.priceWithMargin(totalHpp, margin);

        System.out.println("--- Rincian Biaya ---");
        System.out.printf("Biaya bahan per unit: Rp %.2f%n", bahanPerUnit);
        System.out.printf("Biaya pekerja per unit: Rp %.2f%n", workerPerUnit);
        System.out.printf("Total HPP per unit: Rp %.2f%n", totalHpp);
        System.out.printf("Harga jual (dengan margin %.2f%%): Rp %.2f%n", margin, price);
        System.out.printf("Estimasi pendapatan/bulan @%d unit: Rp %.2f%n", estPerMonth, estPerMonth * price);
    }

    private void addSale(DashboardService ds, ProductService ps) {
        System.out.println("Produk:");
        ps.listProducts().forEach(p -> System.out.println(p.toString()));
        String pid = ConsoleUtil.readLine("Pilih ID produk: ");
        Product p = ps.findById(pid);
        if (p == null) { System.out.println("Produk tidak ditemukan."); return; }
        int qty = ConsoleUtil.readInt("Jumlah terjual: ");
        double price = ConsoleUtil.readDouble("Harga per unit (Rp): ");
        String note = ConsoleUtil.readLine("Catatan (optional): ");
        SaleRecord sr = new SaleRecord(pid, qty, price, note);
        ds.addSale(sr);
        auth.persist(currentUser);
        System.out.println("Record penjualan disimpan.");
    }

    private void doLogout() {
        auth.persist(currentUser);
        currentUser = null;
        System.out.println("Logout sukses.");
    }
}
