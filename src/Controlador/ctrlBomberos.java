package Controlador;

import Modelo.Bomberos;
import Vista.frmBomberos;
import Vista.frmPrimeraVista;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;


public class ctrlBomberos implements MouseListener {
    
    private Bomberos Modelo;
    private frmBomberos Vista;

    public ctrlBomberos(Bomberos Modelo, frmBomberos Vista){
        this.Vista = Vista;
        this.Modelo = Modelo;
        
        
        Vista.btnAgregar.addMouseListener(this);
        Vista.btnEliminar.addMouseListener(this);
        Vista.btnActualizar.addMouseListener(this);
        Vista.jtbBomberos.addMouseListener(this);
        Vista.txtSalir2.addMouseListener(this);
        Modelo.mostrar(Vista.jtbBomberos);

        
    }

    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getSource() == Vista.txtSalir2) {
                frmPrimeraVista salir = new frmPrimeraVista();
                salir.setVisible(true);
            
            Vista.dispose();
        }    
        
        if (e.getSource() == Vista.btnAgregar) {
              try {
                  if (Vista.txtNombre.getText().isEmpty() || Vista.txtEdad.getText().isEmpty() || 
                        Vista.txtPeso.getText().isEmpty() || Vista.txtCorreo.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor ingrese todos los campos");
                        return;
                    }

                    if (!Vista.txtNombre.getText().matches("[a-zA-Z ]+")) {
                        JOptionPane.showMessageDialog(null, "Ingrese solamente letras");
                        return;
                    }

                    try {
                        Integer.parseInt(Vista.txtEdad.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "La edad solo debe contener números");
                        return;
                    }

                    try {
                        Double.parseDouble(Vista.txtPeso.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El peso debe contener solo números");
                        return;
                    }

                    if (!Vista.txtCorreo.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                        JOptionPane.showMessageDialog(null, "El correo no tiene un formato válido");
                        return;
                    }
                Modelo.setNombre_Bombero(Vista.txtNombre.getText());
                Modelo.setEdad_Bombero(Integer.parseInt(Vista.txtEdad.getText()));
                Modelo.setPeso_Bombero(Double.parseDouble(Vista.txtPeso.getText()));
                Modelo.setCorreo_Bombero(Vista.txtCorreo.getText());

                Modelo.insertar();
                Modelo.mostrar(Vista.jtbBomberos); 
                Modelo.limpiarCampos(Vista);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al agregar bombero " + ex.getMessage());
            }
        }
        
        if (e.getSource() == Vista.btnEliminar) {
            Modelo.eliminar(Vista.jtbBomberos);
            Modelo.mostrar(Vista.jtbBomberos);
        }
        
        if (e.getSource() == Vista.btnActualizar) {
            try {
                if (Vista.txtNombre.getText().isEmpty() || Vista.txtEdad.getText().isEmpty() || 
                        Vista.txtPeso.getText().isEmpty() || Vista.txtCorreo.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor ingrese todos los campos");
                        return;
                    }

                    if (!Vista.txtNombre.getText().matches("[a-zA-Z ]+")) {
                        JOptionPane.showMessageDialog(null, "Ingrese solamente letras");
                        return;
                    }

                    try {
                        Integer.parseInt(Vista.txtEdad.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "La edad solo debe contener números");
                        return;
                    }

                    try {
                        Double.parseDouble(Vista.txtPeso.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El peso debe contener solo números");
                        return;
                    }

                    if (!Vista.txtCorreo.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                        JOptionPane.showMessageDialog(null, "El correo no tiene un formato válido");
                        return;
                    }
                Modelo.setNombre_Bombero(Vista.txtNombre.getText());
                Modelo.setEdad_Bombero(Integer.parseInt(Vista.txtEdad.getText()));
                Modelo.setPeso_Bombero(Double.parseDouble(Vista.txtPeso.getText()));
                Modelo.setCorreo_Bombero(Vista.txtCorreo.getText());

                Modelo.actualizar(Vista.jtbBomberos);
                Modelo.mostrar(Vista.jtbBomberos);
                Modelo.limpiarCampos(Vista);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar bombero " + ex.getMessage());
            }

        }
        
        if (e.getSource() == Vista.jtbBomberos) {
            Modelo.cargarDatos(Vista);
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
