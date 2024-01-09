package models.controls;

import conection.Conection;
import models.entities.Conta;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ControleDeContas {

    private Connection conn;

    public ControleDeContas(Connection conn) {
        this.conn = conn;
    }

    public void insertContas(Conta conta){
        PreparedStatement ps = null;

        try{

            ps = conn.prepareStatement(
                    "INSERT INTO controle\n" +
                            "(Contas, Datas, Valor, status_pagamento)\n" +
                            "values\n" +
                            "(?,?,?,?)"
            );
            ps.setString(1, conta.getName());
            ps.setDate(2,new java.sql.Date(conta.getDate().getTime()));
            ps.setDouble(3,conta.getValor());
            ps.setString(4,conta.getStatus());

            ps.executeUpdate();


        }
        catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            Conection.closeStatement(ps);
        }

    }

    public void updateContas(Conta conta){

    }

    public void deleteContas(String name, Date data){

    }

    public Conta findConta(String name,Date data){
        return null;
    }

    public List<Conta> findAllContaName(String name){
        return null;
    }

    public List<Conta> printAllConta(){
        return null;
    }

}
