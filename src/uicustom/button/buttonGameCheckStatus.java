package uicustom.button;

import javax.swing.JButton;
import java.awt.event.ActionListener;

public class buttonGameCheckStatus extends JButton{
    
    public buttonGameCheckStatus(final ActionListener actionListener){
        this.setText("Verificar jogo");
        this.addActionListener(actionListener);
    }

}
