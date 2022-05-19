import java.util.ArrayList;

public class DaftarPesanan<T extends Pesanan> {
    // TODO: tambahkan attributes
    private ArrayList<T> pesanans = new ArrayList<>();
    private int pointer;

    public DaftarPesanan() {
        // TODO: Lengkapi Constructor berikut
        this.pesanans = new ArrayList<>();
        this.pointer = 0;
    }

    public void tambahPesanan(T pesanan) {
        // TODO: proses tambah pesanan
        this.pesanans.add(pesanan);
    }

    public T nextPesanan() {
        // TODO: return sesuai dengan urutan prioritas
        pointer++;
        return pesanans.get(pointer-1);
    }

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

    // Tambahkan getter-setter apabila diperlukan
    public int getPointer() {
        return this.pointer;
    }

}
