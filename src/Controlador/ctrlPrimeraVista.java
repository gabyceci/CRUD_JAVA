package Controlador;

import Vista.frmBomberos;
import Vista.frmPrimeraVista;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ctrlPrimeraVista implements MouseListener{

     private frmPrimeraVista Vista;
     
      public ctrlPrimeraVista(frmPrimeraVista Vista){
        this.Vista = Vista;
        
        Vista.btnSiguiente.addMouseListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
            if (e.getSource() == Vista.btnSiguiente) {
                frmBomberos siguiente = new frmBomberos();
                siguiente.setVisible(true);
            
            Vista.dispose();
        }    
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
