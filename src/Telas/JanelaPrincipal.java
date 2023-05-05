package Telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class JanelaPrincipal extends JFrame {
    private JPanel PainelPrincipal;
    private JButton CLIENTESButton;
    private JButton GERÊNCIADEPRODUTOSButton;
    private JButton GERÊNCIALOCADORAButton;
    private JButton PRODUTOSButton;

    public JanelaPrincipal(){
        super("Sistema de Gestão");
        setSize(650,650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(PainelPrincipal);
        GERÊNCIADEPRODUTOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciaProdutos gerenciaProdutos = null;
                try {
                    gerenciaProdutos = new GerenciaProdutos();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                gerenciaProdutos.setVisible(true);
            }
        });
        CLIENTESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PainelClientes clientes = null;
                try {
                    clientes = new PainelClientes();
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                clientes.setVisible(true);
            }
        });
        PRODUTOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produtos produtos = new Produtos();
                produtos.setVisible(true);
            }
        });
        GERÊNCIALOCADORAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciaLocadora gerenciaLocadora = new GerenciaLocadora();
                gerenciaLocadora.setVisible(true);
            }
        });
    }
}