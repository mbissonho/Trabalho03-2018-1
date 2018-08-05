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
import io.realm.Realm;

public class ManageMecanicoActivity extends AppCompatActivity {

    private EditText tNome, tFuncao, tDataDeNascimento, tRua, tBairro, tMunicipio;
    private Button btnSalvar, btnAlterar, btnDeletar;
    private ConstraintLayout layout;

    private int id;
    private Mecanico mecanico;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_mecanico);

        this.bind();

        Intent intent = this.getIntent();
        this.id = (int) intent.getSerializableExtra("id");
        this.realm = Realm.getDefaultInstance();

        if(this.id != 0){

            this.btnSalvar.setEnabled(false);
            this.mecanico = realm.where(Mecanico.class).equalTo("id",this.id).findFirst();

            this.tNome.setText(this.mecanico.getNome());
            this.tFuncao.setText(this.mecanico.getFuncao());
            this.tRua.setText(this.mecanico.getRua());
            this.tBairro.setText(this.mecanico.getBairro());
            this.tMunicipio.setText(this.mecanico.getMunicipio());

        }else{

            this.btnDeletar.setEnabled(false);
            this.btnAlterar.setEnabled(false);
        }

        this.btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManageMecanicoActivity.this.salvar();
            }
        });


        this.btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManageMecanicoActivity.this.alterar();
            }
        });

        this.btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManageMecanicoActivity.this.deletar();
            }
        });


    }

    private void salvar() {

        int nextID = 1;

        if(this.realm.where(Mecanico.class).max("id") != null){
            nextID = this.realm.where(Mecanico.class).max("id").intValue() + 1;
        }


        this.realm.beginTransaction();

        Mecanico m = new Mecanico();
        m.setId(nextID);

        this.populate(m);

        this.realm.copyToRealm(m);
        this.realm.commitTransaction();
        this.realm.close();

        Toast.makeText(this,"Mecânico Cadastrado!", Toast.LENGTH_SHORT).show();
        this.finish();
    }


    private void bind(){
        this.tNome = findViewById(R.id.tNome);
        this.tFuncao = findViewById(R.id.tFuncao);
        this.tRua = findViewById(R.id.tRua);
        this.tBairro = findViewById(R.id.tBairro);
        this.tMunicipio = findViewById(R.id.tMunicipio);
        this.btnSalvar = findViewById(R.id.btnSalvar);
        this.btnAlterar = findViewById(R.id.btnAlterar);
        this.btnDeletar = findViewById(R.id.btnDeletar);
    }

    private void populate(Mecanico mecanico){
        mecanico.setNome(this.tNome.getText().toString());
        mecanico.setFuncao(this.tFuncao.getText().toString());
        mecanico.setRua(this.tRua.getText().toString());
        mecanico.setBairro(this.tBairro.getText().toString());
        mecanico.setMunicipio(this.tMunicipio.getText().toString());
    }

    private void alterar() {
    }

    private void deletar() {
    }

}
