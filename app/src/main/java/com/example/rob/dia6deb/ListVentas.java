package com.example.rob.dia6deb;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class ListVentas extends ListActivity {
    ArrayList<HashMap<String , String >> Eventos;

    String [] from = new String[] {"Fecha", "Name", "Cantidad", "Total"};
    int [] to = new int[] {R.id.lvFecha, R.id.lvProduct, R.id.lvCantidad, R.id.lvTotal};
    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ventas);

        String content = "";
        try {
            FileInputStream fs = openFileInput("Fecha");
            InputStreamReader sr = new InputStreamReader(fs);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            int readChars = 0;

            while ((readChars = sr.read(inputBuffer)) > 0) {
                String section = String.copyValueOf(inputBuffer, 0, readChars);
                content += section;
                inputBuffer = new char[READ_BLOCK_SIZE];

            }
        }catch (IOException ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        String[] fechas = content.split(",");

        String content1 = "";
        try {
            FileInputStream fs = openFileInput("ProductoVenta");
            InputStreamReader sr = new InputStreamReader(fs);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            int readChars = 0;

            while ((readChars = sr.read(inputBuffer)) > 0) {
                String section = String.copyValueOf(inputBuffer, 0, readChars);
                content1 += section;
                inputBuffer = new char[READ_BLOCK_SIZE];

            }
        }catch (IOException ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
String[] productos = content1.split(",");

        String content2 = "";
        try {
            FileInputStream fs = openFileInput("Cantidad");
            InputStreamReader sr = new InputStreamReader(fs);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            int readChars = 0;

            while ((readChars = sr.read(inputBuffer)) > 0) {
                String section = String.copyValueOf(inputBuffer, 0, readChars);
                content2 += section;
                inputBuffer = new char[READ_BLOCK_SIZE];

            }
        }catch (IOException ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
String[] cantidades = content2.split(",");

        String content3 = "";
        try {
            FileInputStream fs = openFileInput("Total");
            InputStreamReader sr = new InputStreamReader(fs);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            int readChars = 0;

            while ((readChars = sr.read(inputBuffer)) > 0) {
                String section = String.copyValueOf(inputBuffer, 0, readChars);
                content3 += section;
                inputBuffer = new char[READ_BLOCK_SIZE];

            }
        }catch (IOException ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
String[] totales = content3.split(",");


        ArrayList<String[]> lista = new ArrayList<>();
for (int i =0; i<fechas.length;i++){
    String[] item = {fechas[i],productos[i],cantidades[i],totales[i]};
    lista.add(item);

    }

        Eventos = new ArrayList<HashMap<String, String>>();

        for (String[] evento:lista) {
            HashMap<String, String> datosEvento = new HashMap<String, String>();
            datosEvento.put("Fecha", evento [0]);
            datosEvento.put("Name", evento [1]);
            datosEvento.put("Cantidad", evento [2]);
            datosEvento.put("Total", evento [3]);

            Eventos.add(datosEvento);
        }

        SimpleAdapter listAdapter = new SimpleAdapter(this, Eventos, R.layout.actividadpersonal, from, to);

        setListAdapter(listAdapter);
    }
}
