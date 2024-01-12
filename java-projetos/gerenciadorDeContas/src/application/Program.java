package application;

import models.controls.ControleDoMenu;
import models.entities.Conta;

import java.util.Locale;
import java.util.Objects;


public class Program {
    public static void main(String[] args)  {

        Locale.setDefault(Locale.US);
        int conf;


        do{
            conf = ControleDoMenu.menuPrincipal();

            switch(conf){
                case 0:
                    System.out.println("Fim do Programa");
                    break;

                case 1:
                    ControleDoMenu.menuAdicionar();
                    break;

                case 2:
                    ControleDoMenu.menuUpdateStatus();
                    break;

                case 3:
                    ControleDoMenu.menuDelete();
                    break;

                case 4:
                    System.out.println(ControleDoMenu.menuFind());
                    break;

                case 5:
                    try{
                        for(Conta c : Objects.requireNonNull(ControleDoMenu.menuFindName())){
                            System.out.println(c);
                        }
                    }
                    catch(NullPointerException e){
                        System.out.println("busca encerrada.");
                    }
                    break;

                case 6:
                    ControleDoMenu.menuFindAllContas();
                    break;

                default:
                    System.out.println("Opção Indisponivel.");
            }
        }while(conf != 0);


        ControleDoMenu.closeScanner();
        ControleDoMenu.closeConnection();


    }
}
