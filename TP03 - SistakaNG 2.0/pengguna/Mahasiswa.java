package assignments.assignment3.pengguna;

import assignments.assignment3.buku.Buku;
import assignments.assignment3.buku.Peminjaman;

// Import Java Library
import java.util.HashMap;

// Kelas ini merupakan representasi dari mahasiswa yang menjadi pengguna SistakaNG
public class Mahasiswa extends Anggota {
    // Berikut merupakan attribute dari kelas Mahasiswa
    static HashMap<Character, Integer> charToValue = new HashMap<>(36);
    static char[] valueToChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    // Batas ini merupakan batas maksimal buku yang sedang dipinjam (belum dikembalikan)
    public static final int BATAS_JUMLAH_PEMINJAMAN_BUKU = 3;
    // Batas ini merupakan batas maksimal denda untuk mahasiswa dapat meminjam buku
    public static final long BATAS_MAKSIMAL_DENDA = 5000;
    private String tanggalLahir;
    private String programStudi;
    private String angkatan;

    // Constructor Mahasiswa
    public Mahasiswa(String nama, String tanggalLahir, String programStudi, String angkatan) {
        super("", nama);
        this.tanggalLahir = tanggalLahir;
        this.programStudi = programStudi;
        this.angkatan = angkatan;
        // Set ID
        this.setId(generateId());
    }

    // ------------------------------- TEMPLATE GENERATE ID START ---------------------------------
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

    // Method untuk generate ID mahasiswa
    protected String generateId() {
        buildMapCharToValue();
        if (!isValidProgramStudi(this.programStudi)) {
            System.out.println("Tidak dapat menambahkan anggota silahkan periksa kembali input anda!");
            return "";
        }
        String angkatanCode = checkAndGetAngkatanCode(this.angkatan);
        if (angkatanCode.length() > 2) {
            System.out.println("Tidak dapat menambahkan anggota silahkan periksa kembali input anda!"); // Input tidak valid!
            return "";
        }
        String tanggalLahirCode = checkAndGetTanggalLahirCode(this.tanggalLahir);
        if (tanggalLahirCode.length() > 6) {
            System.out.println("Tidak dapat menambahkan anggota silahkan periksa kembali input anda!"); // Input tidak valid!
            return "";
        } 
        String nomorKeanggotaanStr = programStudi + angkatanCode + tanggalLahirCode;
        nomorKeanggotaanStr += getChecksum(nomorKeanggotaanStr); // Checksum "C"
        nomorKeanggotaanStr += getChecksum(nomorKeanggotaanStr); // Checksum "K"

        // SUCCESS
        this.setId(nomorKeanggotaanStr);
        return nomorKeanggotaanStr;
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
    // ---------------------------------------- TEMPLATE GENERATE ID END -----------------------------------------

    // Method pinjam digunakan untuk memproses peminjaman yang dilakukan oleh mahasiswa
    public String pinjam(Buku buku, String tanggalPeminjaman) {
        // STEP 1 : Cek batas peminjaman aktif
        // Saat masih ada < 3 buku maka mahasiswa dapat lanjut le step 2
        if (this.getJumlahPeminjamanAktif() < BATAS_JUMLAH_PEMINJAMAN_BUKU) {
            // STEP 2 : Cek jumlah denda 
            // Saat masih di bawah batas maksimal denda maka dapat lanjut ke step 3
            if (this.getDenda() < BATAS_MAKSIMAL_DENDA) {
                // STEP 3 : Cek buku sedang ia pinjam
                // Saat buku yang sedang dipinjam kosong maka dapat langsung melakukan peminjaman
                if (this.getJumlahPeminjamanAktif() == 0) {

                    Peminjaman pinjaman = new Peminjaman(this, buku, tanggalPeminjaman);
                    this.tambahDaftarPinjaman(pinjaman);
                    // Pengurangan stok buku
                    buku.setStok(buku.getStok()-1);
                    // Menambahkan buku sedang dipinjam anggota ini
                    buku.tambahPeminjam(this);
                    // Menambahkan jumlah peminjaman aktif anggota
                    this.setJumlahPeminjamanAktif(this.getJumlahPeminjamanAktif() + 1);

                    return String.format("%s berhasil meminjam Buku %s!", this.getNama(), buku.getJudul());
                }
                // Cek apakah buku sedang dipinjam sendiri
                // Jika dalam daftar peminjam terdapat anggota ini maka peminjaman tidak bisa dilakukan
                if (buku.getPeminjam().contains(this)) { 
                    // Saat buku sedang ia pinjam, maka ia tidak dapat meminjamnya lagi
                    return String.format("Buku %s oleh %s sedang dipinjam", buku.getJudul(), buku.getPenulis());
                }

                // Saat buku tidak ia pinjam maka peminjaman dapat dilakukan
                Peminjaman pinjaman = new Peminjaman(this, buku, tanggalPeminjaman);
                this.tambahDaftarPinjaman(pinjaman);
                // Pengurangan stok buku
                buku.setStok(buku.getStok()-1);
                // Menambahkan buku sedang dipinjam anggota ini
                buku.tambahPeminjam(this);
                // Menambahkan jumlah peminjaman aktif anggota
                this.setJumlahPeminjamanAktif(this.getJumlahPeminjamanAktif() + 1);

                return String.format("%s berhasil meminjam Buku %s!", this.getNama(), buku.getJudul());
            } else {
                // Saat denda >= BATAS MAKSIMAL DENDA
                return String.format("Denda lebih dari Rp%d", BATAS_MAKSIMAL_DENDA);
            }
        } else {
            // Saat jumlah peminjaman buku melebihi kapasitas pinjaman
            return String.format("Jumlah buku yang sedang dipinjam sudah mencapai batas maksimal");
        }
    }

}
