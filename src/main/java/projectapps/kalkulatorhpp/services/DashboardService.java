package projectapps.kalkulatorhpp.services;

import projectapps.kalkulatorhpp.models.Product;
import projectapps.kalkulatorhpp.models.SaleRecord;
import projectapps.kalkulatorhpp.models.UserData;

import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;

//Anggota 3 (Dashboard)
public class DashboardService {
    private final UserData ud;

    public DashboardService(UserData ud) {
        this.ud = ud;
    }

    public int totalProducts() {
        return ud.products.size();
    }

    public double totalRevenue() {
        return ud.sales.stream().mapToDouble(s -> s.quantity * s.pricePerUnit).sum();
    }

    public double averagePrice() {
        OptionalDouble od = ud.sales.stream().mapToDouble(s -> s.pricePerUnit).average();
        return od.isPresent() ? od.getAsDouble() : 0.0;
    }

    public Product bestSellingProduct() {
        return ud.products.stream().max(Comparator.comparingInt(p -> p.totalSold)).orElse(null);
    }

    public void addSale(SaleRecord sr) {
        ud.sales.add(sr);
        // update product sold
        ud.products.stream().filter(p -> p.id.equals(sr.productId)).findFirst().ifPresent(p -> p.totalSold += sr.quantity);
    }

    public List<SaleRecord> listSales() {
        return ud.sales;
    }
}
