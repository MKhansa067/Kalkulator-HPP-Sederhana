# Kalkulator-HPP-Sederhana  
Aplikasi Kalkulator Harga Pokok Penjualan (HPP) berbasis Java Command Line (CLI). Aplikasi ini tidak menggunakan database, melainkan menyimpan seluruh data secara statis ke dalam file JSON lokal. Proyek ini dirancang sebagai latihan manajemen proyek, pembagian modul antar anggota, serta implementasi alur bisnis sederhana dalam Java.  

## Jalankan Program  
**1. Download File**  
Klik tombol "Code" lalu download format ZIP  
<img width="196" height="43" alt="image" src="https://github.com/user-attachments/assets/b9ff6201-1945-4526-b1b8-5d854d5f7f11" />  
Atau bisa dengan perintah Command Prompt:  
```cmd
cd <folder yang mau kamu jadikan tempat simpan folder project ini>
git clone https://github.com/MKhansa067/Kalkulator-HPP-Sederhana.git
```
Bisa disimpan di folder mana saja.  

**2. Buka Project**  
Buka Apache Netbeans IDE 21, jika belum install, bisa melalui link: https://archive.apache.org/dist/netbeans/netbeans-installers/21/Apache-NetBeans-21-bin-windows-x64.exe  
Setelah dibuka, klik "File" lalu klik "Open Project" dan pilih folder yang sudah di download.  
<img width="344" height="140" alt="image" src="https://github.com/user-attachments/assets/d5fdd73d-97f5-41a3-8cd8-2f877ba5aa5d" />  
Lalu pilih folder program.  
<img width="227" height="27" alt="image" src="https://github.com/user-attachments/assets/605d0ea6-c0a2-401d-8bec-7deeac702bd8" />

**3. Run File**  
Setelah project terbuka, klik kanan pada mouse, lalu klik "Run File" seperti pada gambar ini.  
<img width="542" height="275" alt="image" src="https://github.com/user-attachments/assets/5f4aa6bc-4eff-406d-b036-4cfbdf5ef13f" />  
Dan program sudah berjalan.  


## Deskripsi
### Fitur Utama   
**1. Sistem Login & Register**  
- Setiap pengguna dapat membuat akun.
- Data akun disimpan di data/user.json.
- Mendukung login, logout, serta penyimpanan progress per user.  

**2. Dashboard**  
Menampilkan rangkuman data:  
- Total produk
- Total pendapatan
- Rata-rata harga jual
- Produk terlaris (berdasarkan data penjualan statis)

**3. Manajemen Bahan Baku**  
- Tambah bahan baku: nama bahan, satuan (g, kg, ml, l, pcs, pack), harga per satuan, stok
- Tampilkan semua bahan
- Edit bahan baku
- Cari bahan baku

**4. Manajemen Produk**  
- Tambah produk: nama produk, yield per batch, waktu kerja per batch, pemilihan bahan baku beserta jumlahnya (resep)
- Edit produk
- Cari produk
- Setiap produk memiliki komponen: Ingredients, RecipeItem, dan harga otomatis dihitung lewat service.

**5. Kalkulator HPP**  
Menghitung HPP per unit dan harga jual berdasarkan margin yang ditentukan:
- Biaya bahan per unit
- Biaya pekerja per unit
- Total HPP per unit
- Harga jual dengan margin (10% – 100%)
- Estimasi pendapatan per bulan

**6. Penyimpan Data JSON**  
Seluruh data berikut disimpan dalam file JSON per user:
- Bahan baku
- Produk
- Rekam penjualan
- Data dashboard  
Digunakan Gson untuk serialisasi/deserialisasi.  

### Alur Program  
1. User membuka aplikasi → tampilan beranda
2. Login / Register
3. Masuk ke Dashboard
4. Pilih modul:
   - Bahan Baku
   - Produk
   - Kalkulator HPP
5. Data diperbarui dan disimpan ke file JSON secara otomatis
6. User logout atau keluar aplikasi  

### Teknologi yang Digunakan
- Java 17+
- Maven (build tool NetBeans)
- Gson (untuk JSON)
- CLI / Command Line Interface


## Flowchart
**1. Halaman Beranda:**
- Login
- Register
- Exit   
![Flowchart HPP-1](docs/flowchart1.png)

**2. Halaman Dashboard:**
- Lihat ringkasan dashboard
- Kelola Bahan Baku
- Kelola Produk
- Kalkulator HPP
- Penjualan (tambah record)
- Logout
- Simpan & Exit  
![Flowchart HPP-2](docs/flowchart2.png)

**3. Kelola Bahan Baku:**
- Tambah bahan baku
- Edit bahan baku
- Tampilkan semua
- Cari bahan baku
- Kembali  
![Flowchart HPP-3](docs/flowchart3.png)

**4. Kelola Produk:**
- Tambah produk
- Edit produk
- Tampilkan semua
- Cari produk
- Kembali  
![Flowchart HPP-4](docs/flowchart4.png)

**5. Kalkulator HPP**   
![Flowchart HPP-5](docs/flowchart5.png)   

**6. Penjualan (tambah record)**   
![Flowchart HPP-6](docs/flowchart6.png)   

**7. Logout**   
![Flowchart HPP-7](docs/flowchart7.png)   

**8. Simpan & Exit**   
![Flowchart HPP-8](docs/flowchart8.png)   


## Screenshot per Menu   
**1. Halaman Beranda:**   
![Menu HPP-1](docs/menu1.png)   

**2. Halaman Dashboard:**  
![Menu HPP-2](docs/menu2.png)   

**3. Kelola Bahan Baku:**   
![Menu HPP-3](docs/menu3.png)   

**4. Kelola Produk:**   
![Menu HPP-4](docs/menu4.png)   

**5. Kalkulator HPP**  
![Menu HPP-5](docs/menu5.png)   

**6. Penjualan (tambah record)**  
![Menu HPP-6](docs/menu6.png)   


