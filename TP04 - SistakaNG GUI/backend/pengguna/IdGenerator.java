package assignments.assignment4.backend.pengguna;

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
        if (!isValidProgramStudi(programStudi)) return "Input tidak valid!";

        String angkatanCode = checkAndGetAngkatanCode(angkatan);
        if (angkatanCode.length() > 2) return angkatanCode; // Input tidak valid!

        String tanggalLahirCode = checkAndGetTanggalLahirCode(tanggalLahir);
        if (tanggalLahirCode.length() > 6) return tanggalLahirCode; // Input tidak valid!

        String nomorKeanggotaanStr = programStudi + angkatanCode + tanggalLahirCode;
        nomorKeanggotaanStr += getChecksum(nomorKeanggotaanStr); // Checksum "C"
        nomorKeanggotaanStr += getChecksum(nomorKeanggotaanStr); // Checksum "K"

        return "ID Anggota: " + nomorKeanggotaanStr;
    }

    private static boolean isValidProgramStudi(String programStudi) {
        if (programStudi.length() != 3) return false;

        if (programStudi.equals("SIK")) return true;
        if (programStudi.equals("SSI")) return true;
        if (programStudi.equals("MIK")) return true;
        if (programStudi.equals("MTI")) return true;
        if (programStudi.equals("DIK")) return true;

        return false;
    }

    private static String checkAndGetAngkatanCode(String angkatan) {
        if (angkatan.length() != 4) return "Input tidak valid!";
        if (Integer.parseInt(angkatan) < 2000 || Integer.parseInt(angkatan) > 2021) return "Input tidak valid!";
        return angkatan.substring(2);
    }

    private static boolean isNumerik(String word) {
        if (word.isEmpty()) return false;

        for (char c : word.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }

        return true;
    }

    private static String checkAndGetTanggalLahirCode(String tanggalLahir) {
        String[] arrOfTanggalLahir = tanggalLahir.split("/");

        // Cek apakah ada 3 input (untuk dd, mm, dan yyyy)
        if (arrOfTanggalLahir.length != 3) {
            return "Input tidak valid!";
        }

        // Cek apakah semuanya numerik
        for (String s : arrOfTanggalLahir) {
            if (!isNumerik(s)) {
                return "Input tidak valid!";
            }
        }

        // Cek apakah jumlah digitnya memenuhi aturan (2 digit untuk tanggal dan bulan, serta 4 digit untuk year)
        if (arrOfTanggalLahir[0].length() != 2 || arrOfTanggalLahir[1].length() != 2 || arrOfTanggalLahir[2].length() != 4) {
            return "Input tidak valid!";
        }

        // Cek apakah memenuhi aturan tanggal (tanggal antara 1 - 31)
        int tanggal = Integer.parseInt(arrOfTanggalLahir[0]);
        if (tanggal < 1 || tanggal > 31) {
            return "Input tidak valid!";
        }

        // Cek apakah memenuhi aturan bulan (bulan antara 1 - 12)
        int bulan = Integer.parseInt(arrOfTanggalLahir[1]);
        if (bulan < 1 || bulan > 12) {
            return "Input tidak valid!";
        }

        return arrOfTanggalLahir[0] + arrOfTanggalLahir[1] + arrOfTanggalLahir[2].substring(2);
    }

    private static char getChecksum(String data) {
        char[] arrayOfChar = data.toCharArray();

        int count = 0;
        for (int idx = 0; idx < arrayOfChar.length; idx++) {
            // Mendapatkan bobot
            int weight = arrayOfChar.length - idx;

            // Jumlah hasil kali value dan bobot setiap karakter
            count += getValueFromChar(arrayOfChar[idx]) * weight;
        }

        int checksum = count % 36;
        return getCharFromValue(checksum);
    }

    /*
     * Method checkValidity adalah method untuk mengecek validitas ID keanggotaan perpustakaan
     * Parameter dan return type dari method ini tidak boleh diganti
     */
    public static boolean checkValidity(String idAnggota) {
        if (idAnggota.length() != 13 || !isUpperCaseLetterOrNumerik(idAnggota)) return false;

        char checksumC = getChecksum(idAnggota.substring(0, 11));
        if (checksumC == idAnggota.charAt(11)) {
            char checksumK = getChecksum(idAnggota.substring(0, 12));
            if (checksumK == idAnggota.charAt(12)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isUpperCaseLetterOrNumerik(String word) {
        if (word.isEmpty()) return false;

        for (char c : word.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }

            if (Character.isLetter(c)) {
                if (!Character.isUpperCase(c)) {
                    return false;
                }
            }
        }

        return true;
    }
}
