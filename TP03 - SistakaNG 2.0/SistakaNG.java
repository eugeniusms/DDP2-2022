package assignments.assignment3;

import assignments.assignment3.buku.*;
import assignments.assignment3.pengguna.*;

import java.util.Scanner;
import java.util.*;

// Kelas ini merupakan kelas untuk menjalankan program utama
public class SistakaNG {
    private static Scanner input = new Scanner(System.in);

    // Attribute dari kelas SistakaNG
    // daftarStaf untuk menyimpan semua staf yang terdaftar di SistakaNG
    public static ArrayList<Staf> daftarStaf = new ArrayList<>();
    // daftarAnggota untuk menyimpan semua anggota yang terdaftar di SistakaNG
    public static ArrayList<Anggota> daftarAnggota = new ArrayList<>();
    // daftarBuku untuk menyimpan semua buku yang terdaftar di SistakaNG
    public static ArrayList<Buku> daftarBuku = new ArrayList<>();
    // daftarKategori untuk menyimpan semua kategori yang terdaftar di SistakaNG
    public static ArrayList<Kategori> daftarKategori = new ArrayList<>();
    // penggunaLoggedIn untuk menyimpan pengguna yang sedang login ke SistakaNG
    public static Pengguna penggunaLoggedIn;

    // Program bermula dari sini (main)
    public static void main(String[] args) {
        System.out.println("Start - Register Staf...");
        registerStaf();
        System.out.println("Done - Register Staf...\n");
        menu();
        input.close();
    }

    // Method ini digunakan untuk mendaftarkan staf-staf ketika program dijalankan
    private static void registerStaf() {
        String[] listNama = new String[]{"Dek Depe", "Dek DePram", "Dek Sofita", "Winter", "Boo"};

        for (int i = 0; i < listNama.length; i++) {
            // Membuat objek Staf menggunakan listNama[i]
            Staf staf = new Staf(listNama[i]);
            daftarStaf.add(staf);
            // Mencetak pesan saat berhasil menambahkan staf
            System.out.println("Berhasil menambahkan staf dengan data:");
            System.out.println(staf);
        }
    }

