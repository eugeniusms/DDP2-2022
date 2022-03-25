package assignments.assignment2;

// Import Java Library
import java.util.*;
import java.util.HashMap;
import java.util.Scanner;

public class Library {
    // Inisiasi charToValue and valueToChar untuk generateID anggota
    static HashMap<Character, Integer> charToValue = new HashMap<>(36);
    static char[] valueToChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    // Inisiasi Scanner input
    private Scanner input = new Scanner(System.in);

    // Menginisiasi array dan counter dari Member, Book, dan Category
    // Menginisiasikan array penyimpan data Member beserta penghitung jumlah member
    static int memberCounter = 0;
    private static Member[] members;
    // Menginisiasikan array penyimpan data Book beserta penghitung jumlah buku
    static int bookCounter = 0;
    private static Book[] books;
    // Menginisiasikan array penyimpan data Category beserta penghitung jumlah kategori
    static int categoryCounter = 0;
    private static Category[] categories;

    // Program utama dari Libray berjalan di method main
    public static void main(String[] args) {
        // Membentuk charToValue
        buildMapCharToValue();
        Library program = new Library();
        // Inisiasi Member, Category perpustakaan
        members = new Member[1];
        categories = new Category[1];
        books = new Book[1];
        // Memulai menu
        program.menu();
    }

