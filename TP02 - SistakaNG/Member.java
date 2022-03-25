package assignments.assignment2;

// Import Java Library
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// Class Member digunakan untuk membuat object yang menyimpan segala info anggota
public class Member {
    // Inisiasi data field
    private String id;
    private String name;
    private String dateOfBirth;
    private String studyProgram;
    private String angkatan;
    private long fine;
    private int point;
    private BookLoan[] bookLoans;

    // Constructor Member
    public Member(String id, String name, String dateOfBirth, String studyProgram, String angkatan, long fine, int point) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.studyProgram = studyProgram;
        this.angkatan = angkatan;
        this.fine = fine;
        this.point = point;
        // Inisiasi daftar buku dipinjam
        this.bookLoans = new BookLoan[0];
    }

    // Method peminjaman buku
    public void pinjam(Book book, String loanDate) {
        this.bookLoans = Arrays.copyOf(this.bookLoans, this.bookLoans.length+1);
        // Memasukkan book loan baru untuk member (member, book, loanDate, returnDate, fine, status)
        // status -> true (dikembalikan)
        // status -> false (belum dikembalikan)
        this.bookLoans[this.bookLoans.length-1] = new BookLoan(this, book, loanDate, "-", 0, false);
    }

    // Method pengembalian buku
    public void kembali(Book book, String returnDate) {
        for (BookLoan bl: this.bookLoans) {
            if (bl.getBook().equals(book)) {
                // Data buku yang telah dipinjam akan berubah
                // Perubahan tanggal pengembalian buku, status, dan denda
                bl.setReturnDate(returnDate);
                bl.setStatus(true); // true jika buku dikembalikan
                
                // Denda dihitung berdasarkan selisih hari
                long selisih = selisihHari(bl.getLoanDate(), returnDate);
                // Jika lebih dari 7 hari maka ada denda
                if (selisih > 7) {
                    bl.setFine(3000 * (selisih - 7));
                    // Denda total Member juga bertambah
                    this.fine += 3000 * (selisih - 7);
                } else {
                    // Jika tidak maka denda = 0
                    bl.setFine(0);
                }

                // Menambahkan point anggota berdasarkan kategori buku
                this.point += bl.getBook().getCategory().getPoint();
            }
        }
    }

    // Method untuk menghitung selisih hari pinjam dan kembali
    public long selisihHari(String tglPinjam, String tglKembali) {
        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf
            = new SimpleDateFormat(
                "dd/MM/yyyy");
  
        // Try Block
        try {
  
            // parse method is used to parse
            // the text from a string to
            // produce the date
            Date d1 = sdf.parse(tglPinjam);
            Date d2 = sdf.parse(tglKembali);
  
            // Calucalte time difference
            // in milliseconds
            long difference_In_Time
                = d2.getTime() - d1.getTime();
  
            // Calucalte time difference in
         
            long difference_In_Days
                = (difference_In_Time
                   / (1000 * 60 * 60 * 24))
                  % 365;
  
            return difference_In_Days;
        }
  
        // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Method untuk mengetahui denda dari suatu buku tertentu
    public long dendaBuku(Book book) {
        for (BookLoan bl: this.bookLoans) {
            if (bl.getBook().equals(book)) {
                return bl.getDenda();
            }
        }
        return (long) 0;
    }

    // Getter nama member
    public String getName() {
        return this.name;
    }

    // Getter dateOfBith
    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    // Getter studyProgram
    public String getStudyProgram() {
        return this.studyProgram;
    }

    // Getter angkatan
    public String getAngkatan() {
        return this.angkatan;
    }

    // Getter ID Member
    public String getID() {
        return this.id;
    }

    // Getter point anggota
    public int getPoint() {
        return this.point;
    }

    // Getter bookLoans anggota
    public BookLoan[] getBookLoan() {
        return this.bookLoans;
    }

    // Getter denda Member
    public long getDenda() {
        return this.fine;
    }

    // Setter denda Member
    public void setDenda(long newDenda) {
        this.fine = newDenda;
    }

    // Method ini memberikan data buku yang masih dipinjam
    public int getMasihDipinjam() {
        // Cek jumlah false status (masih belum dikembalikan)
        int masihDipinjam = 0;
        for (BookLoan bl: this.bookLoans) {
            if (bl.getStatus() == false) {
                masihDipinjam++;
            }
        }
        return masihDipinjam;
    }

    // Method ini memberikan info apakah buku sedang dipinjam oleh Member ini sendiri atau tidak
    public boolean getMeminjamBuku(Book buku) {
        // Untuk setiap peminjaman buku diambil data buku pinjaman
        // data yang disamakan adalah judul dan penulis insensitivecase
        for (BookLoan bl: this.bookLoans) {
            if (bl.getBook().equals(buku)) {
                // Mengembalikan status pinjaman
                return bl.getStatus();
            }
        }
        // Saat tidak ada buku dalam daftar loan maka kembalikan false
        return false;
    }

    // Setter status bookLoan
    public void setStatusBookLoan(Book book, boolean status) {
        for (BookLoan bl: this.bookLoans) {
            if (bl.getBook().equals(book)) {
                bl.setStatus(status);
            }
        }
    }
}
