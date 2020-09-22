package co.com.animales;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.animales.adapters.AnimalesAdapter;
import co.com.animales.entity.Animales;
import co.com.animales.entity.Sonido;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.listViewAnimales)
    public ListView listViewAnimales;
    @BindView(R.id.txtBuscar)
    public EditText txtBuscar;
    private AnimalesAdapter animalesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadInfo();
        buscarOnTextListener();
    }

    private void buscarOnTextListener() {
        txtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                animalesAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void loadInfo() {
        List<Animales> animalesList = new ArrayList<>();
        animalesList.add(new Animales(R.drawable.perro1, "Perro", "El perro es un animal de tipo canino y se dice que el fiel amigo del hombre", R.raw.gato));
        animalesList.add(new Animales(R.drawable.gato, "Gato", "El gato es un animal de tipo felino y se dice que el fiel amigo del hombre", R.raw.gato));
        animalesList.add(new Animales(R.drawable.vaca, "Vaca", "La vaca es un animal de tipo felino y se dice que el fiel amigo del hombre", R.raw.cerdo));
        animalesList.add(new Animales(R.drawable.cerdo, "Cerdo", "El Cerdo es un animal de tipo felino y se dice que el fiel amigo del hombre", R.raw.cerdo));
        animalesAdapter = new AnimalesAdapter(this, animalesList);
        listViewAnimales.setAdapter(animalesAdapter);
    }
}