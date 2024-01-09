import conection.Conection;
import models.controls.ControleDeContas;
import models.controls.ControlerFactory;
import models.entities.Conta;

import java.sql.Connection;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Digite a data dd/mm/aaaa: ");
        Date data = sdf.parse(sc.nextLine());

        SimpleDateFormat sdfTeste = new SimpleDateFormat("yyyy-MM-dd");
        String real = sdfTeste.format(data);




        data = sdfTeste.parse(real);
        System.out.println(sdf.format(data));



        ControleDeContas conn =  ControlerFactory.activeConnetion();



        Conta conta = new Conta("Agua",data,50.0,"P");

        conn.insertContas(conta);


        //Conection.closeConnection(conn);





    }
}