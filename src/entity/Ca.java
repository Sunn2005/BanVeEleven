package entity;

import java.util.Objects;
import java.time.LocalTime;

public class Ca {
	private String maCa;
	private String tenCa;
	private LocalTime thoiGianBatDau;
	private LocalTime thoiGianKetThuc;
	
	public Ca(String maCa, String tenCa, LocalTime thoiGianBatDau, LocalTime thoiGianKetThuc) {
		super();
		this.setMaCa(maCa);
		this.setTenCa(tenCa);
		this.setThoiGianBatDau(thoiGianBatDau);
		this.setThoiGianKetThuc(thoiGianKetThuc);
	}

	public Ca(String maCa) {
		super();
		this.maCa = maCa;
	}

	public String getMaCa() {
		return maCa;
	}

	public void setMaCa(String maCa) {
		String regex= "^CA[0-9]{2}$";
		if(maCa.matches(regex))
			this.maCa = maCa;
		else
			throw new IllegalArgumentException("Mã ca không hợp lệ!");
	}

	public String getTenCa() {
		return tenCa;
	}

	public void setTenCa(String tenCa) {
		this.tenCa = tenCa;
	}

	public LocalTime getThoiGianBatDau() {
		return thoiGianBatDau;
	}

	public void setThoiGianBatDau(LocalTime thoiGianBatDau) {
		this.thoiGianBatDau = thoiGianBatDau;
	}

	public LocalTime getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}

	public void setThoiGianKetThuc(LocalTime thoiGianKetThuc) {
		this.thoiGianKetThuc = thoiGianKetThuc;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maCa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ca other = (Ca) obj;
		return Objects.equals(maCa, other.maCa);
	}

	@Override
	public String toString() {
		return "Ca [maCa=" + maCa + ", tenCa=" + tenCa + ", thoiGianBatDau=" + thoiGianBatDau + ", thoiGianKetThuc="
				+ thoiGianKetThuc + "]";
	}
}
