package br.com.allefdeveloper.despesasmensais.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.allefdeveloper.despesasmensais.Model.GastosDiarios;
import br.com.allefdeveloper.despesasmensais.R;

/**
 * Created by allef on 25/02/2018.
 */

public class GastosAdapter extends RecyclerView.Adapter<GastosAdapter.ViewHolderGastos> {
    List<GastosDiarios> gastoslist;
    Context context;

    public GastosAdapter( Context context,List<GastosDiarios> gastoslist) {
        this.gastoslist = gastoslist;
        this.context = context;
    }

    /**
     * inflando o meu layout na view de cada item
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public GastosAdapter.ViewHolderGastos onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_gastos,parent,false);
        ViewHolderGastos holderGastos = new ViewHolderGastos(view);
        return holderGastos;
    }

    @Override
    public void onBindViewHolder(GastosAdapter.ViewHolderGastos h, int position) {
        if (!gastoslist.isEmpty() && gastoslist.size() > 0){
            h.categoria.setText(gastoslist.get(position).getCategoria());
            h.formaPagamento.setText(gastoslist.get(position).getFormaPagamento());
            h.valor.setText(String.valueOf(gastoslist.get(position).getValor()));
            h.data.setText(gastoslist.get(position).getDataDispesa());
            h.titulo.setText(gastoslist.get(position).getTitulo());
            h.descricao.setText(gastoslist.get(position).getDescricao());

        }

    }


    @Override
    public int getItemCount() {
        return gastoslist.size();
    }

    public class ViewHolderGastos extends RecyclerView.ViewHolder {
        TextView categoria;
        TextView titulo;
        TextView descricao;
        TextView valor;
        TextView data;
        TextView formaPagamento;

        public ViewHolderGastos(View view) {
            super(view);
            categoria = view.findViewById(R.id.ttcategoria);
            titulo = view.findViewById(R.id.tttitulo);
            descricao = view.findViewById(R.id.ttdescricao);
            valor = view.findViewById(R.id.ttvalor);
            data = view.findViewById(R.id.ttdata);
            formaPagamento = view.findViewById(R.id.ttpagamento);

        }
    }
}
