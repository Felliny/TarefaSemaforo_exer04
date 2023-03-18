package Controller;

import java.util.concurrent.Semaphore;

public class Banco extends Thread {

    private int saldo;
    private int saque;
    private int deposito;
    private Semaphore sacar;
    private Semaphore depositar;
    private static int tipo= 1; //Tipo de transação, par = deposito, impar = saque;

    public Banco(int saldo, int saque, int deposito, Semaphore depositar, Semaphore sacar){
        this.deposito= deposito;
        this.saque= saque;
        this.saldo= saldo;
        this.sacar= sacar;
        this.depositar= depositar;
    }

    @Override
    public void run() {
        try {
            depositar.acquire();
            sacar.acquire();
            depositar();
            sacar();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            depositar.release();
            sacar.release();
        }



    }

    private void depositar() {
        if(tipo % 2 == 0){
            saldo+= deposito;
            if (deposito!= 1){
                System.out.println("A conta "+ getId()+" depositou "+ deposito +" reais. saldo atual: "+saldo);
            }
            else {
                System.out.println("A conta "+ getId() +" depositou "+ deposito +" real. saldo atual: "+saldo);
            }
            tipo++;

        }

    }

    private void sacar() {
        if(tipo % 2 != 0){

            if (saque > saldo){
                System.out.println("A conta "+ getId() +" tentou sacar "+ saque +" reais, mas não possuia saldo necessário. saldo atual: "+saldo);
            }
            else {
                saldo-= saque;
                if (saque!= 1){
                    System.out.println("A conta "+ getId() +" sacou "+ saque +" reais. saldo atual: "+saldo);
                }
                else {
                    System.out.println("A conta "+ getId() +" sacou "+ saque +" real. saldo atual: "+saldo);
                }

            }
            tipo++;

        }


    }
}
