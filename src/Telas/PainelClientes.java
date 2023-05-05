package Telas;

import ClassesBancoDeDados.Clientes;
import ClassesBancoDeDados.ClientesLocadora;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;

public class PainelClientes extends JFrame{
    private JPanel PainelPrincipal;
    private JTextField textField3;
    private JButton ADICIONARButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JTextField textField2;
    private JButton REMOVERButton;
    private JButton BUSCARButton;
    private JTable table1;
    private JButton RESETARTABELAButton;

    public PainelClientes() throws SQLException, ClassNotFoundException {
        super("Clientes");
        setContentPane(PainelPrincipal);
        setMinimumSize(new Dimension(1300,750));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createTable();
        ADICIONARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String primeiro_nome = textField1.getText().toUpperCase();
                String segundo_nome = textField2.getText().toUpperCase();
                String CPF = textField3.getText();
                String senha = passwordField1.getText();

                if(primeiro_nome.equals("") || segundo_nome.equals("")||CPF.equals("") || senha.equals("")){
                    JOptionPane.showMessageDialog(null,"EXISTE(M) CAMPO(S) VAZIO(S)","AVISO",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    try {
                        double CPFint = Double.valueOf(CPF).doubleValue();
                        Clientes cliente = new Clientes(primeiro_nome,segundo_nome,senha,CPF);
                        ClientesLocadora clientesLocadora = new ClientesLocadora();
                        if(clientesLocadora.verificaClienteExistente(CPF)){
                            JOptionPane.showMessageDialog(null,"CLIENTE JA CADASTRADO","AVISO",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            if(clientesLocadora.adicionarClientes(cliente)){
                                JOptionPane.showMessageDialog(null,"CLIENTE CADASTRADO COM SUCESSO","AVISO",JOptionPane.INFORMATION_MESSAGE);
                            };
                        }
                    }catch (Exception e1){
                        System.out.println(e1);
                        JOptionPane.showMessageDialog(null,"CPF E UM VALOR NUMERICO","AVISO",JOptionPane.INFORMATION_MESSAGE);
                    };
                }
                try {
                    createTable();
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
      REMOVERButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String CPF = textField3.getText();
              String senha = passwordField1.getText();
              if(senha.equals("")||CPF.equals("")){
                  JOptionPane.showMessageDialog(null,"SENHA OU CPF NÃO PREENCHIDO","AVISO",JOptionPane.INFORMATION_MESSAGE);
              }else {
                  try {
                      ClientesLocadora clientesLocadora = new ClientesLocadora();
                      if(clientesLocadora.verificaClienteExistente(CPF)){
                          if(clientesLocadora.excluirCliente(CPF,senha)){
                              JOptionPane.showMessageDialog(null,"CLIENTE EXCLUIDO COM SUCESSO","AVISO",JOptionPane.INFORMATION_MESSAGE);
                          }else{
                              JOptionPane.showMessageDialog(null,"SENHA INVALIDA","AVISO",JOptionPane.INFORMATION_MESSAGE);
                          }
                      }else{
                          JOptionPane.showMessageDialog(null,"CLIENTE NAO CADASTRADO","AVISO",JOptionPane.INFORMATION_MESSAGE);
                      }

                  } catch (SQLException | ClassNotFoundException ex) {
                      ex.printStackTrace();
                  }
              }
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
            String primeiroNome = textField1.getText().toUpperCase();
            String segundoNome = textField2.getText().toUpperCase();
            String CPF = textField3.getText();

            LinkedList<String> pesquisa = new LinkedList<>();

            if(!primeiroNome.equals("")){
                pesquisa.add("primeiro_nome_cliente='"+primeiroNome+"'");
            }
            if(!segundoNome.equals("")){
                pesquisa.add("segundo_nome_cliente='"+segundoNome+"'");
            }
            if(!CPF.equals("")){
                pesquisa.add("CPF='"+CPF+"'");
            }

            if(pesquisa.size()==0){
                JOptionPane.showMessageDialog(null,"EXISTE(M) CAMPO(S) VAZIO(S)","AVISO",JOptionPane.INFORMATION_MESSAGE);
            }else{

                for(String i : pesquisa){
                    if(i.equals(pesquisa.getFirst())){
                        pesquisaTabela = pesquisaTabela.concat(i);
                    }else{
                        pesquisaTabela = pesquisaTabela.concat(" AND "+i);
                    }
                }
                try {
                    createTable(pesquisaTabela);
                } catch (SQLException ignored) {


                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }

          }
      });
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
    }
    private  void createTable() throws SQLException, ClassNotFoundException {
        ClientesLocadora clientesLocadora = new ClientesLocadora();
        Object[][] data = clientesLocadora.gerarValoresTabelas();
        table1.setModel(new DefaultTableModel(data,new String[]{"idclientes","Primeiro nome","Segundo nome","CPF","Qtd de titulos alugados atualmente","Qtde filmes devolvidos"}));
        table1.setVisible(true);
    }

    private  void createTable(String pesquisa) throws SQLException, ClassNotFoundException {
        ClientesLocadora clientesLocadora = new ClientesLocadora();
        Object[][] data = clientesLocadora.gerarValoresTabelas(pesquisa);
        table1.setModel(new DefaultTableModel(data,new String[]{"idclientes","Primeiro nome","Segundo nome","CPF","Qtd de titulos alugados atualmente","Qtde filmes devolvidos"}));
        table1.setVisible(true);
    }

}
