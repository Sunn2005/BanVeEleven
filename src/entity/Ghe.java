package entity;

public class Ghe {
	private int soGhe;
	private Toa toa;
	private boolean trangThai;
	
	public Ghe(int soGhe, Toa toa, boolean trangThai) {
		super();
		this.setSoGhe(soGhe);
		this.setToa(toa);
		this.setTrangThai(trangThai);
	}

	public Ghe(int soGhe, Toa toa) {
		super();
		this.soGhe = soGhe;
		this.toa = toa;
	}

	public int getSoGhe() {
		return soGhe;
	}

	public void setSoGhe(int soGhe) {
		if ((soGhe >= 0) && (soGhe <= 64))
			this.soGhe = soGhe;
		else
			throw new IllegalArgumentException("Số ghế không hợp lệ. Số ghế phải lớn hơn hoặc bằng 0 và bé hơn hoặc bằng 64.");
	}

	public Toa getToa() {
		return toa;
	}

	public void setToa(Toa toa) {
		this.toa = toa;
		//Kiểm tra tồn tại
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "Ghe [soGhe=" + soGhe + ", toa=" + toa + ", trangThai=" + trangThai + "]";
	}
	
}
