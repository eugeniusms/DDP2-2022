// package assignments.assignment1;

import java.util.HashMap;
import java.util.Scanner;

public class IdGenerator {
    static HashMap<Character, Integer> charToValue = new HashMap<>(36);
    static char[] valueToChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /*
     * Code pada method main tidak boleh diganti sama sekali!
     */
    public static void main(String[] args) {
        buildMapCharToValue();
        Scanner input = new Scanner(System.in);
        System.out.println("Selamat Datang di Perpustakaan!");

        boolean shouldTerminateProgram = false;

        while (!shouldTerminateProgram) {
            printMenu();
            System.out.print("Menu yang anda pilih: ");
            int menu = input.nextInt();
            input.nextLine();

            if (menu == 1) {
                System.out.print("Program Studi: ");
                String programStudi = input.nextLine();
                System.out.print("Angkatan: ");
                String angkatan = input.nextLine();
                System.out.print("Tanggal Lahir: ");
                String tanggalLahir = input.nextLine();

                System.out.println(generateId(programStudi, angkatan, tanggalLahir));
            } else if (menu == 2) {
                System.out.print("ID Anggota: ");
                String idAnggota = input.nextLine();

                System.out.print("Validitas: ");
                System.out.println(checkValidity(idAnggota));
            } else {
                shouldTerminateProgram = true;
                System.out.println("Sampai jumpa!");
            }
        }

        input.close();
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

    private static void printMenu() {
        System.out.println("--------------------------------------------");
        System.out.println("Menu yang tersedia:");
        System.out.println("1. Generate ID anggota untuk anggota baru");
        System.out.println("2. Check validitas ID anggota");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
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
            return "ID Anggota: " + getChecksum(idMahasiswa);
        } else {
            // Saat checkInputValidity dari parameter tidak terpenuhi (tidak valid)
            return "Input tidak valid!";
        }
    }

    /*
     * Method checkValidity adalah method untuk mengecek validitas ID keanggotaan perpustakaan
     * Parameter dan return type dari method ini tidak boleh diganti
     * Method ini digunakan untuk mengecek valditas ID Anggota, pengecekan
     * melewati beberapa tahap yaitu:
     * 1. Pengecekan panjang idAnggota = 13
     * 2. Pengecekan karakter idAnggota (Huruf UPPERCASE dan Angka)
     * 3. Pengecekan nilai checksum idAnggota
     * 
     * Jika salah satu syarat di atas tidak terpenuhi, maka akan 
     * dikembalikan nilai false dari method ini (tidak valid),
     * jika memenuhi semua syarat maka akan dikembalikan nilai true (valid)
     */
    public static boolean checkValidity(String idAnggota) {
        // 1. Cek panjang idAnggota
        if (idAnggota.length() != 13) {
            return false;
        }

        // 2. Try digunakan sebagai validator huruf UPPERCASE dan angka saja dalam array valueToChar
        try {
            // Pengecekan dilakukan untuk semua karakter, jika ada dalam valueToChar, continue perulangan
            for (int i = 0; i < idAnggota.length(); i++) {
                char digit = idAnggota.charAt(i);
                if (getValueFromChar(digit) >= 0) {
                    continue;
                }
            }
        } catch (Exception e) {
            // Error terjadi saat value di dalam valueToChar tidak ada (tidak memenuhi huruf UPPERCASE dan angka)
            return false;
        }

        // Mengambil string id tanpa checksum
        String withoutChecksum = idAnggota.substring(0, 11);

        // 3. Memasangkan apakah idAnggota sudah cocok dengan checksumnya dengan memanggil kembarannya
        if (idAnggota.equals(getChecksum(withoutChecksum))) {
            // Saat sudah cocok maka kembalikan true
            return true;
        }

        // Saat tidak memenuhi syarat maka kembalikan false
        return false;
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
    
}