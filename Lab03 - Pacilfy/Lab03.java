import java.util.Scanner;
import java.util.Arrays;

public class Lab03 {
    /* Pacilfy merupakan program untuk menyetel musik yang ditambahkan user
     * User diminta memasukkan beberapa lagu yang ingin didengarkan, lalu
     * program dapat digunakan untuk:
     * 1) Menyetel ke lagu sebelumnya
     * 2) Menambahkan lagu ke dalam playlist
     * 3) Memberikan detail dari lagu yang didengarkan
     * 4) Menghapus lagu dari daftar playlist
     * 5) Menyetel ke lagu selanjutnya
     */
    // Inisiasi dari static variabel yang akan digunakan dalam program
    static int pointer = 0;
    static String[][] playlist = new String[1][4];
    static Scanner in = new Scanner(System.in);
    static int jumlahMusik = 0;

    /* Method ini digunakan untuk mengambil input user untuk pertama kali
     * lalu juga mengambil command dari user untuk melakukan kelima fitur 
     * yang sudah dijelaskan di atas
     */
    public static void main(String[] args) {
        System.out.println("Selamat Datang di Pacilfy!");

        // Inisialisasi playlist minimal 1 lagu dimasukkan ke dalamnya

        // Mengambil input lagu yang dimasukkan oleh user
        int tambahLaguCommand = 1;
        int count = 0;
        while (tambahLaguCommand == 1) {
            // Inisiasi array sementara untuk menyimpan lagu yang ditambahkan
            String[] laguBaru = new String[4];

            // Mengambil input user ke dalam variabel sesuai penamaan
            System.out.print("Judul : ");
            String judulLagu = in.next();
            System.out.print("Artist: ");
            String artistLagu = in.next();
            System.out.print("Album : ");
            String albumLagu = in.next();
            System.out.print("Tahun : ");
            String tahunLagu = in.next();
            
            // Memasukkan setiap nilai variabel ke dalam array sementara
            laguBaru[0] = judulLagu;
            laguBaru[1] = artistLagu;
            laguBaru[2] = albumLagu;
            laguBaru[3] = tahunLagu;

            // Memasukkan array sementara ke dalam array playlist
            playlist[count] = laguBaru;
            count++;

            // Mengambil command untuk menambahkan lagu atau tidak
            System.out.print("Lanjut menambahkan lagu?\n[1] Lanjut\n[0] Berhenti\nPerintah : ");
            tambahLaguCommand = in.nextInt();

            // Saat command = 1 maka lagu akan diambil lagi maka perpanjang array playlist
            if (tambahLaguCommand == 1) {
                // menambah elemen array
                playlist = Arrays.copyOf(playlist, playlist.length + 1);
            }
        }

        // Membuat tampilan Pacilfy di dalam CLI
        System.out.println("Pacilfy siap dimulai");
        System.out.println("\nSELAMAT DATANG DI\n");
        System.out.println(" /$$$$$$$                     /$$ /$$  /$$$$$$");
        System.out.println("| $$__  $$                   |__/| $$ /$$__  $$");
        System.out.println("| $$  \\ $$ /$$$$$$   /$$$$$$$ /$$| $$| $$  \\__//$$   /$$");
        System.out.println("| $$$$$$$/|____  $$ /$$_____/| $$| $$| $$$$   | $$  | $$");
        System.out.println("| $$____/  /$$$$$$$| $$      | $$| $$| $$_/   | $$  | $$");
        System.out.println("| $$      /$$__  $$| $$      | $$| $$| $$     | $$  | $$");
        System.out.println("| $$     |  $$$$$$$|  $$$$$$$| $$| $$| $$     |  $$$$$$$");
        System.out.println("|__/      \\_______/ \\_______/|__/|__/|__/      \\____  $$");
        System.out.println("                                               /$$  | $$");
        System.out.println("                                              |  $$$$$$/");
        System.out.println("                                               \\______/");

        // Inisiasi command default adalah 1
        String command = "1";

        // Program akan selalu mengambil input sampai memasukki command "0" -> break
        while (true){
            checkIsiLagu();
            // Menampilkan display dari Pacilfy
            display();

            // Mengambil input dari user untuk dicocokan sesuai command yang ada
            System.out.print("Command (0 untuk exit) : ");
            command = in.next();

            /* Pencocokan command sama seperti deskripsi di di atas program
             * saat command = "0" maka program berhenti dan mencetak ucapan
             * terima kasih. Saat command tidak ada dalam daftar maka cetak
             * command yang dimasukkan salah
             */
            if (command.equals("1")){
                prevMusic();
            }
            else if (command.equals("2")){
                addMusic();
            }
            else if (command.equals("3")){
                detailsMusic();
            }
            else if (command.equals("4")){
                deleteMusic();
            }
            else if (command.equals("5")){
                nextMusic();
            }
            else if (command.equals("0")){
                break;
            }
            else {
                System.out.println("Maaf, command yang anda masukan salah");
            }
        }
        System.out.println("Terima kasih sudah menggunakan Pacilfy!");
    }

