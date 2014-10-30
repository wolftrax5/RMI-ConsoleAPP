/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Raul
 */
public class Proceso extends Thread {

    private boolean done;
    ImageIcon[] vector;
    private ImageIcon[] resultadoVector;
    int option;

    public Proceso(String str, ImageIcon[] vector, int option) {
        super(str);
        this.option = option;
        this.vector = vector;
        this.done = false;

    }

    public void run() {

        Thread thActual = currentThread();
        
        if ("1".equals(this.getName())&&!done) {
            Metodos prueba = new Metodos();
            resultadoVector = prueba.transformImages(vector, option);
            if (resultadoVector != null) {
                System.out.println("Obtuvo valor");
                done = true;
                
            }
        }
        
        if ("2".equals(this.getName())&&!done) {
            Metodos prueba2 = new Metodos();
            resultadoVector = prueba2.transformImages(vector, option);
            if (resultadoVector != null) {
                System.out.println("Obtuvo valor");
                done = true;
            }
        }

    }

    /**
     * @return the resultadoVector
     */
    
    
    
    public ImageIcon[] getResultadoVector() {
        return resultadoVector;
    }

    /**
     * @return the done
     */
    public boolean isDone() {
        return done;
    }

}
