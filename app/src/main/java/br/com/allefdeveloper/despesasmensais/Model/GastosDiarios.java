package br.com.allefdeveloper.despesasmensais.Model;

/**
 * Created by allef on 07/02/2018.
 */

public class GastosDiarios {

    private String titulo;
    private String descricao;
    private Double valor;
    private String formaPagamento;
    private String dataDispesa;


    public GastosDiarios() {
    }


    public String getDataDispesa() {
        return dataDispesa;
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
}
