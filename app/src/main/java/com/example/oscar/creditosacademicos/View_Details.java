package com.example.oscar.creditosacademicos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class View_Details extends AppCompatActivity {

    TextView txtFechaInicio,txtFechaFin;
    ImageButton ImgButtonEditar,ImgButtonCan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__details);

        ImgButtonEditar = (ImageButton)findViewById(R.id.btnEditar);
        ImgButtonCan = (ImageButton)findViewById(R.id.btnCerrar);

        ImgButtonCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
