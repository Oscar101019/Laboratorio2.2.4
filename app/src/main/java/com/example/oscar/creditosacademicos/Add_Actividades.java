package com.example.oscar.creditosacademicos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Add_Actividades extends AppCompatActivity {

    TextView txtFechaInicio,txtFechaFin;
    ImageButton ImgButtonSave,ImgButtonCan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__actividades);

        ImgButtonSave = (ImageButton)findViewById(R.id.btnGuardar);
        ImgButtonCan = (ImageButton)findViewById(R.id.btnCerrar);


        ImgButtonCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        ImgButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), View_Details.class);
                startActivity(intent);
            }
        });

    }
}
