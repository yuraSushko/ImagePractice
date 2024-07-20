import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyPanel extends JPanel{
    BufferedImage image;
    private BufferedImage imageWithFilter;

    public MyPanel(){
        this.setVisible(true);
        this.setBounds(0,0,800,600);
        addImage();

        imageWithFilter= copyImage(image);
    //    repaint();
        //setBirghtest();
//        rotate180();
  //      repaint();
        //swap();
        MouseListener mouseListener= new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                addToRgb();
                System.out.println(1);
                repaint();
            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }


        } ;
        this.addMouseListener(mouseListener);


        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.drawImage(imageWithFilter,0,0,this.getWidth(),this.getHeight(),this);
    }
    public void addImage() {
        try {
            image = ImageIO.read(new File( "src/main/resources/angryBirdsInstructions.png").getAbsoluteFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage copyImage(BufferedImage original) {
        BufferedImage copy = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
        Graphics g = copy.getGraphics();
        g.drawImage(original, 0, 0, null);
        g.dispose();
        return copy;
    }



    public void addToRgb(){
        int plus =5;
        for (int x = 0; x < this.imageWithFilter.getWidth(); x++) {
            for (int y = 0; y < this.imageWithFilter.getHeight(); y++) {
                Color curr =new Color( this.imageWithFilter.getRGB(x,y));
                int red = (curr.getRed()+plus )>255?0 : (curr.getRed()+plus );
                int green = (curr.getGreen()) +plus>255?0 : (curr.getGreen()+plus) ;
                int blue = (curr.getBlue()+plus)>255?0 : (curr.getBlue()+plus);
                this.imageWithFilter.setRGB(x,y ,new Color(red,green,blue).getRGB());
            }
        }
    }


    public void  rotate180(){
        for (int x = 0; x < this.imageWithFilter.getWidth()/2; x++) {
            for (int y = 0; y <  this.imageWithFilter.getHeight(); y++) {
                Color curr = new Color(this.imageWithFilter.getRGB(x,y));
                int oppX=this.imageWithFilter.getWidth()-1-x;
                int oppY=this.imageWithFilter.getHeight()-1-y;
                Color opp = new Color(this.imageWithFilter.getRGB(oppX,oppY));
                this.imageWithFilter.setRGB(x,y,opp.getRGB() );
                this.imageWithFilter.setRGB(oppX,oppY, curr.getRGB());
            }
        }
    }

    public void  swap(){
        for (int x = 0; x < this.imageWithFilter.getWidth()/2; x++) {
            for (int y = 0; y <  this.imageWithFilter.getHeight(); y++) {
                Color curr = new Color(this.imageWithFilter.getRGB(x,y));
                int oppX=this.imageWithFilter.getWidth()-1-x;
                //int oppY=this.imageWithFilter.getHeight()-1-y;
                Color opp = new Color(this.imageWithFilter.getRGB(oppX,y));
                this.imageWithFilter.setRGB(x,y,opp.getRGB() );
                this.imageWithFilter.setRGB(oppX,y, curr.getRGB());
            }
        }
    }




    public void setBirghtest(){
        int curr=0,brightest=0,minX=0, miny=0; Color minColor=null;
        for (int i = 0; i < this.imageWithFilter.getWidth(); i++) {
            for (int j = 0; j <  this.imageWithFilter.getHeight(); j++) {
                Color currColor = new Color(this.imageWithFilter.getRGB(i,j));
                curr=currColor.getRed()+currColor.getGreen()+ currColor.getBlue();
                if(curr>brightest){
                    brightest= curr;
                    minColor= new Color(this.imageWithFilter.getRGB(i,j));
                    minX=i;miny=j;
                }
            }
        }
        System.out.println(minX+" , "+miny);


        for (int i = 0; i < this.imageWithFilter.getWidth()/2; i++) {
            for (int j = 0; j < this.imageWithFilter.getHeight(); j++) {
                this.imageWithFilter.setRGB(i,j,minColor.getRGB());
            }
        }


    }




}
