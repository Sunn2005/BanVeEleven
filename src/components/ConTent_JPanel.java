package components;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConTent_JPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ConTent_JPanel() {
		
		setBounds(0, 178, 1470, 575);
		setLayout(new GridLayout());
		setForeground(new Color(255, 255, 255));
		
		ImageIcon backGround = new ImageIcon(getClass().getResource("/img/background.png"));
	    Image scaledBG = backGround.getImage().getScaledInstance(1469, 575, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
	    JLabel backGroundLabel = new JLabel(new ImageIcon(scaledBG));
	    backGroundLabel.setBounds(0 ,0 , 1469, 575); // Cập nhật kích thước trên JLabel
		add(backGroundLabel);
	}

}
