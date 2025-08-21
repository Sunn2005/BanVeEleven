package entity;

import java.util.ArrayList;
import java.util.Objects;

public class Toa {
	private String maToa;
	private String loaiToa;
	private ChuyenTau maTau;
	private ArrayList<Ghe> dsGhe;
	
	public Toa(String maToa, String loaiToa, ChuyenTau maTau, ArrayList<Ghe> dsGhe) {
		super();
		this.setMaToa(maToa);
		this.setLoaiToa(loaiToa);
		this.setMaTau(maTau);
		this.setDsGhe(dsGhe);
	}

	public Toa(String maToa) {
		super();
		this.maToa = maToa;
	}

	public String getMaToa() {
		return maToa;
	}

	public void setMaToa(String maToa) {
		String ktMaToa = "^TA\\d{3}_\\d{2}$";
		if (maToa.matches(ktMaToa))
			this.maToa = maToa;
		else 
			throw new IllegalArgumentException("Mã toa không hợp lệ!");
	}

	public String getLoaiToa() {
		return loaiToa;
	}

	public void setLoaiToa(String loaiToa) {
		String ktLoaiToa = "^(VIP|Ghế mềm|Giường nằm)$";
		if (loaiToa.matches(ktLoaiToa))
			this.loaiToa = loaiToa;
		else 
			throw new IllegalArgumentException("Loại toa không hợp lệ. Loại toa phải thuộc (VIP|ghế mềm|giường nằm)");
	}

	public ChuyenTau getMaTau() {
		return maTau;
	}

	public void setMaTau(ChuyenTau maTau) {
		this.maTau = maTau;
	}

	public ArrayList<Ghe> getDsGhe() {
		return dsGhe;
	}

	public void setDsGhe(ArrayList<Ghe> dsGhe) {
		this.dsGhe = dsGhe;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maToa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Toa other = (Toa) obj;
		return Objects.equals(maToa, other.maToa);
	}

	@Override
	public String toString() {
		return "Toa [maToa=" + maToa + ", loaiToa=" + loaiToa + ", maTau=" + maTau + ", dsGhe=" + dsGhe + "]";
	}
	
}