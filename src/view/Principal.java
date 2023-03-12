package view;

import java.util.concurrent.Semaphore;

import controller.ThreadPessoa;

public class Principal {

	public static void main(String[] args) {
		Semaphore mutex = new Semaphore(1);
		
		for (int i = 1; i <= 4; i++) {
			Thread pessoa = new ThreadPessoa(i, mutex);
			pessoa.start();
		}
	}

}
