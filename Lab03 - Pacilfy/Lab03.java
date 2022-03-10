import java.util.Scanner;
import java.util.Arrays;

public class Lab03 {
    static int pointer = 0;
    static int countLast = 0;
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
            
            playlist = Arrays.copyOf(playlist, playlist.length + 1);

            System.out.print("Lanjut menambahkan lagu?\n[1] Lanjut\n[0] Berhenti\nPerintah : ");
            tambahLaguCommand = in.nextInt();

            
        }

        // check
        // for (int i = 0; i < 2; i++) {
        //     System.out.println(playlist[i][0]);
        //     System.out.println(playlist[i][1]);
        //     System.out.println(playlist[i][2]);
        //     System.out.println(playlist[i][3]);
        // }

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
        // cek index setelahnya kosong tidak, jika iya kembalikan ke depan
        if (playlist[pointer + 1][0] == null) {
            pointer = 0;
        } else if (playlist[pointer + 1][0] == "deletedMusic") { // saat deleted music maka setel setelahnya
            pointer += 2;
        } else {
            pointer++;
        }
        // clear
    }

    private static void deleteMusic() {
        //TODO:
        playlist[pointer][0] = "deletedMusic"; // set name of music deleted 
    }

    private static void detailsMusic() {
        //TODO:

    }

    private static void prevMusic() {
        //TODO:
        // cek indexnya 0 (awal) bukan? jika iya maka kembalikan ke belakang
        if (pointer == 0) {
            // search end exist non-null
            searchLastExistPointer();
            pointer = countLast;
        } else {
            pointer--;
        }
        // clear
    }

    private static void addMusic() {
        //TODO:

    }


    private static void display() {
        System.out.println();
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

    // ketika pointernya null hasilnya maka count berhenti *linear search untuk mendapati elemen exist terakhir
    private static void searchLastExistPointer() {
        countLast = 0;
        while (playlist[countLast][0] != null) {
            countLast++;
        }
        countLast--;
        // clear
    }
    
}

