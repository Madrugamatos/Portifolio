package models.exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuException {


    private static final Scanner sc = new Scanner(System.in);

    public static Integer exceptionInt() {
        while(true) {
            try {

                return sc.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Você não digitou um numero valido.");

            }
        }





    }

}
