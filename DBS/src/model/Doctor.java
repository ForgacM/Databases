package model;

/**
 * Created by marcelforgac on 12.4.15.
 */
public class Doctor {

	private int doctorId;
	private String name;
	private String zameranie;

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZameranie() {
		return zameranie;
	}

	public void setZameranie(String zameranie) {
		this.zameranie = zameranie;
	}
}
