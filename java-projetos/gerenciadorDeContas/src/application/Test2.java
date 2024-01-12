package application;

import conection.Conection;
import enums.NomeContas;
import models.controls.ControleDeContas;
import models.controls.ControleDoMenu;
import models.controls.ControlerFactory;
import models.entities.Conta;
import models.entities.ContaTeste;
import models.exceptions.MenuException;

import java.util.*;

public class Test2 {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        //Codigo para apagar todas as contas selecionadas

        /*
        ControleDeContas conn = ControlerFactory.activeConnetion();
        for(Conta c :conn.findAllContaName("internet")){
            conn.deleteContas(c.getName(),c.getDate());
        }
        System.out.println("Conta deletadas com Sucesso!");

         */



        //ControleDeContas conn = ControlerFactory.activeConnetion();
       // Conta conta = new Conta(NomeContas.LUZ.getName(),ControleDoMenu.controlDate("11/01/2024"),100.0,"P");

        List<String> lista = new ArrayList<>(Arrays.asList("",
                NomeContas.LUZ.getName(),
                NomeContas.AGUA.getName(),
                NomeContas.FACULDADE.getName(),
                NomeContas.CELULAR.getName(),
                NomeContas.MEI.getName() ));

        List<String> menuPrincipal = new ArrayList<>(Arrays.asList(
                "0 - Sair\n",
                "1 - Adicionar Conta\n",
                "2 - Atualizar Conta\n",
                "3 - Deletar Conta\n",
                "4 - Buscar Conta\n",
                "5 - Buscar Contas\n",
                "6 - Mostrar Todas as Contas\n"
                ));


        Integer conf;
        String nome = "Voltar";
        System.out.println(nome.equals("Voltar"));

        System.out.println(menuPrincipal.size());
        /*
        do {
            for(String s : lista2){
                System.out.print(s);
            }

            {
                try {
                    conf = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Digite uma opção Valida");
                    sc.nextLine();
                }

            }
        }while(true);

        System.out.println(lista2.get( conf));
        */

        //System.out.println(conta);


        sc.close();

        ControleDoMenu.closeConnection();
    }
}
