package models.controls;

import conection.Conection;

import java.sql.Connection;

public class ControlerFactory {

    public static ControleDeContas activeConnetion(){
        return new ControleDeContas(Conection.getConnection());
    }
}
