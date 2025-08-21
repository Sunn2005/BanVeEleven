package entity;

import java.util.Objects;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private String email;
	private String sdt;
	private String cccd;
	
	public KhachHang(String maKH, String tenKH, String email, String sdt, String cccd) {
		super();
		this.setMaKH(maKH);
		this.setTenKH(tenKH);
		this.setEmail(email);
		this.setSdt(sdt);
		this.setCccd(cccd);
	}
	
	public KhachHang(String maKH) {
		super();
		this.maKH = maKH;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
        String regexMaKH = "KH\\d{4}";
        if(maKH.matches(regexMaKH)) 
        	this.maKH = maKH;
        else
			throw new IllegalArgumentException("Mã khách hàng không hợp lê!");
	}
	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		String regexTenKH = "^[A-ZÀ-Ỵ][a-zà-ỹ\\p{L}]*([ ]+[A-ZÀ-Ỵ][a-zà-ỹ\\p{L}]*)*$";
		if(tenKH.matches(regexTenKH))
			this.tenKH = tenKH;
		else
			throw new IllegalArgumentException("Tên khách hàng không hợp lệ!");
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

	@Override
	public int hashCode() {
		return Objects.hash(maKH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKH, other.maKH);
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", email=" + email + ", sdt=" + sdt + ", cccd=" + cccd
				+ "]";
	}
}
