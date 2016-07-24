package com.example.rob.dia6deb;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Producto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        Button btnG = (Button)findViewById(R.id.btnGuardar);
String[] productos1 = getResources().getStringArray(R.array.productos);
        Spinner sp = (Spinner)findViewById(R.id.spinner);

        ArrayAdapter<String> arr = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,productos1);

        sp.setAdapter(arr);



        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtID = (EditText)findViewById(R.id.edID);
                EditText txtNombre = (EditText)findViewById(R.id.edNombre);
                EditText txtPrecio = (EditText)findViewById(R.id.edPrecio);
                Spinner sp1 = (Spinner)findViewById(R.id.spinner);

                try {
                    FileOutputStream fs = openFileOutput("ID", Context.MODE_APPEND);
                    OutputStreamWriter sw = new OutputStreamWriter(fs);
                    sw.write(txtID.getText().toString()+",");
                    sw.flush();
                    sw.close();
                    txtID.setText("");

                }
                catch (FileNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                catch (IOException ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                try {
                    FileOutputStream fs = openFileOutput("Nombre", Context.MODE_APPEND);
                    OutputStreamWriter sw = new OutputStreamWriter(fs);
                    sw.write(txtNombre.getText().toString()+",");
                    sw.flush();
                    sw.close();
                    txtNombre.setText("");

                }
                catch (FileNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                catch (IOException ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                try {
                    FileOutputStream fs = openFileOutput("Precio", Context.MODE_APPEND);
                    OutputStreamWriter sw = new OutputStreamWriter(fs);
                    sw.write(txtPrecio.getText().toString()+",");
                    sw.flush();
                    sw.close();
                    txtPrecio.setText("");

                }
                catch (FileNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                catch (IOException ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                try {
                    FileOutputStream fs = openFileOutput("Categoria", Context.MODE_APPEND);
                    OutputStreamWriter sw = new OutputStreamWriter(fs);
                    sw.write(sp1.getSelectedItem().toString()+",");
                    sw.flush();
                    sw.close();
                    txtID.setText("");

                }
                catch (FileNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                catch (IOException ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                finish();
            }





        });

    }
}
