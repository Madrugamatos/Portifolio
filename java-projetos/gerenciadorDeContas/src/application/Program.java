package application;

import models.controls.ControleDoMenu;
import models.entities.Conta;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        int conf;

        System.out.println("=== MENU ===");
        do{
            conf = ControleDoMenu.menuPrincipal();

            switch(conf){
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
                    for(Conta c : ControleDoMenu.menuFindName()){
                        System.out.println(c);
                    }
                    break;

                case 6:
                    ControleDoMenu.menuFindAllContas();
                    break;

                default:
                    System.out.println("Fim do Programa");
            }
        }while(conf < 7 && conf > 0);


        ControleDoMenu.closeScanner();
    }
}
