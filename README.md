# Kalkulator-HPP-Sederhana  
Aplikasi Kalkulator Harga Pokok Penjualan (HPP) berbasis Java Command Line (CLI). Aplikasi ini tidak menggunakan database, melainkan menyimpan seluruh data secara statis ke dalam file JSON lokal. Proyek ini dirancang sebagai latihan manajemen proyek, pembagian modul antar anggota, serta implementasi alur bisnis sederhana dalam Java.  

## Kata Pengantar
Puji syukur kami panjatkan kepada Allah SWT. karena dengan rahmat dan karunia-Nya kami bisa menyelesaikan laporan tugas akhir statistika yang berjudul “Kalkulator HPP” ini dengan baik dan tepat pada waktunya. Tidak lupa kami  menyampaikan rasa terima kasih kepada nama nama berikut:    
1. Bapak Ichsan Taufik ST., MT. selaku dosen mata kuliah Algoritma dan Struktur Data yang telah membimbing dan  mengajar kami.   
2. Orang Tua kami yang telah mendoakan serta memberi dukungan kepada kami dalam pengerjaan laporan ini.   
3. Rekan-rekan kelompok 2 yang telah memberikan kontribusi berupa bantuan dalam pengerjaan dan lain-lain sehingga laporan makalah ini bisa selesai pada waktu yang telah ditentukan.   
Meskipun kami telah mengerjakan laporan ini dengan teliti, namun kami menyadari bahwa di dalam laporan yang telah kami susun ini jauh dari kata sempurna sehingga kami harap saran serta masukan dari pembaca demi tersusunnya laporan praktikum selanjutnya yang lebih baik. Kami harap hasil laporan kami bermanfaat bagi pembaca.   

## Daftar Isi
- [Jalankan Program](#jalankan-program)
- [Deskripsi](#deskripsi)
- [Flowchart](#flowchart)
- [Screenshot per Menu](#screenshot-per-menu)
- [Tugas Masing-Masing Anggota](#tugas-masing-masing-anggota)

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

## Tugas masing-masing Anggota   
**1. Khansa**   
Bertanggung jawab atas program KalkulatorHPP.java, yang berfungsi sebagai menu utama dan pengatur alur aplikasi secara keseluruhan, memastikan pengguna dapat login, bernavigasi ke semua sub-menu (bahan, produk, HPP, dashboard), dan memanggil semua fungsi layanan lain dalam urutan yang benar.   

**2. Keira**   
Mengembangkan program AuthService dan model UserData, yang bertugas mengelola autentikasi (login/register) dan bertindak sebagai container sentral untuk semua data pengguna (bahan, produk, penjualan), memastikan setiap progres dan konfigurasi tersimpan dan digunakan secara terpisah per pengguna.   

**3. Zadit**   
Zadit membuat program InventoryService dan model Ingredient, yang berfungsi penuh untuk mengelola semua data bahan baku; ia menangani penambahan, pengeditan, pencarian, dan tampilan stok bahan baku, menjadi sumber data utama mengenai harga dan ketersediaan material.   

**4. Ibnu**   
Mengembangkan program ProductService dan model Product, yang fokus pada manajemen produk olahan dan resep; program ini memungkinkan pembuatan dan pengeditan produk serta menghubungkan produk tersebut dengan bahan baku yang ada (disediakan oleh Zadit) untuk merumuskan daftar resep yang dibutuhkan.   

**5. Alghifari**   
Menyusun program HPPCalculatorService dan model RecipeItem, yang berfungsi sebagai mesin penghitung utama, mengambil resep dari produk (Ibnu) dan harga bahan (Zadit) untuk menghitung Harga Pokok Penjualan (HPP) per batch dan per unit, serta menentukan harga jual dengan margin.   

**6. Ghevyra**   
Membuat program DashboardService dan model SaleRecord, yang bertugas mengumpulkan dan menyajikan laporan kinerja bisnis; fungsinya adalah menghitung total pendapatan, rata-rata harga, dan menentukan produk terlaris berdasarkan catatan penjualan statis yang dimasukkan.   

**7. Firda**   
Bertanggung jawab atas program StorageService dan ConsoleUtil, yang menyediakan fungsi pendukung penting: StorageService mengurus semua interaksi dengan file user.json (membaca/menulis data), sementara ConsoleUtil memastikan semua input dari pengguna melalui konsol diterima dengan aman dan valid oleh semua program service lainnya.   

### Sekian, Terima Kasih.
