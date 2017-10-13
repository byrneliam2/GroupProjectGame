package gfx;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Experimental button solution.
 * @author Andrew McManaway
 */
public class PictureButton extends JButton {

    private String text;
    private final int WIDTH, HEIGHT;
    private static Image d_normal, d_clicked, d_rollover;

    public PictureButton(String text, int width, int height) {
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setAlignmentX(0.5f);
        this.text = text;
        this.WIDTH = width;
        this.HEIGHT = height;

        if(this.d_normal == null) this.d_normal = ImageLoader.image("ui", "button", true);
        if(this.d_clicked == null) this.d_clicked = ImageLoader.image("ui", "clicked", true);
        if(this.d_rollover == null) this.d_rollover = ImageLoader.image("ui", "rollover", true);

        this.createImages();
    }

    private void createImages(){
        /* Image Setup */
        BufferedImage normal = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        BufferedImage clicked = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        BufferedImage rollover = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Graphics2D g2d = normal.createGraphics();
        Font font = new Font(g2d.getFont().getName(), Font.PLAIN, 20);
        FontMetrics metrics = g2d.getFontMetrics(font);
        /* Getting Font Sizes */
        int x = (WIDTH - metrics.stringWidth(this.text)) / 2;
        int y = ((HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();

        /* Create Normal Button*/
        g2d.setRenderingHints(rh);
        g2d.setFont(font);
        g2d.setColor(Color.white);

        g2d.drawImage(d_normal, 0, 0, WIDTH, HEIGHT, null);
        g2d.drawString(this.text, x, y);
        this.setIcon(new ImageIcon(normal));

        /* Create Clicked Button */
        g2d = (Graphics2D) clicked.getGraphics();
        g2d.setRenderingHints(rh);
        g2d.setFont(font);
        g2d.setColor(Color.white);

        g2d.drawImage(d_clicked, 0, 0, WIDTH, HEIGHT, null);
        g2d.drawString(this.text, x, y);
        this.setPressedIcon(new ImageIcon(clicked));

        /* Create Rollover Button */
        g2d = (Graphics2D) rollover.getGraphics();
        g2d.setRenderingHints(rh);
        g2d.setFont(font);
        g2d.setColor(Color.white);

        g2d.drawImage(d_rollover, 0, 0, WIDTH, HEIGHT, null);
        g2d.drawString(this.text, x, y);
        this.setRolloverEnabled(true);
        this.setRolloverIcon(new ImageIcon(rollover));
    }
}
