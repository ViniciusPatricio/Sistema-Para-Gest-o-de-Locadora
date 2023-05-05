package Telas;

import ClassesBancoDeDados.Titulos;
import ClassesBancoDeDados.TitulosLocadora;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Locale;

public class AquicaoDeProduto extends JFrame {
    private JPanel PainelPrincipal;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JButton RESGISTRARButton;
    private JTable table1;

    public AquicaoDeProduto() throws Exception,ClassNotFoundException, SQLException {

        super("Aquicição de Produtos");
        setContentPane(PainelPrincipal);
        setMinimumSize(new Dimension(1300,750));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createTable();
        RESGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String descricao = textField1.getText().toUpperCase();
                String tipo = comboBox1.getSelectedItem().toString().toUpperCase();
                String numExemplares = textField3.getText();
                String periodoMaximoEmprestivo = textField4.getText();
                if(tipo.equals("") || numExemplares.equals("")|| periodoMaximoEmprestivo.equals("") || descricao.equals("")){
                    JOptionPane.showMessageDialog(null,"Existe(m) campo(s) vazio(s)","AVISO",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    try{
                        int numEx = Integer.parseInt(numExemplares);
                        int periodoMax = Integer.parseInt(periodoMaximoEmprestivo);

                        Titulos titulo = new Titulos(tipo.toUpperCase(),numEx,periodoMax,descricao.toUpperCase());
                        TitulosLocadora titulosL = new TitulosLocadora();

                        if (titulosL.verificaTituloExistente(descricao)) {

                            LinkedList<String> tiposTitulo = titulosL.getTipoTitulo(descricao);
                            if(tiposTitulo.contains(tipo)){
                                int confirm = JOptionPane.showConfirmDialog(null,"PRODUTO JA EXISTENTE, DESEJA APENAS ATUALIZAR O NUMERO DE EXEMPLARES? ");
                                if(confirm == 0){
                                    int numeroExemplaresAtual = titulosL.alterarNumExemplares(descricao,tipo, numEx, '+');
                                    JOptionPane.showMessageDialog(null, "ATUALIZOU-SE O NÚMERO DE EXEMPLARES\n VALOR ATUAL DE EXEMPLARES: " + numeroExemplaresAtual, "AVISO", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }else{
                                int confirm = JOptionPane.showConfirmDialog(null,"PRODUTO JA EXISTENTE, MAS EM UMA OUTRA FORMA DE MIDIA.\nDESEJA ADICIONAR O FILME EM OUTRA FOMA DE MIDIA NO BANCO DE DADOS ?");
                                if(confirm==0){
                                    titulosL.adicionarTitulo(titulo);
                                    JOptionPane.showMessageDialog(null, "INSERCAO DO PRODUTO NO BANCO DE DADOS FOI REALZIADA COM SUCESSO", "AVISO", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        } else {
                            titulosL.adicionarTitulo(titulo);
                            JOptionPane.showMessageDialog(null, "INSERCAO DO PRODUTO NO BANCO DE DADOS FOI REALZIADA COM SUCESSO", "AVISO", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }catch (Exception e1){
                        System.out.println(e1);
                        JOptionPane.showMessageDialog(null,"NUMERO DE EXEMPLARES e PERIODO MAXIMO DE EMPRESTIMO SAO VALORES NUMERICOS","AVISO",JOptionPane.INFORMATION_MESSAGE);
                    };
                    try {
                        createTable();
                    } catch (SQLException | ClassNotFoundException ex) {
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
