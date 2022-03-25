package assignments.assignment2;

// Class BookLoan digunakan untuk object Peminjaman Buku dalam program Library
public class BookLoan {
    // Menginisiasi data field BookLoan
    private Member member;
    private Book book;
    private String loanDate;
    private String returnDate;
    private long fine;
    private boolean status;

    // Constructor BookLoan
    public BookLoan(Member member, Book book, String loanDate, String returnDate, long fine, boolean status) {
        this.member = member;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.fine = fine;
        this.status = status;
    }

    // Getter Member
    public Member getMember() {
        return this.member;
    }

    // Getter book BookLoan
    public Book getBook() {
        return this.book;
    }

    // Getter denda pinjaman
    public long getDenda() {
        return this.fine;
    }

    // Getter tanggal pinjam
    public String getLoanDate() {
        return this.loanDate;
    }

    // Getter status pinjaman
    public boolean getStatus() {
        return this.status;
    }

    // Getter returnDate
    public String getReturnDate() {
        return this.returnDate;
    }

    // Setter returnDate
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    // Setter fine
    public void setFine(long fine) {
        this.fine = fine;
    }

    // Setter status
    public void setStatus(boolean status) {
        this.status = status;
    }
}
