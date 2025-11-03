package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import components.ButtonEditor;
import components.ButtonRenderer;
import components.ComboBoxRenderer;
import components.RoundedButton;
import components.TextAreaRenderer;
import dao.Ga_DAO;
import dao.KhachHang_DAO;
import entity.KhachHang;
import entity.Ve;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultCellEditor;
import java.awt.BorderLayout;

public class BanVeNhapThongTin_Gui extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel goBackIconLabel;
	private JLabel lbl_quayLai;
	private JPanel jp_KHMV;
	private JTabbedPane tabbedPane;
	private JTextField textField_Ten_KHMV;
	private JTextField textField_Email_KHMV;
	private JTextField textField_SDT_KHMV;
	private JTextField textField_CCCD_KHMV;
	private JPanel jp_KHSDV;
	private JTextField textField_Ten_KHSDV;
	private JTextField textField_Email_KHSDV;
	private JTextField textField_SDT_KHSDV;
	private JTextField textField_CCCD_KHSDV;
	public JTable table;
	private JPanel jp_Table;
	public Map<Integer, KhachHang> map = new HashMap<>();
	public KhachHang khachHangMua;
	
	private Ga_DAO ga_DAO = new Ga_DAO();
	private KhachHang_DAO dskh = new KhachHang_DAO();
	private ArrayList<KhachHang> list = dskh.docTuBang();
	private JButton bt_Chuyen;
	private JButton bt_ThanhToan_KHSDV, bt_XacNhan_KHSDV;
	private JButton btn_XacNhan; // NEW: nút thanh toán ở tab KHMV
	public BanVe_GUI banVe_GUI;
	public DoiVe_GUI doiVe_GUI;
	
	/**
	 * @wbp.parser.constructor
	 */
	public BanVeNhapThongTin_Gui(BanVe_GUI banVe_GUI, TrangChu_GUI trangChu) {
		setBackground(SystemColor.window);
		setForeground(new Color(255, 255, 255));
		setBounds(0, 170, 1460, 570);
		setLayout(null);
		
		JPanel jp_quayLai = new JPanel();
	    jp_quayLai.setBackground(SystemColor.text);
		jp_quayLai.setBounds(10, 4, 94, 47);
		add(jp_quayLai);
		
		//Icon Quay lại
		ImageIcon goBackIcon = new ImageIcon(getClass().getResource("/img/9054423_bx_arrow_back_icon.png"));
		Image scaledGoBack = goBackIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		jp_quayLai.setLayout(new BorderLayout(0, 0));
		goBackIconLabel = new JLabel(new ImageIcon(scaledGoBack));
		jp_quayLai.add(goBackIconLabel, BorderLayout.CENTER);
		goBackIconLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				goBackIconLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				goBackIconLabel.setCursor(Cursor.getDefaultCursor());
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				trangChu.content.removeAll();
				trangChu.content.add(banVe_GUI);
				trangChu.content.revalidate();
				trangChu.content.repaint();
			}
		});
		
		//JLabel Quay lại
		lbl_quayLai = new JLabel("Quay lại");
		lbl_quayLai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_quayLai.setHorizontalAlignment(SwingConstants.CENTER);
		jp_quayLai.add(lbl_quayLai, BorderLayout.EAST);
		
		lbl_quayLai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_quayLai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lbl_quayLai.setCursor(Cursor.getDefaultCursor());
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				trangChu.content.removeAll();
				trangChu.content.add(banVe_GUI);
				trangChu.content.revalidate();
				trangChu.content.repaint();
			}
		});
//
		// Khởi tạo tabbedPane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(46, 61, 1373, 151);
		add(tabbedPane);
		tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 15));
//
		jp_KHMV = new JPanel();
		jp_KHMV.setBackground(SystemColor.textHighlightText);
		tabbedPane.addTab("Khách hàng mua vé", null, jp_KHMV, null);
		jp_KHMV.setLayout(null);

		JPanel jp_content_KHMV = new JPanel();
		jp_content_KHMV.setLayout(null);
		jp_content_KHMV.setBounds(10, 10, 1345, 98);
		jp_KHMV.add(jp_content_KHMV);

		JLabel lb_Ten_KHMV = new JLabel("Họ và tên");
		lb_Ten_KHMV.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_Ten_KHMV.setBounds(75, 15, 96, 25);
		jp_content_KHMV.add(lb_Ten_KHMV);

		JLabel lb_Email_KHMV = new JLabel("Email");
		lb_Email_KHMV.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_Email_KHMV.setBounds(75, 56, 96, 22);
		jp_content_KHMV.add(lb_Email_KHMV);

		textField_Ten_KHMV = new JTextField();
		textField_Ten_KHMV.setColumns(10);
		textField_Ten_KHMV.setBounds(224, 17, 264, 25);
		jp_content_KHMV.add(textField_Ten_KHMV);

		textField_Email_KHMV = new JTextField();
		textField_Email_KHMV.setColumns(10);
		textField_Email_KHMV.setBounds(224, 57, 264, 25);
		jp_content_KHMV.add(textField_Email_KHMV);

		JLabel lb_SDT_KHMV = new JLabel("Số điện thoại");
		lb_SDT_KHMV.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_SDT_KHMV.setBounds(601, 12, 143, 25);
		jp_content_KHMV.add(lb_SDT_KHMV);

		JLabel lb_CCCD_KHMV = new JLabel("CCCD/ Hộ chiếu");
		lb_CCCD_KHMV.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_CCCD_KHMV.setBounds(601, 53, 143, 25);
		jp_content_KHMV.add(lb_CCCD_KHMV);

		textField_SDT_KHMV = new JTextField();
		textField_SDT_KHMV.setColumns(10);
		textField_SDT_KHMV.setBounds(814, 12, 264, 25);
		jp_content_KHMV.add(textField_SDT_KHMV);

		textField_CCCD_KHMV = new JTextField();
		textField_CCCD_KHMV.setColumns(10);
		textField_CCCD_KHMV.setBounds(814, 50, 264, 25);
		jp_content_KHMV.add(textField_CCCD_KHMV);

