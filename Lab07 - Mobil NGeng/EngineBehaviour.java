// Interface sebuah mesin untuk digunakan dan diimplementasikan
interface EngineBehaviour {
    // Terdapat 3 behaviour
    // 1. start -> Memulai penyalaan mesin
    // 2. gas -> Menjalankan mesin
    // 3. stop -> Memberhentikan mesin
    
    public String start(Mobil mobil);
    public int gas(int persenFuel);
    public String stop(Mobil mobil);
}

