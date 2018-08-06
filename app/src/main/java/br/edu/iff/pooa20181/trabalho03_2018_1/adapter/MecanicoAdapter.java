package br.edu.iff.pooa20181.trabalho03_2018_1.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import br.edu.iff.pooa20181.trabalho03_2018_1.owninterface.ClickRecyclerViewListener;
import br.edu.iff.pooa20181.trabalho03_2018_1.R;
import br.edu.iff.pooa20181.trabalho03_2018_1.model.Mecanico;
import br.edu.iff.pooa20181.trabalho03_2018_1.view.ViewMecanicoActivity;

public class MecanicoAdapter extends RecyclerView.Adapter{

    private List<Mecanico> mecanicos;
    private Context ctx;
    private static ClickRecyclerViewListener clickRecyclerViewListener;


    public MecanicoAdapter(List<Mecanico> mecanicos, Context ctx, ClickRecyclerViewListener clickRecyclerViewListener){
        this.mecanicos = mecanicos;
        this.ctx = ctx;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(this.ctx).inflate(R.layout.item_mecanico, parent, false);
        MecanicoHolder mecanicoHolder = new MecanicoHolder(view);

        return mecanicoHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MecanicoHolder mecanicoHolder = (MecanicoHolder) holder;

        Mecanico mecanico = this.mecanicos.get(position);

        mecanicoHolder.setMecanico(mecanico);
        mecanicoHolder.bind();
    }



    @Override
    public int getItemCount() {
        return this.mecanicos.size();
    }

    public class MecanicoHolder extends RecyclerView.ViewHolder {

        private Mecanico mecanico;
        private TextView nome, funcao;
        private ImageButton btnVer;


        public MecanicoHolder(View view){
            super(view);

            this.nome = view.findViewById(R.id.lNomeMecanico);
            this.funcao = view.findViewById(R.id.lFuncaoMecanico);
            this.btnVer = view.findViewById(R.id.btnVer);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickRecyclerViewListener.onClick(mecanicos.get(getLayoutPosition()));
                }
            });

            this.btnVer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MecanicoAdapter.this.ctx, ViewMecanicoActivity.class);
                    intent.putExtra("id",MecanicoHolder.this.mecanico.getId());
                    MecanicoAdapter.this.ctx.startActivity(intent);
                }
            });

        }

        public void setMecanico(Mecanico mecanico){
            this.mecanico = mecanico;
        }

        public void bind(){
            this.nome.setText(this.mecanico.getNome());
            this.funcao.setText(this.mecanico.getFuncao());
        }

    }

}

