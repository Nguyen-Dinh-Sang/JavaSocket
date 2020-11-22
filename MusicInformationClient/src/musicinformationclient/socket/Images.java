package musicinformationclient.socket;
import musicinformationclient.ok;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
public class Images {
    public static void main(String[] args)
    {
        Images img=new Images();
            JFrame jFrame=new JFrame();
            JPanel jPanel= img.show_image("https://photo-resize-zmp3.zadn.vn/w240_r1x1_jpeg/avatars/2/9/e/b/29ebb66b99eb83e51bf344a16c6461cd.jpg",100,100);
           jFrame.add(jPanel);
           jFrame.setSize(100,100);
           jFrame.setVisible(true);
    }
    public Image getScaledImage(Image Img, int wt, int ht) {
        BufferedImage resizedImg = new BufferedImage(wt, ht, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(Img, 0, 0, wt, ht, null);
        g2.dispose();

        return resizedImg;
    }
    public JPanel show_image(String link, int wt, int ht)
    {JPanel jPanel = new JPanel();
        try
        {
            URL url = new URL(link);
            BufferedImage image = ImageIO.read(url);
            System.out.println("Load image into frame...");
            JLabel label = new JLabel(new ImageIcon(getScaledImage(image, wt,ht)));

            jPanel.setSize(100, 100);
            jPanel.add(label);

        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
        return jPanel;
    }
}
