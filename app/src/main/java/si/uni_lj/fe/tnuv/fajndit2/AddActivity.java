package si.uni_lj.fe.tnuv.fajndit2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText ime_input, kategorija_input, cena_input, slika_input;
    Button save_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ime_input = findViewById(R.id.ime_input);
        kategorija_input = findViewById(R.id.kategorija_input);
        cena_input = findViewById(R.id.cena_input);
        slika_input = findViewById(R.id.slika_input);
        save_button = findViewById(R.id.save_button);

        save_button.setOnClickListener(view -> {
            MyDatabseHelper myDB = new MyDatabseHelper(AddActivity.this);
            myDB.dodajIzdelek(ime_input.getText().toString().trim(), kategorija_input.getText().toString().trim(), Integer.valueOf(cena_input.getText().toString().trim()), slika_input.getText().toString().trim());
        });

    }
}