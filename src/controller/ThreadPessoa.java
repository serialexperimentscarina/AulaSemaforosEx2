package controller;

import java.util.concurrent.Semaphore;

public class ThreadPessoa extends Thread{
	
	private Semaphore semaforo;
	private int pessoaId;
	
	public ThreadPessoa(int pessoaId, Semaphore semaforo) {
		this.pessoaId = pessoaId;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		caminharPessoa();
		try {
			semaforo.acquire();
			cruzarPorta();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			sairPessoa();
		}

	}

	// Imprime em console quando uma pessoa está caminhando até a porta.
	// Cada corredor tem 200m e cada pessoa anda de 4 a 6 m/s.
	private void caminharPessoa() {
		int distPercorrida = 0;
		while (distPercorrida < 200) {
			int metrosPorSeg = (int)((Math.random() * 3) + 4); // 4 - 6 m.
			try {
				sleep(1000); // 1 s.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			distPercorrida = distPercorrida + metrosPorSeg > 200 ? 200 : (distPercorrida + metrosPorSeg);
			System.out.println("A pessoa #" + pessoaId + " percorreu " + distPercorrida + " m.");
		}
		System.out.println("A pessoa #" + pessoaId + " chegou na porta.");
	}

	// Imprime em console quando uma pessoa está abrindo a porta
	// Cada pessoa leva de 1 a 2 segundos para abrir e cruzar a porta.
	private void cruzarPorta() {
		int tempoAbrindo = (int)((Math.random() * 1001) + 1000); // 1 - 2 s.
		System.out.println("A pessoa #" + pessoaId + " abriu a porta.");
		try {
			sleep(tempoAbrindo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Imprime em console quando uma pessoa terminou de cruzar a porta.
	private void sairPessoa() {
		System.out.println("A pessoa #" + pessoaId + " passou pela porta.");
	}
}
