package br.com.convertebin.janela;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class Janela {
	
	private JFrame janela;
	private Painel p1;
	
	public Janela(){
		
		double alt, larg;
		alt = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		larg = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		
		int altura  = (int) alt/2;
		int largura = (int) larg/2;
		
		altura = altura - 200;
		largura = largura - 425;
		
		if(altura < 0) largura = altura = 50;
		if(largura < 0) largura = altura = 50;
		
		
		janela  = new JFrame("Conversor para binário(32 bits)");
		janela.setBounds(largura, altura, 850, 400);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p1 = new Painel(0, 0,850,400);
		p1.criaLabels();
		p1.criaCombobox();
		p1.txtField();
		p1.criaButtons();
		p1.criaLista();
		p1.getPainel().repaint();
		janela.add(p1.getPainel());
		janela.repaint();
		janela.setVisible(true);
		
	}
	
	
	
	

}