    // Method untuk melanjutkan ke lagu selanjutnya
    private static void nextMusic() {
        /* Mengecek jika pointer saat ini adalah pointer terakhir dalam array maka
         * setel lagu dari playlist dengan pointer 0, jika tidak maka next seperti
         * biasa
         */
        if (pointer + 1 == playlist.length) {
            pointer = 0;
        } else {
            pointer++;
        }
    }

    // Method untuk menghapus lagu yang sedang didengar
    private static void deleteMusic() {
        // Mengambil panjang dari playlist saat ini
        int len = playlist.length;

        // Menginisiasi array sementara untuk menyimpan musik yang masih ada (tidak terhapus)
        String[][] newPlaylist = new String[len-1][4]; 
        
        // Memulai penyusunan array sementara, lagu pointer saat ini dilewati karena akan dihapus
        int count = 0;
        for (int i = 0; i < playlist.length; i++) {
            if (playlist[i][0].equals(playlist[pointer][0])) {
                continue;
            } else {
                // Mengisi array sementara dengan nilai playlist yang tidak terhapus
                newPlaylist[count] = playlist[i];
                count++;
            }
        }

        // Mengembalikan array sementara ke dalam array utama yaitu playlist untuk digunakan kembali
        playlist = Arrays.copyOf(newPlaylist, len-1);

        // Mengecek jika pointer saat ini berada di ujung akhir playlist maka setel lagu paling depan
        if (pointer == len - 1) {
            pointer = 0;
        }
    }

    // Method untuk melihat detail dari musik yang kita tentukan judulnya
    private static void detailsMusic() {
        // Mengambil judul lagu yang akan dicari detailnya
        System.out.print("Judul yang ingin dicari: ");
        String judulLagu = in.next();

        // Mengambil komponen array dari lagu yang dipilih menggunakan getMusic()
        String[] laguDipilih = getMusic(judulLagu, playlist);

        // Saat lagu ternyata tidak ditemukan maka cetak "Lagu tidak ditemukan"
        if (laguDipilih[0] == "NotFound") {
            System.out.println("Lagu tidak ditemukan");
        } else {
            // Saat lagu ditemukan maka cetak semua komponen dari lagu itu
            System.out.println("Data lagu:");
            System.out.printf("Judul : %s\n", laguDipilih[0]);
            System.out.printf("Artist: %s\n", laguDipilih[1]);
            System.out.printf("Album : %s\n", laguDipilih[2]);
            System.out.printf("Tahun : %s\n", laguDipilih[3]);
        }
    }

    // Method untuk menyetel lagu sebelumnya
    private static void prevMusic() {
        /* Mengecek jika pointer saat ini adalah pointer paling depan dalam array maka
         * setel lagu dari playlist dengan pointer terakhir, jika tidak maka prev seperti
         * biasa
         */
        if (pointer == 0) {
            pointer = playlist.length - 1;
        } else {
            pointer--;
        }
    }

