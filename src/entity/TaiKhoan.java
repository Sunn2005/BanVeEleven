package entity;

import java.util.Objects;

public class TaiKhoan {
	private String maTaiKhoan;
	private String matKhau;
	private int phanQuyen;
	private NhanVien nhanVien;
	
	public TaiKhoan(String maTaiKhoan, String matKhau, int phanQuyen, NhanVien nhanVien) {
		super();
		this.setMaTaiKhoan(maTaiKhoan);
		this.setMatKhau(matKhau);
		this.setPhanQuyen(phanQuyen);
		this.setNhanVien(nhanVien);
	}

	public TaiKhoan(String maTaiKhoan) {
		super();
		this.maTaiKhoan = maTaiKhoan;
	}

	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}

	public void setMaTaiKhoan(String maTaiKhoan) {
//		String regexMaTaiKhoan="^(TKQL|TKNV)\\d{3}$";
//		if(maTaiKhoan.matches(regexMaTaiKhoan))
//			this.maTaiKhoan = maTaiKhoan;
//		else
//			throw new IllegalArgumentException("Mã tài khoản không hợp lệ!");
		this.maTaiKhoan = maTaiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public int getPhanQuyen() {
		return phanQuyen;
	}

	public void setPhanQuyen(int phanQuyen) {
		this.phanQuyen = phanQuyen;
	}
	
	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maTaiKhoan, matKhau);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(maTaiKhoan, other.maTaiKhoan) && Objects.equals(matKhau, other.matKhau);
	}

	@Override
	public String toString() {
		return "TaiKhoan [maTaiKhoan=" + maTaiKhoan + ", matKhau=" + matKhau + ", phanQuyen=" + phanQuyen
				+ ", nhanVien=" + nhanVien + "]";
	}

	
}
