package gfx;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Experimental button solution.
 * @author Andrew McManaway
 */
public class LabelButton extends JButton {

    private String text;
    private final int WIDTH, HEIGHT;

    public LabelButton(String text, int width, int height) {
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setAlignmentX(0.5f);
        this.text = text;
        this.WIDTH = width;
        this.HEIGHT = height;

        this.createImages();
    }

    private void createImages(){
        /* Image Setup */
        BufferedImage normal = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        BufferedImage clicked = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        BufferedImage rollover = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Font font = new Font("Gill Sans MT Condensed", Font.BOLD, 30);
        Graphics2D g2d = normal.createGraphics();
        FontMetrics metrics = g2d.getFontMetrics(font);
        /* Getting Font Sizes */
        int x = (WIDTH - metrics.stringWidth(this.text)) / 2;
        int y = ((HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();

        /* Create Normal Button*/
        g2d.setRenderingHints(rh);
        g2d.setFont(font);
        g2d.setColor(Color.white);

        g2d.drawImage(ImageLoader.image("ui", "button", true), 0, 0, WIDTH, HEIGHT, null);
        g2d.drawString(this.text, x, y);
        this.setIcon(new ImageIcon(normal));

        /* Create Clicked Button */
        g2d = (Graphics2D) clicked.getGraphics();
        g2d.setRenderingHints(rh);
        g2d.setFont(font);
        g2d.setColor(Color.white);

        g2d.drawImage(ImageLoader.image("ui", "clicked", true), 0, 0, WIDTH, HEIGHT, null);
        g2d.drawString(this.text, x, y);
        this.setPressedIcon(new ImageIcon(clicked));

        /* Create Rollover Button */
        g2d = (Graphics2D) rollover.getGraphics();
        g2d.setRenderingHints(rh);
        g2d.setFont(font);
        g2d.setColor(Color.white);

        g2d.drawImage(ImageLoader.image("ui", "rollover", true), 0, 0, WIDTH, HEIGHT, null);
        g2d.drawString(this.text, x, y);
        this.setRolloverEnabled(true);
        this.setRolloverIcon(new ImageIcon(rollover));
    }
}
