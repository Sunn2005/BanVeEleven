package components;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import entity.Ve;
import gui.BanVeNhapThongTin_Gui;
import gui.BanVe_GUI;
import gui.DoiVe_GUI;

public class ButtonEditor extends DefaultCellEditor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button;
	private JTable table;

	public ButtonEditor(JCheckBox checkBox, BanVe_GUI banVe_GUI, BanVeNhapThongTin_Gui nhapThongTin_GUI) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.setBackground(Color.WHITE);
		button.setHorizontalAlignment(SwingConstants.CENTER); // căn giữa
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
				int row = table.getSelectedRow();
				if (row != -1) {
					DefaultTableModel model = ((DefaultTableModel) table.getModel());
					model.removeRow(row);
					banVe_GUI.dsVeDatTam.remove(row);
					
					if (nhapThongTin_GUI.map.get(row) != null) {
						nhapThongTin_GUI.map.remove(row);						
					}
					nhapThongTin_GUI.loadThongTin(banVe_GUI.dsVeDatTam);
					
					for (int key: nhapThongTin_GUI.map.keySet()) {
						if (key > row) {
							nhapThongTin_GUI.map.put(key-1, nhapThongTin_GUI.map.get(key));
							model.setValueAt(nhapThongTin_GUI.map.get(key).getTenKH(), key-1, 1);
							nhapThongTin_GUI.map.remove(key);
						} else {
							model.setValueAt(nhapThongTin_GUI.map.get(key).getTenKH(), key, 1);
						}
					}
					
					if (banVe_GUI.toaCu != null) {
						Toa_JPanel pToa;
						if (banVe_GUI.toaCu.getLoaiToa().equals("VIP")) {
							pToa = new Toa_JPanel("", 2);
						} else if (banVe_GUI.toaCu.getLoaiToa().equals("Giường nằm")) {
							pToa = new Toa_JPanel("", 3);
						} else {
							pToa = new Toa_JPanel("", 4);
						}
						pToa.setBounds(banVe_GUI.boundsPanelToa);
						// su kien
						banVe_GUI.loadGhe(pToa, banVe_GUI.toaCu);
					}
		
					
					// Tạo panel Vé
					banVe_GUI.jp_VeMua.removeAll();
					for (Ve ve : banVe_GUI.dsVeDatTam) {
						Ve_JPanel pVe = new Ve_JPanel(ve, banVe_GUI.dsVeDatTam, banVe_GUI.jp_VeMua);
						banVe_GUI.jp_VeMua.add(pVe);
					}

				}
			}
		});
	}
	public ButtonEditor(JCheckBox checkBox, DoiVe_GUI doiVe_GUI, BanVeNhapThongTin_Gui nhapThongTin_GUI) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.setBackground(Color.WHITE);
		button.setHorizontalAlignment(SwingConstants.CENTER); // căn giữa
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
				int row = table.getSelectedRow();
				if (row != -1) {
					DefaultTableModel model = ((DefaultTableModel) table.getModel());
					model.removeRow(row);
					doiVe_GUI.dsVeDatTam.remove(row);
					
					if (nhapThongTin_GUI.map.get(row) != null) {
						nhapThongTin_GUI.map.remove(row);						
					}
					nhapThongTin_GUI.loadThongTin(doiVe_GUI.dsVeDatTam);
					
					for (int key: nhapThongTin_GUI.map.keySet()) {
						if (key > row) {
							nhapThongTin_GUI.map.put(key-1, nhapThongTin_GUI.map.get(key));
							model.setValueAt(nhapThongTin_GUI.map.get(key).getTenKH(), key-1, 1);
							nhapThongTin_GUI.map.remove(key);
						} else {
							model.setValueAt(nhapThongTin_GUI.map.get(key).getTenKH(), key, 1);
						}
					}
					
					if (doiVe_GUI.toaCu != null) {
						Toa_JPanel pToa;
						if (doiVe_GUI.toaCu.getLoaiToa().equals("VIP")) {
							pToa = new Toa_JPanel("", 2);
						} else if (doiVe_GUI.toaCu.getLoaiToa().equals("Giường nằm")) {
							pToa = new Toa_JPanel("", 3);
						} else {
							pToa = new Toa_JPanel("", 4);
						}
						pToa.setBounds(doiVe_GUI.boundsPanelToa);
						// su kien
						doiVe_GUI.loadGhe(pToa, doiVe_GUI.toaCu);
					}
		
					
					// Tạo panel Vé
					doiVe_GUI.jp_VeMua.removeAll();
					for (Ve ve : doiVe_GUI.dsVeDatTam) {
						Ve_JPanel pVe = new Ve_JPanel(ve,doiVe_GUI.dsVeDatTam, doiVe_GUI.jp_VeMua);
						doiVe_GUI.jp_VeMua.add(pVe);
					}

				}
			}
		});
	}
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.table = table;
		return button;
	}

	@Override
	public Object getCellEditorValue() {
		return "";
	}

	@Override
	public boolean stopCellEditing() {
		return super.stopCellEditing();
	}

	@Override
	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}
