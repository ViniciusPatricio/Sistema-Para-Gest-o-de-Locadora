package Telas;

import ClassesBancoDeDados.ClientesLocadora;
import ClassesBancoDeDados.FuncionariosLocadora;
import ClassesBancoDeDados.RegistrosLocadora;
import ClassesBancoDeDados.TitulosLocadora;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Objects;

public class JanelaRegistro extends JFrame{
    private JPanel PainelPrincipal;
    private JTable table1;
    private JTextField textField3;
    private JButton BUSCARButton;
    private JButton RESETARTABELAButton;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField1;

    public JanelaRegistro() throws SQLException, ClassNotFoundException {
        super("Registros");
        setContentPane(PainelPrincipal);
        setMinimumSize(new Dimension(1250,650));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createTable();

        RESETARTABELAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createTable();
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pesquisaTabela = "";

                String descricao = textField3.getText().toUpperCase();
                String CPFcliente = textField4.getText();
                String CPFfuncionario = textField1.getText();
                String tipoRegistro = Objects.requireNonNull(comboBox1.getSelectedItem()).toString();
                String tipoTitulo = Objects.requireNonNull(comboBox2.getSelectedItem()).toString().toUpperCase();

                LinkedList<String> pesquisa = new LinkedList<>();

                if(!descricao.equals("")){
                    pesquisa.add("descricao_titulo='"+descricao+"'");
                }
                if(!CPFcliente.equals("")){
                    try {
                        ClientesLocadora clientesLocadora = new ClientesLocadora();

                        String idCliente = clientesLocadora.getIDcliente(CPFcliente);
                        if(idCliente==null){
                            JOptionPane.showMessageDialog(null,"CLIENTE AINDA NÃO REALIZOU ALGUMA ATIVIDADE NA LOCADORA","AVISO",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            pesquisa.add("id_cliente='"+idCliente+"'");
                        }
                    } catch (ClassNotFoundException | SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                if(!CPFfuncionario.equals("")){
                    try {
                        FuncionariosLocadora funcionariosLocadora = new FuncionariosLocadora();
                        String idFuncionario = funcionariosLocadora.getIDfuncionario(CPFfuncionario);
                        if(idFuncionario == null){
                            JOptionPane.showMessageDialog(null,"FUNCIONARIO AINDA NAO REALIZOU ALGUMA ATIVIDADE NA LOCADORA","AVISO",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            pesquisa.add("id_funcionario='"+idFuncionario+"'");
                        }
                    } catch (ClassNotFoundException | SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                if(!tipoTitulo.equals("NENHUM")){
                    pesquisa.add("tipo_titulo='"+tipoTitulo+"'");
                }
                if(!tipoRegistro.equals("NENHUMA")){
                    pesquisa.add("tipo_registro='"+tipoRegistro+"'");
                }

                if(pesquisa.size()==0){
                    JOptionPane.showMessageDialog(null,"TODOS CAMPOS VAZIOS\nPARA REALIZAR BUSCA\nPRECISA PREENCHER PELO MENOS 1","AVISO",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    for(String i : pesquisa){
                        if(i.equals(pesquisa.getFirst())){
                            pesquisaTabela = pesquisaTabela.concat(i);
                        }else{
                            pesquisaTabela = pesquisaTabela.concat(" AND "+i);
                        }
                    }
                }
                try {
                    createTable(pesquisaTabela);
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    private  void createTable() throws SQLException, ClassNotFoundException {
        RegistrosLocadora registrosLocadora = new RegistrosLocadora();
        Object[][] data = registrosLocadora.gerarValoresTabelas();
        table1.setModel(new DefaultTableModel(data,new String[]{"id registro","tipo registro","descricao titulo","tipo titulo","nº exemplares","id funcionario","id cliente","data"}));
        table1.setVisible(true);
    }

    private  void createTable(String pesquisa) throws SQLException, ClassNotFoundException {
        RegistrosLocadora registrosLocadora = new RegistrosLocadora();
        Object[][] data = registrosLocadora.gerarValoresTabelas(pesquisa);
        table1.setModel(new DefaultTableModel(data,new String[]{"id registro","tipo registro","descricao titulo","tipo titulo","nº exemplares","id funcionario","id cliente","data"}));
        table1.setVisible(true);
    }

}
