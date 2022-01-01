package UI;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Ô²½ÇJpanel
 */
public class PartPanel extends JPanel {


    private static final long serialVersionUID = 1L;

//    public PartPanel() {
//        super();
//        setOpaque(true);
//        setSize(80, 30);
//        setBackground(new Color(36, 179, 193));
//        JLabel nameLabel = new JLabel("Ô²½ÇÃæ°å", JLabel.CENTER);
//        nameLabel.setForeground(Color.white);
//        nameLabel.setBounds(0, 0, 80, 30);
//        nameLabel.setAlignmentY(0.1f);
//        add(nameLabel);
//    }

    @Override
    public void paint(Graphics g) {
        int fieldX = 0;
        int fieldY = 0;
        int fieldWeight = getSize().width;
        int fieldHeight = getSize().height;
        RoundRectangle2D rect = new RoundRectangle2D.Double(fieldX, fieldY, fieldWeight, fieldHeight, 20, 20);
        g.setClip(rect);
        super.paint(g);
    }

}