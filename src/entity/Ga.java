package entity;

import java.util.Objects;

public class Ga {
	private String maGa;
	private String tenGa;
	private String diaChi;
	private int chiSoKm;
	private boolean trangThai;

	public Ga(String maGa, String tenGa, String diaChi, int chiSoKm, boolean trangThai) {
		super();
		this.setMaGa(maGa);
		this.setTenGa(tenGa);
		this.setDiaChi(diaChi);
		this.setChiSoKm(chiSoKm);
		this.setTrangThai(trangThai);
	}

	public Ga(String maGa) {
		super();
		this.maGa = maGa;
	}

	public String getMaGa() {
		return maGa;
	}

	public void setMaGa(String maGa) {
		String ktMaGa = "^GA\\d{3}$";
		if (maGa.matches(ktMaGa))
			this.maGa = maGa;
		else 
			throw new IllegalArgumentException("Mã ga không hợp lệ!  Mã ga phải có định dạng 'GA' theo sau bởi 3 chữ số.");
	}

	public String getTenGa() {
		return tenGa;
	}

	public void setTenGa(String tenGa) {
//		String ktTenGa = "^(?!\\s)[A-Z][a-z]*(\\s[A-Z][a-z]*)*$";
//		if (tenGa.matches(ktTenGa))
//			this.tenGa = tenGa;
//		else 
//			throw new IllegalArgumentException("Tên ga không hợp lệ! Chữ cái đầu của mỗi tiếng phải viết hoa");
//		//kt trong database
		this.tenGa = tenGa;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public int getChiSoKm() {
		return chiSoKm;
	}

	public void setChiSoKm(int chiSoKm) {
		if (chiSoKm >= 0)
			this.chiSoKm = chiSoKm;
		else
			throw new IllegalArgumentException("Chỉ số km không hợp lệ. Chỉ số km phải là một số dương.");
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	
	public String getTenGaRaw() {
		return tenGa.substring(3);
	}

	@Override
	public int hashCode() {
		return Objects.hash(maGa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ga other = (Ga) obj;
		return Objects.equals(maGa, other.maGa);
	}

	@Override
	public String toString() {
		return "Ga [maGa=" + maGa + ", tenGa=" + tenGa + ", diaChi=" + diaChi + ", chiSoKm=" + chiSoKm + ", trangThai="
				+ trangThai + "]";
	}
	
}
