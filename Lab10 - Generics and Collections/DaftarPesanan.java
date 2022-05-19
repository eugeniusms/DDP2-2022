import java.util.ArrayList;

// Kelas DaftarPesanan merupakan blueprint arraylist dari daftar pesanan
public class DaftarPesanan<T extends Pesanan> {
    // Atribut daftar pesanan
    private ArrayList<T> pesanans = new ArrayList<>();
    private int pointer;

    // Constructor DaftarPesanan
    public DaftarPesanan() {
        this.pesanans = new ArrayList<>();
        this.pointer = 0;
    }

    // Method untuk menambahkan pesanan
    public void tambahPesanan(T pesanan) {
        this.pesanans.add(pesanan);
    }

    // Method untuk mereturn pesanan sesuai prioritas
    public T nextPesanan() {
        pointer++;
        return pesanans.get(pointer-1);
    }

    // Method untuk melakukan pengurutan prioritas
    public void urutkanPrioritas() {
        // Bubble Sort
        for (int i = 0; i < pesanans.size(); i++) {
            for (int j = 0; j < pesanans.size()-1; j++) {
                if (pesanans.get(j).compareTo(pesanans.get(j+1)) == 1) {
                    T temp = pesanans.get(j);
                    pesanans.set(j, pesanans.get(j+1));
                    pesanans.set(j+1, temp);
                }
            }
        }
    }

    // Getter & Setter

    // Getter pointer
    public int getPointer() {
        return this.pointer;
    }

}
