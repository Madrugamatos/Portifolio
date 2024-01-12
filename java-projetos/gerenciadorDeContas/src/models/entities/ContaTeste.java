package models.entities;

import enums.NomeContas;

import java.util.Date;

public class ContaTeste  extends Conta{

    private NomeContas nome;
    private Date date;
    private Double valor;
    private String status;

    public ContaTeste(NomeContas nome, Date date, Double valor, String status) {
        this.nome = nome;
        this.date = date;
        this.valor = valor;
        this.status = status;
    }

    public NomeContas getNome() {
        return nome;
    }

    public void setNome(NomeContas nome) {
        this.nome = nome;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ContaTeste{" +
                "nome=" + nome +
                ", date=" + date +
                ", valor=" + valor +
                ", status=" + status +
                '}';
    }
}
