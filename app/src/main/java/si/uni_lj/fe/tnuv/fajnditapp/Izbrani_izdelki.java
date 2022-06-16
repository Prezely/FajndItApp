package si.uni_lj.fe.tnuv.fajnditapp;

import java.util.ArrayList;
import java.util.List;

public class Izbrani_izdelki {

    public static List<Izbrani_izdelki> izbrani = new ArrayList<>();

    private String uid;
    private String uime;
    private int ukolicina;
    private int ucena;
    private int uogCena;

    public Izbrani_izdelki(String id, String ime, int kolicina, int cena, int ogCena) {
        uid = id;
        uime = ime;
        ukolicina = kolicina;
        ucena = cena;
        uogCena = ogCena;
    }

    public void setId(String id) { uid = id; }
    public String getId() { return uid; }

    public void setIme(String ime) { uime = ime; }
    public String getIme() { return uime; }

    public void setKolicina(int kolicina) { ukolicina = kolicina; }
    public int getKolicina() { return ukolicina; }

    public void setCena(int cena) { ucena = cena; }
    public int getCena() { return ucena; }

    public void setOgCena(int ogCena) { uogCena = ogCena; }
    public int getOgCena() { return uogCena; }
}