package br.com.allefdeveloper.despesasmensais.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by allef on 07/02/2018.
 */

public class GastosDiarios {

    private String id;
    private String titulo;
    private String descricao;
    private Double valor;
    private String formaPagamento;
    private String dataDispesa;
    private String categoria;


    public GastosDiarios() {
    }


    public String getDataDispesa() {
        return dataDispesa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDataDispesa(String dataDispesa) {
        this.dataDispesa = dataDispesa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Exclude
    public Map<String, Object> MapAtualiza() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("titulo", titulo);
        result.put("descricao", descricao);
        result.put("valor",valor);
        result.put("formaPagamento", formaPagamento);
        result.put("dataDispesa", dataDispesa);
        result.put("categoria",categoria);

        return result;
    }
}
