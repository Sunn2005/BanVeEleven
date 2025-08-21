package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Toa_JPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel containerIconLabel;
	String strLabel;
	int loaiToa;

	/**
	 * Create the panel.
	 */
	public Toa_JPanel(String strLabel, int loaiToa) {
		this.strLabel = strLabel;
		this.loaiToa = loaiToa;
		
		setBounds(0, 0, 100, 72);
		setLayout(null);
		
		ImageIcon containerIcon;
		if (loaiToa == 1) {
			containerIcon = new ImageIcon(getClass().getResource("/img/Toa_Dau.png"));
		} else if (loaiToa == 2) {
			containerIcon = new ImageIcon(getClass().getResource("/img/Toa_VIP.png"));
		} else if (loaiToa == 3) {
			containerIcon = new ImageIcon(getClass().getResource("/img/Toa_Giuong_Nam.png"));
		} else if (loaiToa == 4) {
			containerIcon = new ImageIcon(getClass().getResource("/img/Toa_Ghe_Mem.png"));
		} else {
			containerIcon = new ImageIcon(getClass().getResource("/img/Toa_Het_Slot.png"));
		}
		
		Image scaledContainerIcon = containerIcon.getImage().getScaledInstance(100, 49, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		containerIconLabel = new JLabel(new ImageIcon(scaledContainerIcon));
		containerIconLabel.setBackground(Color.WHITE);
		add(containerIconLabel);
		containerIconLabel.setBounds(0, 0, 100, 49);
		
		JLabel lblTenToa = new JLabel(strLabel);
		lblTenToa.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenToa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTenToa.setBounds(25, 52, 53, 13);
		add(lblTenToa);
	}

	public String getStrLabel() {
		return strLabel;
	}

	public int getLoaiToa() {
		return loaiToa;
	}
}
