package components;

import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Insets;

public class RoundedButton extends JButton {
    private static final long serialVersionUID = 1L;
    private int cornerRadius;

    // Constructor để khởi tạo bán kính bo góc
    public RoundedButton(String text, int radius) {
        super(text);  // Gọi constructor của JButton với tiêu đề nút
        this.cornerRadius = radius;
        setContentAreaFilled(false);  // Đảm bảo nền trong suốt
        setFocusPainted(false);       // Tắt viền khi nhấn nút
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ nền nút với màu nền hiện tại
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ viền đen với góc bo
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        g2.dispose();
    }

    @Override
    public Insets getInsets() {
        int pad = cornerRadius / 3;
        return new Insets(pad, pad, pad, pad);
    }
}