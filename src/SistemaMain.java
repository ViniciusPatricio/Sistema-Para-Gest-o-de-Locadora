import Telas.JanelaPrincipal;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;



public class SistemaMain {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
        Frame frame = new JanelaPrincipal();
        frame.setVisible(true);

    }

}
