package gui;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import components.ConTent_JPanel;
import components.RoundedButton;
import components.RoundedTextField;

import dao.ChuyenTau_DAO;
import dao.Ga_DAO;
import entity.ChuyenTau;
import entity.Ga;
import entity.Toa;

import com.toedter.calendar.JDateChooser;

public class QuanLyChuyenTau_GUI extends JPanel implements ActionListener, MouseListener {

    private static final long serialVersionUID = 1L;
    private JPanel jp_quayLai;
    private JPanel jp_headerThongTin;
    private JPanel jp_thongTinCT;
    private JPanel jp_contentThongTin;
    private JLabel goBackIconLabel;
    private JLabel lbl_quayLai;
    private JLabel lbl_MaTau;
    private JLabel lbl_GaDi;
    private JLabel lbl_GaDen;
    private JLabel lbl_NgayDi;
    private JLabel lbl_GioDi;
    private JLabel lbl_NgayDen;
    private JLabel lbl_GioDen;
    private JLabel lbl_tieuDeTT;
    private JLabel downIconLabel1;
    
    private JTextField textField_MaTau;
    private JComboBox<String> comboBox_GaDi;
    private JComboBox<String> comboBox_GaDen;
    private JDateChooser dateChooser_NgayDi;
    private JDateChooser dateChooser_NgayDen;
    private JTextField textField_GioDi;
    private JTextField textField_GioDen;

    private JTable table_CT;
    private JScrollPane scrollPane;
    private ChuyenTau_DAO dsct = new ChuyenTau_DAO();
    private Ga_DAO gaDAO = new Ga_DAO();
    private Color hoverLabelColor = new Color(0, 153, 255);
    private DefaultTableModel model;
    private JComboBox<String> comboBox_TimTheoMaTau;
    private TableRowSorter<DefaultTableModel> sorter;
    
    private RoundedButton btnThem;
    private RoundedButton btnSua;
    private RoundedButton btnTim;
    private RoundedButton btnXoa;

    ChuyenTau tempChuyenTau = new ChuyenTau(""); // Đối tượng tạm thời

