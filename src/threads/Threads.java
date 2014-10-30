/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author Raul
 */
public class Threads {

    public static void main(String[] args) {
        Metodos c = new Metodos();
        BufferedImage imgsel;
        ImageIcon imgicsel, invertImage;
        int numImagenes = 2;
        ImageIcon[] vectorImagenes = new ImageIcon[numImagenes];
        ImageIcon[] imagenesNuevas = new ImageIcon[numImagenes];
        for (int i = 0; i < numImagenes; i++) {
            JFileChooser selector = new JFileChooser();
            int r = selector.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                try {
                    imgsel = ImageIO.read(selector.getSelectedFile());
                    imgicsel = new ImageIcon(imgsel);
                    vectorImagenes[i] = imgicsel;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        imagenesNuevas = c.GetImages(vectorImagenes, 2);

        for (int i = 0; i < numImagenes; i++) {
            invertImage = imagenesNuevas[i];
            Image myimage = invertImage.getImage();
            BufferedImage imgFinal = new BufferedImage(myimage.getWidth(null), myimage.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = imgFinal.createGraphics();
            g2.drawImage(myimage, 0, 0, null);
            g2.dispose();
            System.out.println("Obtuvo IMAGEN");
            try {
                ImageIO.write(imgFinal, "jpg", new File("foto" + i + ".jpg"));
            } catch (IOException e) {
                System.out.println("Error de escritura");
            }
        }

    }

}
