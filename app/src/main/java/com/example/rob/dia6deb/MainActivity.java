package com.example.rob.dia6deb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnP = (Button)findViewById(R.id.btnP);
        Button btnV = (Button)findViewById(R.id.btnV);

        Button btnL = (Button)findViewById(R.id.btnL);



        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Producto.class);

                startActivity(i);
            }
        });


        btnV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent is = new Intent(getApplicationContext(), Ventas.class);

                startActivity(is);
            }
        });

        btnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ip = new Intent(getApplicationContext(), ListVentas.class);

                startActivity(ip);
            }
        });

    }
}
