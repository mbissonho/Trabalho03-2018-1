package br.edu.iff.pooa20181.trabalho03_2018_1.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import br.edu.iff.pooa20181.trabalho03_2018_1.owninterface.ClickRecyclerViewListener;
import br.edu.iff.pooa20181.trabalho03_2018_1.adapter.OficinaAdapter;
import br.edu.iff.pooa20181.trabalho03_2018_1.R;
import br.edu.iff.pooa20181.trabalho03_2018_1.model.Oficina;
import io.realm.Realm;

public class OficinaListActivity extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oficina_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);

        this.realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabb);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OficinaListActivity.this, ManageOficinaActivity.class);
                intent.putExtra("id",0);
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
        return (List) this.realm.where(Oficina.class).findAll();
    }

    @Override
    public void onClick(Object object) {
        Oficina o = (Oficina) object;
        Intent intent = new Intent(OficinaListActivity.this, ManageOficinaActivity.class);
        intent.putExtra("id", o.getId());
        startActivity(intent);
    }
}
