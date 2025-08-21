package components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

//Renderer cho nút xoá
public class ButtonRenderer extends JButton implements TableCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon trashIcon;
	private Image scaledTrashIcon;
	private JLabel trashIconLabel;

	public ButtonRenderer() {
		trashIcon = new ImageIcon(getClass().getResource("/img/trash-regular-48-removebg-preview.png"));
		scaledTrashIcon = trashIcon.getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH);
		// JLabel cho hình ảnh
		trashIconLabel = new JLabel(new ImageIcon(scaledTrashIcon));
		add(trashIconLabel);
		trashIconLabel.setBounds(120, 0, 150, 50);
		setOpaque(true);
		setBackground(Color.WHITE);
		setHorizontalAlignment(SwingConstants.CENTER); 
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		return this;
	}
}