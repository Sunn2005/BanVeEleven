package gui;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
//import javax.swing.event.TableModelEvent;
//import javax.swing.event.TableModelListener;
import javax.swing.JScrollPane;
import java.awt.Image;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import components.ConTent_JPanel;
import components.RoundedButton;
import components.RoundedTextField;
import dao.KhachHang_DAO;
import entity.KhachHang;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TraCuuKhachHang_GUI extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtCCCD;
	private JTextField txtMaKH;
	private JTextField txtTen;
	private JTextField txtEmail;
	private JTextField txtSDT;
	private JTable table;
	private DefaultTableModel model;
	private JButton btn_Tim;
	private KhachHang_DAO dskh = new KhachHang_DAO();
	private TableRowSorter<TableModel> sorter;

	public TraCuuKhachHang_GUI(TrangChu_GUI trangChu) {

		setBackground(SystemColor.text);
		setForeground(new Color(255, 255, 255));
		setBounds(0, 170, 1460, 570);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 10, 1420, 560);
		add(panel);
		panel.setLayout(null);

		JPanel jp_QL = new JPanel();
		jp_QL.setBounds(10, 10, 124, 28);
		panel.add(jp_QL);
		jp_QL.setLayout(null);

		JLabel lb_quaylai = new JLabel("Quay lại");
		lb_quaylai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lb_quaylai.setBounds(45, 0, 69, 27);
		jp_QL.add(lb_quaylai);

		ImageIcon goBackIcon = new ImageIcon(getClass().getResource("/img/9054423_bx_arrow_back_icon.png"));
		Image scaledGoBack = goBackIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JLabel goBackIconLabel = new JLabel(new ImageIcon(scaledGoBack));
		goBackIconLabel.setBounds(10, 0, 39, 27);
		goBackIconLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ConTent_JPanel jpct = new ConTent_JPanel();
				trangChu.content.removeAll();
				trangChu.content.add(jpct);
				trangChu.content.revalidate();
				trangChu.content.repaint();
			}
		});
		jp_QL.add(goBackIconLabel);

		JPanel panelThongTinKhachHang = new JPanel();
		panelThongTinKhachHang.setBounds(10, 48, 337, 502);
		panelThongTinKhachHang.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panelThongTinKhachHang);
		panelThongTinKhachHang.setLayout(null);

		txtMaKH = new RoundedTextField(15);
		txtMaKH.setForeground(Color.BLACK);
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaKH.setBounds(35, 86, 274, 30);
		panelThongTinKhachHang.add(txtMaKH);
		txtMaKH.setColumns(10);

		txtTen = new RoundedTextField(15);
		txtTen.setForeground(Color.BLACK);
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTen.setColumns(10);
		txtTen.setBounds(35, 165, 274, 30);
		panelThongTinKhachHang.add(txtTen);

		txtEmail = new RoundedTextField(15);
		txtEmail.setForeground(Color.BLACK);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(35, 234, 274, 30);
		panelThongTinKhachHang.add(txtEmail);

		txtSDT = new RoundedTextField(15);
		txtSDT.setForeground(Color.BLACK);
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDT.setColumns(10);
		txtSDT.setBounds(35, 313, 274, 36);
		panelThongTinKhachHang.add(txtSDT);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 337, 36);

		panelThongTinKhachHang.add(panel_2);
		panel_2.setBackground(new Color(51, 102, 153));
		panel_2.setLayout(null);

		JLabel lb_Tim = new JLabel("Tìm kiếm");
		lb_Tim.setForeground(new Color(255, 255, 255));
		lb_Tim.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_Tim.setBounds(125, 0, 95, 36);
		panel_2.add(lb_Tim);

		txtCCCD = new RoundedTextField(15);
		txtCCCD.setForeground(Color.BLACK);
		txtCCCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(35, 392, 274, 36);
		panelThongTinKhachHang.add(txtCCCD);

		JLabel lblCanCuuCongDan = new JLabel("Căn cước công dân:");
		lblCanCuuCongDan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCanCuuCongDan.setBounds(35, 345, 150, 53);
		panelThongTinKhachHang.add(lblCanCuuCongDan);

		JLabel lblMaKhachHang = new JLabel("Mã khách hàng:");
		lblMaKhachHang.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaKhachHang.setBounds(35, 46, 150, 30);
		panelThongTinKhachHang.add(lblMaKhachHang);

		JLabel lblTen = new JLabel("Tên khách hàng:");
		lblTen.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTen.setBounds(35, 126, 126, 29);
		panelThongTinKhachHang.add(lblTen);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(35, 205, 100, 19);
		panelThongTinKhachHang.add(lblEmail);

		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSDT.setBounds(35, 284, 129, 19);
		panelThongTinKhachHang.add(lblSDT);

		JScrollPane scrollPane = new JScrollPane();

		table = new JTable();
		model = new DefaultTableModel(new Object[][] {}, new String[] { "STT", "Mã khách hàng", "Tên khách hàng",
				"Email", "Số điện thoại", "Căn cước công dân" });
		sorter = new TableRowSorter<>(model);

		table.setRowSorter(sorter);
		table.setModel(model);
		scrollPane.setViewportView(table);
		table.setRowHeight(25); // Set chiều cao hàng
		table.setBounds(347, 10, 1053, 517);
		scrollPane.setBounds(357, 10, 1053, 540);
		panel.add(scrollPane);

