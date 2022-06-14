package si.uni_lj.fe.tnuv.fajnditapp;

import static si.uni_lj.fe.tnuv.fajnditapp.Izbrani_izdelki.izbrana_imena;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        return izbrana_imena.size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView ime;


        public ListViewHolder(View itemView) {

            super(itemView);

            ime = (TextView) itemView.findViewById(R.id.izbran_ime_txt);


        }

        public void bindView(int position) {
            ime.setText(izbrana_imena.get(position));
        }

        public void onClick(View view) {

        }


    }
}
