package com.example.sunflora.gestionRecordatorios;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;

import com.example.sunflora.R;
import com.example.sunflora.RoomDatabase.DatabaseYDAO.DAOPlantas;
import com.example.sunflora.RoomDatabase.DatabaseYDAO.PlantasDatabase;
import com.example.sunflora.RoomDatabase.Entities.ContenidoNotificaciones;
import com.example.sunflora.RoomDatabase.Entities.Recordatorio;
import com.example.sunflora.databinding.FragmentCrearRecordatorioBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class FragmentCrearRecordatorio extends Fragment {

    ArrayAdapter arrayAdapterNombreRecordatorio;
    String[] arrayNombreRecordatorios;
    FragmentCrearRecordatorioBinding binding;
    MaterialTimePicker materialTimePicker;
    DAOPlantas daoPlantas;
    String idPlantaRef;
    Calendar cal;
    ArrayList<String> diasParaRecordar;
    ContenidoNotificaciones contenidoNotificaciones;
    public FragmentCrearRecordatorio() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            idPlantaRef = getArguments().getString("idPlanta");

        daoPlantas = PlantasDatabase.getDBInstance(getContext()).daoPlantas();
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

        binding.ImageViewEliminarRecordatorio.setVisibility(View.GONE);
        binding.autoCompleteTextViewNombreRecordatorio.setAdapter(arrayAdapterNombreRecordatorio);
        binding.autoCompleteTextViewHoraRecordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal = parseTime(4, 8);

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

                Recordatorio recordatorio = new Recordatorio();
                contenidoNotificaciones = new ContenidoNotificaciones();

                recordatorio.generarUUID();
                recordatorio.setNombreRecordatorio(binding.autoCompleteTextViewNombreRecordatorio.getText().toString());
                recordatorio.setHoraRecordatorio(materialTimePicker.getHour());
                recordatorio.setMinRecordatorio(materialTimePicker.getMinute());
                recordatorio.setIdPlantaRef(idPlantaRef);

                comprobarLosDiasSeleccionados();
                contenidoNotificaciones.setDiasParaRecordar(diasParaRecordar);
                contenidoNotificaciones.setIdRecordatorioRef(recordatorio.getIdRecordatorio());
                contenidoNotificaciones.setIdPlantaRef(idPlantaRef);
                contenidoNotificaciones.setTituloNotificacion(binding.autoCompleteTextViewNombreRecordatorio.getText().toString());
                contenidoNotificaciones.setHoraNotificacion(materialTimePicker.getHour());
                contenidoNotificaciones.setMinutoNotificacion(materialTimePicker.getMinute());

                daoPlantas.insertarRecordatorio(recordatorio);
                daoPlantas.insertarNotificacion(contenidoNotificaciones);
            }
        });
    }

    private void comprobarLosDiasSeleccionados() {
        diasParaRecordar = new ArrayList<>();
        if (binding.checkBoxLunes.isChecked())
            diasParaRecordar.add("lunes");
        if (binding.checkBoxMartes.isChecked())
            diasParaRecordar.add("martes");
        if (binding.checkBoxMiercoles.isChecked())
            diasParaRecordar.add("mi??rcoles");
        if (binding.checkBoxJueves.isChecked())
            diasParaRecordar.add("jueves");
        if (binding.checkBoxViernes.isChecked())
            diasParaRecordar.add("viernes");
        if (binding.checkBoxSabado.isChecked())
            diasParaRecordar.add("sabado");
        if (binding.checkBoxDomingo.isChecked())
            diasParaRecordar.add("domingo");

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