    // Method ini digunakan untuk mencetak menu utama dari SistakaNG
    public static void menu() {
        // Mencetak tampilan untuk menu oleh pengguna
        boolean hasChosenExit = false;
        System.out.println("Selamat Datang di Sistem Perpustakaan SistakaNG!");
        while (!hasChosenExit) {
            int command = 0;
            System.out.println("================ Menu Utama ================\n");
            System.out.println("1. Login");
            System.out.println("2. Keluar");
            System.out.print("Masukkan pilihan menu: ");

            // Mengambil command untuk menentukan 
            // 1) Login
            // 2) Exit
            // Selain itu, command tidak dikenal
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {
                menuLogin();
            } else if (command == 2) {
                System.out.println("Terima kasih telah menggunakan SistakaNG. Sampai jumpa di lain kesempatan!");
                hasChosenExit = true;
            } else {
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }
    }

    // Method ini digunakan untuk mencetak menu login
    public static void menuLogin() {
        boolean isLoginSuccess = false;
        while (!isLoginSuccess) {
            System.out.println("Masukkan ID Anda untuk login ke sistem");
            System.out.print("ID: ");
            String id = input.nextLine();
            
            // Mengambil jenis role pengguna agar disesuaikan dengan tampilan yang ada
            String rolePengguna = cekPenggunaLogin(id);

            if (rolePengguna.equals("STAF")) { // Mengecek keberadaan id staf (saat role = staf)
                // Saat ditemui staf yang memiliki id yang sama dengan id masukan (Login Berhasil)
                if (cekKeberadaanIdStaf(id) == true) {
                    // Mengubah nilai login sukses dan mengisi siapa yang sedang login
                    isLoginSuccess = true;
                }
                // Penampilan pesan hasil login ke user dan set pengguna -> STAF
                System.out.println(setStafLogin(id));
                
            } else if (rolePengguna.equals("ANGGOTA")) { // Mengecek keberadaan id anggota (saat role = anggota)
                // Saat ditemui staf yang memiliki id yang sama dengan id masukan (Login Berhasil)
                if (cekKeberadaanIdAnggota(id) == true) {
                    // Mengubah nilai login sukses dan mengisi siapa yang sedang login
                    isLoginSuccess = true;
                }
                // Penampilan pesan hasil login ke user dan set pengguna -> ANGGOTA
                System.out.println(setAnggotaLogin(id));

            } else {
                // Saat bukan role keduanya maka langsung saja ID tidak ditemukan
                System.out.printf("Pengguna dengan ID %s tidak ditemukan\n", id);
            }
        }

        // Menampilkan menu 
        showMenu();
    }

    // Method ini digunakan untuk mencetak menu yang dapat diakses berdasarkan jenis penggunaLoggedIn
    private static void showMenu() {
        // Pemilihan berdasarkan pengguna yang sedang login
        if (penggunaLoggedIn instanceof Staf) {
            showMenuStaf();
        } else {
            showMenuAnggota();
        }
    }

    // Method ini digunakan untuk mencetak menu staf dari SistakaNG
    private static void showMenuStaf() {
        int command = 0;
        boolean hasChosenExit = false;
        while (!hasChosenExit) {
            // Mencetak tampilan untuk pengguna dapat melakukan command
            System.out.println("================ Menu Staf ================\n");
            System.out.println("1. Tambah Anggota");
            System.out.println("2. Tambah Kategori");
            System.out.println("3. Tambah Buku");
            System.out.println("4. Hapus Buku");
            System.out.println("5. 3 Peringkat Pertama");
            System.out.println("6. Detail Anggota");
            System.out.println("7. Daftar Peminjam Buku");
            System.out.println("99. Logout");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();

            // Beberapa menu command dalam menu staf adalah
            // 1) Tambah Anggota
            // 2) Tambah Kategori
            // 3) Tambah Buku
            // 4) Hapus Buku
            // 5) 3 Peringkat Pertama
            // 6) Detail Anggota
            // 7) Daftar Peminjam Buku
            // 99) Logout

            if (command == 1) {
                tambahAnggota();
            } else if (command == 2) {
                tambahKategori();
            } else if (command == 3) {
                tambahBuku();
            } else if (command == 4) {
                hapusBuku();
            } else if (command == 5) {
                peringkat();
            } else if (command == 6) {
                stafDetailAnggota();
            } else if (command == 7) {
                daftarPeminjamBuku();
            } else if (command == 99) {
                System.out.println("Terima kasih telah menggunakan SistakaNG!");
                hasChosenExit = true;
            } else {
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }
    }

    // Method ini digunakan untuk mencetak menu anggota dari SistakaNG
    private static void showMenuAnggota() {
        int command = 0;
        boolean hasChosenExit = false;
        while (!hasChosenExit) {
            // Mencetak tampilan untuk pengguna dapat melakukan command
            System.out.println("================ Menu Anggota ================\n");
            System.out.println("1. Peminjaman");
            System.out.println("2. Pengembalian");
            System.out.println("3. Pembayaran Denda");
            System.out.println("4. Detail Anggota");
            System.out.println("99. Logout");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();

            // Beberapa menu command dalam menu angggota adalah
            // 1) Peminjaman
            // 2) Pengembalian
            // 3) Pembayaran denda
            // 4) Detail Anggota
            // 99) Logout

            if (command == 1) {
                menuPeminjaman();
            } else if (command == 2) {
                menuPengembalian();
            } else if (command == 3) {
                menuPembayaran();
            } else if (command == 4) {
                detailAnggota();
            } else if (command == 99) {
                System.out.println("Terima kasih telah menggunakan SistakaNG!");
                hasChosenExit = true;
            } else {
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }
    }

    // ------------------------- START MENU LOGIN -------------------------------

    // Method untuk mengecek yang login adalah staf atau anggota
    public static String cekPenggunaLogin(String id) {
        // Cek banyak char pada id, saat ID lebih dari 4 panjangnya baru dicek
        if (id.length() >= 5) {
            // Saat 4 huruf pertama adalah staf maka yang login adalah staf
            if (id.substring(0,4).equals("STAF")) {
                return "STAF";
            } 
            // Saat substring 3 huruf pertama ada di dalam program studi 
            // maka yang login adalah anggota (Mahasiswa)
            String[] programStudi = {"SIK", "SSI", "MIK", "MTI", "DIK"};
            for (String ps : programStudi) {
                if (ps.equals(id.substring(0,3))) {
                    return "ANGGOTA";
                }
            }
            // Saat substring 5 huruf pertama ada di dalam program studi 
            // maka yang login adalah anggota (Mahasiswa)
            if (id.substring(0,5).equals("DOSEN")) {
                return "ANGGOTA";
            }
        } 
        // Saat tidak ada diantara kedua di atas maka kembalikan "NONE"
        return "NONE";
    }

    // Method untuk mengecek keberadaan ID Staff dalam daftar staf
    public static boolean cekKeberadaanIdStaf(String id) {
        // Saat daftar staf masih kosong
        if (daftarStaf == null || daftarStaf.size() == 0) { 
            return false; 
        }
        // Saat staf dengan ID ada dalam daftarStaf
        for (Staf staf : daftarStaf) {
            if (staf.getId().equals(id)) {
                return true;
            }
        }
        // Saat tidak ditemukan staf dengan id tersebut
        return false;
    }

    // Method untuk mendapatkan nama staf
    public static String setStafLogin(String id) {
        // Saat daftar staf masih kosong
        if (daftarStaf == null || daftarStaf.size() == 0) { 
            return String.format("Pengguna dengan ID %s tidak ditemukan", id); 
        }
        // Saat staf dengan ID ada dalam daftarStaf
        for (Staf staf : daftarStaf) {
            if (staf.getId().equals(id)) {
                // SET STAF LOGIN
                penggunaLoggedIn = staf;
                return String.format("Halo, %s! Selamat datang di SistakaNG", staf.getNama());
            }
        }
        // Saat tidak ditemukan staf dengan id tesebut
        return String.format("Pengguna dengan ID %s tidak ditemukan", id);
    }

    // Method untuk mengecek keberadaan ID Anggota dalam daftar anggota
    public static boolean cekKeberadaanIdAnggota(String id) {
        // Saat daftar staf masih kosong
        if (daftarAnggota == null || daftarAnggota.size() == 0) { 
            return false; 
        }
        // Saat staf dengan ID ada dalam daftarStaf
        for (Anggota anggota : daftarAnggota) {
            if (anggota.getId().equals(id)) {
                return true;
            }
        }
        // Saat tidak ditemukan staf dengan id tersebut
        return false;
    }

    public static String setAnggotaLogin(String id) {
        // Saat daftar staf masih kosong
        if (daftarAnggota == null || daftarAnggota.size() == 0) { 
            return String.format("Pengguna dengan ID %s tidak ditemukan", id); 
        }
        // Saat staf dengan ID ada dalam daftarStaf
        for (Anggota anggota : daftarAnggota) {
            if (anggota.getId().equals(id)) {
                // SET ANGGOTA LOGIN
                penggunaLoggedIn = anggota;
                return String.format("Halo, %s! Selamat datang di SistakaNG", anggota.getNama());
            }
        }
        // Saat tidak ditemukan staf dengan id tesebut
        return String.format("Pengguna dengan ID %s tidak ditemukan", id);
    }

    // --------------------------- END MENU LOGIN -------------------------------

    // -------------------------- START MENU STAF -------------------------------

    // ------------------------ START TAMBAH ANGGOTA ----------------------------

    // Method ini digunakan untuk menambahkan anggota
    public static void tambahAnggota() {
        System.out.println("---------- Tambah Anggota ----------");
        System.out.print("Tipe Anggota: ");
        String tipeAnggota = input.nextLine();

        // Hanya ada dua tipe "Mahasiswa" dan "Dosen" (Case-sensitive)
        if (tipeAnggota.equals("Mahasiswa")) {
            tambahAnggotaMahasiswa();
        } else if (tipeAnggota.equals("Dosen")) {
            tambahAnggotaDosen();
        } else {
            // Selain dua tipe di atas maka tidak akan valid
            System.out.printf("Tipe Anggota %s tidak valid!\n", tipeAnggota);
        }
    }

    // Method ini digunakan untuk melakukan pengambilan data anggota mahasiswa
    public static void tambahAnggotaMahasiswa() {
        System.out.print("Nama : ");
        String nama = input.nextLine();
        System.out.print("Program Studi (SIK/SSI/MIK/MTI/DIK): ");
        String programStudi = input.nextLine();
        System.out.print("Angkatan: ");
        String angkatan = input.nextLine();
        System.out.print("Tanggal Lahir (dd/mm/yyyy): ");
        String tanggalLahir = input.nextLine();

        Mahasiswa mahasiswa = new Mahasiswa(nama, tanggalLahir, programStudi, angkatan);
        // Saat ID sukses ditambahkan maka tambahkan anggota ke dalam array
        if (mahasiswa.getId().length() != 0) {
            daftarAnggota.add(mahasiswa);
            System.out.println("Berhasil menambahkan mahasiswa dengan data:");
            System.out.println(mahasiswa);
        }
    }

    // Method ini digunakan untuk menambahkan anggota dosen
    public static void tambahAnggotaDosen() {
        System.out.print("Nama : ");
        String nama = input.nextLine();
        
        // Menambahkan dosen ke dalam daftar anggota
        Dosen dosen = new Dosen(nama);
        daftarAnggota.add(dosen);
        // Cetak tampilan dosen
        System.out.println("Berhasil menambahkan dosen dengan data:");
        System.out.println(dosen);
    }

    // -------------------------- END TAMBAH ANGGOTA -----------------------------

    // ------------------------ START TAMBAH KATEGORI ----------------------------

    // Method ini digunakan utnuk menambahkan kategori
    public static void tambahKategori() {
        System.out.println("---------- Tambah Kategori ----------");
        System.out.print("Nama Kategori: ");
        String namaKategori = input.nextLine();
        System.out.print("Poin: ");
        int poin = Integer.parseInt(input.nextLine());
        
        // Pengecekan apakah kategori baru dapat ditambahkan
        if (bolehTambahKategori(namaKategori) == true) {
            // Saat kategori boleh ditambahkan
            System.out.printf("Kategori %s dengan poin %d berhasil ditambahkan\n", namaKategori, poin);
            // Menambahkan kategori ke dalam daftar kategori
            Kategori kategori = new Kategori(namaKategori, poin);
            daftarKategori.add(kategori);
        } else { 
            // Saat kategori tidak boleh ditambahkan karena ada yang sama sebelumnya
            System.out.printf("Kategori %s sudah pernah ditambahkan\n", namaKategori);
        }
    }

    // Method ini digunakan sebagai pengecekan kebolehan penambahan kategori baru
    public static boolean bolehTambahKategori(String namaKategori) {
        // Cek apakah sudah ada kategori sebelumnya, jika masih kosong langsung boleh
        if (daftarKategori == null || daftarKategori.size() == 0) {
            return true;
        }
        // Mencari apakah sudah ada kategori dengan nama yang sama (insensitive-case)
        for (Kategori kt : daftarKategori) {
            if (kt.getNama().toLowerCase().equals(namaKategori.toLowerCase())) {
                return false;
            }
        }
        // Saat semuanya aman boleh ditambahkan
        return true;
    }

    // ------------------------ END TAMBAH KATEGORI -----------------------------

    // -------------------------- START TAMBAH BUKU -----------------------------

    // Method ini digunakan untuk menambahkan buku baru ke dalam SistakaNG
    public static void tambahBuku() {
        System.out.println("---------- Tambah Buku ----------");
        System.out.print("Judul: ");
        String judul = input.nextLine();
        System.out.print("Penulis: ");
        String penulis = input.nextLine();
        System.out.print("Penerbit: ");
        String penerbit = input.nextLine();
        System.out.print("Kategori: ");
        String kategori = input.nextLine();
        System.out.print("Stok: ");
        int stok = Integer.parseInt(input.nextLine());

        // Jika buku boleh ditambahkan maka cetak dengan format berikut
        if (bolehTambahBuku(judul, penulis, penerbit, kategori, stok) == true) {
            System.out.printf("Buku %s oleh %s berhasil ditambahkan\n", judul, penulis);
        }
    }

    // Method ini digunakan untuk mengecek kebolehan penambahan buku
    public static boolean bolehTambahBuku(String judul, String penulis, String penerbit, String kategori, int stok) {
        // STEP 1 : Cek Ketersediaan Kategori
        // Saat daftar kategori masih kosong maka langsung return false (tidak boleh)
        if (daftarKategori == null || daftarKategori.size() == 0) {
            System.out.printf("Kategori %s tidak ditemukan\n", kategori);
            return false;
        }
        // Melakukan pengecekan apakah kategori ada di dalam daftar
        for (Kategori kt : daftarKategori) {
            // Jika ditemukan nama kategori yang sama maka lanjut ke STEP 2
            if (kt.getNama().toLowerCase().equals(kategori.toLowerCase())) { // Case Insensitive
                // STEP 2 : Cek stok harus lebih dari 0
                // Jika stok lebih dari 0 maka lanjut ke STEP 3
                if (stok > 0) {
                    // STEP 3 : Cek buku sudah pernah ditambahkan atau belum
                    // Jika buku belum pernah ditambahkan maka return true (boleh ditambahkan)
                    // Saat daftar buku masih kosong maka langsung boleh ditambahkan
                    if (daftarBuku == null || daftarBuku.size() == 0) {
                        Buku buku = new Buku(judul, penulis, penerbit, stok, kt);
                        daftarBuku.add(buku);
                        return true;
                    }
                    // Saat buku ada di dalam daftar maka buku baru tidak boleh ditambahkan
                    for (Buku bk : daftarBuku) {
                        // Case Insensitive
                        String judulBukuTerdaftar = bk.getJudul().toLowerCase();
                        String penulisBukuTerdaftar = bk.getPenulis().toLowerCase();
                        if (judulBukuTerdaftar.equals(judul.toLowerCase()) && penulisBukuTerdaftar.equals(penulis.toLowerCase())) {
                            System.out.printf("Buku %s oleh %s sudah pernah ditambahkan\n", judul, penulis);
                            return false;
                        }
                    }
                    // Jika buku belum pernah ditambahkan maka return true (boleh ditambahkan)
                    Buku buku = new Buku(judul, penulis, penerbit, stok, kt);
                    daftarBuku.add(buku);
                    return true;

                } else {
                    // Jika stok tidak lebih dari 0 maka return false
                    System.out.println("Stok harus lebih dari 0");
                    return false;
                }
            }
        }
        // Saat kategori juga masih tidak ada di dalam daftar kategori return false
        System.out.printf("Kategori %s tidak ditemukan\n", kategori);
        return false;
    }

    // -------------------------- END TAMBAH BUKU -----------------------------

    // -------------------------- START HAPUS BUKU -----------------------------

    // Method ini digunakan untuk menghapus sebuah buku dari SistakaNG
    public static void hapusBuku() {
        System.out.println("---------- Hapus Buku ----------");
        System.out.print("Judul Buku: ");
        String judul = input.nextLine();
        System.out.print("Penulis Buku: ");
        String penulis = input.nextLine();

        // Jika buku tidak ada dalam SistakaNG cetak buku tidak ditemukan
        if (daftarBuku == null || daftarBuku.size() == 0) {
            System.out.printf("Buku %s oleh %s tidak ditemukan\n", judul, penulis);
        } else {
            // Cek buku dalam daftar buku SistakaNG
            boolean bukuDitemukan = false;
            for (Buku bk : daftarBuku) { // INSENSITIVE CASE
                String judulAda = bk.getJudul().toLowerCase();
                String penulisAda = bk.getPenulis().toLowerCase();
                // Saat ditemukan buku dan penulis yang sama dalam daftar
                if (judulAda.equals(judul.toLowerCase()) && penulisAda.equals(penulis.toLowerCase())) {
                    bukuDitemukan = true;
                    // Lakukan pengecekan apakah buku sedang dipinjam atau tidak
                    if (bk.sedangDipinjam() == false) {
                        // Lakukan penghapusan buku dari daftar
                        daftarBuku.remove(bk);
                        System.out.printf("Buku %s oleh %s berhasil dihapus\n", judul, penulis);
                    } else {
                        // Saat buku masih dipinjam tampilkan pesan
                        System.out.printf("Buku %s oleh %s tidak dapat dihapus karena sedang dipinjam\n", judul, penulis);
                    } 
                    break;
                }
            }
            // Saat buku tidak ditemukan
            if (bukuDitemukan == false) {
                System.out.printf("Buku %s oleh %s tidak ditemukan\n", judul, penulis);
            }
        }
    }

    // ----------------------------- END HAPUS BUKU -----------------------------

    // ---------------------------- START PERINGKAT -----------------------------

    // Method ini digunakan untuk menampilkan peringkat anggota yang terdaftar
    public static void peringkat() {
        // Copy dulu daftar anggota
        ArrayList<Anggota> daftar = new ArrayList<>(daftarAnggota);
        System.out.println("---------- Peringkat Anggota ----------");
        // Saat masih belum ada anggota di dalam sistem maka cetak
        if (daftar == null || daftar.size() == 0) {
            System.out.println("Belum ada anggota yang terdaftar pada sistem");
        } else {
            // Urutkan data yang ada (Bubble Sort)
            for (int i = 0; i < daftar.size(); i++) {
                for (int j = 0; j < daftar.size() - 1; j++) {
                    // Lakukan pembandingan dengan compareTo dan tukar jika sesuai
                    if (daftar.get(j).compareTo(daftar.get(j+1)) < 0) {
                        Anggota tmp = daftar.get(j);
                        daftar.set(j, daftar.get(j+1));
                        daftar.set(j+1, tmp);
                    }
                }
            }
            // Cek size daftar vs 3 minimal mana agar tidak ada indexOutOfBonds error
            int count = Math.min(daftar.size(), 3);

            // Lakukan pencetakan (hanya 3 teratas)
            for (int i = 0; i < count; i++) {
                System.out.printf("----------------- %d -----------------\n", i+1);
                System.out.println(daftar.get(i));
            }
        }
    }
    // ---------------------------- END PERINGKAT -------------------------------

    // -------------------------- START DETAIL ANGGOTA --------------------------

    // Method ini digunakan untuk menampilkan detail anggota (dalam menu staf)
    public static void stafDetailAnggota() {
        System.out.println("---------- Detail Anggota ----------");
        System.out.print("ID Anggota: ");
        String idAnggota = input.nextLine();

        // Lakukan pencarian anggota
        cariAnggota(idAnggota);
    }

    // Method ini digunakan untuk mencari anggota dalam SistakaNG
    public static void cariAnggota(String id) {
        // Cek terlebih dahulu daftar anggotanya kosong atau tidak
        if (daftarAnggota == null || daftarAnggota.size() == 0) {
            // Saat kosong maka cetak langsung tidak ditemukan
            System.out.printf("Anggota dengan ID %s tidak ditemukan\n", id);
        } else {
            // Pencarian manual
            boolean idTersedia = false;
            for (Anggota ang : daftarAnggota) {
                if (ang.getId().equals(id)) {
                    // Ketika didapati id sama maka id tersedia
                    idTersedia = true;
                    ang.detail();
                    break;
                }
            }
            // Saat id tidak ditemukan
            if (idTersedia == false) {
                System.out.printf("Anggota dengan ID %s tidak ditemukan\n", id);
            }
        }
    }

    // -------------------------- END DETAIL ANGGOTA ----------------------------

    // --------------------- START DAFTAR PEMINJAM BUKU -------------------------

    // Method ini digunakan untuk menampilkan daftar peminjaman buku tertentu
    public static void daftarPeminjamBuku() {
        System.out.println("---------- Daftar Peminjam Buku ----------");
        System.out.print("Judul Buku: ");
        String judul = input.nextLine();
        System.out.print("Penulis Buku: ");
        String penulis = input.nextLine();

        // Lakukan pencarian buku
        cariBuku(judul, penulis);
    }

    // Method ini digunakan untuk melakukan pencarian buku
    public static void cariBuku(String judul, String penulis) {
        // Cek terlebih dahulu daftar bukunya kosong atau tidak
        if (daftarBuku == null || daftarBuku.size() == 0) {
            // Saat kosong maka cetak langsung tidak ditemukan
            System.out.printf("Buku %s oleh %s tidak ditemukan\n", judul, penulis);
        } else {
            // Pencarian manual
            boolean bukuTersedia = false;
            for (Buku bk : daftarBuku) {
                String judulAda = bk.getJudul().toLowerCase(); // INSENSITIVE CASE
                String penulisAda = bk.getPenulis().toLowerCase();
                if (judulAda.equals(judul.toLowerCase()) && penulisAda.equals(penulis.toLowerCase())) {
                    // Ketika didapati judul dan penulis ada maka cetak info
                    bukuTersedia = true;
                    bk.detailPeminjam();
                    break;
                }
            }
            // Saat id tidak ditemukan
            if (bukuTersedia == false) {
                System.out.printf("Buku %s oleh %s tidak ditemukan\n", judul, penulis);
            }
        }
    }

    // ---------------------- END DAFTAR PEMINJAM BUKU --------------------------

    // --------------------------- END MENU STAF --------------------------------

    // ------------------------ START MENU ANGGOTA ------------------------------

    // ------------------------ START MENU PEMINJAMAN ---------------------------

    // Method ini digunakan untuk melakukan peminjaman buku oleh anggota
    public static void menuPeminjaman() {
        System.out.println("---------- Peminjaman Buku ----------");
        System.out.print("Judul Buku: ");
        String judul = input.nextLine();
        System.out.print("Penulis Buku: ");
        String penulis = input.nextLine();
        System.out.print("Tanggal Peminjaman: ");
        String tanggalPinjam = input.nextLine();

        // Saat buku boleh dipinjam maka cetak pesan berikut
        System.out.println(bolehPinjamBuku(judul, penulis, tanggalPinjam));
    }

    // Method ini menunjukkan apakah sebuah buku boleh dipinjam atau tidak
    public static String bolehPinjamBuku(String judul, String penulis, String tanggalPinjam) {
        // STEP 1 : Cek buku ada di dalam SistakaNG atau tidak
        for (Buku bk : daftarBuku) {
            String judulBukuAda = bk.getJudul().toLowerCase(); // INSENSITIVE CASE
            String penulisBukuAda = bk.getPenulis().toLowerCase(); 
            // Saat buku terdaftar di dalam SistakaNG maka cek ke STEP selanjutnya
            if (judulBukuAda.equals(judul.toLowerCase()) && penulisBukuAda.equals(penulis.toLowerCase())) { 
                // STEP 2 : Cek stok buku
                // Saat stok buku lebih atau sama dengan 1 maka buku dapat dipinjam
                if (bk.getStok() >= 1) {
                    // Lakukan peminjaman buku
                    return String.format(penggunaLoggedIn.pinjam(bk, tanggalPinjam));
                } else {
                    return String.format("Buku %s oleh %s tidak tersedia", judul, penulis);
                }
            }
        }
        // Saat buku tidak ada di dalam SistakaNG
        return String.format("Buku %s oleh %s tidak ditemukan", judul, penulis);
    }

    // ------------------------ END MENU PEMINJAMAN ---------------------------

    // ------------------------ START MENU PENGEMBALIAN ---------------------------
    
    // Method ini digunakan untuk melakukan pengembalian buku oleh anggota
    public static void menuPengembalian() {
        System.out.println("---------- Pengembalian Buku ----------");
        System.out.print("Judul Buku: ");
        String judul = input.nextLine();
        System.out.print("Penulis Buku: ");
        String penulis = input.nextLine();
        System.out.print("Tanggal Pengembalian: ");
        String tanggalPengembalian = input.nextLine();

        // Saat buku dapat dikembalikan maka cetak pesan berikut
        System.out.println(bukuDapatDikembalikan(judul, penulis, tanggalPengembalian));   
    }

    // Method ini menunjukkan apakah sebuah buku boleh dikembalikan atau tidak
    public static String bukuDapatDikembalikan(String judul, String penulis, String tanggalPengembalian) {
        // STEP 1 : Cek apakah buku ada di dalam daftar buku SistakaNG
        // Saat buku dalam SistakaNG masih kosong maka langsung kembalikan buku tidak ada
        if (daftarBuku == null || daftarBuku.size() == 0) {
            return String.format("Buku %s oleh %s tidak ditemukan", judul, penulis);
        }
        // Lakukan pengecekan jika sudah ada buku yang terdaftar dalam SistakaNG
        for (Buku bk : daftarBuku) {
            String judulTerdaftar = bk.getJudul().toLowerCase(); // INSENSITIVE CASE
            String penulisTerdaftar = bk.getPenulis().toLowerCase();
            if (judulTerdaftar.equals(judul.toLowerCase()) && penulisTerdaftar.equals(penulis.toLowerCase())) { 
                // Saat buku terdaftar di dalam perpustakaan maka pengembalian dapat dilaksanakan
                return String.format(penggunaLoggedIn.kembali(bk, tanggalPengembalian));
            }
        } 
        // Saat buku tidak ada perpustakaan
        return String.format("Buku %s oleh %s tidak ditemukan", judul, penulis);
    }

    // --------------------------- END MENU PENGEMBALIAN ---------------------------

    // -------------------------- START MENU PEMBAYARAN ----------------------------

    // Method ini digunakan untuk melangsungkan pembayaran denda anggota
    public static void menuPembayaran() {
        System.out.println("---------- Pembayaran Denda ----------");
        System.out.print("Jumlah: ");
        long jumlah = Long.parseLong(input.nextLine());

        // Lakukan pembayaran denda yang sesuai dan tampilkan pesan dari pembayaran
        System.out.println(penggunaLoggedIn.bayarDenda(jumlah));   
    }

    // -------------------------- END MENU PEMBAYARAN ------------------------------

    // -------------------------- START DETAIL ANGGOTA -----------------------------

    // Method ini digunakan utnuk menampilkan detail angggota sesuai user yang login
    public static void detailAnggota() {
        penggunaLoggedIn.detail();
    }

    // ---------------------------- END DETAIL ANGGOTA -----------------------------
}
