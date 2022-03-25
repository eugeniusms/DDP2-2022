package assignments.assignment2;

// Class Book digunakan untuk object Buku dalam program Library
public class Book {
    // Menginisiasikan data field Book
    private String title;
    private String author;
    private String publisher;
    private int stok;
    private Category category;

    // Constructor Book
    public Book(String title, String author, String publisher, int stok, Category category) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.stok = stok;
        this.category = category;
    }

    // Getter judul buku
    public String getTitle() {
        return this.title;
    }

    // Getter author buku
    public String getAuthor() {
        return this.author;
    }

    // Getter penerbit buku
    public String getPublisher() {
        return this.publisher;
    }

    // Getter stok buku
    public int getStok() {
        return this.stok;
    }

    // Setter stok buku
    public void setStok(int newStok) {
        this.stok = newStok;
    }

    // Getter Category
    public Category getCategory() {
        return this.category;
    }
}
