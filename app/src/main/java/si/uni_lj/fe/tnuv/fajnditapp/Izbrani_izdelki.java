package si.uni_lj.fe.tnuv.fajnditapp;

import java.util.ArrayList;
import java.util.List;

public class Izbrani_izdelki {

    public static List<Izbrani_izdelki> izbrani = new ArrayList<>();

    private String uid;
    private String uime;
    private int ukolicina;
    private float ucena;

    public Izbrani_izdelki(String id, String ime, int kolicina, float cena) {
        uid = id;
        uime = ime;
        ukolicina = kolicina;
        ucena = cena;
    }

    public void setId(String id) { uid = id; }
    public String getId() { return uid; }

    public void setIme(String ime) { uime = ime; }
    public String getIme() { return uime; }

    public void setKolicina(int kolicina) { ukolicina = kolicina; }
    public int getKolicina() { return ukolicina; }

    public void setCena(float cena) { ucena = cena; }
    public float getCena() { return ucena; }
}