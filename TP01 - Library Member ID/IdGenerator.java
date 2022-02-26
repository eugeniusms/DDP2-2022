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
     */
    public static String generateId(String programStudi, String angkatan, String tanggalLahir){
        // TODO: Tuliskan implementasi untuk membuat ID keanggotaan perpustakaan
        // Cek validitas masukan
        if (checkInputValidity(programStudi, angkatan, tanggalLahir) == true) {
            String idMahasiswa = programStudi + angkatan.substring(2) 
                                + tanggalLahir.substring(0,2) 
                                + tanggalLahir.substring(3,5)
                                + tanggalLahir.substring(8);  

            return "ID Anggota: " + getChecksum(idMahasiswa);
        } else {
            return "Input tidak valid!";
        }
    }

    /*
     * Method checkValidity adalah method untuk mengecek validitas ID keanggotaan perpustakaan
     * Parameter dan return type dari method ini tidak boleh diganti
     */
    public static boolean checkValidity(String idAnggota) {
        // TODO: Tuliskan implementasi untuk mengecek validitas ID keanggotaan perpustakaan
        //------PLEASE CHECK VALIDITAS DULU HURUF ANGKA PANJANG-------
        
        // Mengambil string id tanpa checksum
        String withoutChecksum = idAnggota.substring(0, 11);
        System.out.println("ID CEK: " + idAnggota + " | " + getChecksum(withoutChecksum));
        if (idAnggota.equals(getChecksum(withoutChecksum))) {
            return true;
        }
        return false;
    }

    // Method buatan sendiri cek validitas input generateID
    public static boolean checkInputValidity(String programStudi, String angkatan, String tanggalLahir) {
        // Cek validitas program studi
        if (!programStudi.equals("SIK") && !programStudi.equals("SSI")
            && !programStudi.equals("MIK") && !programStudi.equals("MTI")
            && !programStudi.equals("DIK")) {
            return false;
        } 
        System.out.println("LULUS CEK PRODI"); // HAPUS

        // Cek validitas angkatan
        if (Integer.parseInt(angkatan) > 2021 || Integer.parseInt(angkatan) < 2000) {
            return false;
        }
        System.out.println("LULUS CEK ANGKATAN"); // HAPUS

        // Cek validitas format tanggal
        // Cek panjang string tanggalLahir _ _ / _ _ / _ _ _ _
        if (tanggalLahir.length() != 10) {
            return false;
        }
        System.out.println("LULUS CEK PANJANG FORMAT LAHIR"); // HAPUS

        // Cek pastikan index 2 dan index 5 adalah "/"
        if (tanggalLahir.charAt(2) != '/' || tanggalLahir.charAt(5) != '/') {
            return false;
        }
        System.out.println("LULUS CEK FORMAT (/)"); // HAPUS

        // Cek selain "/" merupakan integer dengan catch error parseInt, 
        // jika error maka tidak bisa diparseInt -> tidak valid
        try {
            int tanggal = Integer.parseInt(tanggalLahir.substring(0,2));
            int bulan = Integer.parseInt(tanggalLahir.substring(3,5));
            int tahun = Integer.parseInt(tanggalLahir.substring(6,10));
            System.out.println("LULUS CEK ANGKA PADA FORMAT LAHIR"); // HAPUS

            // Cek penanggalan sesuai 1 <= tanggal <= 31, 1 <= bulan <= 12, tahun <= 2022
            if (tanggal < 1 || tanggal > 31 || bulan < 1 || bulan > 12 || tahun > 2022) {
                return false;
            }
            System.out.println("LULUS CEK KALENDAR"); // HAPUS
        } catch (Exception e) {
            return false;
        }

        // Ketika semua valid
        return true;
    }

    // Method buatan sendiri mencari checksum
    public static String getChecksum(String idMahasiswa) {
        // Memulai generate checksum "C"
        int counter = 11;  
        int total = 0;
        for (int i = 0; i < idMahasiswa.length(); i++) {
            char digit = idMahasiswa.charAt(i);
            total += counter * findIndex(digit);
            counter--;
        }      
        idMahasiswa += valueToChar[total % 36]; // add checksum "C"

        // Memulai generate checksum "K"
        counter = 12;
        total = 0;
        for (int i = 0; i < idMahasiswa.length(); i++) {
            char digit = idMahasiswa.charAt(i);
            total += counter * findIndex(digit);
            counter--;
        }
        idMahasiswa += valueToChar[total % 36]; // add checksum "K"
        return idMahasiswa;
    }
    
    // Method buatan sendiri cari index dari char dalam valueToChar
    public static int findIndex(char c) {
        for (int i = 0; i < valueToChar.length; i++) {
            if (valueToChar[i] == c) {
                return i;
            }
        }
        return -1; // ketika tidak ada
    }
}
