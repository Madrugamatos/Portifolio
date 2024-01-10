package models.controls;

import conection.Conection;
import models.entities.Conta;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ControleDeContas {

    private Connection conn;

    public ControleDeContas(Connection conn) {
        this.conn = conn;
    }

    public Connection getConn() {
        return conn;
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
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(
                    "UPDATE controle \n" +
                    " SET Contas = ?, Datas = ? ,Valor =  ?, status_pagamento = ?\n" +
                    " WHERE Contas = ? and Datas = ?\n" +
                    " limit 1;"
            );
            ps.setString(1,conta.getName());
            ps.setDate(2,new java.sql.Date(conta.getDate().getTime()));
            ps.setDouble(3,conta.getValor());
            ps.setString(4,conta.getStatus());
            ps.setString(5, conta.getName());
            ps.setDate(6,new java.sql.Date(conta.getDate().getTime()));

            ps.executeUpdate();
        }
        catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            Conection.closeStatement(ps);

        }

    }

    public void deleteContas(String name, Date date){
        PreparedStatement ps = null;

        try{

            ps = conn.prepareStatement(
                    "DELETE FROM contas.controle WHERE Contas = ? and Datas = ? limit 1"
            );
            ps.setString(1, name);
            ps.setDate(2,new java.sql.Date(date.getTime()));


            ps.executeUpdate();


        }
        catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            Conection.closeStatement(ps);
        }


    }

    public Conta findConta(String name, Date data){
        PreparedStatement ps =null;
        ResultSet rs = null;
        try{
            ps = conn.prepareStatement(
                    "SELECT * FROM contas.controle\n" +
                        "WHERE Contas = ? and Datas = ?"
            );
            ps.setString(1,name);
            ps.setDate(2,new java.sql.Date(data.getTime()) );

            rs = ps.executeQuery();
            if(rs.next()){


                return instaciaConta(rs);
            }
            return null;
        }
        catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            Conection.closeResultser(rs);
            Conection.closeStatement(ps);
        }


    }

    public List<Conta> findAllContaName(String name){
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Conta> list = new ArrayList<>();

        try{
            ps = conn.prepareStatement(
                    "SELECT * FROM contas.controle\n"
                    + "WHERE Contas = ?");

            ps.setString(1,name);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(instaciaConta(rs));
            }
            return list;
        }
        catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            Conection.closeResultser(rs);
            Conection.closeStatement(ps);
        }


    }

    public List<Conta> printAllConta(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Conta> list = new ArrayList<>();

        try{
            ps = conn.prepareStatement(
                    "SELECT * FROM contas.controle");

            rs = ps.executeQuery();
            while(rs.next()){
                list.add(instaciaConta(rs));
            }
            return list;
        }
        catch(SQLException | NullPointerException e ){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            Conection.closeResultser(rs);
            Conection.closeStatement(ps);
        }

    }

    public static Conta instaciaConta(ResultSet rs) throws SQLException{
        Conta conta = new Conta();
        conta.setName(rs.getString("Contas"));
        conta.setDate(rs.getDate("Datas"));
        conta.setValor(rs.getDouble("Valor"));
        conta.setStatus(rs.getString("status_pagamento"));

        return conta;
    }

}
