/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketlistener;

/**
 *
 * @author Abraham SA
 */
public class Thread1 {
    public static void main(String[] args){
        for(int i = 0; i < 10; i++){
            Thread thread1 = new Thread(new Task());
            thread1.start();
        }
        System.out.println("Thread name: " + Thread.currentThread().getName());
    }

    private static class Task implements Runnable {

        public Task() {
        }

        @Override
        public void run() {
            System.out.println("Thread name: " + Thread.currentThread().getName());
        }
    }
}
