package br.edu.iff.pooa20181.trabalho03_2018_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.pooa20181.trabalho03_2018_1.model.Mecanico;

public class MecanicoListActivity extends AppCompatActivity implements ClickRecyclerViewListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mecanico_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MecanicoListActivity.this, ManageMecanicoActivity.class);
                startActivity(intent);
            }
        });
    }


    protected void onResume(){
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.rvMecanicos);
        recyclerView.setAdapter(new MecanicoAdapter(this.getMecanicos(),this,this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public List<Mecanico> getMecanicos(){

        ArrayList<Mecanico> list = new ArrayList<>();

        Mecanico mecanico = new Mecanico();
        mecanico.setNome("Bruno");
        mecanico.setFuncao("Mecânico");

        list.add(mecanico);

        return list;
    }

    @Override
    public void onClick(Object object) {

    }



}
