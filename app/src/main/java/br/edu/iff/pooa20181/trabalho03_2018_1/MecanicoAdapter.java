package br.edu.iff.pooa20181.trabalho03_2018_1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.iff.pooa20181.trabalho03_2018_1.model.Mecanico;

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

        mecanicoHolder.nome.setText(mecanico.getNome());
        mecanicoHolder.funcao.setText(mecanico.getFuncao());
    }



    @Override
    public int getItemCount() {
        return this.mecanicos.size();
    }

    public class MecanicoHolder extends RecyclerView.ViewHolder {

        private TextView nome, funcao;


        public MecanicoHolder(View view){
            super(view);

            this.nome = view.findViewById(R.id.lNomeMecanico);
            this.funcao = view.findViewById(R.id.lFuncaoMecanico);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickRecyclerViewListener.onClick(mecanicos.get(getLayoutPosition()));
                }
            });
        }

    }

}
