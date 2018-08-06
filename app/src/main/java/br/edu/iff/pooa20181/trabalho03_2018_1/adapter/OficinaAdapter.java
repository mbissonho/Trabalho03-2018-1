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
import br.edu.iff.pooa20181.trabalho03_2018_1.model.Oficina;
import br.edu.iff.pooa20181.trabalho03_2018_1.view.ViewOficinaActivity;

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

        oficinaHolder.setOficina(oficina);
        oficinaHolder.bind();
    }



    @Override
    public int getItemCount() {
        return this.oficinas.size();
    }

    public class OficinaHolder extends RecyclerView.ViewHolder {

        private Oficina oficina;
        private TextView nome, rua;
        private ImageButton btnVer;

        public OficinaHolder(View view){
            super(view);

            this.nome = view.findViewById(R.id.lNomeOficina);
            this.rua = view.findViewById(R.id.lRuaOficina);
            this.btnVer = view.findViewById(R.id.btnVer);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickRecyclerViewListener.onClick(oficinas.get(getLayoutPosition()));
                }
            });


            this.btnVer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(OficinaAdapter.this.ctx, ViewOficinaActivity.class);
                    intent.putExtra("id",OficinaHolder.this.oficina.getId());
                    OficinaAdapter.this.ctx.startActivity(intent);
                }
            });

        }

        public void setOficina(Oficina oficina){
            this.oficina = oficina;
        }

        public void bind(){
            this.nome.setText(this.oficina.getNome());
            this.rua.setText(this.oficina.getRua());
        }

    }

}
