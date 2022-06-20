package si.uni_lj.fe.tnuv.fajnditapp;

import static si.uni_lj.fe.tnuv.fajnditapp.Izbrani_izdelki.izbrani;
import static si.uni_lj.fe.tnuv.fajnditapp.Podatki_izdelki.podatki;
import static si.uni_lj.fe.tnuv.fajnditapp.Podatki_izdelki.cenaVozicka;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter {

    private static List<Podatki_izdelki> prikaz = new ArrayList<>();
    public void setFilteredList(List<Podatki_izdelki> filtrirani)  {
        prikaz = filtrirani;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_izdelek, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return prikaz.size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView id;
        private TextView ime;
        private TextView kategorija;
        private TextView cena;
        private TextView slika;
        private ImageView slika2;

        public ListViewHolder(View itemView) {

            super(itemView);
            id = (TextView) itemView.findViewById(R.id.izdelek_id_txt);
            ime = (TextView) itemView.findViewById(R.id.izdelek_ime_txt);
            kategorija = (TextView) itemView.findViewById(R.id.izdelek_kategorija_txt);
            cena = (TextView) itemView.findViewById(R.id.izdelek_cena_txt);
            slika = (TextView) itemView.findViewById(R.id.izdelek_slika_txt);
            slika2 = (ImageView) itemView.findViewById(R.id.slikaIzdelka);
            //itemView.setOnClickListener(this);

            itemView.findViewById(R.id.gumb_dodaj).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int ix = indeks(prikaz, ime.getText().toString());

                    if (izbrani.size() == 0) {
                        izbrani.add(new Izbrani_izdelki(prikaz.get(ix).getId(), prikaz.get(ix).getIme(), 1, prikaz.get(ix).getCena(), prikaz.get(ix).getCena()));
                        cenaVozicka = cenaVozicka + prikaz.get(ix).getCena();
                    }
                    else {
                        if (!vSeznamu(izbrani, ime.getText().toString())) {
                            izbrani.add(new Izbrani_izdelki(prikaz.get(ix).getId(), prikaz.get(ix).getIme(), 1, prikaz.get(ix).getCena(), prikaz.get(ix).getCena()));
                            cenaVozicka = cenaVozicka + prikaz.get(ix).getCena();
                        }
                        else {
                            Toast.makeText(MainActivity.getAppContext(), ime.getText().toString() + " je že v seznamu", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }

        public void bindView(int position) {
            id.setText(prikaz.get(position).getId());
            ime.setText(prikaz.get(position).getIme());
            kategorija.setText(prikaz.get(position).getKategorija());
            cena.setText(String.valueOf((float) prikaz.get(position).getCena()/100) + " €");
            slika.setText("111");
            slika2.setImageResource(prikaz.get(position).getSlika());
        }

        public void onClick(View view) {

        }

        public boolean vSeznamu(List<Izbrani_izdelki> prvi, String ime) {
            
            boolean notri = false;

            for (int i = 0; i < prvi.size(); i++) {

                if (prvi.get(i).getIme().equals(ime)) {
                    notri = true;
                }
            }
            if (notri) {
                return true;
            }
            return false;

        }

        public int indeks(List<Podatki_izdelki> podatki, String ime) {
            for (int i = 0; i < podatki.size(); i++) {
                if (podatki.get(i).getIme().equals(ime)) {
                    return i;
                }
            }
            return 0;
        }
    }
}
