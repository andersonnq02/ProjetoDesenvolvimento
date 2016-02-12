package Programa;

public class AnimaCronometro  {
	private static final int HORA = 1000 * 60 * 60;
	private static final int MINUTO = 1000 * 60;
	private static final int SEGUNDO = 1000;

	private long inicio;

	public AnimaCronometro() {
		inicio = System.currentTimeMillis();
	}
 //Inicio da Thread.
	public void rodar() {
		while (true) {
			
			long valor = System.currentTimeMillis() - inicio;
			long horas = 0, minutos = 0, segundos = 0;
				
			if (valor > HORA) {
				horas = valor / HORA;
				valor -= horas * HORA;
			}
			if (valor > MINUTO) {
				minutos = valor / MINUTO;
				valor -= minutos * MINUTO;
			}
			if (valor > SEGUNDO) {
				segundos = valor / SEGUNDO;
				valor -= segundos * SEGUNDO;
			}

			System.out.println(String.format("%02d:%02d:%02d - %06d", horas,
					minutos, segundos, valor));
			try {
				Thread.sleep(50);
			} catch (InterruptedException ie) {
			}

		}
	}

	public static void main(String[] args) {
		
		AnimaCronometro n = new AnimaCronometro();
		
		n.rodar();
	}
}