    // Method untuk menambahkan musik ke dalam playlist yang didengar
    private static void addMusic() {
        // Menginisiasi array dari lagu baru
        String[] laguBaru = new String[4];

        // Mengambil masukkan dari user mengenai komponen dari lagu baru
        System.out.println("Silahkan masukkan lagu Anda");
        System.out.print("Judul : ");
        String judulLagu = in.next();
        System.out.print("Artist : ");
        String artistLagu = in.next();
        System.out.print("Album : ");
        String albumLagu = in.next();
        System.out.print("Tahun : ");
        String tahunLagu = in.next();
        
        // Memasukkan komponen yang didapat ke dalam array laguBaru
        laguBaru[0] = judulLagu;
        laguBaru[1] = artistLagu;
        laguBaru[2] = albumLagu;
        laguBaru[3] = tahunLagu;

        // Menambah panjang dari array playlist dan memasukkan lagu baru ke index terbelakang
        playlist = Arrays.copyOf(playlist, playlist.length + 1);
        playlist[playlist.length - 1] = laguBaru;
    }


    // Method ini digunakan untuk menampilkan tampilan dari Pacilfy setiap pemutaran lagunya 
    private static void display() {
        System.out.println();
        
        // Saat panjang dari playlist nol maka tidak ada lagu di dalam playlist
        if (playlist.length == 0) {
            System.out.println("Tidak ada lagu di dalam playlist");
        } else { 
            // Selain itu maka ada lagu di dalam playlist untuk diputar oleh user

            // Mencetak tampilan dari Pacilfy agar keren :D
            System.out.println("Currently Playing");

            // Mencetak lagu dengan artis dan judul yang sedang didengarkan
            String displayedMusic = " " + playlist[pointer][1] + " - " + playlist[pointer][0] + " ";

            // Mencetak command untuk dibaca oleh user
            String command = "|[1] prev |[2] add music |[3] details |[4] delete music |[5] next|";

            // Membuat tampilan box dari Pacilfy
            if (displayedMusic.length() < command.length()){
                int width = 62;
                String s = displayedMusic;

                int padSize = width - s.length();
                int padStart = s.length() + padSize / 2;

                s = String.format("%" + padStart + "s", s);
                s = String.format("%-" + width  + "s", s);

                System.out.println(new String(new char[66]).replace("\0", "="));
                System.out.println("= "+ s +" =");
                System.out.println(new String(new char[66]).replace("\0", "="));
                System.out.println(command);

                return;
            }
            System.out.println("=" + new String(new char[displayedMusic.length()]).replace("\0", "=") + "=");
            System.out.println("=" + displayedMusic + "=");
            System.out.println("=" + new String(new char[displayedMusic.length()]).replace("\0", "=") + "=");
            System.out.println(command);
        }
    }

    // Method ini digunakan untuk mengambil array dari musik yang ingin dicari
    private static String[] getMusic(String judul, String[][] playlist) {
        // Inisiasi lagu adalah array dari lagu yang ingin dicari
        String[] lagu = new String[4];

        // Untuk setiap lagu dalam playlist dilakukan pencocokan judul, jika sama maka kembalikan array lagu 
        for (int i = 0; i < playlist.length; i++) {
            if (playlist[i][0].toLowerCase().equals(judul.toLowerCase())) { // Pengecekan insensitive case
                lagu = playlist[i];
                return lagu;
            }
        }
        // Ketika lagu yang dicari tidak dapat ditemukan maka kembalikan lagu dengan lagu[0] = "NotFound"
        lagu[0] = "NotFound";
        return lagu;
    }

    // hanya untuk ngecek
    private static void checkIsiLagu() {
        System.out.println("----------------- CEK -----------------");
        for (int i = 0; i < playlist.length; i++) {
            System.out.println(Arrays.toString(playlist[i]));
        }
        System.out.println("---------------------------------------");
    }
}

