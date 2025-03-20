package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCaminhada;

public class Porta {

	public static void main(String[] args) {
		int entrada = 1;
		Semaphore semaforo = new Semaphore(entrada);
		
		for (int idCorredor = 0; idCorredor < 4; idCorredor++) {
			Thread tCorredor = new ThreadCaminhada(idCorredor, semaforo);
			tCorredor.start();
		}
	}

}
