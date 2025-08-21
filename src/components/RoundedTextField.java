package components;

import javax.swing.JTextField;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Insets;

public class RoundedTextField extends JTextField {
    private static final long serialVersionUID = 1L;
    private int cornerRadius;

    // Constructor để khởi tạo bán kính bo góc
    public RoundedTextField(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false);  // Đảm bảo nền trong suốt
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ nền trắng với góc bo
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ viền đen với góc bo
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        g2.dispose();
    }

    @Override
    public Insets getInsets() {
        int pad = cornerRadius / 3;
        return new Insets(pad, pad, pad, pad);
    }
}