//		bt_Chuyen = new RoundedButton("Chuyển", 15);
//		bt_Chuyen.setForeground(Color.WHITE);
//		bt_Chuyen.setFont(new Font("Tahoma", Font.BOLD, 16));
//		bt_Chuyen.setBackground(new Color(51, 102, 153));
//		bt_Chuyen.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				tabbedPane.setSelectedIndex(1);
//				textField_Ten_KHSDV.setText(textField_Ten_KHMV.getText());
//				textField_Email_KHSDV.setText(textField_Email_KHMV.getText());
//				textField_SDT_KHSDV.setText(textField_SDT_KHMV.getText());
//				textField_CCCD_KHSDV.setText(textField_CCCD_KHMV.getText());
//			}
//		});
//		bt_Chuyen.setBounds(1020, 24, 120, 40);
//		jp_content_KHMV.add(bt_Chuyen);
//
		// NEW: Thêm nút "Thanh toán" vào tab KHMV
		btn_XacNhan = new RoundedButton("Xác nhận", 15);
		btn_XacNhan.setForeground(Color.WHITE);
		btn_XacNhan.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_XacNhan.setBackground(new Color(51, 102, 153));
		btn_XacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Validate thông tin khách hàng mua vé
				if (!isValidKHMV()) {
					return;
				}

				// Kiểm tra có vé không
				if (banVe_GUI == null || banVe_GUI.dsVeDatTam.size() == 0) {
					JOptionPane.showMessageDialog(null, "Chưa có vé được đặt");
					return;
				}

				// Tạo hoặc lấy khách hàng mua vé
				KhachHang khachHangExist = list.stream()
					.filter(kh -> textField_SDT_KHMV.getText().equals(kh.getSdt()))
					.findFirst()
					.orElse(null);

				KhachHang buyer = new KhachHang(
					khachHangExist != null ? khachHangExist.getMaKH() : generateMaKH(),
					textField_Ten_KHMV.getText(),
					textField_Email_KHMV.getText(),
					textField_SDT_KHMV.getText(),
					textField_CCCD_KHMV.getText()
				);

				// Cập nhật tất cả các hàng trong bảng với thông tin khách hàng mua vé
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				for (int i = 0; i < banVe_GUI.dsVeDatTam.size(); i++) {
					map.put(i, buyer);
					banVe_GUI.dsVeDatTam.get(i).setKhuyenMai("Người lớn"); // Mặc định

					// Cập nhật bảng
					model.setValueAt(buyer.getTenKH(), i, 1); // Tên khách hàng
					model.setValueAt("Người lớn", i, 2); // Đối tượng
					float basePrice = (float) model.getValueAt(i, 4);
					model.setValueAt(0f, i, 5); // Giảm đối tượng
					model.setValueAt(basePrice, i, 6); // Thành tiền
				}

				JOptionPane.showMessageDialog(null, "Đã cập nhật thông tin khách hàng cho vé!");
			}
		});
		btn_XacNhan.setBounds(1160, 24, 120, 40);
		jp_content_KHMV.add(btn_XacNhan);

		
