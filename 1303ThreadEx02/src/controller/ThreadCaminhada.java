package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadCaminhada extends Thread {
	private int idCorredor;
	private static volatile int posChegada;
	private static volatile int posSaida;
    private Random obj = new Random();
	private Semaphore semaforo;

	public ThreadCaminhada(int idCorredor, Semaphore semaforo) {
		this.idCorredor = idCorredor;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		corredorCaminhando();
		try {
			semaforo.acquire();
			corredorAbrindoPorta();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			semaforo.release();
			corredorSaindo();
		}
	}

	private void corredorCaminhando() {
		// TODO Auto-generated method stub
		int distanciaTotal = 200;
		int distanciaPercorrida = 0;
		int distanciaRestante = 0;
	    int saltoAnterior = 0;
	    int deslocamento = 0;
		while (distanciaPercorrida < distanciaTotal) {
	        saltoAnterior = deslocamento;
			deslocamento = obj.nextInt(4,7);
			distanciaPercorrida += deslocamento;
	        if (distanciaRestante < 0) distanciaRestante = 0;
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	        System.out.println("#" + idCorredor + " andou " + deslocamento + "m; Total: " + distanciaPercorrida + "m/200m. Último Salto: " + saltoAnterior + "m;");
		}
		posChegada++;
		System.out.println("#"+idCorredor+" foi o "+posChegada+"o. a chegar");
	}

	private void corredorAbrindoPorta() {
		// TODO Auto-generated method stub
		System.out.println("#" + idCorredor + " entrou");
		int tempo = (int) ((Math.random() * 1001) + 1000);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void corredorSaindo() {
		// TODO Auto-generated method stub
		posSaida++;
		System.out.println("#"+idCorredor+ " foi o " + posSaida + "o. a sair");
	}
}
