package si.uni_lj.fe.tnuv.fajnditapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Podatki_izdelki {

    public static List<Podatki_izdelki> podatki = new ArrayList<>();

    public static int cenaVozicka = 0;

    private String uid;
    private String uime;
    private String ukategorija;
    private int ucena;


    public Podatki_izdelki(String id, String ime, String kategorija, int cena) {
        uid = id;
        uime = ime;
        ukategorija = kategorija;
        ucena = cena;
    }

    public void setId(String id) { uid = id; }
    public String getId() { return uid; }

    public void setIme(String ime) { uime = ime; }
    public String getIme() { return uime; }

    public void setKategorija(String kategorija) { ukategorija = kategorija; }
    public String getKategorija() { return ukategorija; }

    public void setCena(int cena) { ucena = cena; }
    public int getCena() { return ucena; }

}