//		// Tab khách hàng sử dụng vé
//		jp_KHSDV = new JPanel();
//		jp_KHSDV.setBackground(SystemColor.textHighlightText);
//		tabbedPane.addTab("Khách hàng sử dụng vé", null, jp_KHSDV, null);
//		jp_KHSDV.setLayout(null);
//
//		JPanel jp_content_KHSDV = new JPanel();
//		jp_content_KHSDV.setLayout(null);
//		jp_content_KHSDV.setBounds(10, 10, 1345, 98);
//		jp_KHSDV.add(jp_content_KHSDV);
//
//		JLabel lb_Ten_KHSDV = new JLabel("Họ và tên");
//		lb_Ten_KHSDV.setFont(new Font("Tahoma", Font.BOLD, 16));
//		lb_Ten_KHSDV.setBounds(75, 15, 96, 25);
//		jp_content_KHSDV.add(lb_Ten_KHSDV);
//
//		JLabel lb_Email_KHSDV = new JLabel("Email");
//		lb_Email_KHSDV.setFont(new Font("Tahoma", Font.BOLD, 16));
//		lb_Email_KHSDV.setBounds(75, 56, 96, 22);
//		jp_content_KHSDV.add(lb_Email_KHSDV);
//
//		textField_Ten_KHSDV = new JTextField();
//		textField_Ten_KHSDV.setColumns(10);
//		textField_Ten_KHSDV.setBounds(224, 17, 264, 25);
//		jp_content_KHSDV.add(textField_Ten_KHSDV);
//
//		textField_Email_KHSDV = new JTextField();
//		textField_Email_KHSDV.setColumns(10);
//		textField_Email_KHSDV.setBounds(224, 57, 264, 25);
//		jp_content_KHSDV.add(textField_Email_KHSDV);
//
//		JLabel lb_SDT_KHSDV = new JLabel("Số điện thoại");
//		lb_SDT_KHSDV.setFont(new Font("Tahoma", Font.BOLD, 16));
//		lb_SDT_KHSDV.setBounds(601, 12, 143, 25);
//		jp_content_KHSDV.add(lb_SDT_KHSDV);
//
//		JLabel lb_CCCD_KHSDV = new JLabel("CCCD/ Hộ chiếu");
//		lb_CCCD_KHSDV.setFont(new Font("Tahoma", Font.BOLD, 16));
//		lb_CCCD_KHSDV.setBounds(601, 53, 143, 25);
//		jp_content_KHSDV.add(lb_CCCD_KHSDV);
//
//		textField_SDT_KHSDV = new JTextField();
//		textField_SDT_KHSDV.setColumns(10);
//		textField_SDT_KHSDV.setBounds(814, 12, 264, 25);
//		jp_content_KHSDV.add(textField_SDT_KHSDV);
//
//		textField_CCCD_KHSDV = new JTextField();
//		textField_CCCD_KHSDV.setColumns(10);
//		textField_CCCD_KHSDV.setBounds(814, 50, 264, 25);
//		jp_content_KHSDV.add(textField_CCCD_KHSDV);
//
//		bt_XacNhan_KHSDV = new RoundedButton("Nhập", 10);
//		bt_XacNhan_KHSDV.setForeground(Color.WHITE);
//		bt_XacNhan_KHSDV.setBackground(new Color(51, 102, 153));
//		bt_XacNhan_KHSDV.setBounds(1245, 303, 118, 25);
//		bt_XacNhan_KHSDV.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int selectedRow = table.getSelectedRow();
//                if (selectedRow != -1) {
//                	if (isValidTxt()) {
//                		DefaultTableModel model = (DefaultTableModel) table.getModel();
//                		KhachHang khachHangExist = list.stream().filter(kh -> textField_SDT_KHSDV.getText().equals(kh.getSdt())).findFirst().orElse(null);
//                		if (khachHangExist!=null) {
//                			map.put(selectedRow, khachHangExist);
//                		} else {
//                			KhachHang khachHang = new KhachHang("KH0000", textField_Ten_KHSDV.getText(), textField_Email_KHSDV.getText(), textField_SDT_KHSDV.getText(), textField_CCCD_KHSDV.getText());
//                			map.put(selectedRow, khachHang);
//                		}
//                		doiVe_GUI.dsVeDatTam.get(selectedRow).setKhuyenMai(table.getValueAt(selectedRow, 2).toString());
//                		model.setValueAt(textField_Ten_KHSDV.getText(), selectedRow, 1);
//                		textField_Ten_KHSDV.setText("");
//                		textField_SDT_KHSDV.setText("");
//                		textField_Email_KHSDV.setText("");
//                		textField_CCCD_KHSDV.setText("");
//                	}
//                }
//			}
//		});
//		bt_XacNhan_KHSDV.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		bt_XacNhan_KHSDV.setBounds(1160, 24, 120, 40);
//		jp_content_KHSDV.add(bt_XacNhan_KHSDV);
//
		//JP table
		jp_Table = new JPanel();
		jp_Table.setBounds(46, 222, 1373, 338);
		add(jp_Table);
		jp_Table.setLayout(null);
		
		bt_ThanhToan_KHSDV = new RoundedButton("Thanh toán", 10);
		bt_ThanhToan_KHSDV.setForeground(Color.WHITE);
		bt_ThanhToan_KHSDV.setBackground(new Color(51, 102, 153));
		bt_ThanhToan_KHSDV.setBounds(1245, 303, 118, 25);
		jp_Table.add(bt_ThanhToan_KHSDV);
		bt_ThanhToan_KHSDV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bt_ThanhToan_KHSDV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (map.size() != banVe_GUI.dsVeDatTam.size()) {
					JOptionPane.showMessageDialog(null, "Chưa nhập đủ khách hàng sử dụng vé");
					return;
				}
				
				KhachHang khachHang = list.stream().filter(kh -> textField_SDT_KHMV.getText().equals(kh.getSdt())).findFirst().orElse(null);
				BanVeNhapThongTin_Gui.this.khachHangMua = new KhachHang(khachHang != null?khachHang.getMaKH():generateMaKH(), textField_Ten_KHMV.getText(), textField_Email_KHMV.getText(), textField_SDT_KHMV.getText(), textField_CCCD_KHMV.getText());
				BanVeThanhToan_GUI banVeThanToan_GUI= new BanVeThanhToan_GUI(BanVeNhapThongTin_Gui.this, trangChu, banVe_GUI);

				//Gán giá trị khuyễn mãi cho các Ve trong ds đặt tạm
				
				trangChu.content.removeAll();
				trangChu.content.add(banVeThanToan_GUI);
				trangChu.content.revalidate();
				trangChu.content.repaint();
			}
		});
		
		JScrollPane scrollPane_KHSDV = new JScrollPane();
		scrollPane_KHSDV.setBounds(10, 5, 1353, 289);
		jp_Table.add(scrollPane_KHSDV);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {
					KhachHang khachHang = map.get(row);
					if (khachHang != null) {
						textField_Ten_KHSDV.setText(khachHang.getTenKH());
						textField_SDT_KHSDV.setText(khachHang.getSdt());
						textField_Email_KHSDV.setText(khachHang.getEmail());
						textField_CCCD_KHSDV.setText(khachHang.getCccd());
					}
				}
			}
		});
		scrollPane_KHSDV.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "Khách hàng", "Đối tượng", "Thông tin chỗ", "Giá", "Giảm đối tượng", "Thành tiền", "Xóa"
			}
		));
		
		// Điều chỉnh bảng
	    table.setRowHeight(50);
	    table.getColumnModel().getColumn(3).setPreferredWidth(150);
	    table.getColumnModel().getColumn(7).setPreferredWidth(30);
	    
		// Đặt renderer và editor cho nút xóa
        table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JCheckBox(), banVe_GUI, BanVeNhapThongTin_Gui.this));
        
        // Tạo JComboBox cho cột "trạng thái"
        JComboBox<String> comboBoxKhuyenMai = new JComboBox<>();
        comboBoxKhuyenMai.setPreferredSize(new Dimension(20, 20));
        comboBoxKhuyenMai.addItem("Người lớn");
        comboBoxKhuyenMai.addItem("Trẻ em dưới 6 tuổi");
        comboBoxKhuyenMai.addItem("Trẻ em từ 6 đến 10 tuổi");
        comboBoxKhuyenMai.addItem("Sinh viên");
        comboBoxKhuyenMai.addItem("Người lớn tuổi");
        
        // Thêm ActionListener cho comboBox để cập nhật giá trị trong cột 5
        comboBoxKhuyenMai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedItemIndex = comboBoxKhuyenMai.getSelectedIndex();
                    banVe_GUI.dsVeDatTam.get(selectedRow).setKhuyenMai(table.getValueAt(selectedRow, 2).toString());
                    float baseValue = (float) model.getValueAt(selectedRow, 4); // Lấy giá trị cột 4

                    double newValue;
                    switch (selectedItemIndex) {
                        case 0: // Người lớn
                            newValue = 0;
                            break;
                        case 1: // Trẻ em dưới 6 tuổi
                            newValue = baseValue * 1;
                            break;
                        case 2: // Trẻ em từ 6 đến 10 tuổi
                            newValue = baseValue * 0.25;
                            break;
                        case 3: // Sinh viên
                            newValue = baseValue * 0.1;
                            break;
                        case 4: // Người lớn tuổi
                            newValue = baseValue * 0.15;
                            break;
                        default:
                            newValue = baseValue; // Trường hợp không hợp lệ
                            break;
                    }

                    model.setValueAt(newValue, selectedRow, 5); // Cập nhật cột 5 với giá trị mới
                    model.setValueAt((float) model.getValueAt(selectedRow, 4) - newValue, selectedRow, 6); // Cập nhật cột 6 với giá trị mới
                }
            }
        });
        
        TableColumn khuyenMaiColumn = table.getColumnModel().getColumn(2);
        khuyenMaiColumn.setCellEditor(new DefaultCellEditor(comboBoxKhuyenMai));
        // Thiết lập renderer cho cột để hiển thị JComboBox
        khuyenMaiColumn.setCellRenderer(new ComboBoxRenderer(comboBoxKhuyenMai));        
        table.getColumnModel().getColumn(3).setCellRenderer(new TextAreaRenderer());

        chonSdt(textField_SDT_KHMV,textField_Ten_KHMV,textField_Email_KHMV, textField_CCCD_KHMV);
