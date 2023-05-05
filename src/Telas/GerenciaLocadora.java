package Telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class GerenciaLocadora extends JFrame {
    private JPanel PainelPrincipal;
    private JButton REGISTROSDALOCADORAButton;
    private JButton FUNCIONARIOSButton;

    public GerenciaLocadora() {
        super(("Gerência Locadora"));
        setContentPane(PainelPrincipal);
        setMinimumSize(new Dimension(650,650));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        FUNCIONARIOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaFuncionarios janelaFuncionarios = null;
                try {
                    janelaFuncionarios = new JanelaFuncionarios();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                janelaFuncionarios.setVisible(true);
            }
        });
        REGISTROSDALOCADORAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaRegistro janelaRegistro = null;
                try {
                    janelaRegistro = new JanelaRegistro();
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                janelaRegistro.setVisible(true);
            }
        });
    }

}
