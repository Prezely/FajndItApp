package si.uni_lj.fe.tnuv.fajndit2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> implements Filterable {

    private Context context;

    private List<Izdelek> vsiIzdelki;

    private List<Izdelek> izbraniIzdelki;



    CustomAdapter(Context context,
                  List<Izdelek> vsiIzdelki){
        this.context = context;
        this.vsiIzdelki = vsiIzdelki;
        izbraniIzdelki = new ArrayList<>(vsiIzdelki);
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
        holder.izdelek_id_txt.setText(String.valueOf(vsiIzdelki.get(position).getId()));
        holder.izdelek_ime_txt.setText(String.valueOf(vsiIzdelki.get(position).getIme()));
        holder.izdelek_kategorija_txt.setText(String.valueOf(vsiIzdelki.get(position).getKategorija()));
        holder.izdelek_cena_txt.setText(String.valueOf(vsiIzdelki.get(position).getCena()));
        holder.izdelek_slika_txt.setText(String.valueOf(vsiIzdelki.get(position).getSlika()));
    }

    @Override
    public int getItemCount() {
        return vsiIzdelki.size();
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

    @Override
    public Filter getFilter() {
        return filterIzdelkov;
    }

    private Filter filterIzdelkov = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Izdelek> filtriraniIzdelki = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filtriraniIzdelki.addAll(izbraniIzdelki);
            }
            else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (Izdelek izdelek : izbraniIzdelki) {

                    if (izdelek.getIme().toLowerCase().contains(filterPattern)) {
                        filtriraniIzdelki.add(izdelek);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filtriraniIzdelki;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            izbraniIzdelki.clear();

            System.out.println("Filtriraj");


            izbraniIzdelki.addAll((List) filterResults.values);

            System.out.println(filterResults.values);

            notifyDataSetChanged();
        }
    };
}