//        chonSdt(textField_SDT_KHSDV,textField_Ten_KHSDV,textField_Email_KHSDV, textField_CCCD_KHSDV);

        loadThongTin(banVe_GUI.dsVeDatTam);
	}
	
	public BanVeNhapThongTin_Gui(DoiVe_GUI doiVe_GUI, TrangChu_GUI trangChu) {
		// TODO Auto-generated constructor stub
		setBackground(SystemColor.window);
		setForeground(new Color(255, 255, 255));
		setBounds(0, 170, 1460, 570);
		setLayout(null);
		
		JPanel jp_quayLai = new JPanel();
	    jp_quayLai.setBackground(SystemColor.text);
		jp_quayLai.setBounds(10, 4, 94, 47);
		add(jp_quayLai);
		
		//Icon Quay lại
		ImageIcon goBackIcon = new ImageIcon(getClass().getResource("/img/9054423_bx_arrow_back_icon.png"));
		Image scaledGoBack = goBackIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		jp_quayLai.setLayout(new BorderLayout(0, 0));
		goBackIconLabel = new JLabel(new ImageIcon(scaledGoBack));
		jp_quayLai.add(goBackIconLabel, BorderLayout.CENTER);
		goBackIconLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				goBackIconLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				goBackIconLabel.setCursor(Cursor.getDefaultCursor());
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				trangChu.content.removeAll();
				trangChu.content.add(doiVe_GUI);
				trangChu.content.revalidate();
				trangChu.content.repaint();
			}
		});
		
		//JLabel Quay lại
		lbl_quayLai = new JLabel("Quay lại");
		lbl_quayLai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_quayLai.setHorizontalAlignment(SwingConstants.CENTER);
		jp_quayLai.add(lbl_quayLai, BorderLayout.EAST);
		
		lbl_quayLai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_quayLai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lbl_quayLai.setCursor(Cursor.getDefaultCursor());
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				trangChu.content.removeAll();
				trangChu.content.add(doiVe_GUI);
				trangChu.content.revalidate();
				trangChu.content.repaint();
			}
		});
		
		// Khởi tạo tabbedPane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(46, 61, 1373, 151);
		add(tabbedPane);
		tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 15));
				
		jp_KHMV = new JPanel();
		jp_KHMV.setBackground(SystemColor.textHighlightText);
		tabbedPane.addTab("Khách hàng mua vé", null, jp_KHMV, null);
		jp_KHMV.setLayout(null);
		
		JPanel jp_content_KHMV = new JPanel();
		jp_content_KHMV.setLayout(null);
		jp_content_KHMV.setBounds(10, 10, 1345, 98);
		jp_KHMV.add(jp_content_KHMV);
		
		JLabel lb_Ten_KHMV = new JLabel("Họ và tên");
		lb_Ten_KHMV.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_Ten_KHMV.setBounds(75, 15, 96, 25);
		jp_content_KHMV.add(lb_Ten_KHMV);
		
		JLabel lb_Email_KHMV = new JLabel("Email");
		lb_Email_KHMV.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_Email_KHMV.setBounds(75, 56, 96, 22);
		jp_content_KHMV.add(lb_Email_KHMV);
		
		textField_Ten_KHMV = new JTextField();
		textField_Ten_KHMV.setColumns(10);
		textField_Ten_KHMV.setBounds(224, 17, 264, 25);
		jp_content_KHMV.add(textField_Ten_KHMV);
		
		textField_Email_KHMV = new JTextField();
		textField_Email_KHMV.setColumns(10);
		textField_Email_KHMV.setBounds(224, 57, 264, 25);
		jp_content_KHMV.add(textField_Email_KHMV);
		
		JLabel lb_SDT_KHMV = new JLabel("Số điện thoại");
		lb_SDT_KHMV.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_SDT_KHMV.setBounds(601, 12, 143, 25);
		jp_content_KHMV.add(lb_SDT_KHMV);

		JLabel lb_CCCD_KHMV = new JLabel("CCCD/ Hộ chiếu");
		lb_CCCD_KHMV.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_CCCD_KHMV.setBounds(601, 53, 143, 25);
		jp_content_KHMV.add(lb_CCCD_KHMV);

		textField_SDT_KHMV = new JTextField();
		textField_SDT_KHMV.setColumns(10);
		textField_SDT_KHMV.setBounds(814, 12, 264, 25);
		jp_content_KHMV.add(textField_SDT_KHMV);

		textField_CCCD_KHMV = new JTextField();
		textField_CCCD_KHMV.setColumns(10);
		textField_CCCD_KHMV.setBounds(814, 50, 264, 25);
		jp_content_KHMV.add(textField_CCCD_KHMV);

		bt_Chuyen = new RoundedButton("Chuyển", 15);
		bt_Chuyen.setForeground(Color.WHITE);
		bt_Chuyen.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_Chuyen.setBackground(new Color(51, 102, 153));
		bt_Chuyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
				textField_Ten_KHSDV.setText(textField_Ten_KHMV.getText());
				textField_Email_KHSDV.setText(textField_Email_KHMV.getText());
				textField_SDT_KHSDV.setText(textField_SDT_KHMV.getText());
				textField_CCCD_KHSDV.setText(textField_CCCD_KHMV.getText());
			}
		});
		bt_Chuyen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bt_Chuyen.setBounds(1160, 24, 120, 40);
		jp_content_KHMV.add(bt_Chuyen);
		
		// NEW: Thêm nút "Thanh toán" vào tab KHMV
		btn_XacNhan = new RoundedButton("Thanh toán", 15);
		btn_XacNhan.setForeground(Color.WHITE);
		btn_XacNhan.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_XacNhan.setBackground(new Color(51, 102, 153));
		btn_XacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Validate thông tin khách hàng mua vé
				if (!isValidKHMV()) {
					return;
				}

				// Kiểm tra có vé không
				if (banVe_GUI == null || banVe_GUI.dsVeDatTam.size() == 0) {
					JOptionPane.showMessageDialog(null, "Chưa có vé được đặt");
					return;
				}

				// Tạo hoặc lấy khách hàng mua vé
				KhachHang khachHangExist = list.stream()
					.filter(kh -> textField_SDT_KHMV.getText().equals(kh.getSdt()))
					.findFirst()
					.orElse(null);

				KhachHang buyer = new KhachHang(
					khachHangExist != null ? khachHangExist.getMaKH() : generateMaKH(),
					textField_Ten_KHMV.getText(),
					textField_Email_KHMV.getText(),
					textField_SDT_KHMV.getText(),
					textField_CCCD_KHMV.getText()
				);

				// Cập nhật tất cả các hàng trong bảng với thông tin khách hàng mua vé
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				for (int i = 0; i < banVe_GUI.dsVeDatTam.size(); i++) {
					map.put(i, buyer);
					banVe_GUI.dsVeDatTam.get(i).setKhuyenMai("Người lớn"); // Mặc định

					// Cập nhật bảng
					model.setValueAt(buyer.getTenKH(), i, 1); // Tên khách hàng
					model.setValueAt("Người lớn", i, 2); // Đối tượng
					float basePrice = (float) model.getValueAt(i, 4);
					model.setValueAt(0f, i, 5); // Giảm đối tượng
					model.setValueAt(basePrice, i, 6); // Thành tiền
				}

				JOptionPane.showMessageDialog(null, "Đã cập nhật thông tin khách hàng cho tất cả các vé!");
			}
		});
		btn_XacNhan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_XacNhan.setBounds(1160, 24, 120, 40);
		jp_content_KHMV.add(btn_XacNhan);

		// Tab khách hàng sử dụng vé
		jp_KHSDV = new JPanel();
		jp_KHSDV.setBackground(SystemColor.textHighlightText);
		tabbedPane.addTab("Khách hàng sử dụng vé", null, jp_KHSDV, null);
		jp_KHSDV.setLayout(null);
		
		JPanel jp_content_KHSDV = new JPanel();
		jp_content_KHSDV.setLayout(null);
		jp_content_KHSDV.setBounds(10, 10, 1345, 98);
		jp_KHSDV.add(jp_content_KHSDV);
		
		JLabel lb_Ten_KHSDV = new JLabel("Họ và tên");
		lb_Ten_KHSDV.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_Ten_KHSDV.setBounds(75, 15, 96, 25);
		jp_content_KHSDV.add(lb_Ten_KHSDV);
		
		JLabel lb_Email_KHSDV = new JLabel("Email");
		lb_Email_KHSDV.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_Email_KHSDV.setBounds(75, 56, 96, 22);
		jp_content_KHSDV.add(lb_Email_KHSDV);
		
		textField_Ten_KHSDV = new JTextField();
		textField_Ten_KHSDV.setColumns(10);
		textField_Ten_KHSDV.setBounds(224, 17, 264, 25);
		jp_content_KHSDV.add(textField_Ten_KHSDV);
		
		textField_Email_KHSDV = new JTextField();
		textField_Email_KHSDV.setColumns(10);
		textField_Email_KHSDV.setBounds(224, 57, 264, 25);
		jp_content_KHSDV.add(textField_Email_KHSDV);
		
		JLabel lb_SDT_KHSDV = new JLabel("Số điện thoại");
		lb_SDT_KHSDV.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_SDT_KHSDV.setBounds(601, 12, 143, 25);
		jp_content_KHSDV.add(lb_SDT_KHSDV);
		
		JLabel lb_CCCD_KHSDV = new JLabel("CCCD/ Hộ chiếu");
		lb_CCCD_KHSDV.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_CCCD_KHSDV.setBounds(601, 53, 143, 25);
		jp_content_KHSDV.add(lb_CCCD_KHSDV);
		
		textField_SDT_KHSDV = new JTextField();
		textField_SDT_KHSDV.setColumns(10);
		textField_SDT_KHSDV.setBounds(814, 12, 264, 25);
		jp_content_KHSDV.add(textField_SDT_KHSDV);
		
		textField_CCCD_KHSDV = new JTextField();
		textField_CCCD_KHSDV.setColumns(10);
		textField_CCCD_KHSDV.setBounds(814, 50, 264, 25);
		jp_content_KHSDV.add(textField_CCCD_KHSDV);
		
