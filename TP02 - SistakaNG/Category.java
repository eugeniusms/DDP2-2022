package assignments.assignment2;

// Class Category digunakan untuk object kategori dalam program Library
public class Category {
    // Inisiasi data field Category
    private String name;
    private int point;

    // Constructor Category
    public Category(String name, int point) {
        this.name = name;
        this.point = point;
    }

    // Getter nama kategori
    public String getName() {
        return this.name;
    }

    // Getter point 
    public int getPoint() {
        return this.point;
    }
}
