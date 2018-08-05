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
import br.edu.iff.pooa20181.trabalho03_2018_1.model.Oficina;

public class OficinaAdapter extends RecyclerView.Adapter {

    private List<Oficina> oficinas;
    private Context ctx;
    private static ClickRecyclerViewListener clickRecyclerViewListener;


    public OficinaAdapter(List<Oficina> oficinas, Context ctx, ClickRecyclerViewListener clickRecyclerViewListener){
        this.oficinas = oficinas;
        this.ctx = ctx;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(this.ctx).inflate(R.layout.item_oficina, parent, false);
        OficinaHolder oficinaHolder = new OficinaHolder(view);

        return oficinaHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        OficinaHolder oficinaHolder = (OficinaHolder) holder;

        Oficina oficina = this.oficinas.get(position);

        oficinaHolder.nome.setText(oficina.getNome());
        oficinaHolder.rua.setText(oficina.getRua());
    }



    @Override
    public int getItemCount() {
        return this.oficinas.size();
    }

    public class OficinaHolder extends RecyclerView.ViewHolder {

        private TextView nome, rua;


        public OficinaHolder(View view){
            super(view);

            this.nome = view.findViewById(R.id.lNomeOficina);
            this.rua = view.findViewById(R.id.lRuaOficina);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickRecyclerViewListener.onClick(oficinas.get(getLayoutPosition()));
                }
            });
        }

    }

}
