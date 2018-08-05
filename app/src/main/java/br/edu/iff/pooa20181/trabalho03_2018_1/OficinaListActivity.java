package br.edu.iff.pooa20181.trabalho03_2018_1;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.pooa20181.trabalho03_2018_1.model.Oficina;

public class OficinaListActivity extends AppCompatActivity implements ClickRecyclerViewListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oficina_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabb);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OficinaListActivity.this, ManageOficinaActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onResume(){
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.rvOficinas);
        recyclerView.setAdapter(new OficinaAdapter(this.getOficinas(),this,this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public List<Oficina> getOficinas(){

        ArrayList<Oficina> list = new ArrayList<>();

        Oficina oficina = new Oficina();
        oficina.setNome("Nova");
        oficina.setRua("Marechal Deodoro");

        list.add(oficina);

        return list;
    }

    @Override
    public void onClick(Object object) {

    }
}
