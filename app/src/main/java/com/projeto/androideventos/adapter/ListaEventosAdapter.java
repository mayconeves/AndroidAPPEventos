package com.projeto.androideventos.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.projeto.androideventos.R;
import com.projeto.androideventos.model.Eventos;
import com.projeto.androideventos.util.AndroidUtils;
import com.projeto.androideventos.controller.DetalhesActivity;

import java.util.List;

public class ListaEventosAdapter extends RecyclerView.Adapter<ListaEventosAdapter.ViewHolder> {

    /**
     * OBJETIVO.......: INSTANCIAR COMPONENTES
     */
    Context contexto;
    List<Eventos> listaEventos;

    /**
     * OBJETIVO.......: CONSTRUTOR
     */
    public ListaEventosAdapter(List<Eventos> listaEventos, Context contexto) {
        super();
        this.listaEventos = listaEventos;
        this.contexto = contexto;
    }

    /**
     * OBJETIVO.......: ASSOCIAR VIEW AO RECYCLERVIEW ITEM
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_eventos_item_2, parent, false);
        return new ViewHolder(view);
    }

    /**
     * OBJETIVO.......: POPULAR OS DADOS DO RECYCLERVIEW
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Eventos eventosAux = listaEventos.get(position);

        String nomeEvento = eventosAux.getTituloEvento();
        String dataEvento = AndroidUtils.converterTimestampToDate(eventosAux.getDataEvento());
        String imagemEvento = eventosAux.getImagemEvento();

        holder.txvNomeEvento.setText(nomeEvento);
        //holder.txvDataEvento.setText(dataEvento);

        if(imagemEvento != null){
            AndroidUtils.downloadImageTask(imagemEvento, 1080, 480, 15, 0 , holder.imagemEvento);
        }

        /**
         * OBJETIVO.......: AO CLICAR NO EVENTO
         */
        holder.layoutEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detalhesEvento(eventosAux);
            }
        });
    }


    /**
     * OBJETIVO.......: IR PARA A TELA DO EVENTO
     */
    private void detalhesEvento(Eventos evento) {
        Intent intent;
        intent = new Intent(contexto, DetalhesActivity.class);
        intent.putExtra("idEvento", evento.getIdEvento());
        contexto.startActivity(intent);
    }

    /**
     * OBJETIVO.......: RECUPERAR O TOTAL DE ITENS NA LISTA
     */
    @Override
    public int getItemCount() {
        return listaEventos.size();
    }

    /**
     * OBJETIVO.......: ASSOCIAR A VIEW AOS COMPONENTES DA INTERFACE
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

//        final TextView txvNomeEvento;
//        final TextView txvDataEvento;
//        final ImageView imagemEvento;

        final MaterialTextView txvNomeEvento;
        final AppCompatImageView imagemEvento;

        final ConstraintLayout layoutEvento;

        public ViewHolder(View view) {
            super(view);

//            txvNomeEvento   = view.findViewById(R.id.txvNomeEvento);
//            txvDataEvento   = view.findViewById(R.id.txvDataEvento);
//            imagemEvento    = view.findViewById(R.id.imagemEvento);

            txvNomeEvento   = view.findViewById(R.id.txvNomeEvento);
            imagemEvento    = view.findViewById(R.id.imagemEvento);
            layoutEvento    = view.findViewById(R.id.constrainLayoutEvento);
        }
    }
}
