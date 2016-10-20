package br.com.convertebin.janelapop;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class JanelaPop {
	
	private JFrame popup;
	private PainelPop p2;
	
	public JanelaPop(int x, int y, String[] vl ){
		
		double alt, larg;
		alt = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		larg = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		
		int altura  = (int) alt/2;
		int largura = (int) larg/2;
		
		altura = altura - 250;
		largura = largura - 250;
		
		if(altura < 0) largura = altura = 50;
		if(largura < 0) largura = altura = 50;
		
		popup  = new JFrame("Usando Cirucuitos");
		popup.setBounds(largura, altura, 500, 500);
		popup.setVisible(true);
		popup.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		p2 = new PainelPop(0, 0, vl);
		popup.add(p2.getPainel());
		popup.repaint();
	}

}
