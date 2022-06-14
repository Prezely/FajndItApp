package si.uni_lj.fe.tnuv.fajnditapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import si.uni_lj.fe.tnuv.fajnditapp.databinding.FragmentIskanjeBinding;

public class IskanjeFragment extends Fragment {

    private FragmentIskanjeBinding binding;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_iskanje, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleView2);

        IzbraniAdapter izbraniAdapter = new IzbraniAdapter();
        recyclerView.setAdapter(izbraniAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}