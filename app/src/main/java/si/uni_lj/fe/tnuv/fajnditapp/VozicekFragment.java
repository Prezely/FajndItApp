package si.uni_lj.fe.tnuv.fajnditapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static si.uni_lj.fe.tnuv.fajnditapp.Podatki_izdelki.podatki;



import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import si.uni_lj.fe.tnuv.fajnditapp.databinding.FragmentVozicekBinding;

public class VozicekFragment extends Fragment {

    private FragmentVozicekBinding binding;
    private ListAdapter listAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_vozicek, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);

        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length() == 0){
                    posljiPrazno();
                }
                else{
                    filterList(newText);
                }

                return true;
            }
        });

        listAdapter = new ListAdapter();
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    private void filterList(String text) {
        List<Podatki_izdelki> filtrirani = new ArrayList<>();
        for (int i = 0; i < podatki.size(); i++) {
            if(podatki.get(i).getIme().toLowerCase().contains(text.toLowerCase())){
                filtrirani.add(podatki.get(i));
            }
        }
        if(!filtrirani.isEmpty()){
            listAdapter.setFilteredList(filtrirani);
        }
        else{
            Toast.makeText(getContext(), "Ni najdenih zadetkov.", Toast.LENGTH_SHORT).show();
        }

    }

    public void posljiPrazno(){
        List<Podatki_izdelki> prazna = new ArrayList<>();
        listAdapter.setFilteredList(prazna);
        System.out.println("prazna tabela:");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}