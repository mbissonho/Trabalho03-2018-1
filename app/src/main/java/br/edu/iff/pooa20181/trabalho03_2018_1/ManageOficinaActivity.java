package br.edu.iff.pooa20181.trabalho03_2018_1;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.iff.pooa20181.trabalho03_2018_1.model.Mecanico;
import br.edu.iff.pooa20181.trabalho03_2018_1.model.Oficina;
import io.realm.Realm;

public class ManageOficinaActivity extends AppCompatActivity {

    private EditText tNome, tRua, tBairro, tMunicipio;
    private Button btnSalvar, btnAlterar, btnDeletar;
    private ConstraintLayout layout;

    private int id;
    private Oficina oficina;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_oficina);

        this.bind();

        Intent intent = this.getIntent();
        this.id = (int) intent.getSerializableExtra("id");
        this.realm = Realm.getDefaultInstance();

        if(this.id != 0){

            this.btnSalvar.setEnabled(false);
            this.oficina = realm.where(Oficina.class).equalTo("id",this.id).findFirst();

            this.tNome.setText(this.oficina.getNome());
            this.tRua.setText(this.oficina.getRua());
            this.tBairro.setText(this.oficina.getBairro());
            this.tMunicipio.setText(this.oficina.getMunicipio());

        }else{

            this.btnDeletar.setEnabled(false);
            this.btnAlterar.setEnabled(false);
        }

        this.btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManageOficinaActivity.this.salvar();
            }
        });


        this.btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManageOficinaActivity.this.alterar();
            }
        });

        this.btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManageOficinaActivity.this.deletar();
            }
        });
    }

    private void bind(){
        this.tNome = findViewById(R.id.tNome);
        this.tRua = findViewById(R.id.tRua);
        this.tBairro = findViewById(R.id.tBairro);
        this.tMunicipio = findViewById(R.id.tMunicipio);
        this.btnSalvar = findViewById(R.id.btnSalvar);
        this.btnAlterar = findViewById(R.id.btnAlterar);
        this.btnDeletar = findViewById(R.id.btnDeletar);
    }

    private void salvar() {

        int nextID = 1;

        if(this.realm.where(Oficina.class).max("id") != null){
            nextID = this.realm.where(Mecanico.class).max("id").intValue() + 1;
        }

        this.realm.beginTransaction();

        Oficina o = new Oficina();
        o.setId(nextID);

        this.populate(o);

        this.realm.copyToRealm(o);
        this.realm.commitTransaction();
        this.realm.close();

        Toast.makeText(this,"Oficina Cadastrada!", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    private void populate(Oficina oficina){
        oficina.setNome(this.tNome.getText().toString());
        oficina.setRua(this.tRua.getText().toString());
        oficina.setBairro(this.tBairro.getText().toString());
        oficina.setMunicipio(this.tMunicipio.getText().toString());
    }

    private void alterar() {
        realm.beginTransaction();

        populate(this.oficina);

        realm.copyToRealm(this.oficina);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Oficina Alterada!", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    private void deletar() {
        realm.beginTransaction();
        this.oficina.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Oficina Excluída!", Toast.LENGTH_SHORT).show();
        this.finish();
    }

}
