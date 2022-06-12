package si.uni_lj.fe.tnuv.fajndit2;

import android.graphics.Bitmap;

public class Izdelek {

    private String uid;
    private String uime;
    private String ukategorija;
    private int ucena;
    private String uslika;

    public Izdelek(String id, String ime, String kategorija, int cena, String slika) {
        uid = id;
        uime = ime;
        ukategorija = kategorija;
        ucena = cena;
        uslika = slika;
    }

    public void setId(String id) { uid = id; }
    public String getId() {
        return uid;
    }

    public void setIme(String ime) { uime = ime; }
    public String getIme() { return uime; }

    public void setKategorija(String kategorija) { ukategorija = kategorija; }
    public String getKategorija() {
        return ukategorija;
    }

    public void setCena(int cena) { ucena = cena; }
    public int getCena() {
        return ucena;
    }

    public void setSlika(String slika) { uslika = slika; }
    public String getSlika() {
        return uslika;
    }
}
