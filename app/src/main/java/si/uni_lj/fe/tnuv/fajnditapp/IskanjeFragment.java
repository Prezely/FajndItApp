package si.uni_lj.fe.tnuv.fajnditapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import static si.uni_lj.fe.tnuv.fajnditapp.Izbrani_izdelki.izbrani;
import static si.uni_lj.fe.tnuv.fajnditapp.Podatki_izdelki.cenaVozicka;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.security.PublicKey;

import si.uni_lj.fe.tnuv.fajnditapp.databinding.FragmentIskanjeBinding;

public class IskanjeFragment extends Fragment {

    private FragmentIskanjeBinding binding;
    public static TextView skupaj;
    public static ConstraintLayout dodajArtikleTxt;
    public static ConstraintLayout katicaIzdelka;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_iskanje, container, false);

        skupaj = (TextView) view.findViewById(R.id.skupnaCena);
        izracunajSkupnoCeno(skupaj);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleView2);

        IzbraniAdapter izbraniAdapter = new IzbraniAdapter();
        recyclerView.setAdapter(izbraniAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        dodajArtikleTxt = (ConstraintLayout) view.findViewById(R.id.dodajArtkleTxt);
        katicaIzdelka = (ConstraintLayout) view.findViewById(R.id.karticaIzdelka);


        // ČE JE VOZIČEK PRAZEN JE NA SREDINI ZASLONA PRIKAZAN TEXT "DODAJ V VOZIČEK"
        nastaviVidnost(dodajArtikleTxt);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // NASTAVLJANJE SKUPNE CENE NA SPODNJEM DELU ZASLONA V FRAGMENTU VOZIČEK
    public static void izracunajSkupnoCeno(TextView skupaj) {

        int cena = 0;

        for (int i = 0; i < izbrani.size(); i++) {
            cena = cena + izbrani.get(i).getCena();
        }
        skupaj.setText(String.valueOf((float) cena/100) + " €");
    }

    public static void nastaviVidnost(ConstraintLayout dodajArtikleTxt) {
        if (izbrani.size() != 0) {
            dodajArtikleTxt.setVisibility(View.INVISIBLE);
        } else {
            dodajArtikleTxt.setVisibility(View.VISIBLE);
        }
    }




}