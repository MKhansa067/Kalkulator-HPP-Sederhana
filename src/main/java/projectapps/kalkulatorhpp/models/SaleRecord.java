package projectapps.kalkulatorhpp.models;

import java.time.LocalDateTime;

//Anggota 4
public class SaleRecord {
    public String productId;
    public int quantity;
    public double pricePerUnit;
    public String note;
    public String timestamp;

    public SaleRecord() {}

    public SaleRecord(String productId, int quantity, double pricePerUnit, String note) {
        this.productId = productId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.note = note;
        this.timestamp = LocalDateTime.now().toString();
    }
}
