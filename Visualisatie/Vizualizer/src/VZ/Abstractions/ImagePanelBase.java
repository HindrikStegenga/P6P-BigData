package VZ.Abstractions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by hindrik on 28-1-17.
 */

/**
 * Provides an easy interface for a panel supporting an image.
 */
public class ImagePanelBase extends JPanel {

    private BufferedImage _image;

    /**
     * Constructor of the panel
     * @param image path to image to show
     * @param x width of panel
     * @param y height of panel
     */
    public ImagePanelBase(String image, int x, int y)
    {
        try {
            _image = ImageIO.read(new File(image));


        } catch (IOException e) {
            e.printStackTrace();
        }

        _image = getScaledImage(_image,x,y);

        this.setMinimumSize(new Dimension(x,y));
        this.setPreferredSize(new Dimension(x,y));
        this.setMaximumSize(new Dimension(x,y));
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(_image,0,0,this);
    }

    /**
     * Scales the image
     * @param srcImg image to scale
     * @param w width to scale to
     * @param h height to scale to
     * @return scaled image
     */
    private BufferedImage getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }
}
