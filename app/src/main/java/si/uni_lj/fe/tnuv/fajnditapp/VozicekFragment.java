package si.uni_lj.fe.tnuv.fajnditapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
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

        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.iskanjeFragment);
        ConstraintLayout brezZadetkov = (ConstraintLayout) view.findViewById(R.id.brezZadetkov);

        // ISKALNA VRSTICA
        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setFocusable(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length() == 0){
                    posljiPrazno();
                    brezZadetkov.setVisibility(View.VISIBLE);
                    constraintLayout.setBackgroundResource(R.drawable.rendernavadn);
                }
                else{
                    brezZadetkov.setVisibility(View.INVISIBLE);
                    filterList(newText, constraintLayout, brezZadetkov);
                }

                return true;
            }
        });

        listAdapter = new ListAdapter();
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    // FILTRIRANJE IZDELKOV Z SEARCHBAROM, USTVARJANJE NOVE TABELE IN PRIKAZ ZADETKOV ISKANJA
    private void filterList(String text, ConstraintLayout constraintLayout, ConstraintLayout brezZadetkov) {
        List<Podatki_izdelki> filtrirani = new ArrayList<>();

        for (int i = 0; i < podatki.size(); i++) {
            if(podatki.get(i).getIme().toLowerCase().contains(text.toLowerCase())){
                filtrirani.add(podatki.get(i));
            }
        }

        if(!filtrirani.isEmpty()){
            listAdapter.setFilteredList(filtrirani);

            if (filtrirani.get(0).getKategorija().equals("Pijača")) {
                constraintLayout.setBackgroundResource(R.drawable.renderpijaca);
            }
            else if (filtrirani.get(0).getKategorija().equals("Mleko, jajca in mlečni izdelki")) {
                constraintLayout.setBackgroundResource(R.drawable.rendermleko);
            }
        }
        else{
            posljiPrazno();
            brezZadetkov.setVisibility(View.VISIBLE);
            constraintLayout.setBackgroundResource(R.drawable.rendernavadn);
            Toast.makeText(getContext(), "Ni najdenih zadetkov.", Toast.LENGTH_SHORT).show();
        }
    }

    // USTVARIMO NOVO PRAZNO TABELO IN JO PRIKAŽEMO MED ZADETKI ISKANJA
    public void posljiPrazno(){
        List<Podatki_izdelki> prazna = new ArrayList<>();
        listAdapter.setFilteredList(prazna);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}