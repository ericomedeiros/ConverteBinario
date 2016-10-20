package br.com.convertebin.janelapop;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class PainelPop {

	private JPanel painel;
	private JTable tableTypeR;
	private JLabel labelTypeI, labelTypeR, labelControle;
	private DefaultTableModel modelTypeR;
	private JTable tableComandos;
	private DefaultTableModel modelComandos;
	private JTable tableTypeI;
	private DefaultTableModel modelTypeI;
	private JTable tableControle;
	private DefaultTableModel modelControle;
	private JScrollPane scrollist,scrollistTypeR, scrollistTypeI, scrollistControle;
	private String[] comandos;


	public PainelPop(int x, int y, String[] comandos){

		painel = new JPanel();
		painel.setLayout(null);
		painel.setBounds(x, y, 500, 500);
		criarTabelaComandos(comandos);

	}


	private void criarTabelaComandos(String[] vl) {
		// TODO Auto-generated method stub

		labelTypeI = new JLabel("Comando do tipe I:");
		labelTypeR = new JLabel("Comando do tipo R:");
		labelControle = new JLabel("Sinais de Controle");

		modelControle = new DefaultTableModel();
		modelControle.addColumn("RegDest");
		modelControle.addColumn("WriteReg");
		modelControle.addColumn("ALUSrc");
		modelControle.addColumn("PCSrc");
		modelControle.addColumn("ReadMem");
		modelControle.addColumn("WriteMem");
		modelControle.addColumn("MemToReg");

		modelComandos = new DefaultTableModel();
		modelComandos.addColumn("Linha");
		modelComandos.addColumn("Comando");

		modelTypeR = new DefaultTableModel();
		modelTypeR.addColumn("Operação");
		modelTypeR.addColumn("Resultado");
		modelTypeR.addColumn("Variavel 1");
		modelTypeR.addColumn("Variavel 2");
		modelTypeR.addColumn("Outros bits");

		modelTypeI = new DefaultTableModel();
		modelTypeI.addColumn("Operação");
		modelTypeI.addColumn("Resultado");
		modelTypeI.addColumn("Variavel");
		modelTypeI.addColumn("Constante");

		int idLinha = 1;

		for(int i = 0; i < vl.length; i++){
			if(vl[i].equals("")) continue;
			String[] l = new String[2];
			l[0] = ""+idLinha;
			idLinha++;
			l[1] = vl[i] .replace(";", "");
			modelComandos.addRow(l);
		}

		comandos = new String[modelComandos.getRowCount()];
		int cont = 0;
		for(int i = 0; i < vl.length; i++){
			if(vl[i].equals("")) continue;
			comandos[cont++] = vl[i];
		}

		tableTypeI = new JTable(modelTypeI);
		tableTypeR = new JTable(modelTypeR);
		tableControle = new JTable(modelControle);
		tableComandos = new JTable(modelComandos);
		tableComandos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tableComandos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(100);
		tableTypeI.getTableHeader().getColumnModel().getColumn(3).setMinWidth(200);
		tableControle.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(50);
		tableControle.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(50);

		//tableComandos.setEnabled(false);
		tableControle.setEnabled(false);
		tableTypeI.setEnabled(false);
		tableTypeR.setEnabled(false);
		
		scrollist= new JScrollPane(tableComandos);
		scrollistTypeI = new JScrollPane(tableTypeI);
		scrollistTypeR = new JScrollPane(tableTypeR);
		scrollistControle = new JScrollPane(tableControle);

		scrollistTypeI.setBounds(18, 320, 450, 39);
		scrollistTypeR.setBounds(18, 390, 450, 39);
		scrollistControle.setBounds(18, 250, 450, 39);
		scrollist.setBounds(43, 10, 400, 200);
		labelTypeI.setBounds(18, 305, 150, 15);
		labelTypeR.setBounds(18, 375, 150, 15);
		labelControle.setBounds(18, 235, 150, 15);

		painel.add(scrollist);
		painel.add(scrollistTypeI);
		painel.add(scrollistTypeR);
		painel.add(scrollistControle);
		painel.add(labelTypeI);
		painel.add(labelTypeR);
		painel.add(labelControle);
		painel.repaint();

		selecionadoComandoBin();

	}


	private void selecionadoComandoBin() {
		// TODO Auto-generated method stub

		tableComandos.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int index = tableComandos.getSelectedRow();
				String[] separa = comandos[index].split(";");
				if(modelTypeI.getRowCount() != 0)
					modelTypeI.removeRow(0);

				if(modelTypeR.getRowCount() != 0)
					modelTypeR.removeRow(0);
				
				if(modelControle.getRowCount() != 0)
					modelControle.removeRow(0);
				
				switch (separa[0]) {
				case "0000"://add
					modelControle.addRow(new String[]{"0","1","0","0","0","0","0"});
					break;
				case "0001"://sub
					modelControle.addRow(new String[]{"0","1","0","0","0","0","0"});
					break;
				case "0010"://and
					modelControle.addRow(new String[]{"0","1","0","0","0","0","0"});
					break;
				case "0011"://or
					modelControle.addRow(new String[]{"0","1","0","0","0","0","0"});
					break;
				case "0100"://slt
					modelControle.addRow(new String[]{"0","1","0","0","0","0","0"});
					break;
				case "0101"://beq
					modelControle.addRow(new String[]{"X","0","1","1","0","0","X"});
					break;
				case "0110"://j
					modelControle.addRow(new String[]{"X","0","1","1","0","0","X"});
					break;
				case "0111"://addi
					modelControle.addRow(new String[]{"0","1","1","0","0","0","0"});
					break;
				case "1001"://subi
					modelControle.addRow(new String[]{"0","1","1","0","0","0","0"});
					break;
				case "1010"://andi
					modelControle.addRow(new String[]{"0","1","1","0","0","0","0"});
					break;
				case "1011"://ori
					modelControle.addRow(new String[]{"0","1","1","0","0","0","0"});
					break;
				case "1100"://slti
					modelControle.addRow(new String[]{"0","1","1","0","0","0","0"});
					break;
				case "1101"://lw
					modelControle.addRow(new String[]{"0","1","1","0","1","0","1"});
					break;
				case "1110"://sw
					modelControle.addRow(new String[]{"X","0","1","0","0","1","X"});
					break;
				case "1111"://beqi
					modelControle.addRow(new String[]{"X","0","0","1","0","0","X"});
					break;


				default:
					break;
				}

				//0110
				int num = Integer.valueOf(separa[0]);

				if(num >= 101){
					modelTypeI.insertRow(0, separa);
				}else{
					modelTypeR.insertRow(0, separa);
				}
			}
		});



	}


	public JPanel getPainel() {
		return painel;
	}


	public void setPainel(JPanel painel) {
		this.painel = painel;
	}



}
