package com.example.sunflora;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sunflora.configuracionNotificaciones.WorkManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;

import com.example.sunflora.databinding.ActivityMainBinding;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    public static final String TAG_MY_WORK = "mywork";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_jardin, R.id.navigation_descubrir, R.id.navigation_ajustes)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        if(!comprobarWorkManagerIniciado(TAG_MY_WORK)) // revisa si el workmanager esta inicializado o no
            inicializarWorkManager(TAG_MY_WORK); // inicializa el workmanager



        /*androidx.work.WorkManager.getInstance(getApplicationContext())
                .enqueue(saveRequest);*/
    }

    private void inicializarWorkManager(String tag) {

        PeriodicWorkRequest saveRequest = new PeriodicWorkRequest.Builder(WorkManager.class, 900000, TimeUnit.MILLISECONDS)
                .setConstraints(new Constraints.Builder().setRequiresBatteryNotLow(true).build())
                .build();

        androidx.work.WorkManager.getInstance(getApplicationContext())
                .enqueueUniquePeriodicWork(tag, ExistingPeriodicWorkPolicy.KEEP, saveRequest);
    }

    private boolean comprobarWorkManagerIniciado(String tag) {
        androidx.work.WorkManager instance = androidx.work.WorkManager.getInstance(getApplicationContext());
        ListenableFuture<List<WorkInfo>> statuses = instance.getWorkInfosByTag(tag);
        try {
            boolean running = false;
            List<WorkInfo> workInfoList = statuses.get();
            for (WorkInfo workInfo : workInfoList) {
                WorkInfo.State state = workInfo.getState();
                running = state == WorkInfo.State.RUNNING | state == WorkInfo.State.ENQUEUED;
            }
            return running;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
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