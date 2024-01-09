package models.entities;

import java.util.Date;

public class Conta {

    private String name;
    private Date date;
    private Double valor;
    private String status;

    public Conta() {
    }

    public Conta(String name, Date date, Double valor, String status) {
        this.name = name;
        this.date = date;
        this.valor = valor;
        this.status = status;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Conta{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", valor=" + valor +
                ", status='" + status + '\'' +
                '}';
    }
}
