package com.example.sunflora.registroDePlantas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sunflora.R;

public class ActivityContenedoraRegistroPlantas extends AppCompatActivity {

    FragmentListaPlantas fragmentListaPlantas;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedora_registro_plantas);

        String tipoDePlanta = getIntent().getStringExtra("tipoDePlanta");
        fragmentListaPlantas = new FragmentListaPlantas();

        Bundle bundle = new Bundle();
        bundle.putString("tipoDePlanta", tipoDePlanta);
        fragmentListaPlantas.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.ActivityContenedoraRegistroPlantas, fragmentListaPlantas).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.restaurarCopiaDeSeguridad:
                Toast.makeText(this, "subir copia de seguridad", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subirCopiaDeSeguridad:
                Toast.makeText(this, "restaurar copia de seguridad", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}