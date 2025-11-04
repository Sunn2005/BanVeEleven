package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import org.mindrot.jbcrypt.BCrypt;

import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.TaiKhoan;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DangNhap_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BufferedImage backgroundImage;
	public JTextField txtUser;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private Color hoverColor = new Color(0, 102, 204); // Màu khi di chuột qua
	private Color defaultColor = new Color(0, 153, 255); // Màu mặc định
	private LocalTime thoiGianBatDau;
	public TaiKhoan taiKhoanLogined;

	private NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				DangNhap_GUI frame = new DangNhap_GUI();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@SuppressWarnings("serial")
	public DangNhap_GUI() {
		// Tải hình ảnh từ package img
		try {
			backgroundImage = ImageIO.read(getClass().getResource("/img/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 600);
		setLocationRelativeTo(null);
		setTitle("Quản lý nhà ga ElevenR");
		setResizable(false);

		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (backgroundImage != null) {
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				}

				// Vẽ hai đường kẻ màu trắng
				g.setColor(Color.WHITE); // Đặt màu cho đường kẻ
				g.drawLine(213, 320, 693, 320); // Đường kẻ thứ nhất
				g.drawLine(213, 380, 693, 380); // Đường kẻ thứ hai
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
				txtPassword.transferFocus();
			}
		});

		// Logo
		ImageIcon originalLogo = new ImageIcon(getClass().getResource(""));
		Image scaledLogo = originalLogo.getImage().getScaledInstance(350, 220, Image.SCALE_SMOOTH);
																									
		JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
		logoLabel.setBounds(284, 66, 300, 120); // Cập nhật kích thước trên JLabel
		contentPane.add(logoLabel);

		// User Icon
		ImageIcon originalUserIcon = new ImageIcon(getClass().getResource("/img/user-icon.png"));
		Image scaledUserIcon = originalUserIcon.getImage().getScaledInstance(37, 37, Image.SCALE_SMOOTH);
		JLabel userIconLabel = new JLabel(new ImageIcon(scaledUserIcon));
		userIconLabel.setBounds(236, 275, 37, 37); // Cập nhật kích thước trên JLabel
		contentPane.add(userIconLabel);

		// Password Icon
		ImageIcon originalPassIcon = new ImageIcon(getClass().getResource("/img/password-icon.png"));
		Image scaledPassIcon = originalPassIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		JLabel passIconLabel = new JLabel(new ImageIcon(scaledPassIcon));
		passIconLabel.setBounds(236, 330, 40, 40); // Cập nhật kích thước trên JLabel
		contentPane.add(passIconLabel);

		// Tên người dùng
		txtUser = new JTextField("Tên đăng nhập");
		txtUser.setBounds(286, 266, 409, 54);
		txtUser.setFont(new Font("Arial", Font.PLAIN, 24));
		txtUser.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
		txtUser.setForeground(Color.white);
		txtUser.setOpaque(false);
		txtUser.setCaretColor(Color.BLACK);

		// Tương tác với trường tên người dùng
		txtUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtUser.getForeground().equals(Color.white)) {
					txtUser.setText("");
					txtUser.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtUser.getText().isEmpty()) {
					txtUser.setForeground(Color.white);
					txtUser.setText("Tên đăng nhập");
				}
			}
		});
		contentPane.add(txtUser);

		// Mật khẩu
		txtPassword = new JPasswordField("Mật khẩu");
		txtPassword.setBounds(286, 330, 409, 54);
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 24));
		txtPassword.setForeground(Color.WHITE);
		txtPassword.setBorder(new MatteBorder(0, 0, 0, 0, Color.WHITE));
		txtPassword.setOpaque(false);
		txtPassword.setCaretColor(Color.WHITE);
		contentPane.add(txtPassword);

		// Placeholder ban đầu
		txtPassword.setEchoChar((char) 0); // Tắt hiển thị dấu chấm

		// Tương tác focus
		txtPassword.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (String.valueOf(txtPassword.getPassword()).equals("Mật khẩu")) {
		            txtPassword.setText("");
		            txtPassword.setForeground(Color.black);
		            txtPassword.setEchoChar('•'); // Bật lại dấu chấm khi nhập
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txtPassword.getPassword().length == 0) {
		            txtPassword.setText("Mật khẩu");
		            txtPassword.setForeground(Color.white);
		            txtPassword.setEchoChar((char) 0); // Tắt dấu chấm để hiển thị placeholder
		        }
		    }
		});


		// Nút Đăng Nhập
		btnLogin = new JButton("Đăng nhập") {
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

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtUser.getForeground().equals(Color.white)) {
					JOptionPane.showMessageDialog(null, "Tài khoản không được rỗng!", "Thông báo", JOptionPane.ERROR_MESSAGE);					
					return;
				}

				TaiKhoan_DAO dsTK = new TaiKhoan_DAO();
				ArrayList<TaiKhoan> list = dsTK.docTuBang(); // Đọc danh sách tài khoản từ cơ sở dữ liệu

				String user = txtUser.getText(); // Lấy tên người dùng
				String pass = new String(txtPassword.getPassword()); // Lấy mật khẩu
				

				// Tìm tài khoản dựa trên tên người dùng
				for (TaiKhoan taiKhoan : list) {
					//if (taiKhoan.getMaTaiKhoan().equals(user)) 
				if (taiKhoan.getMaTaiKhoan().equals(user)) 
					{
						// Lấy mật khẩu đã mã hóa từ cơ sở dữ liệu
				        String matKhauSQL = taiKhoan.getMatKhau();
						// Kiểm tra mật khẩu
						if (kiemTraMatKhau(pass, matKhauSQL)
							) {
							// Kiểm tra trạng thái nhân viên
							if (nhanVien_DAO.getNhanVienTheoMaNV(taiKhoan.getNhanVien().getMaNV()).isTrangThai()) {
								// Đăng nhập thành công
								setTaiKhoanLogined(taiKhoan);
								thoiGianBatDau = LocalTime.now();
								setVisible(false);
								TrangChu_GUI trangChu = new TrangChu_GUI(DangNhap_GUI.this);
								trangChu.setVisible(true);
								return;
							} else {
								// Trạng thái đã nghỉ việc
								JOptionPane.showMessageDialog(null, "Nhân viên đã nghỉ!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
								return;
							}
						} else {
							if (txtPassword.getForeground().equals(Color.white)) {
								JOptionPane.showMessageDialog(null, "Mật khẩu không được rỗng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
								return;
							}
							// Mật khẩu không đúng
							JOptionPane.showMessageDialog(null, "Mật khẩu không đúng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
							return; // Kết thúc vòng lặp
						}
					}
				}
				// Tài khoản không tồn tại
				JOptionPane.showMessageDialog(null, "Tài khoản không tồn tại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
			}
		});
		// Thiết lập hành động khi ấn Enter
		getRootPane().setDefaultButton(btnLogin);

		// Đặt màu nền mặc định
		btnLogin.setBackground(defaultColor);
		btnLogin.setForeground(Color.WHITE); // Màu chữ
		btnLogin.setFont(new Font("Arial", Font.BOLD, 20));
		btnLogin.setBorderPainted(false); // Không vẽ viền
		btnLogin.setFocusPainted(false); // Không vẽ viền khi nút được chọn
		btnLogin.setContentAreaFilled(false); // Ẩn nền mặc định

		// Thêm sự kiện cho nút
		btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnLogin.setBackground(hoverColor); // Thay đổi màu nền khi đưa chuột vào
				btnLogin.repaint(); // Vẽ lại nút

				// Đổi con trỏ chuột thành kiểu tay chỉ
				btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnLogin.setBackground(defaultColor); // Trở về màu mặc định khi chuột ra
				btnLogin.repaint(); // Vẽ lại nút

				// Trả lại con trỏ chuột về mặc định
				btnLogin.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		btnLogin.setBounds(222, 413, 472, 38);
		contentPane.add(btnLogin);

		// Liên kết liên hệ với quản lý
		JLabel lblQuenMK = new JLabel("Quên mật khẩu ?");
		lblQuenMK.setBounds(550, 461, 144, 30);
		lblQuenMK.setForeground(Color.WHITE);
		lblQuenMK.setFont(new Font("Arial", Font.PLAIN, 18));
		contentPane.add(lblQuenMK);

		// Footer
		JLabel lblFooter = new JLabel("Nhà ga Eleven | Group 11 | DHKTPM19BTT - 422000422704");
		lblFooter.setBounds(305, 532, 500, 21);
		lblFooter.setForeground(Color.WHITE);
		lblFooter.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblFooter);
		

		// Màu mặc định và màu khi đưa chuột qua
		Color defaultLabelColor = Color.WHITE;
		Color hoverLabelColor = new Color(0, 153, 255);

		// Lấy font mặc định
		Font originalFont = lblQuenMK.getFont();

		// Thêm sự kiện cho lblContact
		lblQuenMK.addMouseListener(new java.awt.event.MouseAdapter() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblQuenMK.setForeground(hoverLabelColor); // Thay đổi màu khi đưa chuột vào

				// Gạch chân văn bản
				Font font = lblQuenMK.getFont();
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				lblQuenMK.setFont(font.deriveFont(attributes)); // Gạch chân chữ

				// Đổi con trỏ chuột thành kiểu tay chỉ
				lblQuenMK.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblQuenMK.setForeground(defaultLabelColor); // Trở về màu mặc định khi chuột ra

				// Trả lại font gốc (không gạch chân)
				lblQuenMK.setFont(originalFont);

				// Trả lại con trỏ chuột về mặc định
				lblQuenMK.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				QuenMatKhau_GUI qmk_GUI= new QuenMatKhau_GUI();
				qmk_GUI.setVisible(true);
				setVisible(false);
			}
		});

		this.setFocusableWindowState(false); // Vô hiệu hóa focus của cửa sổ
		EventQueue.invokeLater(() -> {
			this.setFocusableWindowState(true); // Bật lại focus sau khi cửa sổ hiển thị
		});
	}
	public boolean kiemTraMatKhau(String matKhauNhap, String matKhauSQL) {
	    return matKhauNhap.equals(matKhauSQL);
	}
	public LocalTime getThoiGianBatDau() {
		return thoiGianBatDau;
	}

	public void setThoiGianBatDau(LocalTime thoiGianBatDau) {
		this.thoiGianBatDau = thoiGianBatDau;
	}

	public TaiKhoan getTaiKhoanLogined() {
		return taiKhoanLogined;
	}

	public void setTaiKhoanLogined(TaiKhoan taiKhoanLogined) {
		this.taiKhoanLogined = taiKhoanLogined;
	}

}
