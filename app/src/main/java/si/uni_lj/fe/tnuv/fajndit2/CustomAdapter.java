package si.uni_lj.fe.tnuv.fajndit2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList izdelek_id,  izdelek_ime, izdelek_kategorija, izdelek_cena, izdelek_slika;

    CustomAdapter(Context context,
                  ArrayList izdelek_id,
                  ArrayList izdelek_ime,
                  ArrayList izdelek_kategorija,
                  ArrayList izdelek_cena,
                  ArrayList izdelek_slika){
        this.context = context;
        this.izdelek_id = izdelek_id;
        this.izdelek_ime = izdelek_ime;
        this.izdelek_kategorija = izdelek_kategorija;
        this.izdelek_cena = izdelek_cena;
        this.izdelek_slika = izdelek_slika;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.izdelek_id_txt.setText(String.valueOf(izdelek_id.get(position)));
        holder.izdelek_ime_txt.setText(String.valueOf(izdelek_ime.get(position)));
        holder.izdelek_kategorija_txt.setText(String.valueOf(izdelek_kategorija.get(position)));
        holder.izdelek_cena_txt.setText(String.valueOf(izdelek_cena.get(position)));
        holder.izdelek_slika_txt.setText(String.valueOf(izdelek_slika.get(position)));
    }

    @Override
    public int getItemCount() {
        return izdelek_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView izdelek_id_txt, izdelek_ime_txt, izdelek_kategorija_txt, izdelek_cena_txt, izdelek_slika_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            izdelek_id_txt = itemView.findViewById(R.id.izdelek_id_txt);
            izdelek_ime_txt = itemView.findViewById(R.id.izdelek_ime_txt);
            izdelek_kategorija_txt = itemView.findViewById(R.id.izdelek_kategorija_txt);
            izdelek_cena_txt = itemView.findViewById(R.id.izdelek_cena_txt);
            izdelek_slika_txt = itemView.findViewById(R.id.izdelek_slika_txt);


        }
    }
}
