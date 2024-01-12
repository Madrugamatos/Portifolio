package models.controls;

import conection.Conection;
import enums.NomeContas;
import models.entities.Conta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ControleDoMenu {





    private static final Scanner sc = new Scanner(System.in);
    private static final ControleDeContas conn =  ControlerFactory.activeConnetion();
    private static final List<String> menuPrincipalLista = new ArrayList<>(Arrays.asList(
            "Sair",
            "Adicionar Conta",
            "Atualizar Conta",
            "Deletar Conta",
            "Buscar Conta",
            "Buscar Contas",
            "Mostrar Todas as Contas"
            ));

    private static final List<String> insertMenuList = new ArrayList<>(Arrays.asList(
            "Voltar",
            NomeContas.LUZ.getName(),
            NomeContas.AGUA.getName(),
            NomeContas.FACULDADE.getName(),
            NomeContas.CELULAR.getName(),
            NomeContas.MEI.getName() ));


//Constructor
    public ControleDoMenu(){
        Locale.setDefault(Locale.US);
    }

//Methods
    public static Integer menuPrincipal(){
        System.out.println("=== MENU ===");
        return menuMainFactory(menuPrincipalLista);
    }

    public static void menuAdicionar(){

        System.out.println("Nome da Conta: ");
        String nome = menuSecondFactory(insertMenuList);
        if(!nome.equals("Voltar")) {
            System.out.print("Date dd/mm/aaaa: ");
            Date date = ControleDoMenu.controlDate(sc.nextLine());

            double valor;
            do {
                System.out.print("Valor da Conta: ");
                try {
                    valor = sc.nextDouble();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Digite um valor Valido");
                    sc.nextLine();
                }

            } while (true);

            System.out.print("Status da Conta: ");
            char status = sc.next().charAt(0);
            System.out.println("Conta Adicionada com sucesso");
            conn.insertContas(instanciaDeConta(nome, date, valor, Character.toString(status).toUpperCase()));
            sc.nextLine();
        }

    }

    public static void menuUpdateStatus(){
        System.out.println("Qual Conta vai Atualizar:");
        String nome = menuSecondFactory(insertMenuList);
        if(!nome.equals("Voltar")) {
            System.out.print("Data dd/mm/aaaa: ");
            Date date = ControleDoMenu.controlDate(sc.nextLine());
            Conta conta = conn.findConta(nome, date);
            System.out.print("Digite o Status para alterar: ");
            conta.setStatus(sc.next().toUpperCase());
            conn.updateContas(conta);
            System.out.println("Conta Alterada com Sucesso!");
            sc.nextLine();
        }
    }

    public static void menuDelete(){
        System.out.println("Nome e Data da Conta: ");
        System.out.println("Nome: ");
        String nome = menuSecondFactory(insertMenuList);

        while(!nome.equals("Voltar")) {

            System.out.print("Data dd/mm/aaaa: ");
            Date date = ControleDoMenu.controlDate(sc.nextLine());
            conn.deleteContas(nome, date);
            System.out.println("Conta deletada com Sucesso!");
        }
    }

    public static Conta menuFind(){
        System.out.println("Nome e Data da Conta para busca: ");
        System.out.println("Nome: ");
        String nome = menuSecondFactory(insertMenuList);
        if(!nome.equals("Voltar")) {

            System.out.print("Data dd/mm/aaaa: ");
            Date date = ControleDoMenu.controlDate(sc.nextLine());
            return conn.findConta(nome, date);

        }
        System.out.println("busca encerrada.");
        return null;
    }

    public static void menuFindAllContas(){
        for(Conta c : conn.printAllConta()){
            System.out.println(c);
        }
    }

    public static List<Conta> menuFindName(){
        System.out.println("Nome da Conta para busca: ");
        System.out.println("Nome: ");
        String nome = menuSecondFactory(insertMenuList);
        if(nome.equals("Voltar")) {
            return conn.findAllContaName(nome);
        }

        return null;
    }



    public static Integer menuMainFactory(List<String> list){

        int conf = list.size() ;
        do {

            try {
                for(int i = 0; i < list.size();i++) {
                    System.out.println(i + " - " + list.get(i));
                }
                conf = sc.nextInt();
                if(conf >= list.size() || conf < 0){
                    System.out.println("Digite uma opção valida.");
                }
                sc.nextLine();

            }
            catch (InputMismatchException e) {
                System.out.println("Character Invalido.");
                sc.nextLine();
            }


        }while(conf >= list.size() || conf < 0);
        return conf;
    }

    public static String menuSecondFactory(List<String> list){
        int conf = list.size() ;
        do {

            try {
                for(int i = 0; i < list.size();i++) {
                    System.out.println(i + " - " + list.get(i));
                }
                conf = sc.nextInt();


                if(conf >= list.size() || conf < 0){
                    System.out.println("Digite uma opção valida.");

                }


                sc.nextLine();

            }
            catch (InputMismatchException e) {
                System.out.println("Character Invalido.");
                sc.nextLine();
            }


        }while(conf >= list.size() || conf < 0);

        return list.get(conf);
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

    public static void closeScanner(){
        sc.close();
        System.out.println("Scan Fechado!");
    }

    public static void closeConnection(){
        Conection.closeConnection(conn.getConn());
    }



}
