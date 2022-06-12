package si.uni_lj.fe.tnuv.fajndit2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recView;
    FloatingActionButton add_button;

    MyDatabseHelper myDB;

    ArrayList<String> izdelek_id;
    ArrayList<String> izdelek_ime;
    ArrayList<String> izdelek_kategorija;
    ArrayList<String> izdelek_cena;
    ArrayList<String> izdelek_slika;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Izdelek> vsiIzdelki = new ArrayList<Izdelek>();

        vsiIzdelki.add(new Izdelek("1", "Sok", "Kategorija1", 1, "Slika1"));
        vsiIzdelki.add(new Izdelek("2", "Mleko", "Kategorija2", 2, "Slika2"));
        vsiIzdelki.add(new Izdelek("3", "Cokolada", "Kategorija3", 3, "Slika3"));

        recView = findViewById(R.id.recycleView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabseHelper(MainActivity.this);
        izdelek_id = new ArrayList<>();
        izdelek_ime = new ArrayList<>();
        izdelek_kategorija = new ArrayList<>();
        izdelek_cena = new ArrayList<>();
        izdelek_slika = new ArrayList<>();

        //storeDataInArrays();

        izdelek_id.add("1");
        izdelek_ime.add("Sok");
        izdelek_kategorija.add("Pijača");
        izdelek_cena.add("1.99");
        izdelek_slika.add("Slika1");

        izdelek_id.add("2");
        izdelek_ime.add("Mleko");
        izdelek_kategorija.add("Mlečni Izdelki");
        izdelek_cena.add("2.99");
        izdelek_slika.add("Slika2");

        izdelek_id.add("3");
        izdelek_ime.add("Čokolada");
        izdelek_kategorija.add("Prigrizki");
        izdelek_cena.add("3.99");
        izdelek_slika.add("Slika3");

        customAdapter = new CustomAdapter(MainActivity.this, vsiIzdelki/*izdelek_id, izdelek_ime, izdelek_kategorija, izdelek_cena, izdelek_slika*/);
        recView.setAdapter(customAdapter);
        recView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meni, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                customAdapter.getFilter().filter(s);
                return true;
            }
        });
        return true;
    }

    void storeDataInArrays(){
        Cursor cursor  = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Ni podatkov.", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                izdelek_id.add(cursor.getString(0));
                izdelek_ime.add(cursor.getString(1));
                izdelek_kategorija.add(cursor.getString(2));
                izdelek_cena.add(cursor.getString(3));
                izdelek_slika.add(cursor.getString(4));
            }
        }
    }
}