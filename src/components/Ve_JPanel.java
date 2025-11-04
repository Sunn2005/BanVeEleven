package components;

import javax.swing.JPanel;
import entity.Ve;
import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Ve_JPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lblTongTien; // Thêm label hiển thị tổng tiền
	/**
	 * Create the panel.
	 */
	public Ve_JPanel(Ve ve, ArrayList<Ve> dsVeDatTam, JPanel jp_VeMua) {
	    setBounds(0, 0, 244, 90); // Tăng chiều cao để chứa giá vé
	    setLayout(null);
	    setBorder(new LineBorder(new Color(221, 221, 221), 1));
	    
	    JPanel jp_ThongTinVe = new JPanel();
	    jp_ThongTinVe.setBounds(0, 0, 244, 90);
	    jp_ThongTinVe.setBackground(Color.WHITE);
	    add(jp_ThongTinVe);
	    jp_ThongTinVe.setLayout(null);
	    
	    JLabel lblNewLabel = new JLabel(ve.getChuyenTau().getMaTau() + ": " + 
	                                     ve.getGaDi().getTenGaRaw() + " - " + 
	                                     ve.getGaDen().getTenGaRaw());
	    lblNewLabel.setBounds(10, 5, 224, 13);
	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    jp_ThongTinVe.add(lblNewLabel);
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    JLabel lblNewLabel_1 = new JLabel("Ngày: " + ve.getNgayDi().format(formatter) + 
	                                      "   Lúc: " + ve.getGioDi().toString());
	    lblNewLabel_1.setBounds(10, 22, 224, 13);
	    lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    jp_ThongTinVe.add(lblNewLabel_1);
	    
	    JLabel lblNewLabel_1_1;
	    if (ve.getHang().equalsIgnoreCase("VIP")) {
	        lblNewLabel_1_1 = new JLabel("Hạng VIP Toa " + 
	                ve.getToa().getMaToa().substring(ve.getToa().getMaToa().length() - 2) + 
	                " Ghế số " + ve.getSoGhe().getSoGhe());
	    } else if (ve.getHang().equalsIgnoreCase("Giường nằm")) {
	        lblNewLabel_1_1 = new JLabel("Giường nằm Toa " + 
	                ve.getToa().getMaToa().substring(ve.getToa().getMaToa().length() - 2) + 
	                " Ghế số " + ve.getSoGhe().getSoGhe());
	    } else {
	        lblNewLabel_1_1 = new JLabel("Ghế mềm Toa " + 
	                ve.getToa().getMaToa().substring(ve.getToa().getMaToa().length() - 2) + 
	                " Ghế số " + ve.getSoGhe().getSoGhe());
	    }
	    lblNewLabel_1_1.setBounds(10, 39, 224, 13);
	    lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    jp_ThongTinVe.add(lblNewLabel_1_1);
	    
	    // Thêm label hiển thị giá vé
	    JLabel lblGiaVe = new JLabel("Giá: " + dinhDangTienTe(ve.tinhGiaVe()));
	    lblGiaVe.setBounds(10, 56, 224, 13);
	    lblGiaVe.setFont(new Font("Tahoma", Font.BOLD, 12));
	    lblGiaVe.setForeground(new Color(51, 102, 153)); // Màu xanh dương
	    jp_ThongTinVe.add(lblGiaVe);
	    
	    // Nếu có khuyến mãi, hiển thị
	    if (ve.getKhuyenMai() != null && !ve.getKhuyenMai().equalsIgnoreCase("Người lớn")) {
	        JLabel lblKhuyenMai = new JLabel("(" + ve.getKhuyenMai() + ")");
	        lblKhuyenMai.setBounds(10, 72, 224, 13);
	        lblKhuyenMai.setFont(new Font("Tahoma", Font.ITALIC, 10));
	        lblKhuyenMai.setForeground(Color.GRAY);
	        jp_ThongTinVe.add(lblKhuyenMai);
	    }
	}
	
	// Phương thức định dạng tiền tệ
	private String dinhDangTienTe(double soTien) {
	    NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	    return formatter.format(soTien);
	}
}