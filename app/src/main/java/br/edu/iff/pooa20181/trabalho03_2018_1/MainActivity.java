package br.edu.iff.pooa20181.trabalho03_2018_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnMecanico, btnOficina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnMecanico = findViewById(R.id.btnMecanico);
        this.btnOficina = findViewById(R.id.btnOficina);

        this.btnMecanico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Teste" ,Toast.LENGTH_LONG).show();
            }
        });

        this.btnOficina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Teste" ,Toast.LENGTH_LONG).show();
            }
        });

    }
}
