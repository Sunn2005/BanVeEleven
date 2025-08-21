package components;

import javax.swing.JPanel;

import entity.Ve;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JLabel;

public class Ve_JPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Ve_JPanel(Ve ve, ArrayList<Ve> dsVeDatTam, JPanel jp_VeMua) {
	    setBounds(0, 0, 244, 73);
	    setLayout(null);
	    
	    JPanel jp_ThongTinVe = new JPanel();
	    jp_ThongTinVe.setBounds(0, 0, 234, 73);
	    add(jp_ThongTinVe);
	    jp_ThongTinVe.setLayout(null);
	    
	    JLabel lblNewLabel = new JLabel(ve.getChuyenTau().getMaTau() + ": Từ " + ve.getGaDi().getDiaChi() + " đến " + ve.getGaDen().getDiaChi());
	    lblNewLabel.setBounds(10, 10, 181, 13);
	    jp_ThongTinVe.add(lblNewLabel);
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    JLabel lblNewLabel_1 = new JLabel("Ngày: " + ve.getNgayDi().format(formatter) + "   Lúc: " + ve.getGioDi().toString());
	    lblNewLabel_1.setBounds(10, 27, 159, 13);
	    jp_ThongTinVe.add(lblNewLabel_1);
	    
	    JLabel lblNewLabel_1_1;
	    if (ve.getHang().equalsIgnoreCase("VIP")) {
	        lblNewLabel_1_1 = new JLabel("Hạng VIP Toa " + ve.getToa().getMaToa().substring(ve.getToa().getMaToa().length() - 2) + " Ghế số " + ve.getSoGhe().getSoGhe());
	    } else if (ve.getHang().equalsIgnoreCase("Giường nằm")) {
	        lblNewLabel_1_1 = new JLabel("Giường nằm Toa " + ve.getToa().getMaToa().substring(ve.getToa().getMaToa().length() - 2) + " Ghế số " + ve.getSoGhe().getSoGhe());
	    } else {
	        lblNewLabel_1_1 = new JLabel("Ghế mềm Toa " + ve.getToa().getMaToa().substring(ve.getToa().getMaToa().length() - 2) + " Ghế số " + ve.getSoGhe().getSoGhe());
	    }
	    lblNewLabel_1_1.setBounds(10, 44, 181, 13);
	    jp_ThongTinVe.add(lblNewLabel_1_1);
	}


}
