package View;

import Controller.Banco;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        int deposito= 0;
        int saque= 0;
        int saldo= 0;
        Semaphore sacar= new Semaphore(1);
        Semaphore depositar= new Semaphore(1);



        for (int i=1; i<=20; i++){
            saldo=(int) (Math.random()* 1000) + 1;
            deposito=(int) (Math.random()* 1000) + 1;
            saque=(int) (Math.random()* 500) + 1;
            Thread transacoes= new Banco(saldo, deposito, saque, sacar, depositar);
            transacoes.start();


        }
    }
}