//		model.addTableModelListener(new TableModelListener() {
//			@Override
//			public void tableChanged(TableModelEvent e) {
//				int row = e.getFirstRow();
//				int column = e.getColumn();
//			}
//		});

//		btn_Tim = new JButton("Tìm");
		btn_Tim = new RoundedButton("Tìm", 15);
		btn_Tim.setForeground(new Color(255, 255, 255));
		btn_Tim.setBackground(new Color(51, 102, 153));

//		btn_Tim.setBackground(Color.decode("#0091D4"));
		btn_Tim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_Tim.setBounds(122, 449, 100, 30);
		panelThongTinKhachHang.add(btn_Tim);

		btn_Tim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btn_Tim.addActionListener(this);
		datatoTable();
	}

	public void datatoTable() {
		dskh.reset();
		ArrayList<KhachHang> list = dskh.docTuBang();
		model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // Xóa tất cả hàng trong bảng
		int stt = 1; // Biến đếm bắt đầu từ 1 cho STT

		for (KhachHang kh : list) {
			model.addRow(new Object[] { stt++, kh.getMaKH(), kh.getTenKH(),kh.getEmail(),   kh.getSdt(),kh.getCccd()

			});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		dskh.reset();
		if (o.equals(btn_Tim)) {
			if (txtMaKH.getText() != null) {
				filterRows();

			}
			if (txtTen.getText() != null) {
				filterRows();

			}

			if (txtEmail.getText() != null) {
				filterRows();

			}
			if (txtSDT.getText() != null) {
				filterRows();

			}
			if (txtCCCD.getText() != null) {
				filterRows();

			}
		}
	}

	private void filterRows() {
		ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();
		String maKH = txtMaKH.getText().trim();
		String hoTen = txtTen.getText().trim();
		String email = txtEmail.getText().trim();
		String sdt = txtSDT.getText().trim();
		String cccd = txtCCCD.getText().trim();
		// Lọc theo các điều kiện
		if (!maKH.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + maKH, 1));
		}
		if (!hoTen.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + hoTen, 2));
		}
		if (!email.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + email, 3));
		}
		if (!sdt.isEmpty()) {
		    filters.add(RowFilter.regexFilter("(?i)" + sdt, 4));
		}
		if (!cccd.isEmpty()) {
		    filters.add(RowFilter.regexFilter("(?i)" + cccd, 5));
		}
		// Cập nhật bộ lọc
		if (filters.isEmpty()) {
			sorter.setRowFilter(null); // Nếu không có bộ lọc nào, xóa bộ lọc
		} else {
			sorter.setRowFilter(RowFilter.andFilter(filters));
		}
	}
	
	
}
