import java.util.Scanner;
import java.util.Arrays;

public class Lab03 {
    static int pointer = 0;
    static String[][] playlist = new String[1][4];
    static Scanner in = new Scanner(System.in);
    static int jumlahMusik = 0;

    public static void main(String[] args) {
        System.out.println("Selamat Datang di Pacilfy!");

        // TODO:
        // loop inisialisasi playlist minimal 1 lagu dimasukkan
        // Inisiasi Array
        String judulLagu;
        String artistLagu;
        String albumLagu;
        String tahunLagu;
        
        // Mengambil Input
        int tambahLaguCommand = 1;
        int count = 0;
        while (tambahLaguCommand == 1) {
            String[] laguBaru = new String[4];

            System.out.print("Judul : ");
            judulLagu = in.next();
            System.out.print("Artist : ");
            artistLagu = in.next();
            System.out.print("Album : ");
            albumLagu = in.next();
            System.out.print("Tahun : ");
            tahunLagu = in.next();
            
            laguBaru[0] = judulLagu;
            laguBaru[1] = artistLagu;
            laguBaru[2] = albumLagu;
            laguBaru[3] = tahunLagu;

            playlist[count] = laguBaru;

            count++;

            System.out.print("Lanjut menambahkan lagu?\n[1] Lanjut\n[0] Berhenti\nPerintah : ");
            tambahLaguCommand = in.nextInt();

            if (tambahLaguCommand == 1) {
                // menambah elemen array
                playlist = Arrays.copyOf(playlist, playlist.length + 1);
            }
        }

        // currenty playing index [0] first

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


        String command = "1";
        while (true){
            checkIsiLagu();
            display();
            System.out.print("Command (0 untuk exit) : ");
            command = in.next();
            if (command.equals("1")){
                System.out.println("MASUK");
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

    private static void nextMusic() {
        //TODO:
        // cek index setelahnya apakah sama dengan panjang playlist jika iya kembalikan ke depan
        if (pointer + 1 == playlist.length) {
            pointer = 0;
        } else {
            pointer++;
        }
        // clear
    }

    private static void deleteMusic() {
        //TODO:
        int len = playlist.length;

        // inisiasi array baru simpan sementara
        String[][] newPlaylist = new String[len-1][4]; // inisiate
        int count = 0;
        for (int i = 0; i < playlist.length; i++) {
            System.out.printf("\nSEK CEK DULU %s | %s\n", playlist[i][0], playlist[pointer][0]);
            if (playlist[i][0].equals(playlist[pointer][0])) {
                continue;
            } else {
                newPlaylist[count] = playlist[i];
                count++;
            }
        }
        // kembalikan playlist menjadi array sementara yang sudah dihilangkan elemennya
        playlist = Arrays.copyOf(newPlaylist, len-1);

        // saat pointer berada di paling belakang jadi lompat ke depan
        if (pointer == len - 1) {
            pointer = 0;
        }

        // saat lagu 0 = {}
        if (playlist.length == 0) {
            System.out.println("Tidak Ada Lagu di dalam Playlist");
        }

        // clear
    }

    private static void detailsMusic() {
        //TODO:
        System.out.print("Judul yang ingin dicari: ");
        String judulLagu = in.next();

        String[] laguDipilih = getMusic(judulLagu, playlist);
        if (laguDipilih[0] == "NotFound") {
            System.out.println("Lagu tidak ditemukan");
        } else {
            System.out.println("Data lagu:");
            System.out.printf("Judul : %s\n", laguDipilih[0]);
            System.out.printf("Artist : %s\n", laguDipilih[1]);
            System.out.printf("Album : %s\n", laguDipilih[2]);
            System.out.printf("Tahun : %s\n", laguDipilih[3]);
        }
        // clear
    }

    private static void prevMusic() {
        //TODO:
        // cek indexnya 0 (awal) bukan? jika iya maka kembalikan ke belakang
        if (pointer == 0) {
            pointer = playlist.length - 1;
        } else {
            pointer--;
        }
        // clear
    }

    private static void addMusic() {
        //TODO:
        String[] laguBaru = new String[4];
        System.out.println("Silahkan masukkan lagu Anda");

        System.out.print("Judul : ");
        String judulLagu = in.next();
        System.out.print("Artist : ");
        String artistLagu = in.next();
        System.out.print("Album : ");
        String albumLagu = in.next();
        System.out.print("Tahun : ");
        String tahunLagu = in.next();
        
        laguBaru[0] = judulLagu;
        laguBaru[1] = artistLagu;
        laguBaru[2] = albumLagu;
        laguBaru[3] = tahunLagu;

        playlist = Arrays.copyOf(playlist, playlist.length + 1);
        playlist[playlist.length - 1] = laguBaru;

        // clear
    }


    private static void display() {
        System.out.println();
        
        // TAMBAHAN : SAAT INDEX OUT OF BOUND LAGU 0
        if (playlist.length == 0) {
            System.out.println("Tidak ada lagu di dalam playlist");
        } else { // saat ada lagu di dalam
            System.out.println("Currently Playing");

            String displayedMusic = " " + playlist[pointer][1] + " - " + playlist[pointer][0] + " ";
            String command = "|[1] prev |[2] add music |[3] details |[4] delete music |[5] next|";

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

    private static String[] getMusic(String judul, String[][] playlist) {
        String[] lagu = new String[4];
        for (int i = 0; i < playlist.length; i++) {
            // ketika didapati sama
            if (playlist[i][0].toLowerCase().equals(judul.toLowerCase())) { // insensitive case
                lagu = playlist[i];
                return lagu;
            }
        }
        // Ketika lagu tidak ditemukan
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

