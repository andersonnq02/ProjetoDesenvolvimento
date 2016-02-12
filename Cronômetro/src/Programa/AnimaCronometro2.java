package Programa;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class AnimaCronometro2 extends JFrame implements Runnable {

	private JTextField display;
	private JTextArea parciais;
	private JButton parcial;
	private JButton start;
	private JPanel painelBotoes;
	private boolean rodar;

	private static final int HORA = 1000 * 60 * 60;
	private static final int MINUTO = 1000 * 60;
	private static final int SEGUNDO = 1000;

	private long inicio;

	public AnimaCronometro2() {
		initialize();

	}

	// Inicio da Thread.
	public void run() {

		inicio = 0;

		while (rodar) {
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

			display.setText(String.format("%02d:%02d:%02d - %03d", horas,
					minutos, segundos, valor));
			try {
				Thread.sleep(50);
			} catch (InterruptedException ie) {
			}

		}
	}

	private void initialize() {
		setTitle("Cronômetro - hh:mm:ss - mss");
		setLayout(new BorderLayout());
		setSize(350, 500);

		
		display = new JTextField();
		display.setText("00:00:00");
		parciais = new JTextArea();
		

		painelBotoes = new JPanel();
		painelBotoes.add(getStart());

		add(display, BorderLayout.NORTH);
		add(new JScrollPane(parciais), BorderLayout.CENTER);
		add(painelBotoes, BorderLayout.SOUTH);

		

	}

	
	public JButton getStart() {
		if (start == null) {
			start = new JButton("Start");
			start.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					start.setEnabled(false);
					
					parcial.setEnabled(true);
					
					rodar = true;
					AnimaCronometro2 ac = new AnimaCronometro2();
					Thread t = new Thread(ac);
					t.start();
				}
			});
		}

		return start;
	}
	
	public static void main(String[] args) {
		AnimaCronometro2 c = new AnimaCronometro2();
		c.setDefaultCloseOperation(EXIT_ON_CLOSE);
		c.setVisible(true);
	}

}
