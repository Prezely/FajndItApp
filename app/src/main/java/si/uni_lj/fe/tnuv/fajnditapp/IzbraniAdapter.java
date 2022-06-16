package si.uni_lj.fe.tnuv.fajnditapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static si.uni_lj.fe.tnuv.fajnditapp.Izbrani_izdelki.izbrani;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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


        }

        public void bindView(int position) {
            ime.setText(izbrani.get(position).getIme());
            kolicina.setText(String.valueOf(izbrani.get(position).getKolicina()) + "x");
            cena.setText(izbrani.get(position).getCena() + " €");
        }

        public void onClick(View view) {

        }


    }
}
