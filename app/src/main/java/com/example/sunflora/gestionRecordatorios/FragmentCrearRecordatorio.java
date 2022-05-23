package com.example.sunflora.gestionRecordatorios;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.example.sunflora.R;
import com.example.sunflora.RoomDatabase.DatabaseYDAO.DAOPlantas;
import com.example.sunflora.RoomDatabase.DatabaseYDAO.PlantasDatabase;
import com.example.sunflora.RoomDatabase.Entities.PlantaYRecordatorios;
import com.example.sunflora.RoomDatabase.Entities.Recordatorios;
import com.example.sunflora.databinding.FragmentAnyadirPlantaBinding;
import com.example.sunflora.databinding.FragmentCrearRecordatorioBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class FragmentCrearRecordatorio extends Fragment {

    ArrayAdapter arrayAdapterNombreRecordatorio;
    String[] arrayNombreRecordatorios;
    FragmentCrearRecordatorioBinding binding;
    MaterialTimePicker materialTimePicker;
    DAOPlantas daoPlantas;
    String idPlantaRef;
    public FragmentCrearRecordatorio() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            idPlantaRef = bundle.getString("idPlanta");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCrearRecordatorioBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        arrayNombreRecordatorios = view.getResources().getStringArray(R.array.Recordatorios);

        arrayAdapterNombreRecordatorio = new ArrayAdapter<String>(getContext(), R.layout.row_dropdowmenu, arrayNombreRecordatorios);

        binding.autoCompleteTextViewNombreRecordatorio.setAdapter(arrayAdapterNombreRecordatorio);
        binding.autoCompleteTextViewNombreRecordatorio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String nombreRecordatorio = adapterView.getItemAtPosition(position).toString();
            }
        });

        binding.autoCompleteTextViewHoraRecordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = parseTime(4, 8);

                materialTimePicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_24H)
                        .setHour(cal.get(Calendar.HOUR_OF_DAY))
                        .setMinute(cal.get(Calendar.MINUTE))
                        .build();
                materialTimePicker.show(getActivity().getSupportFragmentManager(), "TAG");

                materialTimePicker.addOnPositiveButtonClickListener(dialog -> {

                    int newHour = materialTimePicker.getHour();
                    int newMinute = materialTimePicker.getMinute();

                    binding.autoCompleteTextViewHoraRecordatorio.setText(new StringBuilder().append(newHour).append(":").append(newMinute).toString());
                });
            }
        });

        binding.BotonGuardarRecordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daoPlantas = PlantasDatabase.getDBInstance(getContext()).daoPlantas();

                Recordatorios recordatorio = new Recordatorios();
                recordatorio.setNombreRecordatorio(binding.autoCompleteTextViewNombreRecordatorio.getText().toString());
                recordatorio.setCiclo(Integer.parseInt(binding.EditTextRepeticiones.getText().toString()));
                recordatorio.setHoraRecordatorio(materialTimePicker.getHour());
                recordatorio.setMinRecordatorio(materialTimePicker.getMinute());
                recordatorio.setIdPlantaRef(idPlantaRef);
                daoPlantas.insertarRecordatorios(recordatorio);

            }
        });
    }

    public Calendar parseTime(int hour, int minute){

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        Date date = null;
        try {
            date = sdf.parse(String.valueOf(hour)+":"+String.valueOf(minute));
        } catch (ParseException e) {
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;
    }
}
