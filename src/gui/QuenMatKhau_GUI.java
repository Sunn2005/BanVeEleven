package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import org.mindrot.jbcrypt.BCrypt;

import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.NhanVien;
import entity.TaiKhoan;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class QuenMatKhau_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BufferedImage backgroundImage;
	private JTextField txtUser;
	private JPasswordField txtRePassword;
	private JPasswordField txtPassword;
	private JTextField txtPhone;
	private JTextField txtCCCD;
	private JLabel titleLabel;
	private JButton btnXacNhan;
	private Color hoverColor = new Color(0, 102, 204); // Màu khi di chuột qua
	private Color defaultColor = new Color(0, 153, 255); // Màu mặc định
	
	private Color hoverReTurn = new Color(225,225,225);
	private TaiKhoan_DAO dsTK = new TaiKhoan_DAO();
	private JLabel lblQuayLai;
	/**
	 * Create the frame.
	 */

	@SuppressWarnings("serial")
	public QuenMatKhau_GUI() {
		// Tải hình ảnh từ package img
		try {
			backgroundImage = ImageIO.read(getClass().getResource("/img/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 600);
		setLocationRelativeTo(null);

		contentPane = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (backgroundImage != null) {
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				}

				// Vẽ hai đường kẻ màu trắng
				g.setColor(Color.WHITE); // Đặt màu cho đường kẻ
				g.drawLine(213, 140, 690, 140); // Đường kẻ thứ nhất (giảm xuống 50px)
				g.drawLine(213, 200, 690, 200); // Đường kẻ thứ hai (giảm xuống 50px)
				g.drawLine(213, 254, 690, 254); // Đường kẻ thứ ba (giảm xuống 50px)
				g.drawLine(213, 310, 690, 310); // Đường kẻ thứ tư (giảm xuống 50px)
				g.drawLine(213, 370, 690, 370); // Đường kẻ thứ năm (giảm xuống 50px)

			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// Thêm MouseListener vào contentPane
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Mất focus của txtUser và txtPassword khi nhấp chuột ra ngoài
				txtUser.transferFocus();
				txtPhone.transferFocus();
				txtCCCD.transferFocus();
				txtRePassword.transferFocus();
				txtPassword.transferFocus();
			}
		});

//		// Logo
//		ImageIcon originalLogo = new ImageIcon(getClass().getResource("/img/LogoDepHonTrang.png"));
//		Image scaledLogo = originalLogo.getImage().getScaledInstance(120, 70, Image.SCALE_SMOOTH); 
//
//		JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
//		logoLabel.setBounds(10, 10, 120, 70); // Cập nhật kích thước trên JLabel
//		contentPane.add(logoLabel);

		// User Icon
		ImageIcon originalUserIcon = new ImageIcon(getClass().getResource("/img/user-icon.png"));
		Image scaledUserIcon = originalUserIcon.getImage().getScaledInstance(37, 37, Image.SCALE_SMOOTH);
		JLabel userIconLabel = new JLabel(new ImageIcon(scaledUserIcon));
		userIconLabel.setBounds(234, 96, 37, 40); // Giảm xuống thêm 50px
		contentPane.add(userIconLabel);

		// Phone Icon
		ImageIcon originalPhoneIcon = new ImageIcon(getClass().getResource("/img/phone.png"));
		Image scaledPhoneIcon = originalPhoneIcon.getImage().getScaledInstance(37, 37, Image.SCALE_SMOOTH); 
		JLabel phoneIconLabel = new JLabel(new ImageIcon(scaledPhoneIcon));
		phoneIconLabel.setBounds(234, 155, 37, 40); // Giảm xuống thêm 50px
		contentPane.add(phoneIconLabel);

		// Card Icon
		ImageIcon originalCardIcon = new ImageIcon(getClass().getResource("/img/card.png"));
		Image scaledCardIcon = originalCardIcon.getImage().getScaledInstance(37, 37, Image.SCALE_SMOOTH);
		JLabel cardIconLabel = new JLabel(new ImageIcon(scaledCardIcon));
		cardIconLabel.setBounds(234, 215, 37, 40); // Giảm xuống thêm 50px
		contentPane.add(cardIconLabel);

		// Password Icon
		ImageIcon originalPassIcon = new ImageIcon(getClass().getResource("/img/password-icon.png"));
		Image scaledPassIcon = originalPassIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		JLabel passIconLabel = new JLabel(new ImageIcon(scaledPassIcon));
		passIconLabel.setBounds(234, 267, 40, 40); // Giảm xuống thêm 50px
		contentPane.add(passIconLabel);

		// RePassword Icon
		ImageIcon originalRePassIcon = new ImageIcon(getClass().getResource("/img/password-icon.png"));
		Image scaledRePassIcon = originalRePassIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		JLabel rePassIconLabel = new JLabel(new ImageIcon(scaledRePassIcon));
		rePassIconLabel.setBounds(234, 327, 40, 40); // Giảm xuống thêm 50px
		contentPane.add(rePassIconLabel);


		// Tên người dùng
		txtUser = new JTextField("Mã đăng nhập");
		txtUser.setBounds(281, 91, 409, 54); // Giảm xuống thêm 50px
		txtUser.setFont(new Font("Arial", Font.PLAIN, 24));
		txtUser.setBorder(new MatteBorder(0, 0, 0, 0, Color.WHITE));
		txtUser.setForeground(new Color(245, 245, 245));
		txtUser.setOpaque(false);
		txtUser.setCaretColor(Color.WHITE);

		// Tương tác với trường tên người dùng
		txtUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtUser.getForeground().equals(new Color(245, 245, 245))) {
					txtUser.setText("");
					txtUser.setForeground(Color.WHITE);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtUser.getText().isEmpty()) {
					txtUser.setForeground(new Color(245, 245, 245));
					txtUser.setText("Mã đăng nhập");
				}
			}
		});
		contentPane.add(txtUser);

		// Số điện thoại
		txtPhone = new JTextField("Nhập số điện thoại");
		txtPhone.setBounds(281, 155, 409, 54); // Giảm xuống thêm 50px
		txtPhone.setFont(new Font("Arial", Font.PLAIN, 24));
		txtPhone.setBorder(new MatteBorder(0, 0, 0, 0, Color.WHITE));
		txtPhone.setForeground(new Color(245, 245, 245));
		txtPhone.setOpaque(false);
		txtPhone.setCaretColor(Color.WHITE);

		// Tương tác với trường số điện thoại
		txtPhone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtPhone.getForeground().equals(new Color(245, 245, 245))) {
					txtPhone.setText("");
					txtPhone.setForeground(Color.WHITE);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtPhone.getText().isEmpty()) {
					txtPhone.setForeground(new Color(245, 245, 245));
					txtPhone.setText("Nhập số điện thoại");
				}
			}
		});
		contentPane.add(txtPhone);

		// Số CCCD
		txtCCCD = new JTextField("Nhập căn cước công dân");
		txtCCCD.setBounds(281, 209, 409, 54); // Giảm xuống thêm 50px
		txtCCCD.setFont(new Font("Arial", Font.PLAIN, 24));
		txtCCCD.setBorder(new MatteBorder(0, 0, 0, 0, Color.WHITE));
		txtCCCD.setForeground(new Color(245, 245, 245));
		txtCCCD.setOpaque(false);
		txtCCCD.setCaretColor(Color.WHITE);

		// Tương tác với trường căn cước công dân
		txtCCCD.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtCCCD.getForeground().equals(new Color(245, 245, 245))) {
					txtCCCD.setText("");
					txtCCCD.setForeground(Color.WHITE);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtCCCD.getText().isEmpty()) {
					txtCCCD.setForeground(new Color(245, 245, 245));
					txtCCCD.setText("Nhập căn cước công dân");
				}
			}
		});
		contentPane.add(txtCCCD);

		// Mật khẩu
		txtPassword = new JPasswordField("Nhập mật khẩu mới");
		txtPassword.setBounds(281, 263, 409, 54); // Giảm xuống thêm 50px
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 24));
		txtPassword.setForeground(new Color(245, 245, 245));
		txtPassword.setBorder(new MatteBorder(0, 0, 0, 0, Color.WHITE));
		txtPassword.setOpaque(false);
		txtPassword.setCaretColor(Color.WHITE);

		// Đặt EchoChar thành 0 để không hiển thị dấu chấm khi chưa có mật khẩu
		txtPassword.setEchoChar((char) 0);

		contentPane.add(txtPassword);

		// Tương tác với trường mật khẩu
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// Khi có tiêu điểm, nếu văn bản là "Nhập mật khẩu mới", xóa đi
				if (txtPassword.getForeground().equals(new Color(245, 245, 245))) {
					txtPassword.setText("");
					txtPassword.setForeground(Color.WHITE);
					txtPassword.setEchoChar('•');  // Hiển thị dấu chấm khi bắt đầu nhập
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				// Khi mất tiêu điểm, nếu không có mật khẩu, hiển thị lại văn bản mặc định
				if (txtPassword.getPassword().length == 0) {
					txtPassword.setForeground(new Color(245, 245, 245));
					txtPassword.setText("Nhập mật khẩu mới");
					txtPassword.setEchoChar((char) 0); // Không hiển thị dấu chấm khi không có mật khẩu
				}
			}
		});

		// Nhập lại Mật khẩu
		txtRePassword = new JPasswordField("Nhập lại mật khẩu");
		txtRePassword.setBounds(284, 327, 409, 54); // Giảm xuống thêm 50px
		txtRePassword.setFont(new Font("Arial", Font.PLAIN, 24));
		txtRePassword.setForeground(new Color(245, 245, 245));
		txtRePassword.setBorder(new MatteBorder(0, 0, 0, 0, Color.WHITE));
		txtRePassword.setOpaque(false);
		txtRePassword.setCaretColor(Color.WHITE);

		// Đặt EchoChar thành 0 để không hiển thị dấu chấm khi chưa có mật khẩu
		txtRePassword.setEchoChar((char) 0);

		contentPane.add(txtRePassword);

		titleLabel = new JLabel("VUI LÒNG NHẬP ĐẦY ĐỦ CÁC THÔNG TIN PHÍA DƯỚI");
		titleLabel.setForeground(new Color(240, 128, 128));
		titleLabel.setBounds(198, 36, 528, 30);
		titleLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		contentPane.add(titleLabel);


		// Tương tác với trường mật khẩu
		txtRePassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// Nếu văn bản hiện tại là "Nhập lại mật khẩu", xóa văn bản đó
				if (txtRePassword.getForeground().equals(new Color(245, 245, 245))) {
					txtRePassword.setText("");
					txtRePassword.setForeground(Color.WHITE);
					txtRePassword.setEchoChar('•');  // Hiển thị dấu chấm khi bắt đầu nhập
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				// Nếu không có mật khẩu nào được nhập, hiển thị lại văn bản mặc định
				if (txtRePassword.getPassword().length == 0) {
					txtRePassword.setForeground(new Color(245, 245, 245));
					txtRePassword.setText("Nhập lại mật khẩu");
					txtRePassword.setEchoChar((char) 0); // Không hiển thị dấu chấm khi không có mật khẩu
				}
			}
		});

		// Nút Đăng Nhập
		btnXacNhan = new JButton("Xác nhận") {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D) g.create();
				// Đặt màu nền
				g2d.setColor(getBackground());
				// Vẽ hình chữ nhật bo góc
				g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
				g2d.setColor(getForeground()); // Đặt màu chữ
				g2d.drawString(getText(), (getWidth() - g2d.getFontMetrics().stringWidth(getText())) / 2,
						(getHeight() + g2d.getFontMetrics().getAscent()) / 2 - 2);
				g2d.dispose(); // Giải phóng tài nguyên Graphics
			}

			@Override
			public boolean contains(int x, int y) {
				// Kiểm tra nếu x, y nằm trong vùng bo góc
				return new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20).contains(x, y);
			}
		};
		btnXacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		// Thiết lập hành động khi ấn Enter
		getRootPane().setDefaultButton(btnXacNhan);

		// Đặt màu nền mặc định
		btnXacNhan.setBackground(defaultColor);
		btnXacNhan.setForeground(Color.WHITE); // Màu chữ
		btnXacNhan.setFont(new Font("Arial", Font.BOLD, 20));
		btnXacNhan.setBorderPainted(false); // Không vẽ viền
		btnXacNhan.setFocusPainted(false); // Không vẽ viền khi nút được chọn
		btnXacNhan.setContentAreaFilled(false); // Ẩn nền mặc định

		// Thêm sự kiện cho nút
		btnXacNhan.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnXacNhan.setBackground(hoverColor); // Thay đổi màu nền khi đưa chuột vào
				btnXacNhan.repaint(); // Vẽ lại nút

				// Đổi con trỏ chuột thành kiểu tay chỉ
				btnXacNhan.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnXacNhan.setBackground(defaultColor); // Trở về màu mặc định khi chuột ra
				btnXacNhan.repaint(); // Vẽ lại nút

				// Trả lại con trỏ chuột về mặc định
				btnXacNhan.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		btnXacNhan.setBounds(218, 404, 472, 38);
		contentPane.add(btnXacNhan);

		// Sự kiện nút xác nhận
		btnXacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtUser.getForeground().equals(new Color(245, 245, 245))) {
					JOptionPane.showMessageDialog(null, "Tài khoản không được rỗng!", "Thông báo", JOptionPane.ERROR_MESSAGE);					
					return;
				}
				ArrayList<TaiKhoan> list = dsTK.docTuBang(); // Đọc danh sách tài khoản từ cơ sở dữ liệu
				String user = txtUser.getText(); // Lấy tên người dùng
				String std = txtPhone.getText(); // Lấy số điện thoại
				String cccd = txtCCCD.getText(); // Lây CCCD
				String pass = new String(txtPassword.getPassword()); // Lấy mật khẩu
				String rePass = new String(txtRePassword.getPassword()); // Lấy mật khẩu nhập lại
				// Tìm tài khoản dựa trên tên người dùng
				boolean found = false; // Biến kiểm tra tài khoản đã tồn tại hay chưa
				for (TaiKhoan taiKhoan : list) {
					if (taiKhoan.getMaTaiKhoan().equals(user.trim())) {
						found = true; // Đánh dấu tìm thấy tài khoản
						NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
						NhanVien nv = nhanVien_DAO.getNhanVienTheoMaNV(taiKhoan.getNhanVien().getMaNV());
						// Kiểm tra thông tin tài khoản
						if (nv.getSdt().equals(std)) {
							if(nv.getCccd().equals(cccd)) {
								// Kiểm tra trạng thái nhân viên
								if (nhanVien_DAO.getNhanVienTheoMaNV(taiKhoan.getNhanVien().getMaNV()).isTrangThai()) {
									if(pass.equals(rePass)) {
										// Nhập thông tin thành công
										String matKhauMaHoa = BCrypt.hashpw(pass, BCrypt.gensalt());
										dsTK.updatePassword(matKhauMaHoa, user);
										JOptionPane.showMessageDialog(contentPane, "Cập nhật mật khẩu thành công");
										setVisible(false);
										DangNhap_GUI dn_GUI= new DangNhap_GUI();
										dn_GUI.setVisible(true);
										SwingUtilities.invokeLater(() -> dn_GUI.txtUser.requestFocus());
										return; // Thoát sau khi hoàn tất
									} else {
										// Các trường hợp lỗi mật khẩu không khớp
										if (txtPassword.getForeground().equals(new Color(245, 245, 245))) {
											JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu mới!", "Thông báo", JOptionPane.ERROR_MESSAGE);
											xoaTrang();
											return;
										}
										if (txtRePassword.getForeground().equals(new Color(245, 245, 245))) {
											JOptionPane.showMessageDialog(null, "Vui lòng nhập lại mật khẩu mới!", "Thông báo", JOptionPane.ERROR_MESSAGE);
											xoaTrang();
											return;
										}
										JOptionPane.showMessageDialog(null, "Mật khẩu không trùng khớp!", "Thông báo", JOptionPane.ERROR_MESSAGE);
										xoaTrang();
										return;
									}
								} else {
									JOptionPane.showMessageDialog(null, "Nhân viên đã nghỉ!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
									xoaTrang();
									return;
								}
							} else {
								if (txtCCCD.getForeground().equals(new Color(245, 245, 245))) {
									JOptionPane.showMessageDialog(null, "Căn cước công dân không được rỗng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
									xoaTrang();
									return;
								}
								JOptionPane.showMessageDialog(null, "Căn cước công dân không đúng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
								xoaTrang();
								return;
							}
						} else {
							if (txtPhone.getForeground().equals(new Color(245, 245, 245))) {
								JOptionPane.showMessageDialog(null, "Số điện thoại không được rỗng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
								xoaTrang();
								return;
							}
							JOptionPane.showMessageDialog(null, "Số điện thoại không đúng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
							xoaTrang();
							return;
						}
					}
				}

				if (!found) { // Nếu không tìm thấy
					JOptionPane.showMessageDialog(null, "Mã tài khoản không tồn tại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
					xoaTrang();
				}

			}
		});

		// Nút Hủy
//		btnHuy = new JButton("Quay lại") {
//			@Override
//			protected void paintComponent(Graphics g) {
//				Graphics2D g2d = (Graphics2D) g.create();
//				// Đặt màu nền
//				g2d.setColor(getBackground());
//				// Vẽ hình chữ nhật bo góc
//				g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
//				g2d.setColor(getForeground()); // Đặt màu chữ
//				g2d.drawString(getText(), (getWidth() - g2d.getFontMetrics().stringWidth(getText())) / 2,
//						(getHeight() + g2d.getFontMetrics().getAscent()) / 2 - 2);
//				g2d.dispose(); // Giải phóng tài nguyên Graphics
//			}
//
//			@Override
//			public boolean contains(int x, int y) {
//				// Kiểm tra nếu x, y nằm trong vùng bo góc
//				return new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20).contains(x, y);
//			}
//		};
//
//		// Thiết lập hành động khi ấn Enter
//		getRootPane().setDefaultButton(btnHuy);

		//Icon Quay lại
//		ImageIcon goBackIcon = new ImageIcon(getClass().getResource("/img/9054423_bx_arrow_back_icon.png"));
//		Image scaledGoBack = goBackIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		

		// Liên kết liên hệ với quản lý
		JLabel lblContact = new JLabel("Liên hệ với người quản lý?");
		lblContact.setBounds(464, 452, 226, 30);
		lblContact.setForeground(Color.WHITE);
		lblContact.setFont(new Font("Arial", Font.PLAIN, 18));
		contentPane.add(lblContact);

		// Footer
		JLabel lblFooter = new JLabel("ĐTHP | Group 5 | DHKTPM18ATT - 422000422703");
		lblFooter.setBounds(305, 532, 316, 21);
		lblFooter.setForeground(Color.WHITE);
		lblFooter.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblFooter);
		
		lblQuayLai = new JLabel("Quay lại");
		lblQuayLai.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuayLai.setBounds(10, 20, 122, 30);
		contentPane.add(lblQuayLai);
		lblQuayLai.setForeground(new Color(255, 255, 255));
		lblQuayLai.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		lblQuayLai.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				lblQuayLai.setForeground(hoverReTurn); // Trở về màu mặc định khi chuột ra
				lblQuayLai.repaint(); // Vẽ lại nút

				// Trả lại con trỏ chuột về mặc định
				lblQuayLai.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				lblQuayLai.setForeground(defaultColor); // Thay đổi màu nền khi đưa chuột vào
				lblQuayLai.repaint(); // Vẽ lại nút

				// Đổi con trỏ chuột thành kiểu tay chỉ
				lblQuayLai.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				DangNhap_GUI dangNhap = new DangNhap_GUI();
				setVisible(false);
				dangNhap.setVisible(true);
			}
		});

		// Màu mặc định và màu khi đưa chuột qua
		Color defaultLabelColor = Color.WHITE;
		Color hoverLabelColor = new Color(0, 153, 255);

		// Lấy font mặc định
		Font originalFont = lblContact.getFont();

		// Thêm sự kiện cho lblContact
		lblContact.addMouseListener(new java.awt.event.MouseAdapter() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblContact.setForeground(hoverLabelColor); // Thay đổi màu khi đưa chuột vào

				// Gạch chân văn bản
				Font font = lblContact.getFont();
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				lblContact.setFont(font.deriveFont(attributes)); // Gạch chân chữ

				// Đổi con trỏ chuột thành kiểu tay chỉ
				lblContact.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblContact.setForeground(defaultLabelColor); // Trở về màu mặc định khi chuột ra

				// Trả lại font gốc (không gạch chân)
				lblContact.setFont(originalFont);

				// Trả lại con trỏ chuột về mặc định
				lblContact.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				// Thông tin quản lý
				String tenQuanLy = "Lê Tấn Phong";
				String emailQuanLy = "letanphong400@gmail.com";
				String soDienThoaiQuanLy = "0919 128 639";

				// Hiển thị JOptionPane chứa thông tin quản lý
				JOptionPane
				.showMessageDialog(
						null, "Tên quản lý: " + tenQuanLy + "\nEmail: " + emailQuanLy
						+ "\nSố điện thoại (Zalo): " + soDienThoaiQuanLy,
						"Thông tin quản lý", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	public void xoaTrang() {
		// Đặt lại giá trị mặc định cho từng trường
		txtUser.setText("Mã đăng nhập"); 
		txtUser.setForeground(new Color(245, 245, 245));

		txtPhone.setText("Nhập số điện thoại"); 
		txtPhone.setForeground(new Color(245, 245, 245));

		txtCCCD.setText("Nhập căn cước công dân"); 
		txtCCCD.setForeground(new Color(245, 245, 245));

		txtPassword.setText("Nhập mật khẩu mới");
		txtPassword.setForeground(new Color(245, 245, 245));
		txtPassword.setEchoChar((char) 0); // Không hiển thị dấu chấm

		txtRePassword.setText("Nhập lại mật khẩu");
		txtRePassword.setForeground(new Color(245, 245, 245));
		txtRePassword.setEchoChar((char) 0); // Không hiển thị dấu chấm
	}
}