    public QuanLyChuyenTau_GUI(TrangChu_GUI trangChu) {
        setBackground(SystemColor.text);
        setForeground(new Color(255, 255, 255));
        setBounds(0, 170, 1460, 570);
        setLayout(null);

        // JPanel quay lại
        jp_quayLai = new JPanel();
        jp_quayLai.setBackground(SystemColor.text);
        jp_quayLai.setBounds(10, 10, 124, 28);
        add(jp_quayLai);
        jp_quayLai.setLayout(null);

        // Icon Quay lại
        ImageIcon goBackIcon = new ImageIcon(getClass().getResource("/img/9054423_bx_arrow_back_icon.png"));
        Image scaledGoBack = goBackIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        goBackIconLabel = new JLabel(new ImageIcon(scaledGoBack));
        jp_quayLai.add(goBackIconLabel);
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
        goBackIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                goBackIconLabel.setForeground(hoverLabelColor);
                goBackIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                goBackIconLabel.setForeground(null);
                goBackIconLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        // JLabel Quay lại
        lbl_quayLai = new JLabel("Quay lại");
        lbl_quayLai.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbl_quayLai.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_quayLai.setBounds(45, 0, 69, 27);
        jp_quayLai.add(lbl_quayLai);

        // JPanel thông tin chuyến tàu
        jp_thongTinCT = new JPanel();
        jp_thongTinCT.setBounds(10, 39, 327, 526);
        add(jp_thongTinCT);
        jp_thongTinCT.setLayout(null);

        // Icon xổ xuống
        ImageIcon downIcon1 = new ImageIcon(getClass().getResource("/img/Polygon_20.png"));
        Image scaledDown1 = downIcon1.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        jp_contentThongTin = new JPanel();
        jp_contentThongTin.setBounds(0, 31, 327, 495);
        jp_thongTinCT.add(jp_contentThongTin);
        jp_contentThongTin.setLayout(null);

        // Mã tàu
        lbl_MaTau = new JLabel("Mã tàu");
        lbl_MaTau.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_MaTau.setBounds(10, 18, 101, 25);
        jp_contentThongTin.add(lbl_MaTau);

        textField_MaTau = new RoundedTextField(10);
        textField_MaTau.setBounds(129, 18, 188, 25);
        textField_MaTau.setEditable(false);
        jp_contentThongTin.add(textField_MaTau);
        textField_MaTau.setColumns(10);

        // Ga đi
        lbl_GaDi = new JLabel("Ga đi");
        lbl_GaDi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_GaDi.setBounds(10, 64, 101, 25);
        jp_contentThongTin.add(lbl_GaDi);

        comboBox_GaDi = new JComboBox<>();
        comboBox_GaDi.setBounds(129, 66, 188, 25);
        jp_contentThongTin.add(comboBox_GaDi);

        // Ga đến
        lbl_GaDen = new JLabel("Ga đến");
        lbl_GaDen.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_GaDen.setBounds(10, 105, 101, 25);
        jp_contentThongTin.add(lbl_GaDen);

        comboBox_GaDen = new JComboBox<>();
        comboBox_GaDen.setBounds(129, 107, 188, 25);
        jp_contentThongTin.add(comboBox_GaDen);

        // Ngày đi
        lbl_NgayDi = new JLabel("Ngày đi");
        lbl_NgayDi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_NgayDi.setBounds(10, 149, 101, 25);
        jp_contentThongTin.add(lbl_NgayDi);

        dateChooser_NgayDi = new JDateChooser();
        dateChooser_NgayDi.setBounds(129, 149, 188, 25);
        dateChooser_NgayDi.setDateFormatString("dd/MM/yyyy");
        jp_contentThongTin.add(dateChooser_NgayDi);

        // Giờ đi
        lbl_GioDi = new JLabel("Giờ đi");
        lbl_GioDi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_GioDi.setBounds(10, 194, 101, 25);
        jp_contentThongTin.add(lbl_GioDi);

        textField_GioDi = new RoundedTextField(10);
        textField_GioDi.setColumns(10);
        textField_GioDi.setBounds(129, 194, 188, 25);
        jp_contentThongTin.add(textField_GioDi);

        // Ngày đến
        lbl_NgayDen = new JLabel("Ngày đến");
        lbl_NgayDen.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_NgayDen.setBounds(10, 244, 101, 25);
        jp_contentThongTin.add(lbl_NgayDen);

        dateChooser_NgayDen = new JDateChooser();
        dateChooser_NgayDen.setBounds(129, 244, 188, 25);
        dateChooser_NgayDen.setDateFormatString("dd/MM/yyyy");
        jp_contentThongTin.add(dateChooser_NgayDen);

        // Giờ đến
        lbl_GioDen = new JLabel("Giờ đến");
        lbl_GioDen.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_GioDen.setBounds(10, 290, 101, 25);
        jp_contentThongTin.add(lbl_GioDen);

        textField_GioDen = new RoundedTextField(10);
        textField_GioDen.setColumns(10);
        textField_GioDen.setBounds(129, 290, 188, 25);
        jp_contentThongTin.add(textField_GioDen);

        // Buttons
        btnThem = new RoundedButton("Thêm", 15);
        btnThem.setForeground(new Color(255, 255, 255));
        btnThem.setBackground(new Color(51, 102, 153));
        btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnThem.setBounds(10, 458, 70, 27);
        jp_contentThongTin.add(btnThem);

        btnSua = new RoundedButton("Sửa", 15);
        btnSua.setForeground(new Color(255, 255, 255));
        btnSua.setBackground(new Color(51, 102, 153));
        btnSua.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnSua.setBounds(90, 458, 70, 27);
        jp_contentThongTin.add(btnSua);

        btnXoa = new RoundedButton("Xóa", 15);
        btnXoa.setForeground(new Color(255, 255, 255));
        btnXoa.setBackground(new Color(51, 102, 153));
        btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnXoa.setBounds(170, 458, 70, 27);
        jp_contentThongTin.add(btnXoa);

        btnTim = new RoundedButton("Tìm", 15);
        btnTim.setForeground(new Color(255, 255, 255));
        btnTim.setBackground(new Color(51, 102, 153));
        btnTim.setBounds(250, 458, 67, 27);
        jp_contentThongTin.add(btnTim);
        btnTim.setFont(new Font("Tahoma", Font.PLAIN, 15));

        // JPanel header tiêu đề
        jp_headerThongTin = new JPanel();
        jp_headerThongTin.setBounds(0, 0, 327, 32);
        jp_thongTinCT.add(jp_headerThongTin);
        jp_headerThongTin.setBackground(new Color(51, 102, 153));
        jp_headerThongTin.setLayout(null);

        lbl_tieuDeTT = new JLabel("Thông tin chuyến tàu");
        lbl_tieuDeTT.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_tieuDeTT.setForeground(new Color(255, 255, 255));
        lbl_tieuDeTT.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbl_tieuDeTT.setBounds(10, 0, 180, 35);
        jp_headerThongTin.add(lbl_tieuDeTT);

        downIconLabel1 = new JLabel(new ImageIcon(scaledDown1));
        downIconLabel1.setBounds(287, 0, 30, 35);
        jp_headerThongTin.add(downIconLabel1);

        // Table
        scrollPane = new JScrollPane();
        scrollPane.setBounds(347, 78, 1113, 487);
        add(scrollPane);

        table_CT = new JTable();
        model = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "STT", "Mã tàu", "Ga đi", "Ga đến", "Ngày đi", "Giờ đi", "Ngày đến", "Giờ đến"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 1; // STT và Mã tàu không được chỉnh sửa
            }
        };
        sorter = new TableRowSorter<>(model);
        table_CT.setRowSorter(sorter);
        table_CT.setModel(model);
        scrollPane.setViewportView(table_CT);
        table_CT.setRowHeight(25);

        // ComboBox tìm theo mã tàu
        comboBox_TimTheoMaTau = new JComboBox<String>();
        comboBox_TimTheoMaTau.setBounds(347, 39, 155, 29);
        add(comboBox_TimTheoMaTau);
        
        comboBox_TimTheoMaTau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                @SuppressWarnings("unchecked")
                JComboBox<String> cb = (JComboBox<String>) e.getSource();
                String selectedObj = cb.getSelectedItem() != null ? cb.getSelectedItem().toString() : null;
                ChuyenTau ct = dsct.getChuyenTauTheoMaTau(selectedObj);
                if (ct != null) {
                    int rowIndex = -1;
                    for (int i = 0; i < table_CT.getRowCount(); i++) {
                        if (table_CT.getValueAt(i, 1).equals(ct.getMaTau())) {
                            rowIndex = i;
                            break;
                        }
                    }
                    if (rowIndex != -1) {
                        table_CT.setRowSelectionInterval(rowIndex, rowIndex);
                        hienThiThongTinChuyenTau(ct);
                    }
                }
            }
        });

        // Thêm sự kiện
        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
        btnTim.addActionListener(this);
        table_CT.addMouseListener(this);

        // Load dữ liệu
        loadGa();
        datatoTable();
    }

 // Validation đầy đủ
    public boolean validData() {
        // 1. Kiểm tra Ga đi
        if (comboBox_GaDi.getSelectedItem() == null || comboBox_GaDi.getSelectedItem().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn ga đi!", 
                "Lỗi - Thiếu thông tin", 
                JOptionPane.WARNING_MESSAGE);
            comboBox_GaDi.requestFocus();
            return false;
        }
        
        // 2. Kiểm tra Ga đến
        if (comboBox_GaDen.getSelectedItem() == null || comboBox_GaDen.getSelectedItem().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn ga đến!", 
                "Lỗi - Thiếu thông tin", 
                JOptionPane.WARNING_MESSAGE);
            comboBox_GaDen.requestFocus();
            return false;
        }
        
        // 3. Kiểm tra Ga đi và Ga đến không trùng nhau
        if (comboBox_GaDi.getSelectedItem().equals(comboBox_GaDen.getSelectedItem())) {
            JOptionPane.showMessageDialog(this, 
                "Ga đi và ga đến không được trùng nhau!\nVui lòng chọn lại.", 
                "Lỗi - Dữ liệu không hợp lệ", 
                JOptionPane.ERROR_MESSAGE);
            comboBox_GaDen.requestFocus();
            return false;
        }
        
        // 4. Kiểm tra Ngày đi
        if (dateChooser_NgayDi.getDate() == null) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn ngày đi!", 
                "Lỗi - Thiếu thông tin", 
                JOptionPane.WARNING_MESSAGE);
            dateChooser_NgayDi.requestFocus();
            return false;
        }
        
        // 5. Kiểm tra Ngày đến
        if (dateChooser_NgayDen.getDate() == null) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn ngày đến!", 
                "Lỗi-Thiếu thông tin", 
                JOptionPane.WARNING_MESSAGE);
            dateChooser_NgayDen.requestFocus();
            return false;
        }
        
        // 6. Kiểm tra Giờ đi không được để trống
        if (textField_GioDi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng nhập giờ đi!\n(Định dạng: HH:mm:ss)\nVí dụ: 08:30:00", 
                "Lỗi - Thiếu thông tin", 
                JOptionPane.WARNING_MESSAGE);
            textField_GioDi.requestFocus();
            return false;
        }
        
        // 7. Kiểm tra Giờ đến không được để trống
        if (textField_GioDen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng nhập giờ đến!\n(Định dạng: HH:mm:ss)\nVí dụ: 18:45:00", 
                "Lỗi - Thiếu thông tin", 
                JOptionPane.WARNING_MESSAGE);
            textField_GioDen.requestFocus();
            return false;
        }
        
        // 8. Kiểm tra định dạng Giờ đi
        LocalTime gioDi = null;
        try {
            gioDi = LocalTime.parse(textField_GioDi.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Giờ đi không đúng định dạng!\n" +
                "Định dạng yêu cầu: HH:mm:ss (24 giờ)\n" +
                "Ví dụ: 08:30:00 hoặc 14:15:30\n\n" +
                "Lỗi: " + e.getMessage(), 
                "Lỗi - Định dạng không hợp lệ", 
                JOptionPane.ERROR_MESSAGE);
            textField_GioDi.requestFocus();
            textField_GioDi.selectAll();
            return false;
        }
        
        // 9. Kiểm tra định dạng Giờ đến
        LocalTime gioDen = null;
        try {
            gioDen = LocalTime.parse(textField_GioDen.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Giờ đến không đúng định dạng!\n" +
                "Định dạng yêu cầu: HH:mm:ss (24 giờ)\n" +
                "Ví dụ: 18:45:00 hoặc 23:59:00\n\n" +
                "Lỗi: " + e.getMessage(), 
                "Lỗi - Định dạng không hợp lệ", 
                JOptionPane.ERROR_MESSAGE);
            textField_GioDen.requestFocus();
            textField_GioDen.selectAll();
            return false;
        }
        
        // 10. Kiểm tra logic thời gian (Ngày đến phải sau hoặc bằng Ngày đi)
        LocalDate ngayDi = dateChooser_NgayDi.getDate().toInstant()
            .atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ngayDen = dateChooser_NgayDen.getDate().toInstant()
            .atZone(ZoneId.systemDefault()).toLocalDate();
        
        if (ngayDen.isBefore(ngayDi)) {
            JOptionPane.showMessageDialog(this, 
                "Ngày đến phải sau hoặc bằng ngày đi!\n\n" +
                "Ngày đi: " + ngayDi.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n" +
                "Ngày đến: " + ngayDen.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
                "Lỗi - Thời gian không hợp lệ", 
                JOptionPane.ERROR_MESSAGE);
            dateChooser_NgayDen.requestFocus();
            return false;
        }
        
        // 11. Nếu cùng ngày, kiểm tra giờ đến phải sau giờ đi
        if (ngayDi.equals(ngayDen)) {
            if (gioDen.isBefore(gioDi) || gioDen.equals(gioDi)) {
                JOptionPane.showMessageDialog(this, 
                    "Giờ đến phải sau giờ đi!\n\n" +
                    "Giờ đi: " + gioDi.toString() + "\n" +
                    "Giờ đến: " + gioDen.toString() + "\n\n" +
                    "(Vì cùng ngày: " + ngayDi.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")", 
                    "Lỗi - Thời gian không hợp lệ", 
                    JOptionPane.ERROR_MESSAGE);
                textField_GioDen.requestFocus();
                textField_GioDen.selectAll();
                return false;
            }
        }
        
        // 12. Kiểm tra ngày đi không được là ngày quá khứ (chỉ cho nút THÊM)
        LocalDate today = LocalDate.now();
        if (textField_MaTau.getText().trim().isEmpty()) { // Chỉ kiểm tra khi THÊM mới
            if (ngayDi.isBefore(today)) {
                int confirm = JOptionPane.showConfirmDialog(this, 
                    "Ngày đi đã là ngày trong quá khứ!\n\n" +
                    "Ngày đi: " + ngayDi.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n" +
                    "Hôm nay: " + today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n\n" +
                    "Bạn có chắc muốn tiếp tục?", 
                    "Cảnh báo - Ngày quá khứ", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
                
                if (confirm == JOptionPane.NO_OPTION) {
                    dateChooser_NgayDi.requestFocus();
                    return false;
                }
            }
        }
        
        return true;
    }

    // Cập nhật lại actionPerformed với validation bổ sung
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        
        // NÚT THÊM - Tự động tạo toa và ghế
        if (o.equals(btnThem)) {
            if (validData()) {
                ChuyenTau ct = revertChuyenTau();
                if (ct != null) {
                    ChuyenTau existingCT = dsct.getChuyenTauTheoMaTau(ct.getMaTau());
                    if (existingCT != null) {
                        JOptionPane.showMessageDialog(this, "Chuyến tàu đã tồn tại trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            // Thêm chuyến tàu
                            dsct.create(ct);
                            
                            // Tự động tạo toa và ghế cho chuyến tàu
                            String maTau = ct.getMaTau();
                            
                            // Tạo 2 toa giường nằm (32 ghế mỗi toa)
                            for (int i = 1; i <= 2; i++) {
                                String maToa = maTau + "_0" + i;
                                if (dsct.themToa(maToa, "Giường nằm", maTau)) {
                                    dsct.themGheChoToa(maToa, 32);
                                }
                            }
                            
                            // Tạo 2 toa ghế mềm (64 ghế mỗi toa)
                            for (int i = 3; i <= 4; i++) {
                                String maToa = maTau + "_0" + i;
                                if (dsct.themToa(maToa, "Ghế mềm", maTau)) {
                                    dsct.themGheChoToa(maToa, 64);
                                }
                            }
                            
                            // Tạo 1 toa VIP (20 ghế)
                            String maToaVIP = maTau + "_05";
                            if (dsct.themToa(maToaVIP, "VIP", maTau)) {
                                dsct.themGheChoToa(maToaVIP, 20);
                            }
                            
                            model.setRowCount(0);
                            datatoTable();
                            JOptionPane.showMessageDialog(this, "Thêm chuyến tàu thành công!\nĐã tạo 5 toa: 2 Giường nằm, 2 Ghế mềm, 1 VIP");
                        } catch (Exception e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(this, "Lỗi khi thêm chuyến tàu: " + e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                deleteField();
            }
        }
        
        // NÚT TÌM - Lọc dữ liệu và cập nhật comboBox
        if (o.equals(btnTim)) {
            // Lọc dữ liệu theo điều kiện
            filterRows();
            
            // Cập nhật lại comboBox_TimTheoMaTau với kết quả lọc
            updateComboBoxTimTheoMaTau();
            
            // KHÔNG gọi deleteField() để giữ lại điều kiện tìm kiếm
        }
        
        // NÚT SỬA - Cập nhật thông tin chuyến tàu
        if (o.equals(btnSua)) {
            if (validData()) {
                update();
            }
        }
        
        // NÚT XÓA - Xóa cascade Ghế -> Toa -> ChuyenTau
        if (o.equals(btnXoa)) {
            int row = table_CT.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, 
                    "Vui lòng chọn chuyến tàu cần xóa từ bảng!", 
                    "Lỗi - Chưa chọn dòng cần xóa", 
                    JOptionPane.WARNING_MESSAGE);
                JOptionPane.showMessageDialog(this, "Chọn chuyến tàu muốn xóa!");
                return;
            }
            
            int confirmResult = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc muốn xóa chuyến tàu này?\n(Tất cả toa và ghế liên quan cũng sẽ bị xóa)",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            
            if (confirmResult == JOptionPane.NO_OPTION) {
                return;
            }
            
            String maTau = table_CT.getValueAt(row, 1).toString();
            try {
                // Xóa chuyến tàu (CASCADE sẽ xóa ghế -> toa -> chuyến tàu)
                boolean success = dsct.delete(maTau);
                
                if (success) {
                    model.setRowCount(0);
                    datatoTable();
                    JOptionPane.showMessageDialog(this, "Xóa chuyến tàu thành công!\n(Đã xóa tất cả toa và ghế liên quan)");
                    deleteField();
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Không thể xóa chuyến tàu!\nCó thể đã có vé được đặt cho chuyến tàu này.", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, 
                    "Lỗi khi xóa chuyến tàu: " + ex.getMessage() + 
                    "\n\nKhông thể xóa nếu đã có vé được đặt!", 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table_CT.getSelectedRow();
        if (row != -1) {
            String maTau = table_CT.getValueAt(row, 1).toString();
            String gaDi = table_CT.getValueAt(row, 2).toString();
            String gaDen = table_CT.getValueAt(row, 3).toString();
            
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate ngayDi = LocalDate.parse(table_CT.getValueAt(row, 4).toString(), dateFormatter);
            LocalDate ngayDen = LocalDate.parse(table_CT.getValueAt(row, 6).toString(), dateFormatter);
            
            String gioDi = table_CT.getValueAt(row, 5).toString();
            String gioDen = table_CT.getValueAt(row, 7).toString();

            textField_MaTau.setText(maTau);
            comboBox_GaDi.setSelectedItem(gaDi);
            comboBox_GaDen.setSelectedItem(gaDen);
            dateChooser_NgayDi.setDate(Date.from(ngayDi.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            dateChooser_NgayDen.setDate(Date.from(ngayDen.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            textField_GioDi.setText(gioDi);
            textField_GioDen.setText(gioDen);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    // Validation
    public boolean validData() {
        if (comboBox_GaDi.getSelectedItem() == null || comboBox_GaDi.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ga đi");
            return false;
        }
        if (comboBox_GaDen.getSelectedItem() == null || comboBox_GaDen.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ga đến");
            return false;
        }
        if (comboBox_GaDi.getSelectedItem().equals(comboBox_GaDen.getSelectedItem())) {
            JOptionPane.showMessageDialog(this, "Ga đi và ga đến không được trùng nhau");
            return false;
        }
        if (dateChooser_NgayDi.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày đi");
            return false;
        }
        if (dateChooser_NgayDen.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày đến");
            return false;
        }
        if (textField_GioDi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giờ đi (định dạng HH:mm:ss)");
            return false;
        }
        if (textField_GioDen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giờ đến (định dạng HH:mm:ss)");
            return false;
        }
        
        // Kiểm tra định dạng giờ
        try {
            LocalTime.parse(textField_GioDi.getText().trim());
            LocalTime.parse(textField_GioDen.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giờ không đúng định dạng (HH:mm:ss)");
            return false;
        }
        
        return true;
    }

    // Tạo ChuyenTau từ form
    public ChuyenTau revertChuyenTau() {
        String maTau = generateMaTau();
        String maGaDi = comboBox_GaDi.getSelectedItem().toString();
        String maGaDen = comboBox_GaDen.getSelectedItem().toString();
        
        // Lấy đối tượng Ga từ mã ga
        Ga gaDi = gaDAO.getGaTheoMaGa(maGaDi);
        Ga gaDen = gaDAO.getGaTheoMaGa(maGaDen);
        
        if (gaDi == null || gaDen == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin ga", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        LocalDate ngayDi = dateChooser_NgayDi.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ngayDen = dateChooser_NgayDen.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        LocalTime gioDi = LocalTime.parse(textField_GioDi.getText().trim());
        LocalTime gioDen = LocalTime.parse(textField_GioDen.getText().trim());

        try {
            // Tạo danh sách trạm dừng rỗng và danh sách toa rỗng
            ArrayList<Ga> tramDung = new ArrayList<>();
            ArrayList<Toa> dsToa = new ArrayList<>();
            
            ChuyenTau ct = new ChuyenTau(maTau, gaDi, gaDen, tramDung, ngayDi, gioDi, ngayDen, gioDen, dsToa);
            return ct;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Update chuyến tàu
    public void update() {
        int index = table_CT.getSelectedRow();
        if (index != -1) {
            String maTau = textField_MaTau.getText();
            String maGaDi = comboBox_GaDi.getSelectedItem().toString();
            String maGaDen = comboBox_GaDen.getSelectedItem().toString();
            
            // Lấy đối tượng Ga từ mã ga
            Ga gaDi = gaDAO.getGaTheoMaGa(maGaDi);
            Ga gaDen = gaDAO.getGaTheoMaGa(maGaDen);
            
            if (gaDi == null || gaDen == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin ga", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            LocalDate ngayDi = dateChooser_NgayDi.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate ngayDen = dateChooser_NgayDen.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            
            LocalTime gioDi = LocalTime.parse(textField_GioDi.getText().trim());
            LocalTime gioDen = LocalTime.parse(textField_GioDen.getText().trim());

            // Tạo danh sách trạm dừng rỗng và danh sách toa rỗng
            ArrayList<Ga> tramDung = new ArrayList<>();
            ArrayList<Toa> dsToa = new ArrayList<>();
            
            ChuyenTau ct = new ChuyenTau(maTau, gaDi, gaDen, tramDung, ngayDi, gioDi, ngayDen, gioDen, dsToa);
            try {
                dsct.update(ct);
                model.setRowCount(0);
                datatoTable();
                updateComboBoxTimTheoMaTau();
                deleteField();
                JOptionPane.showMessageDialog(this, "Cập nhật chuyến tàu thành công!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chọn chuyến tàu cần sửa.");
        }
    }

    // Xóa chuyến tàu
    public void xoaChuyenTau() {
        int row = table_CT.getSelectedRow();
        if (row != -1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa chuyến tàu này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String maTau = table_CT.getValueAt(row, 1).toString();
                try {
                    dsct.delete(maTau);
                    model.setRowCount(0);
                    datatoTable();
                    updateComboBoxTimTheoMaTau();
                    deleteField();
                    JOptionPane.showMessageDialog(this, "Xóa chuyến tàu thành công!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chọn chuyến tàu cần xóa.");
        }
    }

    // Load dữ liệu vào table
    public void datatoTable() {
        dsct.reset();
        ArrayList<ChuyenTau> list = dsct.docTuBang();
        model.setRowCount(0);
        int stt = 1;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        for (ChuyenTau ct : list) {
            comboBox_TimTheoMaTau.addItem(ct.getMaTau());
            model.addRow(new Object[] {
                stt++,
                ct.getMaTau(),
                ct.getGaDi().getMaGa(),  // Lấy mã ga từ đối tượng Ga
                ct.getGaDen().getMaGa(), // Lấy mã ga từ đối tượng Ga
                ct.getNgayDi().format(dateFormatter),
                ct.getGioDi().toString(),
                ct.getNgayDen().format(dateFormatter),
                ct.getGioDen().toString()
            });
        }
    }

    // Load danh sách ga
    public void loadGa() {
        ArrayList<Ga> listGa = gaDAO.docTuBang();
        for (Ga ga : listGa) {
            comboBox_GaDi.addItem(ga.getMaGa());
            comboBox_GaDen.addItem(ga.getMaGa());
        }
    }

    // Tạo mã tàu tự động
    public String generateMaTau() {
        dsct.reset();
        ArrayList<ChuyenTau> list = dsct.docTuBang();
        String maTau = String.format("TA%03d", list.size() + 1);
        return maTau;
    }

    // Xóa thông tin
    public void deleteField() {
        textField_MaTau.setText("");
        comboBox_GaDi.setSelectedIndex(-1);
        comboBox_GaDen.setSelectedIndex(-1);
        dateChooser_NgayDi.setDate(null);
        dateChooser_NgayDen.setDate(null);
        textField_GioDi.setText("");
        textField_GioDen.setText("");
    }

 // Cập nhật hàm filterRows()
    private void filterRows() {
        ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();
        
        String gaDi = comboBox_GaDi.getSelectedItem() != null ? comboBox_GaDi.getSelectedItem().toString() : "";
        String gaDen = comboBox_GaDen.getSelectedItem() != null ? comboBox_GaDen.getSelectedItem().toString() : "";

        if (!gaDi.isEmpty()) {
            filters.add(RowFilter.regexFilter(gaDi, 2));
        }
        if (!gaDen.isEmpty()) {
            filters.add(RowFilter.regexFilter(gaDen, 3));
        }

        Date ngayDiDate = dateChooser_NgayDi.getDate();
        if (ngayDiDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = dateFormat.format(ngayDiDate);
            filters.add(RowFilter.regexFilter(formattedDate, 4));
        }

        Date ngayDenDate = dateChooser_NgayDen.getDate();
        if (ngayDenDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = dateFormat.format(ngayDenDate);
            filters.add(RowFilter.regexFilter(formattedDate, 6));
        }

        if (filters.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.andFilter(filters));
        }
    }
    
    // Update ComboBox tìm theo mã tàu
    private void updateComboBoxTimTheoMaTau() {
        comboBox_TimTheoMaTau.removeAllItems();
        for (int i = 0; i < table_CT.getRowCount(); i++) {
            String maTau = table_CT.getValueAt(i, 1).toString();
            comboBox_TimTheoMaTau.addItem(maTau);
        }
        deleteField();
    }

    // Hiển thị thông tin chuyến tàu
    private void hienThiThongTinChuyenTau(ChuyenTau ct) {
        textField_MaTau.setText(ct.getMaTau());
        comboBox_GaDi.setSelectedItem(ct.getGaDi().getMaGa());
        comboBox_GaDen.setSelectedItem(ct.getGaDen().getMaGa());
        dateChooser_NgayDi.setDate(Date.from(ct.getNgayDi().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        dateChooser_NgayDen.setDate(Date.from(ct.getNgayDen().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        textField_GioDi.setText(ct.getGioDi().toString());
        textField_GioDen.setText(ct.getGioDen().toString());
    }
    
}