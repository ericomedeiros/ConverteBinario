package br.com.convertebin.janela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import br.com.convertebin.janelapop.JanelaPop;

public class Painel {


	private JPanel painel;
	private JLabel labelValor, labelComandos, labelVariaveis , labelVariaveis2, labelLista, labelResultado, labelIndice, labelComboIndice, linhaComando;
	private JTextField textValor, textIndice, textLinhaComando;
	private JComboBox<String> comboComandos, comboVariaveis, comboVariaveis2, comboResultado, comboIndice;
	private JButton btnAdicionar, btnRemover, btnLimpar, btnExecutar;
	private JList<String> listComandos;
	private DefaultListModel<String> modeloComandos;
	private JScrollPane scrollist;
	private String linhaCmd, txtvalor, txtindice, txtvar1, txtvar2, txtres, txtcmd;// para formar uma linha de comando
	private Stack<String[]> posLabels;


	public Painel(int x, int y, int largura, int altura){

		painel = new JPanel();
		painel.setLayout(null);
		painel.setBounds(x, y, largura, altura);
		posLabels = new Stack<String[]>();


	}

	public void criaLabels(){

		labelValor       = new JLabel("Informe o valor:"); 
		labelComandos    = new JLabel("Selecione o comando:"); 
		labelVariaveis   = new JLabel("Selecione a variavel:");
		labelVariaveis2  = new JLabel("Selecione outra variavel:");
		labelLista       = new JLabel("Lista de comandos:");
		labelResultado   = new JLabel("Selecione variavel p/ resultado:");
		labelIndice      = new JLabel("Informe uma label para operação:");
		labelComboIndice = new JLabel("Labels a serem informadas:");
		linhaComando     = new JLabel("Linha de comando:");


		labelValor.setBounds(	  10,  70, 150, 25); 
		labelComandos.setBounds(  10,  10, 150, 25);
		labelVariaveis.setBounds( 10,  40, 150, 25);
		labelVariaveis2.setBounds(250, 40, 150, 25);
		labelLista.setBounds(	  530, 10, 150, 25);
		labelIndice.setBounds(    10, 100, 200, 25);
		labelComboIndice.setBounds(10, 130, 200, 25);
		labelResultado.setBounds( 10, 160, 200, 25);
		linhaComando.setBounds(10, 225, 150, 25);

		painel.add(labelValor);
		painel.add(labelComandos);
		painel.add(labelVariaveis);
		painel.add(labelVariaveis2);
		painel.add(labelLista);
		painel.add(labelResultado);
		painel.add(labelIndice);
		painel.add(labelComboIndice);
		painel.add(linhaComando);

	}

	public void criaCombobox(){

		comboComandos   = new JComboBox<String>();
		comboVariaveis  = new JComboBox<String>(); 
		comboVariaveis2 = new JComboBox<String>();
		comboResultado  = new JComboBox<String>();
		comboIndice     = new JComboBox<String>();

		comboComandos.setBounds(  140, 10, 100, 25); 
		comboVariaveis.setBounds( 140, 40, 70,  25);  
		comboVariaveis2.setBounds(400, 40, 70,  25); 
		comboResultado.setBounds( 200, 160, 70,  25); 
		comboIndice.setBounds(    180, 130, 100, 25);

		comboComandos.addItem("");//0
		comboComandos.addItem("add");//1
		comboComandos.addItem("sub");//2
		comboComandos.addItem("and");//3
		comboComandos.addItem("or");//4
		comboComandos.addItem("slt");//5
		comboComandos.addItem("beq");//6
		comboComandos.addItem("j");//7
		comboComandos.addItem("addi");//8
		comboComandos.addItem("subi");//9
		comboComandos.addItem("andi");//10
		comboComandos.addItem("ori");//11
		comboComandos.addItem("slti");//12
		comboComandos.addItem("lw");//13
		comboComandos.addItem("sw");//14
		comboComandos.addItem("beqi");//15

		comboVariaveis.addItem("");
		comboResultado.addItem("");
		comboVariaveis2.addItem("");
		comboIndice.addItem("");

		comboVariaveis.addItem("$v0");
		comboVariaveis2.addItem("$v0");

		for(int i = 1; i < 63; i++ ){

			comboVariaveis.addItem("$v"+i);
			comboVariaveis2.addItem("$v"+i);
			comboResultado.addItem("$v"+i);

		}

		comboVariaveis.setEnabled(false);
		comboVariaveis2.setEnabled(false);
		comboResultado.setEnabled(false);

		painel.add(comboComandos);
		painel.add(comboVariaveis);
		painel.add(comboVariaveis2);
		painel.add(comboResultado);
		painel.add(comboIndice);

		selecionarComboBox();

	}

