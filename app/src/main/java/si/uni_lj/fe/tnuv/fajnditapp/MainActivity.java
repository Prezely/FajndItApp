package si.uni_lj.fe.tnuv.fajnditapp;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import java.io.IOException;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import si.uni_lj.fe.tnuv.fajnditapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    private static Context context;
    private static Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        MainActivity.context = getApplicationContext();

        // BRANJE PODATKOV IZ JSON DATOTEKE
        resources = context.getResources();

        String json;
        try {
            InputStream is = getAssets().open("podatkiizdelki.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            System.out.println(is);
            System.out.println(size);

            json = new String(buffer, "UTF-8");
            JSONObject object = new JSONObject(json);
            JSONArray jsonArray = object.getJSONArray("podatki");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String imeSlike = obj.getString("slika");
                int idSlike = resources.getIdentifier(imeSlike, "drawable", context.getPackageName());
                podatki.add(new Podatki_izdelki(obj.getString("id"), obj.getString("ime"), obj.getString("kategorija"), obj.getInt("cena"), idSlike));
            }

        } catch(IOException e) {
            e.printStackTrace();
        } catch(JSONException e) {
            e.printStackTrace();
        }


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