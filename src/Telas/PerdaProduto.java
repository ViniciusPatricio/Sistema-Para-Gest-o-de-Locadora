package Telas;

import ClassesBancoDeDados.TitulosLocadora;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PerdaProduto extends  JFrame{
    private JPanel PainelPrincipal;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton REMOVERButton;
    private JComboBox comboBox2;
    private JTable table1;

    public PerdaProduto() throws Exception,ClassNotFoundException, SQLException {
        super("Perda de Produto");
        setContentPane(PainelPrincipal);
        setMinimumSize(new Dimension(1300,750));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createTable();
        REMOVERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = textField1.getText();
                String tipo = comboBox2.getSelectedItem().toString();
                String tipoPerdas = comboBox1.getSelectedItem().toString();

                if(nome.equals("") || tipo.equals("")){
                    JOptionPane.showMessageDialog(null,"Existe(m) campo(s) vazio(s)","AVISO",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    TitulosLocadora titulosLocadora = null;
                    try {
                        titulosLocadora = new TitulosLocadora();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    if(titulosLocadora.verificaTituloExistente(nome,tipo)){
                        if(tipoPerdas.equals("PERDA DE TODOS OS EXEMPLARES")){
                            int confirm = JOptionPane.showConfirmDialog(null,"CERTEZA QUE DESEJA REMOVER TODO O TITULO DO BANCO DE DADOS?");
                            if(confirm==0){
                                if(titulosLocadora.removerTitulo(nome,tipo)){
                                    JOptionPane.showMessageDialog(null, "REMOCAO FOI REALZIADA COM SUCESSO", "AVISO", JOptionPane.INFORMATION_MESSAGE);

                                }else{
                                    JOptionPane.showMessageDialog(null, "REMOCAO NAO FOI REALZIADA COM SUCESSO", "AVISO", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        }else{
                            int confirm = JOptionPane.showConfirmDialog(null,"CERTEZA QUE DESEJA REMOVER UMA QUANTIDADE ESPECIFICA DO TITULO DO BANCO DE DADOS?");
                            if(confirm == 0){
                                String numExemplaresRemovidos = JOptionPane.showInputDialog("DIGITE O NUMERO DE EXEMPLARES A SER REMOVIDO");
                                titulosLocadora.alterarNumExemplares(nome,tipo,Integer.parseInt(numExemplaresRemovidos),'-');
                                JOptionPane.showMessageDialog(null, "REMOCAO FOI REALZIADA COM SUCESSO", "AVISO", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"TITULO "+nome.toUpperCase()+" DO TIPO "+tipo.toUpperCase()+" NAO ENCONTRADO","AVISO",JOptionPane.INFORMATION_MESSAGE);
                    }
                    try {
                        createTable();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
    private  void createTable() throws SQLException, ClassNotFoundException {
        TitulosLocadora titulosLocadora = new TitulosLocadora();
        Object[][] data = titulosLocadora.gerarValoresTabelas();
        table1.setModel(new DefaultTableModel(data,new String[]{"idtitulos","descricao","tipo","nº exemplares","period max emprest","nº exemp alugados"}));
        table1.setVisible(true);
    }
}
