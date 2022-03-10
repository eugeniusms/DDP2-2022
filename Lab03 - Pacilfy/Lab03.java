import java.util.Scanner;

public class Lab03 {
    static int pointer = 0;
    static String[][] playlist = new String[1][4];
    static Scanner in = new Scanner(System.in);
    static int jumlahMusik = 0;

    public static void main(String[] args) {
        System.out.println("Selamat Datang di Pacilfy!");

        // TODO:
        // loop inisialisasi playlist minimal 1 lagu dimasukkan


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


        int command = 1;
        while (true){
            display();
            System.out.print("Command (0 untuk exit) : ");
            command = Integer.parseInt(in.nextLine());
            if (command == 1){
                prevMusic();
            }
            else if (command == 2){
                addMusic();
            }
            else if (command == 3){
                detailsMusic();
            }
            else if (command == 4){
                deleteMusic();
            }
            else if (command == 5){
                nextMusic();
            }
            else if (command == 0){
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

    }

    private static void deleteMusic() {
        //TODO:

    }

    private static void detailsMusic() {
        //TODO:

    }

    private static void prevMusic() {
        //TODO:
 
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
    
}