    /* Membuat susunan tampilan untuk memudahkan pengguna dalam menggunakan program
     * method menu() mengambil perintah dari pengguna yaitu:
     * 1) Menambah Anggota
     * 2) Menambah Kategori
     * 3) Menambah Buku
     * 4) Peminjaman Buku
     * 5) Pengembalian Buku
     * 6) Pembayaran Denda
     * 7) Menampilkan Detail Anggota
     * 8) Menampilkan Peringkat 3 Teratas Anggota 
     * 99) Keluar Program
     * Selain perintah di atas maka program akan mencetak "Menu tidak dikenal!"
     */ 
    public void menu() {
        int command = 0;
        boolean hasChosenExit = false;
        // Menampilkan tampilan susunan perintah
        System.out.println("Selamat Datang di Sistem Perpustakaan SistakaNG!");
        while (!hasChosenExit) {
            System.out.println("================ Menu Utama ================\n");
            System.out.println("1. Tambah Anggota");
            System.out.println("2. Tambah Kategori");
            System.out.println("3. Tambah Buku");
            System.out.println("4. Peminjaman");
            System.out.println("5. Pengembalian");
            System.out.println("6. Pembayaran Denda");
            System.out.println("7. Detail Anggota");
            System.out.println("8. 3 Peringkat Pertama");
            System.out.println("99. Keluar");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();

            // Mengambil command dan mengarahkan ke fungsi masing-masing
            if (command == 1) {
                tambahAnggota();
            } else if (command == 2) {
                tambahKategori();
            } else if (command == 3) {
                tambahBuku();
            } else if (command == 4) {
                peminjaman();
            } else if (command == 5) {
                pengembalian();
            } else if (command == 6) {
                sBayarDenda();
            } else if (command == 7) {
                detailAnggota();
            } else if (command == 8) {
                rankingAnggota();
            } else if (command == 99) {
                System.out.println("Terima kasih telah menggunakan SistakaNG!");
                hasChosenExit = true;
            } else {
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }
        // Menutup Scanner
        input.close();
    }

    ///////////////////////////////////// MULAI TAMBAH ANGGOTA ////////////////////////////////////

    /* Method ini digunakan untuk menambahkan anggota baru perpustakaan
     * terdapat beberapa langkah untuk menambahkan anggota baru, yaitu
     * 1) Pengambilan Data Pribadi
     * 2) Penyusunan ID
     * 3) Pengiriman Object ke Array members
     * Saat data pribadi tidak sesuai maka program akan menolak masukan
     * dari pengguna dan meminta masukan kembali
     */
    public void tambahAnggota() {
        System.out.println("---------- Tambah Anggota ----------");

        // Mengambil input dari user mengenai data diri
        System.out.print("Nama: ");
        String namaAnggota = input.nextLine();
        System.out.print("Program Studi (SIK/SSI/MIK/MTI/DIK): ");
        String programStudi = input.nextLine();
        System.out.print("Angkatan: ");
        String angkatan = input.nextLine();
        System.out.print("Tanggal Lahir (dd/mm/yyyy): ");
        String tanggalLahir = input.nextLine();

        // Inisiasi beberapa atribut member
        int totalPointAnggota = 0;
        long dendaAnggota = 0;

        // Membuat dan menyusun ID anggota berdasarkan masukan user
        String idAnggota = generateId(programStudi, angkatan, tanggalLahir);

        // Ketika idAnggota valid maka cetak penambahan anggota berhasil dengan format berikut
        if (idAnggota != "invalid") {
            System.out.printf("Member %s berhasil ditambahkan dengan data:\nID Anggota: %s\n",
                                namaAnggota, idAnggota);
            System.out.printf("Nama Anggota: %s\nTotal Point: %d\nDenda: %d\n",
                                namaAnggota, totalPointAnggota, dendaAnggota);

            // Menambahkan member baru sebagai suatu object Member
            if (memberCounter == 0) {
                // Saat belum ada anggota sama sekali
                members[0] = new Member(idAnggota, namaAnggota, tanggalLahir, programStudi, angkatan, dendaAnggota, totalPointAnggota);
            } else {
                // Saat sudah ada anggota
                members = Arrays.copyOf(members, members.length+1);
                members[members.length-1] = new Member(idAnggota, namaAnggota, tanggalLahir, programStudi, angkatan, dendaAnggota, totalPointAnggota);
            }
            memberCounter++;

        } else {
            // Saat idAnggota invalid maka beritahu user tidak dapat menambahkan anggota
            System.out.println("Tidak dapat menambahkan anggota silahkan periksa kembali input anda!");
        }
    }

     /*
     * Method buildMapCodeToNumber adalah method untuk membuat mapping reference numbers Code 93
     */
    public static void buildMapCharToValue() {
        for (int count = 0; count < valueToChar.length; count++) {
            charToValue.put(valueToChar[count], count);
        }
    }

    /*
     * Method getCharFromValue adalah method yang akan mengembalikan Code 93 dari value yang diberikan
     */
    private static char getCharFromValue(int value) {
        return valueToChar[value];
    }

    /*
     * Method getValueFromChar adalah method yang akan mengembalikan value dari Code 93 yang diberikan
     */
    private static int getValueFromChar(char character) {
        return charToValue.get(character);
    }

    /*
     * Method generateId adalah method untuk membuat ID keanggotaan perpustakaan
     * Parameter dan return type dari method ini tidak boleh diganti
     * Method ini mengecek valid tidaknya parameternya kemudian mengembalikan
     * string "ID Anggota: " dengan idAnggota diambil dari hasil checksum
     * dengan getCheckSum()
     */
    public static String generateId(String programStudi, String angkatan, String tanggalLahir){
        // Cek terlebih dahulu validitas dari parameter generateId dengan method checkInputValidity
        if (checkInputValidity(programStudi, angkatan, tanggalLahir) == true) {
            // Menyusun format ID Anggota sebelum ada checksum
            String idMahasiswa = programStudi + angkatan.substring(2) 
                                + tanggalLahir.substring(0,2) 
                                + tanggalLahir.substring(3,5)
                                + tanggalLahir.substring(8);  

            // Mengembalikan ID Anggota beserta checksum yang sudah didapati dengan method getChecksum
            return getChecksum(idMahasiswa);
        } else {
            // Saat checkInputValidity dari parameter tidak terpenuhi (tidak valid)
            return "invalid";
        }
    }

    /*
     * Method ini digunakan menjadi pengecekan input user dalam memasukkan data anggota
     * Beberapa langkah yang ada dalam tahapan pengecekan ini adalah:
     * 1. Pengecekan program studi sesuai dengan yang ada atau tidak
     * 2. Pengecekan tahun angkatan yang valid yaitu 2000 <= angkatan <= 2021
     * 3. Pengecekan panjang format tanggalLahir
     * 4. Pengecekan format "/" sebagai pemisah dd/mm/yyyy
     * 5. Pengecekan format tipe angka pada dd, mm, dan yyyy
     * 6. Pengecekan rentang tanggal, bulan, tahun yang sah
     * 
     * Ketika 6 pengecekan di atas terpenuhi maka checkInputValidity() akan mengembalikan
     * true yang berarti input oleh user adalah valid, tetapi jika ditemukan pengecekan
     * yang tidak terpenuhi maka checkInputValidity() akan mengembalikan false
     */
    public static boolean checkInputValidity(String programStudi, String angkatan, String tanggalLahir) {
        // 1. Cek validitas program studi sesuai dengan yang ada 
        if (!programStudi.equals("SIK") && !programStudi.equals("SSI")
            && !programStudi.equals("MIK") && !programStudi.equals("MTI")
            && !programStudi.equals("DIK")) {
            return false;
        }

        // 2. Cek validitas tahun angkatan yang valid
        if (Integer.parseInt(angkatan) > 2021 || Integer.parseInt(angkatan) < 2000) {
            return false;
        }

        // 3. Cek panjang string tanggalLahir
        if (tanggalLahir.length() != 10) {
            return false;
        }

        // 4. Cek pastikan index 2 dan index 5 adalah "/"
        if (tanggalLahir.charAt(2) != '/' || tanggalLahir.charAt(5) != '/') {
            return false;
        }

        // 5. Cek selain "/" merupakan integer dengan catch error parseInt, 
        try {
            // Mengubah format penanggalan String -> Integer
            int tanggal = Integer.parseInt(tanggalLahir.substring(0,2));
            int bulan = Integer.parseInt(tanggalLahir.substring(3,5));
            int tahun = Integer.parseInt(tanggalLahir.substring(6,10));

            // 6. Cek penanggalan sesuai 1 <= tanggal <= 31, 1 <= bulan <= 12, tahun <= 2022
            if (tanggal < 1 || tanggal > 31 || bulan < 1 || bulan > 12 || tahun > 2022) {
                return false;
            }
        } catch (Exception e) {
            // Jika error maka tidak bisa diparseInt -> tidak valid
            return false;
        }

        // Ketika semua syarat terpenuhi -> valid
        return true;
    }

    /*
     * Method ini digunakan untuk mengubah bentuk ID Anggota tanpa checksum
     * menjadi ID Anggota yang sudah dilengkapi dengan checksum, penerapan
     * method ini ada saat:
     * 1. Membuat ID Anggota setelah didapati data anggota
     * 2. Memvalidasi kebenaran format ID Anggota 
     * 
     * Method dijalankan dengan perulangan i menaik digunakan untuk mengambil 
     * digit ID, kemudian dikalikan counter menurun dari:
     * 11 -> 1 untuk checksum "C"
     * 12 -> 1 untuk checksum "K" 
     * 
     * Total dari hasil penjumlahan dimodulo 36 untuk getCharFromValue() dari 
     * hasil modulonya sehingga bisa ditambahkan ke ID Anggota
     */
    public static String getChecksum(String idMahasiswa) {
        // Memulai generate checksum "C"
        // Inisiasi counter dan total
        int counter = 11;  
        int total = 0;
        // Mendapati hasil jumlah dari perkalian counter * getValueFromChar(digit)
        for (int i = 0; i < idMahasiswa.length(); i++) {
            char digit = idMahasiswa.charAt(i);
            total += counter * getValueFromChar(digit);
            counter--;
        }      
        // Menambahkan checksum "C"
        idMahasiswa += getCharFromValue(total % 36); // add checksum "C"

        // Memulai generate checksum "K"
        // Inisiasi counter dan total
        counter = 12;
        total = 0;
        // Mendapati hasil jumlah dari perkalian counter * getValueFromChar(digit)
        for (int i = 0; i < idMahasiswa.length(); i++) {
            char digit = idMahasiswa.charAt(i);
            total += counter * getValueFromChar(digit);
            counter--;
        }
        // Menambahkan checksum "K"
        idMahasiswa += getCharFromValue(total % 36); // add checksum "K"

        // Mengembalikan idMahasiswa yang sudah berisi checksum di 2 digit terakhir
        return idMahasiswa;
    }

    ////////////////////////////////////////// AKHIRI TAMBAH ANGGOTA //////////////////////////////

    ////////////////////////////////////////// MULAI TAMBAH KATEGORI //////////////////////////////

    // Method ini digunakan untuk menambah kategori buku dalam perpustakaan
    public void tambahKategori() {
        System.out.println("---------- Tambah Kategori ----------");

        // Mengambil input dari user mengenai kategori ditambahkan
        System.out.print("Nama Kategori: ");
        String namaKategori = input.nextLine();
        System.out.print("Point: ");
        String pointKategoriString = input.nextLine();
        int pointKategori = Integer.valueOf(pointKategoriString);

        // Saat jumlah kategori masih 0 dapat langsung ditambahkan
        if (categoryCounter == 0) {
            // Langsung memasukkan kategori baru ke dalam categories
            categories[0] = new Category(namaKategori, pointKategori);
            System.out.printf("Kategori %s dengan %d point berhasil ditambahkan", namaKategori, pointKategori);
            System.out.println();

            // Menambahkan jumlah kategori yang ada
            categoryCounter++;
        } else {
            // Melakukan pengecekan apakah kategori sudah terdaftar atau belum
            boolean existCategory = false;
            String existName = "";
            for (Category c: categories) {
                if (c.getName().toLowerCase().equals(namaKategori.toLowerCase())) {
                    // Saat kategori ada maka existCategory menjadi true, dan existName adalah naam kategori ada
                    existCategory = true;
                    existName = c.getName();
                }
            }

            // Ketika kategori belum ada maka kategori baru ditambahkan
            if (existCategory == false) {
                // Menambah panjang array dan menambahkan kategori baru
                categories = Arrays.copyOf(categories, categories.length+1);
                categories[categories.length-1] = new Category(namaKategori, pointKategori);
                // Mencetak notifikasi berhasil ditambahkan
                System.out.printf("Kategori %s dengan %d point berhasil ditambahkan", namaKategori, pointKategori);
                System.out.println();
                // Menambahkan jumlah kategori yang ada
                categoryCounter++;
            } else {
                // Ketika kategori sudah ada maka kategori tidak dapat ditambahkan
                System.out.printf("Kategori %s sudah pernah ditambahkan", existName);
                System.out.println();
            }
        }
    }

    ////////////////////////////////////////// AKHIRI TAMBAH KATEGORI /////////////////////////////

    ////////////////////////////////////////// MULAI TAMBAH BUKU //////////////////////////////////

    /* Method ini digunakan untuk menambah buku baru ke dalam perpustakaan
     * terdapat beberapa langkah untuk penambahan buku baru, yaitu:
     * 1) Pengambilan Masukan Pengguna
     * 2) Cek Validitas Buku yang Akan Ditambahkan
     * Untuk pengecekan validitas buku yang akan ditambahkan, tahap-tahapnya adalah
     * 1) Pengecekan Keberadaan Buku Dengan Judul dan Penulis Sama
     * 2) Pengecekan Keberadaan Kategori 
     * 3) Pengecekan Stok Buku yang Ditambahkan
     * Saat semua tahap terpenuhi, maka buku baru dapat ditambahkan
     */
    public void tambahBuku() {
        System.out.println("---------- Tambah Buku ----------");

        // Mengambil input dari user
        System.out.print("Judul: ");
        String judulBuku = input.nextLine();
        System.out.print("Penulis: ");
        String penulisBuku = input.nextLine();
        System.out.print("Penerbit: ");
        String penerbitBuku = input.nextLine();
        System.out.print("Kategori: ");
        String kategoriBuku = input.nextLine();
        System.out.print("Stok: ");
        String stokBukuString = input.nextLine();
        int stokBuku = Integer.valueOf(stokBukuString);

        System.out.println(cekValiditasBuku(judulBuku, penulisBuku, penerbitBuku, kategoriBuku, stokBuku));
    }

    // Method untuk mengecek validitas buku yang diinput user
    public String cekValiditasBuku(String judul, String penulis, String penerbit, String kategori, int stok) {

        // TAHAP 1
        // Berisi pengecekan buku dengan judul dan penulis sama, untuk buku pertama maka pengecekan ini dilewati
        if (bookCounter > 0) {
            // Tidak boleh ada Buku dengan judul dan penulis yang sama (case insensitive)
            for (Book b: books) {
                // Saat ditemui judul dan penulis yang sama maka kembalikan kata-kata berikut
                if (b.getTitle().toLowerCase().equals(judul.toLowerCase())
                    && b.getAuthor().toLowerCase().equals(penulis.toLowerCase())) {
                        return ("Buku " + judul + " oleh " + penulis + " sudah pernah ditambahkan");
                    }
            }
        }

        // TAHAP 2
        // Kategori juga harus kategori yang sudah terdaftar (case insensitive) pada sistem
        // Cek setiap kategori terdaftar
        boolean checkCategory = false;
        Category kategoriAda = new Category("", 0);
        for (Category c: categories) {
            if (c.getName().toLowerCase().equals(kategori.toLowerCase())) {
                checkCategory = true;
                // Memasukkan kategori c object ke dalam kategoriAda
                kategoriAda = c;
            }
        }
        // Ketika checkCategory == false -> kategori tidak terdaftar kembalikan kata-kata
        if (checkCategory == false) {
            return ("Kategori " + kategori + " tidak ditemukan");
        }

        // TAHAP 3
        // Stok buku harus lebih dari 0, ketika dibawah nol maka kembalikan
        if (stok <= 0) {
            return ("Stok harus lebih dari 0");
        }

        // Setelah semua terlewati maka buku ditambahkan valid sehingga tambahkan buku dan cetak
        if (bookCounter == 0) {
            // Ketika masih buku pertama maka langsung saja
            books[0] = new Book(judul, penulis, penerbit, stok, kategoriAda);
        } else {
            // Ketika buku lebih dari satu
            books = Arrays.copyOf(books, books.length+1);
            books[books.length-1] = new Book(judul, penulis, penerbit, stok, kategoriAda);
        }
        // Menambahkan bookCounter -> jumlah buku terdata
        bookCounter++;

        // Mengembalikan buku berhasil ditambahkan
        return ("Buku " + judul + " oleh " + penulis + " berhasil ditambahkan");
    } 

    ////////////////////////////////////////// AKHIRI TAMBAH BUKU /////////////////////////////////

    ////////////////////////////////////////// MULAI PEMINJAMAN ///////////////////////////////////

    /* Method ini digunakan untuk akses peminjaman buku oleh anggota
     * terdapat beberapa langkah peminjaman buku yang harus dipenuhi:
     * 1) Pengambilan ID Anggota dan Buku serta Tanggal Pinjam
     * 2) Pengecekan Peminjaman Buku 
     * Untuk pengecekan peminjaman buku terdapat beberapa tahap validasi:
     * 1) Pengecekan Keberadaan ID Member
     * 2) Pengecekan Keberadaan Buku
     * 3) Pengecekan Stok Buku
     * 4) Pengecekan Peminjaman Aktif (Buku Sedang Dipinjam)
     * 5) Pengecekan Denda
     * 6) Pengecekan Buku Sedang Dipinjam Sendiri
     * Ketika semua tahap pengecekan dilewati maka peminjaman buku 
     * dapat dilakukan dan buku masuk ke bookLoans Member
     */
    public void peminjaman() {
        System.out.println("---------- Peminjaman Buku ----------");

        // Mengambil input dari user untuk peminjaman buku
        System.out.print("ID Anggota: ");
        String idAnggota = input.nextLine();
        System.out.print("Judul Buku: ");
        String judulBuku = input.nextLine();
        System.out.print("Penulis Buku: ");
        String penulisBuku = input.nextLine();
        System.out.print("Tanggal Peminjaman: ");
        String tanggalPinjam = input.nextLine();

        System.out.println(cekValiditasPeminjaman(idAnggota, judulBuku, penulisBuku, tanggalPinjam));
    }

    // Method untuk memvalidasi peminjaman oleh user
    public String cekValiditasPeminjaman(String id, String judul, String penulis, String tanggalPinjam) {

        // TAHAP 1 : Pengecekan keberadaan ID
        // Tahap ini sekaligus mengambil info member dan object member
        Member cekMember = new Member("", "", "", "", "", (long) 0, 0);
        boolean isIDExist = false;
        for (Member m: members) {
            if (m.getID().equals(id)) {
                isIDExist = true;
                cekMember = m;
            }
        }
        // Saat ID tidak ada maka return peringatan
        if (isIDExist == false) {
            return ("Anggota dengan ID " + id + " tidak ditemukan");
        }

        // TAHAP 2 : Pengecekan keberadaan buku
        // Tahap ini sekaligus mengambil info stok buku dan object buku
        int stokTersedia = 0;
        Category kategoriAda = new Category("", 0);
        Book cekBook = new Book("", "", "", 0, kategoriAda);
        boolean isBookExist = false;
        for (Book b: books) {
            if (b.getTitle().toLowerCase().equals(judul.toLowerCase())
                    && b.getAuthor().toLowerCase().equals(penulis.toLowerCase())) {
                        isBookExist = true;
                        stokTersedia = b.getStok();
                        cekBook = b;
                    } 
        }
        // Saat buku tidak ada maka return peringatan
        if (isBookExist == false) {
            return ("Buku " + judul + " oleh " + penulis + " tidak ditemukan");
        }

        // TAHAP 3 : Pengecekan stok buku
        // Saat stok buku kurang dari 1 maka cetak pesan
        if (stokTersedia <= 0) {
            return ("Buku " + judul + " oleh " + penulis + " tidak tersedia");
        }

        // TAHAP 4 : Pengecekan peminjaman aktif lebih dari 3 tidak diizinkan
        if (cekMember.getMasihDipinjam() >= 3) {
            return ("Jumlah buku yang sedang dipinjam sudah mencapai batas maksimal");
        }

        // TAHAP 5 : Cek denda, jika denda >= 5000 maka cetak peringatan
        if (cekMember.getDenda() >= 5000) {
            return ("Denda lebih dari Rp 5000");
        }

        // TAHAP 6 : Cek apakah bukunya sedang dipinjam sendiri
        // Saat dipinjam maka status meminjam buku == true
        if (cekMember.getMeminjamBuku(cekBook) == true) {
            return ("Buku " + judul + " oleh " + penulis + " sedang dipinjam");
        }

        // Ketika semua tahap sudah terlewati maka peminjaman dapat dilakukan oleh anggota
        cekMember.pinjam(cekBook, tanggalPinjam);
        // Status bookLoan anggota akan aktif true (sedang dipinjam)
        cekMember.setStatusBookLoan(cekBook, true);
        // Stok buku tersedia akan dikurangi    
        cekBook.setStok(cekBook.getStok()-1);
        // Cetak buku berhasil dipinjam
        return (cekMember.getName() + " berhasil meminjam " + judul + "!");
    }

    ////////////////////////////////////////// AKHIRI PEMINJAMAN //////////////////////////////////

    ////////////////////////////////////////// MULAI PENGEMBALIAN /////////////////////////////////

    /* Method ini digunakan untuk akses pengembalian buku oleh anggota
     * terdapat beberapa langkah pengembalian buku yaitu:
     * 1) Pengambilan ID Anggota, Buku, dan Tanggal Pengembalian
     * 2) Pengecekan Data Pengembalian
     * Untuk pengecekan data pengembalian terdapat beberapa langkah:
     * 1) Pengecekan Keberadaan ID Anggota
     * 2) Pengecekan Keberadaan Buku
     * 3) Pengecekan Buku Sedang Ia Pinjam atau Tidak
     * Ketika lolos dari semua tahap pengecekan maka buku dapat dikembalikan
     */
    public void pengembalian() {
        System.out.println("---------- Pengembalian Buku ----------");

        // Mengambil input dari user untuk pengembalian buku
        System.out.print("ID Anggota: ");
        String idAnggota = input.nextLine();
        System.out.print("Judul Buku: ");
        String judulBuku = input.nextLine();
        System.out.print("Penulis Buku: ");
        String penulisBuku = input.nextLine();
        System.out.print("Tanggal Pengembalian: ");
        String tanggalPengembalian = input.nextLine();

        System.out.println(cekValiditasPengembalian(idAnggota, judulBuku, penulisBuku, tanggalPengembalian));
    }

    // Method pengecekan pengembalian buku
    public String cekValiditasPengembalian(String id, String judul, String penulis, String tanggalPengembalian) {

        // TAHAP 1 : Cek keberadaan ID anggota
        // Tahap ini sekaligus mengambil info member dan object member
        Member cekMember = new Member("", "", "", "", "", (long) 0, 0);
        boolean isIDExist = false;
        for (Member m: members) {
            if (m.getID().equals(id)) {
                isIDExist = true;
                cekMember = m;
            }
        }
        // Saat ID tidak ada maka return peringatan
        if (isIDExist == false) {
            return ("Anggota dengan ID " + id + " tidak ditemukan");
        }

        // TAHAP 2 : Cek keberadaan buku
        // Jika buku tidak ditemukan maka munculkan pesan
        // Tahap ini sekaligus mengambil info stok buku dan object buku
        Category kategoriAda = new Category("", 0);
        Book cekBook = new Book("", "", "", 0, kategoriAda);
        boolean isBookExist = false;
        for (Book b: books) {
            if (b.getTitle().toLowerCase().equals(judul.toLowerCase())
                    && b.getAuthor().toLowerCase().equals(penulis.toLowerCase())) {
                        isBookExist = true;
                        cekBook = b;
                    } 
        }
        // Saat buku tidak ada maka return peringatan
        if (isBookExist == false) {
            return ("Buku " + judul + " oleh " + penulis + " tidak ditemukan");
        }

        // TAHAP 3 : Cek apakah buku sedang ia pinjam atau tidak
        // Jika buku tidak ia pinjam maka berikan pesan peringatan
        if (cekMember.getMeminjamBuku(cekBook) == false) {
            return ("Buku " + judul + " tidak sedang dipinjam");
        }

        // Ketika berhasil lolos dari 3 validasi tahap maka buku akan dikembalikan
        cekMember.kembali(cekBook, tanggalPengembalian);
        // Stok buku bertambah kembali
        cekBook.setStok(cekBook.getStok()+1);
        // Status bookLoan anggota kembali pulih ke false (tidak dipinjam)
        cekMember.setStatusBookLoan(cekBook, false);
        // Cetak info pengembalian
        return ("Buku " + judul + " berhasil dikembalikan oleh " + cekMember.getName() 
                + " dengan denda Rp " + cekMember.dendaBuku(cekBook) + "!");
    }

    ////////////////////////////////////////// AKHIRI PENGEMBALIAN ////////////////////////////////

    ////////////////////////////////////////// MULAI SBAYAR DENDA /////////////////////////////////

    /* Method ini digunakan untuk melangsungkan pembayaran denda anggota
     * beberapa langkah pembayaran denda adalah:
     * 1) Pengambilan Data User dan Uang Setoran
     * 2) Pengecekan Validitas Bayar
     * Untuk pengecekan validitas bayar terdapat beberapa tahap:
     * 1) Pengecekan Keberadaan ID Anggota
     * 2) Pengecakan Denda 0
     * Ketika semua terpenuhi maka pembayaran denda dapat dilakukan
     */
    public void sBayarDenda() {
        System.out.println("---------- Pembayaran Denda ----------");

        // Mengambil input dari user
        System.out.print("ID Anggota: ");
        String id = input.nextLine();
        System.out.print("Jumlah: ");
        String jumlahBayarString = input.nextLine();
        long jumlahBayar = Long.valueOf(jumlahBayarString);

        // Pengecekan validitas
        System.out.println(cekValiditasSBayarDenda(id, jumlahBayar));
    }

    // Method pengecekan validitas bayar
    public String cekValiditasSBayarDenda(String id, long jumlahBayar) {
        // Tahap-tahap validasi
        // TAHAP 1 : Cek keberadaan ID Anggota
        // Tahap ini sekaligus mengambil info member dan object member
        Member cekMember = new Member("", "", "", "", "", (long) 0, 0);
        boolean isIDExist = false;
        for (Member m: members) {
            if (m.getID().equals(id)) {
                isIDExist = true;
                cekMember = m;
            }
        }
        // Saat ID tidak ada maka return peringatan
        if (isIDExist == false) {
            return ("Anggota dengan ID " + id + " tidak ditemukan");
        }

        // TAHAP 2 : Cek denda 0 
        if (cekMember.getDenda() == 0) {
            return (cekMember.getName() + " tidak memiliki denda");
        }

        // Ketika setiap pengecekan terlewati maka pembayaran denda sukses dilakukan
        long dendaMember = cekMember.getDenda();
        // Saat member membayar sebagian dendanya saja
        if (jumlahBayar < dendaMember) {
            cekMember.setDenda(dendaMember-jumlahBayar);
            return (cekMember.getName() + " berhasil membayar denda sebesar Rp " + jumlahBayar 
                        + "\nSisa denda saat ini: Rp " + cekMember.getDenda());
        } else {
            //Ketika membear melunasi dendanya
            cekMember.setDenda(0);
            return (cekMember.getName() + " berhasil membayar lunas denda\nJumlah kembalian: Rp " 
                        + (jumlahBayar - dendaMember));
        }
    }

    ////////////////////////////////////////// AKHIRI SBAYAR DENDA ////////////////////////////////

    ////////////////////////////////////////// MULAI DETAIL ANGGOTA ///////////////////////////////

    /* Method ini digunakan untuk memberikan tampilan detail anggota
     * terdapat beberapa langkah dalam menampilkan detail anggota
     * 1) Mengambil Data ID Anggota
     * 2) Pengecekan Keberadaan ID 
     * 3) Pencetakan Tampilan Detail Anggota yang Komprehensif
     */
    public void detailAnggota() {
        System.out.println("---------- Detail Anggota ----------");

        // Mengambil input dari user
        System.out.print("ID Anggota: ");
        String id = input.nextLine();

        if (cekExistID(id) == true) {
            // KOSONG (SUDAH DILAKUKAN PENCETAKAN DETAIL)
        } else {
            System.out.println("Anggota dengan ID " + id + " tidak ditemukan");
        }
        
    }

    // Mengecek keberadaan ID member
    public boolean cekExistID(String id) {
        for (Member m: members) {
            if (m.getID().equals(id)) {
                // Mencetak detail dari anggota
                cetakDetail(m);
                return true;
            }
        }
        return false;
    }

    // Mencetak tampilan detail Anggota
    public void cetakDetail(Member anggota) {
        System.out.println("ID Anggota: " + anggota.getID() + "\n"
                            + "Nama Anggota: " + anggota.getName() + "\n"
                            + "Total Point: " + anggota.getPoint() + "\n"
                            + "Denda: " + anggota.getDenda());
        System.out.println("Riwayat Peminjaman Buku :");
        // Mencetak riwayat buku yang dipinjam
        int counter = 1;
        for (BookLoan bl: anggota.getBookLoan()) {
            // Menandai buku yang dipinjam
            System.out.printf("—------------- %d —-------------\n", counter);

            // Mencetak tampilan layar informatif
            System.out.println("Judul Buku: " + bl.getBook().getTitle() + "\n"
                                + "Penulis Buku: " + bl.getBook().getAuthor() + "\n"
                                + "Penerbit Buku: " + bl.getBook().getPublisher() + "\n"
                                + "Kategori: " + bl.getBook().getCategory().getName() + "\n"
                                + "Point: " + bl.getBook().getCategory().getPoint() + "\n"
                                + "Tanggal Peminjaman: " + bl.getLoanDate() + "\n"
                                + "Tanggal Pengembalian: " + bl.getReturnDate() + "\n" 
                                + "Denda: Rp " + bl.getDenda());
            counter++;
        }
    }

    ////////////////////////////////////////// AKHIRI DETAIL ANGGOTA //////////////////////////////

    ////////////////////////////////////////// MULAI RANKING ANGGOTA //////////////////////////////

    /* Method ini digunakan untuk meranking anggota sesuai poin-poinnya
     * Terdapat beberapa langkah dalam meranking anggota, yaitu:
     * 1) Menyalin Array Member Baru
     * 2) Mengurutkan Member Berdasarkan Character
     * 3) Mengurutkan Member Berdasarkan Point
     * 4) Mengambil Member 1 - 3 Terdepan
     */
    public void rankingAnggota() {
        // Inisiasikan array members
        Member[] membersCopy = Arrays.copyOf(members, members.length);

        // Ketika belum ada anggota terdaftar maka cetak 
        if (membersCopy[0] == null) {
            System.out.println("Belum ada anggota yang terdaftar pada sistem");
        } else {
            // Saat sudah ada anggota terdaftar

            // Urutkan berdasarkan nama anggota (bubbleSort)
            for (int j = 0; j < membersCopy.length; j++) {
                for (int i = j + 1; i < membersCopy.length; i++) {
                    // Comparing adjacent strings
                    if (membersCopy[i].getName().compareTo(membersCopy[j].getName()) < 0) {
                        Member temp = membersCopy[j];
                        membersCopy[j] = membersCopy[i];
                        membersCopy[i] = temp;
                    }
                }
            }

            // Urutkan berdasarkan point anggota (bubbleSort)
            for (int j = 0; j < membersCopy.length; j++) {
                for (int i = j + 1; i < membersCopy.length; i++) {
                    // Swap jika lebih besar
                    if (membersCopy[i].getPoint() > membersCopy[j].getPoint()) {
                        Member temp = membersCopy[j];
                        membersCopy[j] = membersCopy[i];
                        membersCopy[i] = temp;
                    }
                }
            }

            // Ambil 3 teratas saja (atau saat > 3 sudah tidak terhitung)
            System.out.println("---------- Peringkat Anggota ----------");
            int count3Atas = 0;
            for (Member m: membersCopy) {
                if (count3Atas < 3) {
                    // Cetak tampilan layar
                    System.out.printf("—------------- %d —-------------\n", count3Atas+1);
                    System.out.println("ID Anggota: " + m.getID() + "\n"
                                        + "Nama Anggota: " + m.getName() + "\n"
                                        + "Total Point: " + m.getPoint() + "\n"
                                        + "Denda: " + m.getDenda());
                }
                count3Atas++;
            }
        }
    }

    ////////////////////////////////////////// AKHIRI RANKING ANGGOTA /////////////////////////////
}
