package threads;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Cronometro extends JFrame {
	private JTextField display;
	private JTextArea parciais;
	private JButton parcial;
	private JButton start;
	private JButton stop;
	private JButton zerar;
	private JPanel painelBotoes;
	private boolean rodar;
	private AnimaCronometro ac = new AnimaCronometro();;
	Thread t ;
	
	
	public Cronometro() {
		initialize();
	}

	private void initialize() {
		setTitle("Cronômetro - hh:mm:ss - mss");
		setLayout(new BorderLayout());
		setSize(350, 500);
		add(getDisplay(), BorderLayout.NORTH);
		add(new JScrollPane(getParciais()), BorderLayout.CENTER);
		add(getPainelBotoes(), BorderLayout.SOUTH);
	}

	public JTextField getDisplay() {
		if (display == null) {
			display = new JTextField();
			display.setText("00:00:00");
		}
		return display;
	}

	public JTextArea getParciais() {
		if (parciais == null) {
			parciais = new JTextArea();
		}
		return parciais;
	}

	public JButton getStart() {
		if (start == null) {
			start = new JButton("Start");
			start.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					start.setEnabled(false);
					stop.setEnabled(true);
					parcial.setEnabled(true);
					zerar.setEnabled(false);
					rodar = true;
					
				
						t = new Thread(ac);
						t.start();
						
					
					
				
					
					}
			});
		}
		
		return start;
	}

	public JButton getStop() {
		if (stop == null) {
			stop = new JButton("Stop");
			stop.setEnabled(false);
			stop.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					start.setEnabled(true);
					stop.setEnabled(false);
					parcial.setEnabled(false);
					zerar.setEnabled(true);
					rodar = false;
				}
			});

		}
		return stop;
	}

	public JButton getZerar() {
		if (zerar == null) {
			zerar = new JButton("Zerar");
			zerar.setEnabled(false);
			zerar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					display.setText("00:00:00");
					zerar.setEnabled(false);
				}
			});

		}

		return zerar;
	}

	public JButton getParcial() {
		if (parcial == null) {
			parcial = new JButton("Parcial");
			parcial.setEnabled(false);
			parcial.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					parciais.setText(parciais.getText() + display.getText() + "\n");
				}
			});
			
		}

		return parcial;
	}

	public JPanel getPainelBotoes() {
		if (painelBotoes == null) {
			painelBotoes = new JPanel();
			painelBotoes.add(getStart());
			painelBotoes.add(getParcial());
			painelBotoes.add(getStop());
			painelBotoes.add(getZerar());
		}
		return painelBotoes;
	}

	public static void main(String[] args) {
		Cronometro c = new Cronometro();
		c.setDefaultCloseOperation(EXIT_ON_CLOSE);
		c.setVisible(true);
	}
	
	private class AnimaCronometro implements Runnable {
		private static final int HORA    = 1000 * 60 * 60;
		private static final int MINUTO  = 1000 * 60;
		private static final int SEGUNDO = 1000;
		
		private long inicio;
		public AnimaCronometro() {
			inicio = System.currentTimeMillis();
		}
		
		public void run() {
			while(rodar) {
				long valor = System.currentTimeMillis() - inicio;
				long horas, minutos, segundos;
				horas = minutos = segundos = 0;
				
				if (valor > HORA) {
					horas  = valor / HORA;
					valor -= horas * HORA;
				}
				if (valor > MINUTO) {
					minutos = valor / MINUTO;
					valor  -= minutos * MINUTO;
				}
				if (valor > SEGUNDO) {
					segundos = valor / SEGUNDO;
					valor   -= segundos * SEGUNDO;
				}

				display.setText(String.format("%02d:%02d:%02d - %03d", horas, minutos, segundos, valor));
				try {
					Thread.sleep(50);				
				} catch (InterruptedException ie) {}
				
			}
		}
	}
}