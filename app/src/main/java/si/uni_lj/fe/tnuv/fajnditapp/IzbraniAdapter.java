package si.uni_lj.fe.tnuv.fajnditapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import static si.uni_lj.fe.tnuv.fajnditapp.Izbrani_izdelki.izbrani;
import static si.uni_lj.fe.tnuv.fajnditapp.Podatki_izdelki.podatki;
import static si.uni_lj.fe.tnuv.fajnditapp.Podatki_izdelki.cenaVozicka;
import static si.uni_lj.fe.tnuv.fajnditapp.IskanjeFragment.izracunajSkupnoCeno;
import static si.uni_lj.fe.tnuv.fajnditapp.IskanjeFragment.skupaj;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class IzbraniAdapter extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_izbran, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return izbrani.size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView ime;
        private TextView kolicina;
        private TextView cena;


        private int rezultat = 0;

        public ListViewHolder(View itemView) {

            super(itemView);

            ime = (TextView) itemView.findViewById(R.id.izbran_ime_txt);
            kolicina = (TextView) itemView.findViewById(R.id.izbran_kolicina_txt);
            cena = (TextView) itemView.findViewById(R.id.izbran_cena_txt);

            itemView.findViewById(R.id.gumb_povecajKolicino).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //System.out.println("IZ PODATKOV  " + podatki.get(1).getIme());

                    for(int i = 0; i<izbrani.size(); i++){
                        if(izbrani.get(i).getIme().equals(ime.getText().toString())){
                            izbrani.get(i).setKolicina(izbrani.get(i).getKolicina() + 1);
                            izbrani.get(i).setCena(izbrani.get(i).getCena() + izbrani.get(i).getOgCena());
                            cenaVozicka = cenaVozicka + izbrani.get(i).getOgCena();
                            notifyDataSetChanged();
                            izracunajSkupnoCeno(skupaj);
                        }
                    }
                }
            });

            itemView.findViewById(R.id.gumb_zmanjsajKolicino).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(int i = 0; i<izbrani.size(); i++){
                        if(izbrani.get(i).getIme().equals(ime.getText().toString()) && izbrani.get(i).getKolicina() > 1){
                            izbrani.get(i).setKolicina(izbrani.get(i).getKolicina() - 1);
                            izbrani.get(i).setCena(izbrani.get(i).getCena() - izbrani.get(i).getOgCena());
                            cenaVozicka = cenaVozicka - izbrani.get(i).getOgCena();
                            notifyDataSetChanged();
                            izracunajSkupnoCeno(skupaj);
                        }
                    }
                }
            });
        }

        public void bindView(int position) {
            ime.setText(izbrani.get(position).getIme());
            kolicina.setText(String.valueOf(izbrani.get(position).getKolicina()) + "x");
            cena.setText((float)izbrani.get(position).getCena()/100 + " €");
        }

        public void onClick(View view) {

        }

    }
}
