package gfx;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LabelButton extends JButton {
    private String text;
    private final int WIDTH, HEIGHT;

    public LabelButton(String text, int width, int height, boolean rollover) {
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setAlignmentX(0.5f);
        this.text = text;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.createImages(rollover);
    }

    private void createImages(boolean rollover){
        BufferedImage main = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = main.createGraphics();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        g2d.drawImage(ImageLoader.image("ui", "Button", true), 0, 0, WIDTH, HEIGHT, null);

        Font font = new Font("Gill Sans MT Condensed", Font.BOLD, 30);
        FontMetrics metrics = g2d.getFontMetrics(font);
        g2d.setFont(font);

        int x = (WIDTH - metrics.stringWidth(this.text)) / 2;
        int y = ((HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();


        g2d.setPaint(Color.WHITE);
        g2d.drawString(this.text, x, y);
        this.setIcon(new ImageIcon(main));

        if (rollover) {
            BufferedImage roll = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
            g2d = roll.createGraphics();
            g2d.setRenderingHints(rh);
            g2d.setColor(Color.white);

            g2d.drawImage(ImageLoader.image("ui", "Rollover", true), 0, 0, WIDTH, HEIGHT, null);

            g2d.setColor(Color.WHITE);
            g2d.setFont(font);
            g2d.drawString(this.text, x, y);
            this.setRolloverEnabled(true);
            this.setRolloverIcon(new ImageIcon(roll));
        }
    }
}