//		bt_XacNhan_KHSDV = new RoundedButton("Nhập", 10);
//		bt_XacNhan_KHSDV.setForeground(Color.WHITE);
//		bt_XacNhan_KHSDV.setBackground(new Color(51, 102, 153));
//		bt_XacNhan_KHSDV.setBounds(1245, 303, 118, 25);
//		bt_XacNhan_KHSDV.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int selectedRow = table.getSelectedRow();
//                if (selectedRow != -1) {
//                	if (isValidTxt()) {
//                		DefaultTableModel model = (DefaultTableModel) table.getModel();
//                		KhachHang khachHangExist = list.stream().filter(kh -> textField_SDT_KHSDV.getText().equals(kh.getSdt())).findFirst().orElse(null);
//                		if (khachHangExist!=null) {
//                			map.put(selectedRow, khachHangExist);
//                		} else {
//                			KhachHang khachHang = new KhachHang("KH0000", textField_Ten_KHSDV.getText(), textField_Email_KHSDV.getText(), textField_SDT_KHSDV.getText(), textField_CCCD_KHSDV.getText());
//                			map.put(selectedRow, khachHang);
//                		}
//                		doiVe_GUI.dsVeDatTam.get(selectedRow).setKhuyenMai(table.getValueAt(selectedRow, 2).toString());
//                		model.setValueAt(textField_Ten_KHSDV.getText(), selectedRow, 1);
//                		textField_Ten_KHSDV.setText("");
//                		textField_SDT_KHSDV.setText("");
//                		textField_Email_KHSDV.setText("");
//                		textField_CCCD_KHSDV.setText("");
//                	}
//                }
//			}
//		});
		bt_XacNhan_KHSDV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bt_XacNhan_KHSDV.setBounds(1160, 24, 120, 40);
		jp_content_KHSDV.add(bt_XacNhan_KHSDV);
			
		//JP table
		jp_Table = new JPanel();
		jp_Table.setBounds(46, 222, 1373, 338);
		add(jp_Table);
		jp_Table.setLayout(null);
		
		bt_ThanhToan_KHSDV = new RoundedButton("Thanh toán", 10);
		bt_ThanhToan_KHSDV.setForeground(Color.WHITE);
		bt_ThanhToan_KHSDV.setBackground(new Color(51, 102, 153));
		bt_ThanhToan_KHSDV.setBounds(1245, 303, 118, 25);
		jp_Table.add(bt_ThanhToan_KHSDV);
		bt_ThanhToan_KHSDV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bt_ThanhToan_KHSDV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (map.size() != banVe_GUI.dsVeDatTam.size()) {
					JOptionPane.showMessageDialog(null, "Chưa nhập đủ khách hàng sử dụng vé");
					return;
				}
				
				KhachHang khachHang = list.stream().filter(kh -> textField_SDT_KHMV.getText().equals(kh.getSdt())).findFirst().orElse(null);
				BanVeNhapThongTin_Gui.this.khachHangMua = new KhachHang(khachHang != null?khachHang.getMaKH():generateMaKH(), textField_Ten_KHMV.getText(), textField_Email_KHMV.getText(), textField_SDT_KHMV.getText(), textField_CCCD_KHMV.getText());
				BanVeThanhToan_GUI banVeThanToan_GUI= new BanVeThanhToan_GUI(BanVeNhapThongTin_Gui.this, trangChu, banVe_GUI);

				//Gán giá trị khuyễn mãi cho các Ve trong ds đặt tạm
				
				trangChu.content.removeAll();
				trangChu.content.add(banVeThanToan_GUI);
				trangChu.content.revalidate();
				trangChu.content.repaint();
			}
		});
		
		JScrollPane scrollPane_KHSDV = new JScrollPane();
		scrollPane_KHSDV.setBounds(10, 5, 1353, 289);
		jp_Table.add(scrollPane_KHSDV);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {
					KhachHang khachHang = map.get(row);
					if (khachHang != null) {
						textField_Ten_KHSDV.setText(khachHang.getTenKH());
						textField_SDT_KHSDV.setText(khachHang.getSdt());
						textField_Email_KHSDV.setText(khachHang.getEmail());
						textField_CCCD_KHSDV.setText(khachHang.getCccd());
					}
				}
			}
		});
		scrollPane_KHSDV.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "Khách hàng", "Đối tượng", "Thông tin chỗ", "Giá", "Giảm đối tượng", "Thành tiền", "Xóa"
			}
		));
		
		// Điều chỉnh bảng
	    table.setRowHeight(50);
	    table.getColumnModel().getColumn(3).setPreferredWidth(150);
	    table.getColumnModel().getColumn(7).setPreferredWidth(30);
	    
		// Đặt renderer và editor cho nút xóa
        table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JCheckBox(), doiVe_GUI, BanVeNhapThongTin_Gui.this));
        
        // Tạo JComboBox cho cột "trạng thái"
        JComboBox<String> comboBoxKhuyenMai = new JComboBox<>();
        comboBoxKhuyenMai.setPreferredSize(new Dimension(20, 20));
        comboBoxKhuyenMai.addItem("Người lớn");
        comboBoxKhuyenMai.addItem("Trẻ em dưới 6 tuổi");
        comboBoxKhuyenMai.addItem("Trẻ em từ 6 đến 10 tuổi");
        comboBoxKhuyenMai.addItem("Sinh viên");
        comboBoxKhuyenMai.addItem("Người lớn tuổi");
        
        // Thêm ActionListener cho comboBox để cập nhật giá trị trong cột 5
        comboBoxKhuyenMai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedItemIndex = comboBoxKhuyenMai.getSelectedIndex();
                    doiVe_GUI.dsVeDatTam.get(selectedRow).setKhuyenMai(table.getValueAt(selectedRow, 2).toString());
                    float baseValue = (float) model.getValueAt(selectedRow, 4); // Lấy giá trị cột 4

                    double newValue;
                    switch (selectedItemIndex) {
                        case 0: // Người lớn
                            newValue = 0;
                            break;
                        case 1: // Trẻ em dưới 6 tuổi
                            newValue = baseValue * 1;
                            break;
                        case 2: // Trẻ em từ 6 đến 10 tuổi
                            newValue = baseValue * 0.25;
                            break;
                        case 3: // Sinh viên
                            newValue = baseValue * 0.1;
                            break;
                        case 4: // Người lớn tuổi
                            newValue = baseValue * 0.15;
                            break;
                        default:
                            newValue = baseValue; // Trường hợp không hợp lệ
                            break;
                    }

                    model.setValueAt(newValue, selectedRow, 5); // Cập nhật cột 5 với giá trị mới
                    model.setValueAt((float) model.getValueAt(selectedRow, 4) - newValue, selectedRow, 6); // Cập nhật cột 6 với giá trị mới
                }
            }
        });
        
        TableColumn khuyenMaiColumn = table.getColumnModel().getColumn(2);
        khuyenMaiColumn.setCellEditor(new DefaultCellEditor(comboBoxKhuyenMai));
        // Thiết lập renderer cho cột để hiển thị JComboBox
        khuyenMaiColumn.setCellRenderer(new ComboBoxRenderer(comboBoxKhuyenMai));        
        table.getColumnModel().getColumn(3).setCellRenderer(new TextAreaRenderer());

        chonSdt(textField_SDT_KHMV,textField_Ten_KHMV,textField_Email_KHMV, textField_CCCD_KHMV);
