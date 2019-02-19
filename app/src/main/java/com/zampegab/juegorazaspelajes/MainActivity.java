package com.zampegab.juegorazaspelajes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_conf;
    ImageButton btn_jugar;
    ImageButton btn_reconocimiento;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        btn_conf = findViewById(R.id.btn_conf);
        btn_reconocimiento = findViewById(R.id.btn_reconocimiento);
        btn_jugar = findViewById(R.id.btn_jugar);

        btn_conf.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton imagen = findViewById(R.id.btn_conf);
                imagen.setImageResource(R.drawable.config_click);

                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                imagen.setImageResource(R.drawable.config_click);
            }
        });

        btn_reconocimiento.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton imagen = findViewById(R.id.btn_reconocimiento);
                imagen.setImageResource(R.drawable.reconocimiento_click);
                new CuadroDialogo(context, 1, "correcta", 1);
                String pref_reconocimiento = sharedPreferences.getString("modo_reconocimiento", "");
                if (pref_reconocimiento.equals("1")) {
                    Intent intent = new Intent(MainActivity.this, ListaActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, GrillaActivity.class);
                    startActivity(intent);
                }
                imagen.setImageResource(R.drawable.reconocimiento_regular);
            }
        });

        btn_jugar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton imagen = findViewById(R.id.btn_jugar);
                imagen.setImageResource(R.drawable.jugar_click);

                //List<Caballo> caballos = Repositorio.getCaballosRandom(getCantCaballos(sharedPreferences));
                String pref_interaccion = sharedPreferences.getString("modo_interaccion", "");
                String pref_minijuego = sharedPreferences.getString("minijuego", "");
                Intent intent = null;
                switch (pref_interaccion){
                    case "A":
                        //AGREGAR IF(pref_nivel) PARA SABER A QUE MINIJUEGO SE VA.
                        intent = new Intent(MainActivity.this, InteraccionAActivity.class);
                        break;

                    case "B":
                        //AGREGAR IF(pref_nivel) PARA SABER A QUE MINIJUEGO SE VA.
                        intent = new Intent(MainActivity.this, InteraccionBActivity.class);
                        break;

                    case "C":
                        //AGREGAR IF(pref_nivel) PARA SABER A QUE MINIJUEGO SE VA.
                        intent = new Intent(MainActivity.this, InteraccionCActivity.class);
                        break;
                }
                intent.putExtra("caballos", getCantCaballos(sharedPreferences));
                startActivity(intent);
                imagen.setImageResource(R.drawable.jugar_regular);
            }
        });
    }

    public Integer getCantCaballos(SharedPreferences sharedPreferences) {
        String pref_nivel = sharedPreferences.getString("nivel", "1");
        if (pref_nivel.equals("1")) {
            return 2;
        } else {
            return 4;
        }
    }

}



