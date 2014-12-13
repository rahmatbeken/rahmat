package com.souttab.kamus;

public class Kamus {

    private String istilah;
    private String pengertian;
    private byte[] gambar;

    public byte[] getGambar() {
        return gambar;
    }

    public void setGambar(byte[] gambar) {
        this.gambar = gambar;
    }

    public String getIstilah() {
        return istilah;
    }

    public void setIstilah(String istilah) {
        this.istilah = istilah;
    }

    public String getPenjelasan() {
        return pengertian;
    }

    public void setPenjelasan(String penjelasan) {
        this.pengertian = penjelasan;
    }
}
