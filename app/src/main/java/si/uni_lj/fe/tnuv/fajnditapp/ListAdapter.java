package si.uni_lj.fe.tnuv.fajnditapp;

import static si.uni_lj.fe.tnuv.fajnditapp.Izbrani_izdelki.izbrani;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter {

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
        return Podatki.id.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView id;
        private TextView ime;
        private TextView kategorija;
        private TextView cena;
        private TextView slika;

        public ListViewHolder(View itemView) {

            super(itemView);
            id = (TextView) itemView.findViewById(R.id.izdelek_id_txt);
            ime = (TextView) itemView.findViewById(R.id.izdelek_ime_txt);
            kategorija = (TextView) itemView.findViewById(R.id.izdelek_kategorija_txt);
            cena = (TextView) itemView.findViewById(R.id.izdelek_cena_txt);
            slika = (TextView) itemView.findViewById(R.id.izdelek_slika_txt);
            //itemView.setOnClickListener(this);

            itemView.findViewById(R.id.gumb_dodaj).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (izbrani.size() == 0) {
                        izbrani.add(new Izbrani_izdelki(id.getText().toString(), ime.getText().toString(), 1, Float.valueOf(cena.getText().toString())));
                    }
                    else {
                        if (!vSeznamu(izbrani, ime.getText().toString())) {
                            izbrani.add(new Izbrani_izdelki(id.getText().toString(), ime.getText().toString(), 1, Float.valueOf(cena.getText().toString())));
                        }
                        else {
                            Toast.makeText(MainActivity.getAppContext(), ime.getText().toString() + " je že v seznamu", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }

        public void bindView(int position) {
            id.setText(Podatki.id[position]);
            ime.setText(Podatki.ime[position]);
            kategorija.setText(Podatki.kategorija[position]);
            cena.setText(Podatki.cena[position]);
            slika.setText(Podatki.slika[position]);
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
    }
}
