package Telas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class GerenciaProdutos extends JFrame {
    private JButton aquisicaoDeProdutoButton;
    private JButton perdaProdutoButton;
    private JPanel PanelPrincipal;

    public GerenciaProdutos() throws Exception,ClassNotFoundException, SQLException {
        super("Gerência Produtos");
        setContentPane(PanelPrincipal);
        setMinimumSize(new Dimension(650,650));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        aquisicaoDeProdutoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AquicaoDeProduto aquicaoDeProduto = null;
                try {
                    aquicaoDeProduto = new AquicaoDeProduto();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                aquicaoDeProduto.setVisible(true);
            }
        });
        perdaProdutoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PerdaProduto perdaProduto = null;
                try {
                    perdaProduto = new PerdaProduto();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                perdaProduto.setVisible(true);
            }
        });
    }
}
