package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class ChuyenTau {
	private String maTau;
	private Ga gaDi;
	private Ga gaDen;
	private ArrayList<Ga> tramDung;
	private LocalDate ngayDi;
	private LocalTime gioDi;
	private LocalDate ngayDen;
	private	LocalTime gioDen;
	private ArrayList<Toa> dsToa;

	public ChuyenTau(String maTau,Ga gaDi, Ga gaDen, ArrayList<Ga> tramDung, LocalDate ngayDi, LocalTime gioDi,LocalDate ngayDen, LocalTime gioDen
			,ArrayList<Toa> dsToa) {
		super();
		this.setMaTau(maTau);
		this.setGaDi(gaDi);
		this.setGaDen(gaDen);
		this.setTramDung(tramDung);
		this.setNgayDi(ngayDi);
		this.setGioDi(gioDi);
		this.setNgayDen(ngayDen);
		this.setGioDen(gioDen);
		this.setDsToa(dsToa);
	}

	public ChuyenTau(String maTau) {
		super();
		this.maTau = maTau;
	}

	public String getMaTau() {
		return maTau;
	}

	public void setMaTau(String maTau) {
		String ktMatau = "^TA\\d{3}$";
		if (maTau.matches(ktMatau))
			this.maTau = maTau;
		else
			throw new IllegalArgumentException("Mã tàu không hợp lệ. Mã tàu phải có định dạng 'TA' theo sau bởi 3 chữ số.");
	}

	public Ga getGaDi() {
		return gaDi;
	}

	public void setGaDi(Ga gaDi) {
		this.gaDi = gaDi;
		//Kiểm tra tồn tại
	}

	public Ga getGaDen() {
		return gaDen;
	}

	public void setGaDen(Ga gaDen) {
		this.gaDen = gaDen;
		//Kiểm tra tồn tại
	}

	public ArrayList<Ga> getTramDung() {
		return tramDung;
	}

	public void setTramDung(ArrayList<Ga> tramDung) {
		this.tramDung = tramDung;
		//Kiểm tra tồn tại tất cả các ga
	}

	public LocalDate getNgayDi() {
		return ngayDi;
	}

	public void setNgayDi(LocalDate ngayDi) {
			this.ngayDi = ngayDi;
	}

	public LocalTime getGioDi() {
		return gioDi;
	}

	public void setGioDi(LocalTime gioDi) {
		this.gioDi = gioDi;
	}

	
	public LocalDate getNgayDen() {
		return ngayDen;
	}

	public void setNgayDen(LocalDate ngayDen) {
			this.ngayDen = ngayDen;
	}

	public LocalTime getGioDen() {
		return gioDen;
	}

	public void setGioDen(LocalTime gioDen) {
		this.gioDen = gioDen;
	}
	public ArrayList<Toa> getDsToa() {
		return dsToa;
	}

	public void setDsToa(ArrayList<Toa> dsToa) {
		this.dsToa = dsToa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maTau);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChuyenTau other = (ChuyenTau) obj;
		return Objects.equals(maTau, other.maTau);
	}

	@Override
	public String toString() {
		return "ChuyenTau [maTau=" + maTau + ", gaDi=" + gaDi + ", gaDen=" + gaDen + ", tramDung=" + tramDung
				+ ", ngayDi=" + ngayDi + ", gioDi=" + gioDi + ", ngayDen=" + ngayDen + ", gioDen=" + gioDen + ", dsToa="
				+ dsToa + "]";
	}

	
	
}
