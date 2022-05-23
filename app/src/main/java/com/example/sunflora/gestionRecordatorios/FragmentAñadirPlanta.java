package com.example.sunflora.gestionRecordatorios;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.sunflora.R;
import com.example.sunflora.RoomDatabase.ConversorDeDatos;
import com.example.sunflora.RoomDatabase.DatabaseYDAO.DAOPlantas;
import com.example.sunflora.RoomDatabase.DatabaseYDAO.PlantasDatabase;
import com.example.sunflora.RoomDatabase.Entities.PlantaRoom;
import com.example.sunflora.databinding.FragmentAnyadirPlantaBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class FragmentAñadirPlanta extends Fragment {

    ArrayAdapter<String> arrayAdapterUbicaciones;
    ArrayAdapter<String> arrayAdaptertipoDePlantas;
    FragmentAnyadirPlantaBinding binding;
    String[] arrayUbicaciones;
    String[] arrayTipoDePlantas;
    FragmentTransaction fragmentTransaction;
    FragmentCrearRecordatorio fragmentCrearRecordatorio;
    final int CAMERA_INT = 51;
    ActivityResultLauncher<Intent> activityResultLauncherCamara;
    ActivityResultLauncher<String> activityResultLauncherGaleria;

    MaterialDatePicker materialDatePicker;

    DAOPlantas daoPlantas;
    public FragmentAñadirPlanta() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnyadirPlantaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("ResourceType")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        arrayUbicaciones = view.getResources().getStringArray(R.array.ubicacion_de_planta);
        arrayTipoDePlantas = view.getResources().getStringArray(R.array.tipos_de_plantas);

        arrayAdapterUbicaciones = new ArrayAdapter<String>(getContext(), R.layout.row_dropdowmenu, arrayUbicaciones);
        arrayAdaptertipoDePlantas = new ArrayAdapter<String>(getContext(), R.layout.row_dropdowmenu, arrayTipoDePlantas);

        binding.autoCompleteTextViewUbicaciones.setAdapter(arrayAdapterUbicaciones);
        binding.autoCompleteTextViewUbicaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String ubicacion = adapterView.getItemAtPosition(position).toString();
            }
        });

        binding.autoCompleteTextViewTipoPlanta.setAdapter(arrayAdaptertipoDePlantas);
        binding.autoCompleteTextViewTipoPlanta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String tipoDePlanta = adapterView.getItemAtPosition(position).toString();
            }
        });

        binding.TextViewNombreFechaDelRecordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecogerFecha(view);
            }
        });

        //boton para guardar la planta en la base de datos
        binding.BotonAAdirPlanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { añadirRecordatorio(view); }
        });

        //boton para añadir otro recordatorio
        binding.botonAAdirRecordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentCrearRecordatorio = new FragmentCrearRecordatorio();
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.ActivityContenedoraGestionRecordatorios, fragmentCrearRecordatorio).addToBackStack(null).commit();
            }
        });

        //metodo para recoger la foro tomada por camara
        activityResultLauncherCamara = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK && result.getData() !=null){
                    Bundle bundle = result.getData().getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    binding.ImageViewFotoPlantaACrear.setImageBitmap(bitmap);
                }
            }
        });

        binding.AgregarFotoPorCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getContext().getPackageManager()) !=null)
                    activityResultLauncherCamara.launch(intent);
            }
        });
        //metodo para obtener la foto de la planta a traves de la galeria
        activityResultLauncherGaleria = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                binding.ImageViewFotoPlantaACrear.setImageURI(result);
            }
        });

        binding.AgregarFotoPorGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityResultLauncherGaleria.launch("image/*");
            }
        });
}

    private void añadirRecordatorio(View view) {
        daoPlantas = PlantasDatabase.getDBInstance(getContext()).daoPlantas();

        PlantaRoom plantaRoom = new PlantaRoom();
        plantaRoom.setNombre(binding.EditTextNombrePlanta.getText().toString());
        plantaRoom.setLocalizacion(binding.autoCompleteTextViewUbicaciones.getText().toString());
        plantaRoom.setTipo(binding.autoCompleteTextViewTipoPlanta.getText().toString());
        plantaRoom.setFechaInicioRecordatorio(materialDatePicker.getHeaderText());

        //convertimos los recursos drawable de la foto de la planta a bitmap para poder insertarlo en la base de datos.
        Bitmap bitmapFoto = ((BitmapDrawable)binding.ImageViewFotoPlantaACrear.getDrawable()).getBitmap();
        plantaRoom.setFotoFlor(ConversorDeDatos.convertirBitmapAByteArray(bitmapFoto));

        daoPlantas.insertarPlanta(plantaRoom);
    }

    private void RecogerFecha(View view) {

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Selecciona una la fecha del recordatorio");
        materialDatePicker = builder.build();

        materialDatePicker.show(getActivity().getSupportFragmentManager(), "DATE_PICKER");

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                binding.TextViewNombreFechaDelRecordatorio.setText(materialDatePicker.getHeaderText());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void 
}