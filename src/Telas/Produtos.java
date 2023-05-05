package Telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Produtos  extends JFrame{
    private JPanel PainelPrincipal;
    private JButton PRECIONEButton;
    private JButton DEVOLUCOESTITULOSButton;

    public Produtos(){
        super("Produtos");
        setContentPane(PainelPrincipal);
        setMinimumSize(new Dimension(650,650));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        PRECIONEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlugarTitulos alugarTitulos = null;
                try {
                    alugarTitulos = new AlugarTitulos();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                alugarTitulos.setVisible(true);
            }
        });
        DEVOLUCOESTITULOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DevolucaoTitulos devolucaoTitulos = null;
                try {
                    devolucaoTitulos = new DevolucaoTitulos();
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                assert devolucaoTitulos != null;
                devolucaoTitulos.setVisible(true);
            }
        });
    }
}
