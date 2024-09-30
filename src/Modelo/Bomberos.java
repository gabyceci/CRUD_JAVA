/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.frmBomberos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Latitude E5470
 */
public class Bomberos {
    
    private String UUID_Bombero;
    private String Nombre_Bombero;
    private Number Edad_Bombero;
    private Double Peso_Bombero;
    private String Correo_Bombero;

    public String getUUID_Bombero() {
        return UUID_Bombero;
    }

    public void setUUID_Bombero(String UUID_Bombero) {
        this.UUID_Bombero = UUID_Bombero;
    }

    public String getNombre_Bombero() {
        return Nombre_Bombero;
    }

    public void setNombre_Bombero(String Nombre_Bombero) {
        this.Nombre_Bombero = Nombre_Bombero;
    }

    public Number getEdad_Bombero() {
        return Edad_Bombero;
    }

    public void setEdad_Bombero(Number Edad_Bombero) {
        this.Edad_Bombero = Edad_Bombero;
    }

    public Double getPeso_Bombero() {
        return Peso_Bombero;
    }

    public void setPeso_Bombero(Double Peso_Bombero) {
        this.Peso_Bombero = Peso_Bombero;
    }

    public String getCorreo_Bombero() {
        return Correo_Bombero;
    }

    public void setCorreo_Bombero(String Correo_Bombero) {
        this.Correo_Bombero = Correo_Bombero;
    }
    
    public void insertar() {
        Connection conexion = ClaseConexion.getConexion();

        try {
            PreparedStatement addBombero = conexion.prepareStatement(
                "insert into tbBombero(UUID_Bombero, Nombre_Bombero, Edad_Bombero, Peso_Bombero, Correo_Bombero) values (?, ?, ?, ?, ?)"
            );
            addBombero.setString(1, UUID.randomUUID().toString());
            addBombero.setString(2, getNombre_Bombero());
            addBombero.setInt(3, Integer.parseInt(getEdad_Bombero().toString()));
            addBombero.setDouble(4, getPeso_Bombero());
            addBombero.setString(5, getCorreo_Bombero());

            addBombero.executeUpdate();
            JOptionPane.showMessageDialog(null, "Bombero insertado.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar bombero por " + ex.getMessage());
        }
    }
    
    public void actualizar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            String UUID_Bombero = tabla.getValueAt(filaSeleccionada, 0).toString();

            try {
                String query = "update tbBombero set Nombre_Bombero = ?, Edad_Bombero = ?, Peso_Bombero = ?, Correo_Bombero = ? where UUID_Bombero = ?";
                PreparedStatement actualizar = conexion.prepareStatement(query);

                actualizar.setString(1, getNombre_Bombero());
                actualizar.setInt(2, Integer.parseInt(getEdad_Bombero().toString()));
                actualizar.setDouble(3, getPeso_Bombero());
                actualizar.setString(4, getCorreo_Bombero());
                actualizar.setString(5, UUID_Bombero);

                actualizar.executeUpdate();
                JOptionPane.showMessageDialog(null, "Bombero actualizado con éxito.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar por " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor selecciona un bombero para actualizar.");
        }
    }

    public void mostrar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel Modelo = new DefaultTableModel();
        Modelo.setColumnIdentifiers(new Object[]{"UUID_Bombero", "Nombre", "Edad", "Peso", "Correo"});

        try {
            String query = "select * from tbBombero";
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Modelo.addRow(new Object[]{
                    rs.getString("UUID_Bombero"),
                    rs.getString("Nombre_Bombero"),
                    rs.getInt("Edad_Bombero"),
                    rs.getDouble("Peso_Bombero"),
                    rs.getString("Correo_Bombero")
                });
            }

            tabla.setModel(Modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al mostrar la lista de bomberos: " + ex.getMessage());
        }
    }

    public void eliminar(JTable tabla) {
    Connection conexion = ClaseConexion.getConexion();
    int filaSeleccionada = tabla.getSelectedRow();

    if (filaSeleccionada != -1) {
        String UUID_Bombero = tabla.getValueAt(filaSeleccionada, 0).toString();

        try {
            String sql = "delete from tbBombero where UUID_Bombero = ?";
            PreparedStatement eliminar = conexion.prepareStatement(sql);
            eliminar.setString(1, UUID_Bombero);
            eliminar.executeUpdate();
            JOptionPane.showMessageDialog(null, "El bombero se eliminó con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar bombero " + ex.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor selecciona un bombero para eliminar.");
    }
}

    
    public void cargarDatos(frmBomberos Vista){
        
        int filaSeleccionada = Vista.jtbBomberos.getSelectedRow();
        
        if (filaSeleccionada != -1) {
            
            String UUID_Bombero = Vista.jtbBomberos.getValueAt(filaSeleccionada, 0).toString();
            String Nombre_Bombero = Vista.jtbBomberos.getValueAt(filaSeleccionada, 1).toString();
            String Edad_Bombero = Vista.jtbBomberos.getValueAt(filaSeleccionada, 2).toString();
            String Peso_Bombero = Vista.jtbBomberos.getValueAt(filaSeleccionada, 3).toString();
            String Correo_Bombero = Vista.jtbBomberos.getValueAt(filaSeleccionada, 4).toString();
            
            Vista.txtNombre.setText(Nombre_Bombero);
            Vista.txtEdad.setText(Edad_Bombero);
            Vista.txtPeso.setText(Peso_Bombero);
            Vista.txtCorreo.setText(Correo_Bombero);
            
            
        }
        
    }
    
    public void limpiarCampos(frmBomberos Vista){
         
         Vista.txtNombre.setText("");
         Vista.txtEdad.setText("");
         Vista.txtPeso.setText("");
         Vista.txtCorreo.setText("");
        
    }

}
