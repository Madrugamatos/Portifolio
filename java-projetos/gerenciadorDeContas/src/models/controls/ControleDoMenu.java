package models.controls;

import conection.Conection;
import models.entities.Conta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ControleDoMenu {





    private static final Scanner sc = new Scanner(System.in);
    private static final ControleDeContas conn =  ControlerFactory.activeConnetion();
    private static final List<String> menuPrincipalLista = new ArrayList<>(Arrays.asList(
            "0 - Sair\n",
            "1 - Adicionar Conta\n",
            "2 - Atualizar Conta\n",
            "3 - Deletar Conta\n",
            "4 - Buscar Conta\n",
            "5 - Buscar Contas\n",
            "6 - Mostrar Todas as Contas\n"
            ));

    public ControleDoMenu(){
        Locale.setDefault(Locale.US);
    }


    public static Integer menuPrincipal(){





                int conf = menuPrincipalLista.size() ;
                do {

                        try {
                            for(String s : menuPrincipalLista) {
                                System.out.print(s);
                            }
                            conf = sc.nextInt();
                            sc.nextLine();

                        }
                        catch (InputMismatchException e) {
                            System.out.println("Character Invalido.");
                            sc.nextLine();
                        }


                }while(conf >= menuPrincipalLista.size() || conf < 0);
                return conf;




    }

    public static Date controlDate(String date){


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        boolean teste = false;


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

        double valor;
         do{
             System.out.print("Valor da Conta: ");
            try {
                valor = sc.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Digite um valor Valido");
                sc.nextLine();
            }

        }while(true);

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

    public static void closeConnection(){
        Conection.closeConnection(conn.getConn());
    }



}
