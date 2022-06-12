package si.uni_lj.fe.tnuv.fajndit2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recView;
    FloatingActionButton add_button;

    MyDatabseHelper myDB;
    ArrayList<String> izdelek_id, izdelek_ime, izdelek_kategorija, izdelek_cena, izdelek_slika;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, izdelek_id, izdelek_ime, izdelek_kategorija, izdelek_cena, izdelek_slika);
        recView.setAdapter(customAdapter);
        recView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
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