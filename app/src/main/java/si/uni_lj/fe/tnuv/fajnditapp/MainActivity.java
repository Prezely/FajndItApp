package si.uni_lj.fe.tnuv.fajnditapp;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import static si.uni_lj.fe.tnuv.fajnditapp.Podatki_izdelki.podatki;

import java.util.ArrayList;
import java.util.List;

import si.uni_lj.fe.tnuv.fajnditapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // PODATKI
        podatki.add(new Podatki_izdelki("0", "Pomarančni sok", "Pijača", 159, R.drawable.pomarancnisok));
        podatki.add(new Podatki_izdelki("1", "Jagodni sok", "Pijača", 179, R.drawable.jagodnisok));
        podatki.add(new Podatki_izdelki("2", "Jabolčni sok", "Pijača", 199, R.drawable.jabolcnisok));
        podatki.add(new Podatki_izdelki("3", "Alpsko mleko 1,5", "Mleko, jajca in mlečni izdelki", 169, R.drawable.alpskomlekoena));
        podatki.add(new Podatki_izdelki("4", "Riževo mleko", "Mleko, jajca in mlečni izdelki", 215, R.drawable.rizevomleko));

        super.onCreate(savedInstanceState);

        MainActivity.context = getApplicationContext();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // NAVIGACIJA SPODAJ
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_dashboard).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // FRAGMENT
        /*ListFragment fragment = new ListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();*/
    }

    public static Context getAppContext() {
        return MainActivity.context;
    }



}