//        chonSdt(textField_SDT_KHSDV,textField_Ten_KHSDV,textField_Email_KHSDV, textField_CCCD_KHSDV);

        loadThongTin(doiVe_GUI.dsVeDatTam);
	}
	
	private boolean isValidKHMV() {
		String regexTenKH = "^[A-ZÀ-Ỵ][a-zà-ỹ\\p{L}]*([ ]+[A-ZÀ-Ỵ][a-zà-ỹ\\p{L}]*)*$";
		String regexEmail = "^[a-zA-Z0-9.]+@gmail\\.com$";
		String regexSdt = "^(03|05|07|08|09)\\d{8}$";
		String regexCccd = "^0[0-9]{2}[0-3]\\d{2}\\d{6}$";

		if (!textField_Ten_KHMV.getText().matches(regexTenKH)) {
			JOptionPane.showMessageDialog(null, "Tên khách hàng mua vé không hợp lệ!");
			return false;
		}
		if (!textField_Email_KHMV.getText().matches(regexEmail)) {
			JOptionPane.showMessageDialog(null, "Email không hợp lệ!");
			return false;
		}
		if (!textField_SDT_KHMV.getText().matches(regexSdt)) {
			JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!");
			return false;
		}
		if (!textField_CCCD_KHMV.getText().matches(regexCccd)) {
			JOptionPane.showMessageDialog(null, "Căn cước công dân hoặc số hộ chiếu không hợp lệ!");
			return false;
		}
		return true;
	}

	private boolean isValidTxt() {
		String regexTenKH="^[A-ZÀ-Ỵ][a-zà-ỹ\\p{L}]*([ ]+[A-ZÀ-Ỵ][a-zà-ỹ\\p{L}]*)*$";
		String regexEmail = "^[a-zA-Z0-9.]+@gmail\\.com$";
		String regexSdt = "^(03|05|07|08|09)\\d{8}$";
		String regexCccd = "^0[0-9]{2}[0-3]\\d{2}\\d{6}$";
		if(!textField_Ten_KHSDV.getText().matches(regexTenKH)) {
			JOptionPane.showMessageDialog(null,"Tên khách hàng không hợp lệ!");
			return false;			
		}
		if(!textField_Email_KHSDV.getText().matches(regexEmail)) {
			JOptionPane.showMessageDialog(null,"Email không hợp lệ!");
			return false;			
		}
	    if(!textField_SDT_KHSDV.getText().matches(regexSdt)) {
			JOptionPane.showMessageDialog(null,"Số điện thoại không hợp lệ!");
			return false;
		}
		if(!textField_CCCD_KHSDV.getText().matches(regexCccd)) {
			JOptionPane.showMessageDialog(null,"Căn cước công dân hoặc số hộ chiếu không hợp lệ!");
			return false;			
		}
		return true;
	}

	public void loadThongTin(ArrayList<Ve> dsVeDatTam) {
		
		DefaultTableModel defaultModel = ((DefaultTableModel) table.getModel());
		defaultModel.setRowCount(0);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		int count = 1;
		for (Ve ve: dsVeDatTam) {
			// Thông tin vé + giá gốc
			String thongTinVe = ve.getChuyenTau().getMaTau() + ": Từ " + ga_DAO.getGaTheoMaGa(ve.getGaDi().getMaGa()).getTenGa() + " đến "
					+ ga_DAO.getGaTheoMaGa(ve.getGaDen().getMaGa()).getTenGa() + "\nNgày: "
					+ ve.getNgayDi().format(formatter) + "   Lúc: " + ve.getGioDi().toString();
			if (ve.getHang().equalsIgnoreCase("VIP")) {
				thongTinVe = thongTinVe + "\nHạng VIP Toa "
						+ ve.getToa().getMaToa().substring(ve.getToa().getMaToa().length() - 2) + " Ghế số "
						+ ve.getSoGhe().getSoGhe();
			} else if (ve.getHang().equalsIgnoreCase("Giường nằm")) {
				thongTinVe = thongTinVe + "\nGiường nằm Toa "
						+ ve.getToa().getMaToa().substring(ve.getToa().getMaToa().length() - 2) + " Ghế số "
						+ ve.getSoGhe().getSoGhe();
			} else {
				thongTinVe = thongTinVe + "\nGhế mềm Toa "
						+ ve.getToa().getMaToa().substring(ve.getToa().getMaToa().length() - 2) + " Ghế số "
						+ ve.getSoGhe().getSoGhe();
			}
			defaultModel.addRow(new Object[] {count++, null, "Người lớn", thongTinVe, ve.tinhGiaVeGoc(), 0, ve.tinhGiaVeGoc()});
		}
	}
	
	public String generateMaKH() {
		dskh.reset();
		ArrayList<KhachHang> list = dskh.docTuBang();
		int sl = list.size() + 1;
		String maKH = String.format("KH%04d", sl);
		return maKH;
	}
	
	private void chonSdt(JTextField txt_sdt, JTextField txt_ten, JTextField txt_email, JTextField txt_cccd) {
		// Tạo JPopupMenu để hiển thị gợi ý
		JPopupMenu suggestionMenu = new JPopupMenu();

		// Hàm cập nhật gợi ý khi người dùng nhập vào text field
		txt_sdt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String input = txt_sdt.getText();
				suggestionMenu.removeAll(); // Xóa các gợi ý cũ

				if (!input.isEmpty()) {
					int count = 0; // Biến đếm số gợi ý đã thêm
					// Lọc danh sách ga theo từ khóa người dùng nhập
					for (KhachHang kh : list) {
						if (kh.getSdt().toLowerCase().startsWith(input.toLowerCase())) {
							JMenuItem item = new JMenuItem(kh.getSdt());
							item.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									txt_sdt.setText(item.getText());
									suggestionMenu.setVisible(false); // Ẩn gợi ý sau khi chọn
									KhachHang khachHang = list.stream().filter(kh -> txt_sdt.getText().equals(kh.getSdt())).findFirst().orElse(null);
									txt_ten.setText(khachHang.getTenKH());
									txt_email.setText(khachHang.getEmail());
									txt_cccd.setText(khachHang.getCccd());
								}
							});
							suggestionMenu.add(item);
							count++; // Tăng biến đếm
						}
						if (count >= 5) { // Kiểm tra nếu đã có 5 gợi ý
							break; // Thoát vòng lặp nếu đã đủ 5 gợi ý
						}
					}
				}

				// Hiển thị danh sách gợi ý nếu có ít nhất một gợi ý
				if (suggestionMenu.getComponentCount() > 0) {
					suggestionMenu.show(txt_sdt, 0, txt_sdt.getHeight());
					txt_sdt.requestFocus(); // Đặt lại focus cho JTextField
				} else {
					suggestionMenu.setVisible(false); // Ẩn nếu không có gợi ý
				}
			}
		});
	}
}

