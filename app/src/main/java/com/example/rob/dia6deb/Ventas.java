package com.example.rob.dia6deb;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class Ventas extends AppCompatActivity {
    static final int READ_BLOCK_SIZE = 100;
    private String content1 = "";
private TextView txtPrecio;
    private  Spinner sp1;
    Calendar c = Calendar.getInstance();
    TextView fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);
        Button btnG2 = (Button)findViewById(R.id.btnG2);
        sp1 = (Spinner)findViewById(R.id.spProducto);
        txtPrecio = (TextView)findViewById(R.id.txtPrecio);
        final EditText edC = (EditText)findViewById(R.id.edCantidad);
        final TextView txtTotal = (TextView)findViewById(R.id.txtTotal);
        Button btnFecha = (Button)findViewById(R.id.btnFecha);
        fecha = (TextView)findViewById(R.id.textView5);




        String content = "";
try {
    FileInputStream fs = openFileInput("Nombre");
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

        String[] productos = content.split(",");
        ArrayAdapter<String> arr1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,productos);

        sp1.setAdapter(arr1);


        try {
            FileInputStream fs = openFileInput("Precio");
            InputStreamReader sr = new InputStreamReader(fs);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            int readChars = 0;

            while ((readChars = sr.read(inputBuffer)) > 0) {
                String section1 = String.copyValueOf(inputBuffer, 0, readChars);
                content1 += section1;
                inputBuffer = new char[READ_BLOCK_SIZE];

            }
        }catch (IOException ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }



        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item!=null) {
                    String[] precios = content1.split(",");
                    txtPrecio = (TextView)findViewById(R.id.txtPrecio);
                    sp1 = (Spinner)findViewById(R.id.spProducto);
                    Integer pro = sp1.getSelectedItemPosition();
                    txtPrecio.setText(precios[pro].toString());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        /*
        sp1.s(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String[] precios = content1.split(",");
                txtPrecio = (TextView)findViewById(R.id.txtPrecio);
               sp1 = (Spinner)findViewById(R.id.spProducto);
                //Integer pro = sp1.getSelectedItemPosition();
                txtPrecio.setText(precios[position].toString());
            }
        });*/

        edC.setOnEditorActionListener (new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                  /* Write your logic here that will be executed when user taps next button */

                    String precio = txtPrecio.getText().toString();
                    String cantidad = edC.getText().toString();

                    int n=0;
                    int c=0;
                    if(precio.matches("\\d+")|| cantidad.matches("\\d+")) //check if only digits. Could also be text.matches("[0-9]+")
                    {
                        n = Integer.parseInt(precio);
                        c= Integer.parseInt(cantidad);
                    }
                    else
                    {
                        System.out.println("not a valid number");
                    }

                    txtTotal.setText(String.valueOf(n*c));
                    handled = false;
                }

                                   return handled;


            }
        });



/*
        assert edC != null;
        while (edC.hasFocus()) {

    String precio = txtPrecio.getText().toString();
   String cantidad = edC.getText().toString();

    int n=0;
    int c=0;
    if(precio.matches("\\d+")|| cantidad.matches("\\d+")) //check if only digits. Could also be text.matches("[0-9]+")
    {
        n = Integer.parseInt(precio);
        c= Integer.parseInt(cantidad);
    }
    else
    {
        System.out.println("not a valid number");
    }

    txtTotal.setText(String.valueOf(n*c));
}
        //else{}*/

btnFecha.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        new DatePickerDialog(Ventas.this, d, c.get(Calendar.YEAR),
                c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

    }
});




        btnG2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    FileOutputStream fs = openFileOutput("Fecha", Context.MODE_APPEND);
                    OutputStreamWriter sw = new OutputStreamWriter(fs);
                    sw.write(fecha.getText().toString()+",");
                    sw.flush();
                    sw.close();
                    fecha.setText("");

                }
                catch (FileNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                catch (IOException ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

                try {
                    FileOutputStream fs = openFileOutput("Cantidad", Context.MODE_APPEND);
                    OutputStreamWriter sw = new OutputStreamWriter(fs);
                    sw.write(edC.getText().toString()+",");
                    sw.flush();
                    sw.close();
                    edC.setText("");

                }
                catch (FileNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                catch (IOException ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

                try {
                    FileOutputStream fs = openFileOutput("Total", Context.MODE_APPEND);
                    OutputStreamWriter sw = new OutputStreamWriter(fs);
                    sw.write(txtTotal.getText().toString()+",");
                    sw.flush();
                    sw.close();
                    txtTotal.setText("");

                }
                catch (FileNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                catch (IOException ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

                try {
                    FileOutputStream fs = openFileOutput("ProductoVenta", Context.MODE_APPEND);
                    OutputStreamWriter sw = new OutputStreamWriter(fs);
                    sw.write(sp1.getSelectedItem().toString()+",");
                    sw.flush();
                    sw.close();


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


    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            fecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/"+ year);

        }
    };
}
