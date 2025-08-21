package entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class NhanVien {
	private String maNV;
	private String tenNV;
	private LocalDate ngaySinh;
	private boolean gioiTinh;
	private Ca ca;
	private String cccd;
	private String email;
	private String sdt;
	private boolean trangThai;
	private int chucVu;
	
	public NhanVien(String maNV, String tenNV, LocalDate ngaySinh, boolean gioiTinh, Ca maCa, String cccd, String email,
			String sdt, boolean trangThai, int chucVu) {
		super();
		this.setMaNV(maNV);
		this.setTenNV(tenNV);
		this.setNgaySinh(ngaySinh);
		this.setGioiTinh(gioiTinh);
		this.setCa(maCa);
		this.setCccd(cccd);
		this.setEmail(email);
		this.setSdt(sdt);
		this.setTrangThai(trangThai);
		this.setChucVu(chucVu);
	}

	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		String regex = "^NV[0-9]{3}$";
		if(maNV.matches(regex))
			this.maNV = maNV;
		else
			throw new IllegalArgumentException("Mã nhân viên không hợp lệ");
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		String regexTenNV = "^[A-ZÀ-Ỵ][a-zà-ỹ\\p{L}]*([ ]+[A-ZÀ-Ỵ][a-zà-ỹ\\p{L}]*)*$";
		if(tenNV.matches(regexTenNV))
			this.tenNV = tenNV;
		else
			throw new IllegalArgumentException("Tên nhân viên không hợp lệ!");
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		LocalDate ngayHienTai = LocalDate.now();
	    int tuoi = Period.between(ngaySinh, ngayHienTai).getYears();
	    if(this.gioiTinh) {// Nam
	    	if (tuoi >= 18 && tuoi < 61) {
                this.ngaySinh = ngaySinh;
            } else {
                throw new IllegalArgumentException("Tuổi của nam phải từ 18 đến dưới 61.");
            }
	    }else { // Nữ
            if (tuoi >= 18 && tuoi < 56) {
                this.ngaySinh = ngaySinh;
            } else {
                throw new IllegalArgumentException("Tuổi của nữ phải từ 18 đến dưới 56.");
            }
        }
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Ca getCa() {
		return ca;
	}

	public void setCa(Ca maCa) {
		this.ca = maCa;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		String regexCccd = "^0[0-9]{2}[0-3]\\d{2}\\d{6}$";
		if(cccd.matches(regexCccd))
			this.cccd = cccd;
		else
			throw new IllegalArgumentException("Căn cước công dân không hợp lê!");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		String regexEmail = "^[a-zA-Z0-9.]+@gmail\\.com$";
		if(email.matches(regexEmail))
			this.email = email;
		else
			throw new IllegalArgumentException("Email không hợp lê!");
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		String regexSdt = "^(03|05|07|08|09)\\d{8}$";
	    if(sdt.matches(regexSdt))
	    	this.sdt = sdt;
	    else
	    	throw new IllegalArgumentException("Số điện thoại không hợp lê!");
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public int getChucVu() {
		return chucVu;
	}

	public void setChucVu(int chucVu) {
		this.chucVu = chucVu;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh
				+ ", ca=" + ca + ", cccd=" + cccd + ", email=" + email + ", sdt=" + sdt + ", trangThai=" + trangThai
				+ ", chucVu=" + chucVu + "]";
	}
	
}
