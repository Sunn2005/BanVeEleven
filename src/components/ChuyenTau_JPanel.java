package components;

import javax.swing.JPanel;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import dao.ChuyenTau_DAO;
import dao.Ghe_DAO;
import dao.Toa_DAO;
import entity.ChuyenTau;
import entity.Ghe;
import entity.Toa;

import java.util.ArrayList;
import java.awt.Font;

public class ChuyenTau_JPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public JLabel trainIconLabel;
	ChuyenTau_DAO chuyenTau_DAO = new ChuyenTau_DAO();
	Toa_DAO toa_DAO = new Toa_DAO();
	Ghe_DAO ghe_DAO = new Ghe_DAO();

	/**
	 * Create the panel.
	 */
	public ChuyenTau_JPanel(ChuyenTau chuyenTau1) {
		setLayout(null);
		ImageIcon trainIcon;
		//Logo chương trình
		trainIcon = new ImageIcon(getClass().getResource("/img/Chuyen_0.png"));
		
		ChuyenTau chuyenTau = chuyenTau_DAO.getChuyenTauTheoMaTau(chuyenTau1.getMaTau());
		
		Image scaledTrainIcon = trainIcon.getImage().getScaledInstance(122,140, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		trainIconLabel = new JLabel(new ImageIcon(scaledTrainIcon));
		trainIconLabel.setBackground(Color.WHITE);
		add(trainIconLabel);
		trainIconLabel.setBounds(0, 0, 122, 140);
		
		JLabel lblMaTau = new JLabel(chuyenTau.getMaTau());
		lblMaTau.setBounds(49, 2, 48, 21);
		lblMaTau.setFont(new Font("Tahoma", Font.PLAIN, 8));
		add(lblMaTau);
		
		JLabel lblThoiGian = new JLabel("TG đi: ");
		lblThoiGian.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblThoiGian.setBounds(10, 25, 38, 21);
		add(lblThoiGian);
		
		JLabel lblThoiGian1 = new JLabel(chuyenTau.getGioDi().toString());
		lblThoiGian1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblThoiGian1.setBounds(48, 25, 61, 21);
		add(lblThoiGian1);
		
		int soLuongDat = 0, soLuongCon = 0;
		ArrayList<Toa> dsToa = chuyenTau.getDsToa();
		for (Toa toa: dsToa) {
			ArrayList<Ghe> dsGhe = toa.getDsGhe();
			for (Ghe ghe: dsGhe) {
				if (ghe.isTrangThai()) {
					soLuongCon++;
				} else {
					soLuongDat++;
				}
			}
		}
		
		JLabel lblSoLuongDat = new JLabel("SL chỗ đặt: ");
		lblSoLuongDat.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSoLuongDat.setBounds(10, 45, 74, 21);
		add(lblSoLuongDat);
		
		JLabel lblSoLuongCon = new JLabel("SL chỗ còn: ");
		lblSoLuongCon.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSoLuongCon.setBounds(10, 65, 74, 21);
		add(lblSoLuongCon);
		
		JLabel lblSoLuongDat1 = new JLabel(soLuongDat + "");
		lblSoLuongDat1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSoLuongDat1.setBounds(88, 45, 38, 21);
		add(lblSoLuongDat1);
		
		JLabel lblSoLuongCon1 = new JLabel(soLuongCon + "");
		lblSoLuongCon1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSoLuongCon1.setBounds(88, 65, 38, 21);
		add(lblSoLuongCon1);
	}
}
