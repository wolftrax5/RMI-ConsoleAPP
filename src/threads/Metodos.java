/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package threads;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author Raul
 */
public class Metodos {
    
    private ImageIcon[] imagesPart1;
    private ImageIcon[] imagesPart2;
    
    public int prueba(){
        int result = 2;
        
        long TimeOne = System.currentTimeMillis();

        for (int index = 0; index < 25; index++) {
            //System.out.println("metodo prueba ejecutandose");

            // Insert a half-second (approx.) delay
            do {
            } while ((TimeOne + 500) > System.currentTimeMillis());

            TimeOne = System.currentTimeMillis();
        }
        return result;
    }
    
    
    public void divideImages(ImageIcon[] image) {
        int numeroImagenes = image.length;
        int x, z = 0;
        if (numeroImagenes % 2 == 0) {
            x = numeroImagenes / 2;
            imagesPart1 = new ImageIcon[x];
            imagesPart2 = new ImageIcon[x];
        } else {
            x = (numeroImagenes - 1) / 2;
            imagesPart1 = new ImageIcon[x + 1];
            imagesPart1 = new ImageIcon[x];
        }

        for (int i = 0; i < numeroImagenes; i++) {
            if (i < x) {
                imagesPart1[i] = image[i];
            } else {
                imagesPart2[z] = image[i];
                z++;
            }
        }

    }

    public ImageIcon[] GetImages(ImageIcon[] image, int op) {
        int numeroImagenes = image.length;
        int option = op;
       
        ImageIcon[] vectorImagenes3 = new ImageIcon[numeroImagenes];
        divideImages(image);
        Proceso parte1 = new Proceso("1",imagesPart1,op);
        Proceso parte2 = new Proceso("2",imagesPart2,op);

        parte1.start();
        parte2.start();
        
        boolean bandera1=true,bandera2 = true;
        
        while (bandera1||bandera2) {
            if (parte1.isDone()&&bandera1) {
                imagesPart1 = parte1.getResultadoVector();
                System.out.println("Obtuvo valor1");
                bandera1 = false;
            }
            if (parte2.isDone()&&bandera2) {
                imagesPart2 = parte2.getResultadoVector();
                System.out.println("Obtuvo valor2");
                bandera2 = false;
            }
            System.out.print("");
        }
        System.out.println("Incorporacion de imagenes completa");
        System.arraycopy(imagesPart1, 0, vectorImagenes3, 0, imagesPart1.length);
        System.arraycopy(imagesPart2, 0, vectorImagenes3, imagesPart1.length, imagesPart2.length);
//
//        System.out.println("ENTRO");
        return vectorImagenes3;
    }
    
    public ImageIcon[] transformImages(ImageIcon[] images, int op) {
        BufferedImage bi = null;
        int r, g, b;
        Color color;
        ImageIcon[] invertImage = new ImageIcon[images.length];
        

        for (int k = 0; k < images.length; k++) {

        //Convert ImageIcon to BufferedImage
            Image myimage = images[k].getImage();
            BufferedImage imgsel = new BufferedImage(myimage.getWidth(null), myimage.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = imgsel.createGraphics();
            g2.drawImage(myimage, 0, 0, null);
            g2.dispose();

            //Negativos
            if (imgsel != null && op == 1) {
                bi = new BufferedImage(imgsel.getWidth(), imgsel.getHeight(), imgsel.getType());
                for (int i = 0; i < imgsel.getWidth(); i++) {
                    for (int j = 0; j < imgsel.getHeight(); j++) {
                        //Se obtiene el colo del pixel
                        color = new Color(imgsel.getRGB(i, j));
                        //se extraen los valores RGB
                        r = color.getRed();
                        g = color.getGreen();
                        b = color.getBlue();
                        //se coloca len la nueva imagen los valores invertidos
                        Color miColor = new Color(255 - r, 255 - g, 255 - b);
                        bi.setRGB(i, j, miColor.getRGB());
                    }
                }
                invertImage[k] = new ImageIcon(bi);
            } //Escala de Grises
            else if (imgsel != null && op == 2) {
                int mediaPixel, colorSRGB;
                Color colorAux;

                //Recorremos la imagen píxel a píxel
                for (int i = 0; i < imgsel.getWidth(); i++) {
                    for (int j = 0; j < imgsel.getHeight(); j++) {
                        //Almacenamos el color del píxel
                        colorAux = new Color(imgsel.getRGB(i, j));
                        //Calculamos la media de los tres canales (rojo, verde, azul)
                        mediaPixel = (int) ((colorAux.getRed() + colorAux.getGreen() + colorAux.getBlue()) / 3);
                        //Cambiamos a formato sRGB
                        colorSRGB = (mediaPixel << 16) | (mediaPixel << 8) | mediaPixel;
                        //Asignamos el nuevo valor al BufferedImage
                        imgsel.setRGB(i, j, colorSRGB);
                    }
                }
                invertImage[k] = new ImageIcon(imgsel);
            } else {
                System.out.println("No hay opcion");
                invertImage[k] = null;
            }
        }
        
      //try {
        //      ImageIO.write(imgsel, "jpg", new File("nombre.jpg"));
        //      System.out.println("LISTO");
        //      } catch (IOException e) {
        //      System.out.println("Error de escritura");
        //    }
        return invertImage;
    }
}