	public void criaButtons(){

		btnAdicionar = new JButton("Adicionar >>"); 
		btnRemover = new JButton("Remover"); 
		btnLimpar = new JButton("Limpar");  
		btnExecutar = new JButton("Executar");

		btnAdicionar.setBounds(330,  250, 120, 25); 
		btnRemover.setBounds(  180, 300, 100, 25); 
		btnLimpar.setBounds(   340, 300, 100, 25); 
		btnExecutar.setBounds( 10,  300, 100, 25);

		painel.add(btnAdicionar);
		painel.add(btnRemover);
		painel.add(btnLimpar);
		painel.add(btnExecutar);

		adicionarComandosBut();

	}

	public void adicionarComandosBut(){

		btnAdicionar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if(validaAdicionar()){

					adicionarComando();

				}

			}
		});

		btnRemover.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				removerComandos();

			}
		});

		btnLimpar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				modeloComandos.clear();
				comboComandos.setSelectedIndex(0);
				comboVariaveis2.setSelectedIndex(0);
				comboResultado.setSelectedIndex(0);
				comboVariaveis.setSelectedIndex(0);
				textValor.setText(null);
				comboIndice.setSelectedIndex(0);
				textIndice.setText(null);
				textLinhaComando.setText(null);
				txtcmd = "";
				txtindice = "";
				txtres = "";
				txtvalor = "";
				txtvar1 = "";
				txtvar2 = "";
				posLabels.clear();
			}
		});

		btnExecutar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				executarComandos();

			}
		});
	}

	public void selecionarComboBox(){

		comboComandos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				linhaCmd = "";
				txtcmd = "";
				txtvalor = ""; 
				txtindice = ""; 
				txtvar1 = "";
				txtvar2 = ""; 
				txtres = "";

				comboIndice.setSelectedIndex(0);
				comboVariaveis.setSelectedIndex(0);  
				comboVariaveis2.setSelectedIndex(0);
				comboResultado.setSelectedIndex(0);
				textIndice.setText("");
				textLinhaComando.setText("");
				textValor.setText("");

				int comand = comboComandos.getSelectedIndex();
				//setEnableText(boolean valor, boolean indice, boolean linhaComando)
				//setEnableCombobox(boolean variavel, boolean variavel2, boolean resultado, boolean indice)
				switch (comand) {
				case 0://nenhum comando
					setEnableText(false, false);
					setEnableCombobox(false, false, false, true);
					break;
				case 1://add
					setEnableText(false, false);
					setEnableCombobox(true, true, true, false);
					break;
				case 2://sub
					setEnableText(false, false);
					setEnableCombobox(true, true, true, false);
					break;
				case 3://and
					setEnableText(false, false);
					setEnableCombobox(true, true, true, false);
					break;
				case 4://or
					setEnableText(false, false);
					setEnableCombobox(true, true, true, false);
					break;
				case 5://slt
					setEnableText(false, false);
					setEnableCombobox(true, true, true, false);
					break;
				case 6://beq
					setEnableText(false, true);
					setEnableCombobox(true, true, false, false);
					break;
				case 7://j
					setEnableText(false, true);
					setEnableCombobox(false, false, false, false);
					break;
				case 8://addi
					setEnableText(true, false);
					setEnableCombobox(true, false, true, false);
					break;
				case 9://subi
					setEnableText(true, false);
					setEnableCombobox(true, false, true, false);
					break;
				case 10://andi
					setEnableText(true, false);
					setEnableCombobox(true, false, true, false);
					break;
				case 11://ori
					setEnableText(true, false);
					setEnableCombobox(true, false, true, false);
					break;
				case 12://slti
					setEnableText(true, false);
					setEnableCombobox(true, false, true, false);
					break;
				case 13://lw
					setEnableText(true, false);
					setEnableCombobox(true, false, true, false);
					break;
				case 14://sw
					setEnableText(true, false);
					setEnableCombobox(true, false, true, false);
					break;
				case 15://beqi
					setEnableText(true, true);
					setEnableCombobox(true, false, false, false);
					break;

				default:
					break;
				}

				if(comand > 0){
					textLinhaComando.setText(""+comboComandos.getSelectedItem());
					txtcmd = ""+comboComandos.getSelectedItem();
				}

			}
		});

		comboIndice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtindice = "" + comboIndice.getSelectedItem();
				criaLinhaComando();
				textLinhaComando.setText(linhaCmd);

			}
		});

		comboResultado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtres = "" + comboResultado.getSelectedItem();
				criaLinhaComando();
				textLinhaComando.setText(linhaCmd);

			}
		});

		comboVariaveis.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtvar1 = "" + comboVariaveis.getSelectedItem();
				criaLinhaComando();
				textLinhaComando.setText(linhaCmd);


			}
		});

		comboVariaveis2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtvar2 = "" + comboVariaveis2.getSelectedItem();
				criaLinhaComando();
				textLinhaComando.setText(linhaCmd);

			}
		});

	}

	protected void criaLinhaComando() {
		// TODO Auto-generated method stub
		linhaCmd = "";
		linhaCmd = txtcmd;
		//comboComandos.addItem("lw");//13
		//comboComandos.addItem("sw");//14
		if(!txtres.equals("")){
			linhaCmd += " " + txtres;
		}

		if(comboComandos.getSelectedIndex() == 13 || comboComandos.getSelectedIndex() == 14){

			linhaCmd += " " + txtvalor + "(";
			if(!txtvar1.equals("")){
				linhaCmd += txtvar1;
			}

			linhaCmd += ")";
			return;

		}

		if(!txtvar1.equals("")){
			linhaCmd += " " + txtvar1;
		}
		if(!txtvar2.equals("")){
			linhaCmd += " " + txtvar2;
		}
		if(!txtvalor.equals("")){
			linhaCmd += " " + txtvalor;
		}
		if(!txtindice.equals("")){
			if(comboComandos.getSelectedIndex() == 0){
				linhaCmd += txtindice;
			}else{
				linhaCmd += " " + txtindice;
			}
		}
	}

	protected void executarComandos() {
		// TODO Auto-generated method stub

		if(comboIndice.getItemCount() > 1){
			JOptionPane.showMessageDialog(null, "Por favor informe as labels restantes", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if(modeloComandos.isEmpty()){
			JOptionPane.showMessageDialog(null, "Adicione pelo menos um comando", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String[] comand = new String[modeloComandos.getSize()];
		String linha;
		String[] separa;
		int rep;

		for1: for(int i = 0; i < modeloComandos.getSize(); i++){

			linha = modeloComandos.getElementAt(i);
			linha = linha.replace('(', ' ');
			//linha = linha.replaceAll("(", " ");
			//linha = linha.replaceAll(")", "");
			linha = linha.replace(')', ' ' );
			separa = linha.split(" ");

			if(separa.length < 2){
				//quando for a label
				comand[i] = "";
				continue for1;
			}

			switch (separa[0]) {
			case "add":
				comand[i] = "0000";
				break;
			case "sub":
				comand[i] = "0001";
				break;
			case "and":
				comand[i] = "0010";
				break;
			case "or":
				comand[i] = "0011";
				break;
			case "slt":
				comand[i] = "0100";
				break;
			case "beq":
				int var1;

				if(separa[1].length() > 3){
					var1 = Integer.valueOf(separa[1].charAt(2)+""+separa[1].charAt(3));
				}else{
					var1 = Integer.valueOf(separa[1].charAt(2)+"");
				}

				String vl1 = Integer.toBinaryString(var1);
				rep = 6 - vl1.length();
				if(rep > 0){
					while(rep != 0){
						vl1 = "0" + vl1;
						rep--;
					}
				}else{
					JOptionPane.showMessageDialog(null, "O numero da posição do label na linha não é comportado. Erro acorrido na linha: "+ i, "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				int var2;

				if(separa[2].length() > 3){
					var2 = Integer.valueOf(separa[2].charAt(2)+""+separa[2].charAt(3));
				}else{
					var2 = Integer.valueOf(separa[2].charAt(2)+"");
				}

				String vl2 = Integer.toBinaryString(var2);
				rep = 6 - vl2.length();
				if(rep > 0){
					while(rep != 0){
						vl2 = "0" + vl2;
						rep--;
					}
				}else{
					JOptionPane.showMessageDialog(null, "O numero da posição do label na linha não é comportado. Erro acorrido na linha: "+ i, "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				String labl = getPosicaoLabelBin(separa[3]);
				rep = 16 - labl.length();
				if(rep > 0){
					while(rep != 0){
						labl = "0" + labl;
						rep--;
					}
				}else{
					JOptionPane.showMessageDialog(null, "O numero da posição do label na linha não é comportado. Erro acorrido na linha: "+ i, "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				comand[i] = "0101;"+ vl1 +";"+ vl2 +";"+labl;
				continue for1;

			case "j":
				//pega o id da linha
				String posicaobin = getPosicaoLabelBin(separa[1]);
				rep = 16 - posicaobin.length();
				if(rep > 0){
					while(rep != 0){
						posicaobin = "0" + posicaobin;
						rep--;
					}
				}else{
					JOptionPane.showMessageDialog(null, "O numero da posição do label na linha não é comportado. Erro acorrido na linha: "+ i, "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				comand[i] = "0110;000000;000000;"+ posicaobin;
				//op;res;var1;var2;cons
				continue for1;
			case "addi":
				comand[i] = "0111";
				break;
			case "subi":
				comand[i] = "1001";
				break;
			case "andi":
				comand[i] = "1010";
				break;
			case "ori":
				comand[i] = "1011";
				break;
			case "slti":
				comand[i] = "1100";
				break;
			case "lw":
				comand[i] = "1101";
				String conlw = separa[2];
				separa[2] = separa[3];
				separa[3] = conlw;
				break;
			case "sw":
				comand[i] = "1110";
				String consw = separa[2];
				separa[2] = separa[3];
				separa[3] = consw;
				break;
			case "beqi":
				//pega o id da label
				int idVar;

				if(separa[1].length() > 3){
					idVar = Integer.valueOf(separa[1].charAt(2)+""+separa[1].charAt(3));
				}else{
					idVar = Integer.valueOf(separa[1].charAt(2)+"");
				}

				String vl = Integer.toBinaryString(idVar);
				rep = 6 - vl.length();
				if(rep > 0){
					while(rep != 0){
						vl = "0" + vl;
						rep--;
					}
				}else{
					JOptionPane.showMessageDialog(null, "O numero da posição do label na linha não é comportado. Erro acorrido na linha: "+ i, "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				String con = Integer.toBinaryString(Integer.valueOf(separa[2]));
				rep = 6 - con.length();
				if(rep > 0){
					while(rep != 0){
						con = "0" + con;
						rep--;
					}
				}else{
					JOptionPane.showMessageDialog(null, "O numero da posição do label na linha não é comportado. Erro acorrido na linha: "+ i, "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				String lbl = getPosicaoLabelBin(separa[3]);
				rep = 16 - lbl.length();
				if(rep > 0){
					while(rep != 0){
						lbl = "0" + lbl;
						rep--;
					}
				}else{
					JOptionPane.showMessageDialog(null, "O numero da posição do label na linha não é comportado. Erro acorrido na linha: "+ i, "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				comand[i] = "1111;"+ idVar +";"+ con +";"+lbl;
				continue for1;

			default:

				break;

			}

			for(int j = 1; j < separa.length; j++){

				int idVar;

				if(separa[j].charAt(0) == '$'){
					if(separa[j].length() > 3){
						idVar = Integer.valueOf(separa[j].charAt(2)+""+separa[j].charAt(3));
					}else{
						idVar = Integer.valueOf(separa[j].charAt(2)+"");
					}

					String vl = Integer.toBinaryString(idVar);
					if(vl.length() < 6){
						int qtdzero = 6 - vl.length();
						while(qtdzero != 0){
							vl = "0" + vl;
							qtdzero--;
						}

					}
					comand[i] =comand[i] +";"+  vl;
				}else{

					idVar = Integer.valueOf(separa[j]);
					String vl = Integer.toBinaryString(idVar);
					if(vl.length() < 16){
						int qtdzero = 16 - vl.length();
						while(qtdzero != 0){
							vl = "0" + vl;
							qtdzero--;
						}

					}else{
						vl = vl.substring((vl.length() - 16), vl.length());
					}
					comand[i] =comand[i] +";"+  vl;
					continue for1;
				}




			}

			comand[i] += ";0000000000";

		}

		new JanelaPop(painel.getHeight(),painel.getWidth(),comand);

	}

	private String getPosicaoLabelBin(String label) {
		// TODO Auto-generated method stub
		String[] v = new String[2];
		for1: for(int i = 0; i < posLabels.size(); i++){
			v = posLabels.get(i);
			if(label.equals(v[0])){
				break for1;
			}
		}


		return Integer.toBinaryString(Integer.valueOf(v[1]));
	}

	protected void removerComandos() {
		// TODO Auto-generated method stub
		if(listComandos.getSelectedIndex() > -1){

			modeloComandos.remove(listComandos.getSelectedIndex());

		}else{

			JOptionPane.showMessageDialog(null, "Selecione um comando da lista", "Warning", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	protected void adicionarComando() {
		// TODO Auto-generated method stub

		modeloComandos.addElement(textLinhaComando.getText());

		switch (comboComandos.getSelectedIndex()) {
		case 0:
			if(comboIndice.getSelectedIndex() != 0){
				String[] pos = {(""+comboIndice.getSelectedItem()), (""+ modeloComandos.getSize())};
				posLabels.add(pos);
				comboIndice.removeItemAt(comboIndice.getSelectedIndex());
			}
			break;
		case 6:
			comboIndice.addItem(txtindice);
			break;
		case 7:
			comboIndice.addItem(txtindice);
			break;
		case 15:
			comboIndice.addItem(txtindice);
			break;

		default:
			break;
		}

		comboComandos.setSelectedIndex(0);
		comboVariaveis2.setSelectedIndex(0);
		comboResultado.setSelectedIndex(0);
		comboVariaveis.setSelectedIndex(0);
		textValor.setText(null);
		comboIndice.setSelectedIndex(0);
		textIndice.setText(null);
		textLinhaComando.setText(null);
		txtcmd = "";
		txtindice = "";
		txtres = "";
		txtvalor = "";
		txtvar1 = "";
		txtvar2 = "";


	}

	public boolean validaAdicionar(){

		boolean test = true;

		if(comboComandos.getSelectedIndex() == 0 && comboIndice.getItemCount() == 1){
			test = false;
			JOptionPane.showMessageDialog(null, "Precisa selecionar um comando", "Error", JOptionPane.ERROR_MESSAGE);
		}


		if(comboResultado.isEnabled()){
			if(comboResultado.getSelectedIndex() == 0){
				test = false;
				JOptionPane.showMessageDialog(null, "Precisa selecionar uma variavel para receber a resposta", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if(comboVariaveis.isEnabled()){
			if(comboVariaveis.getSelectedIndex() == 0){
				test = false;
				JOptionPane.showMessageDialog(null, "Precisa selecionar uma variavel", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if(comboVariaveis2.isEnabled()){
			if(comboVariaveis2.getSelectedIndex() == 0){
				test = false;
				JOptionPane.showMessageDialog(null, "Precisa selecionar uma variavel", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if(textIndice.isEnabled()){
			if(textIndice.getText() == null || textIndice.equals("")){
				test = false;
				JOptionPane.showMessageDialog(null, "Precisa informar o indice", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if(textValor.isEnabled()){
			if(textValor.getText() == null || textValor.equals("")){
				test = false;
				JOptionPane.showMessageDialog(null, "Precisa informar o valor da constante", "Error", JOptionPane.ERROR_MESSAGE);
			}else{

				try{

					int i = Integer.valueOf(textValor.getText());
					if(comboComandos.getSelectedIndex() == 15){
						if(i > 31 || i < -32){
							test = false;
							JOptionPane.showMessageDialog(null, "O valor da constante precisa ser um numero inteiro dentre -32 e 31 para função \"beqi\"", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						if(i > 32767 || i < -32768){
							test = false;
							JOptionPane.showMessageDialog(null, "O valor da constante precisa ser um numero inteiro dentre -32768 e 32767", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}

				}catch (Exception e) {
					// TODO: handle exception
					test = false;
					JOptionPane.showMessageDialog(null, "O valor da constante precisa ser um numero inteiro", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		} 

		return test;
	}

	public void txtField(){

		textValor = new JTextField();
		textIndice = new JTextField();
		textLinhaComando = new JTextField();

		textValor.setEnabled(false);
		textIndice.setEnabled(false);
		textLinhaComando.setEditable(false);

		textValor.setBounds( 110, 70, 100, 25);
		textIndice.setBounds(210, 100, 100, 25);
		textLinhaComando.setBounds(10, 250, 300, 25);

		painel.add(textValor);
		painel.add(textIndice);
		painel.add(textLinhaComando);

		onWriteFields();

	}

	private void onWriteFields() {
		// TODO Auto-generated method stub

		textIndice.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				// TODO Auto-generated method stub
				txtindice = "" + textIndice.getText();
				criaLinhaComando();
				textLinhaComando.setText(linhaCmd);

			}
		});

		textValor.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				// TODO Auto-generated method stub
				txtvalor = "" + textValor.getText();
				criaLinhaComando();
				textLinhaComando.setText(linhaCmd);
			}
		});
	}

	public void criaLista(){

		modeloComandos = new DefaultListModel<String>();
		listComandos = new JList<String>(modeloComandos);

		listComandos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		scrollist = new JScrollPane(listComandos);

		scrollist.setBounds(530, 40, 300, 300);

		painel.add(scrollist);


	}

	public JPanel getPainel() {
		return painel;
	}

	public void setPainel(JPanel painel) {
		this.painel = painel;
	}

	private void setEnableText(boolean valor, boolean indice){

		textValor.setEnabled(valor);
		textIndice.setEnabled(indice);
	}

	private void setEnableCombobox(boolean variavel, boolean variavel2, boolean resultado, boolean indice){

		comboVariaveis.setEnabled(variavel);
		comboVariaveis2.setEnabled(variavel2);
		comboResultado.setEnabled(resultado);
		comboIndice.setEnabled(indice);

	}
}
