package si.uni_lj.fe.tnuv.fajnditapp;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
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

        public ListViewHolder(View itemView) {

            super(itemView);

            ime = (TextView) itemView.findViewById(R.id.izbran_ime_txt);
            kolicina = (TextView) itemView.findViewById(R.id.izbran_kolicina_txt);
            cena = (TextView) itemView.findViewById(R.id.izbran_cena_txt);

            itemView.findViewById(R.id.gumb_povecajKolicino).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    izbrani.get(getAdapterPosition()).setKolicina(izbrani.get(getAdapterPosition()).getKolicina() + 1);
                    izbrani.get(getAdapterPosition()).setCena(izbrani.get(getAdapterPosition()).getCena() + izbrani.get(getAdapterPosition()).getOgCena());
                    cenaVozicka = cenaVozicka + izbrani.get(getAdapterPosition()).getOgCena();
                    notifyDataSetChanged();
                    izracunajSkupnoCeno(skupaj);
                }
            });

            itemView.findViewById(R.id.gumb_zmanjsajKolicino).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(izbrani.get(getAdapterPosition()).getKolicina() > 1){
                        izbrani.get(getAdapterPosition()).setKolicina(izbrani.get(getAdapterPosition()).getKolicina() - 1);
                        izbrani.get(getAdapterPosition()).setCena(izbrani.get(getAdapterPosition()).getCena() - izbrani.get(getAdapterPosition()).getOgCena());
                        cenaVozicka = cenaVozicka - izbrani.get(getAdapterPosition()).getOgCena();
                        notifyDataSetChanged();
                        izracunajSkupnoCeno(skupaj);
                    }
                    else if (izbrani.get(getAdapterPosition()).getKolicina() == 1) {
                        izbrani.remove(izbrani.get(getAdapterPosition()));
                        notifyDataSetChanged();
                        izracunajSkupnoCeno(skupaj);
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
