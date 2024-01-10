package models.controls;

import models.entities.Conta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static java.util.Locale.*;

public class ControleDoMenu {





    private static final Scanner sc = new Scanner(System.in);
    private static final ControleDeContas conn =  ControlerFactory.activeConnetion();

    public ControleDoMenu(){
        Locale.setDefault(Locale.US);
    }


    public static Integer menuPrincipal(){
        System.out.println("1 - Adicionar Conta\n" +
                "2 - Atualizar Conta\n" +
                "3 - Deletar Conta\n" +
                "4 - Buscar Conta\n" +
                "5 - Buscar Contas\n" +
                "6 - Mostrar Todas as Contas\n" +
                "7 - Sair"
        );

        Integer conf = sc.nextInt();
        sc.nextLine();
        return conf;
    }

    public static Date controlDate(String date){


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date data = sdf.parse(date);
            SimpleDateFormat sdfTeste = new SimpleDateFormat("yyyy-MM-dd");
            String real = sdfTeste.format(data);
            return sdfTeste.parse(real);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }


    public static Conta instanciaDeConta(String name, Date date,Double valor, String status){


        return new Conta(name,date,valor,status);

    }

    public static void menuAdicionar(){

        System.out.print("Nome da Conta: ");
        String nome = sc.nextLine();
        System.out.print("Date dd/mm/aaaa: ");
        Date date = ControleDoMenu.controlDate(sc.nextLine());
        System.out.print("Valor da Conta: ");
        Double valor = sc.nextDouble();
        System.out.print("Status da Conta: ");
        char status = sc.next().charAt(0);
        System.out.println("Conta Adicionada com sucesso");
        conn.insertContas(instanciaDeConta(nome,date,valor,Character.toString(status).toUpperCase()));
        sc.nextLine();

    }

    public static void menuUpdateStatus(){
        System.out.println("Nome e Data da Conta para busca: ");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Data dd/mm/aaaa: ");
        Date date = ControleDoMenu.controlDate(sc.nextLine());
        Conta conta = conn.findConta(nome,date);
        System.out.print("Digite o Status para alterar: ");
        conta.setStatus(sc.next().toUpperCase());
        conn.updateContas(conta);
        System.out.println("Conta Alterada com Sucesso!");
        sc.nextLine();
    }

    public static void menuDelete(){
        System.out.println("Nome e Data da Conta: ");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Data dd/mm/aaaa: ");
        Date date = ControleDoMenu.controlDate(sc.nextLine());
        conn.deleteContas(nome,date);
        System.out.println("Conta deletada com Sucesso!");

    }

    public static Conta menuFind(){
        System.out.println("Nome e Data da Conta para busca: ");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Data dd/mm/aaaa: ");
        Date date = ControleDoMenu.controlDate(sc.nextLine());
        return conn.findConta(nome,date);

    }

    public static List<Conta> menuFindName(){
        System.out.println("Nome da Conta para busca: ");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        return conn.findAllContaName(nome);
    }

    public static void menuFindAllContas(){
        for(Conta c : conn.printAllConta()){
            System.out.println(c);
        }
    }

    public static void closeScanner(){
        sc.close();
        System.out.println("Scan Fechado!");
    }



}
