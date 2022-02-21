package model;

public class Users_VO {

	private String uPk;
	private String uPw;
	private String uName;
	private int uAge;
	private int uPoint;
	private int uAdmin;
	
	public String getuPk() {
		return uPk;
	}
	public void setuPk(String uPk) {
		this.uPk = uPk;
	}
	public String getuPw() {
		return uPw;
	}
	public void setuPw(String uPw) {
		this.uPw = uPw;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public int getuAge() {
		return uAge;
	}
	public void setuAge(int uAge) {
		this.uAge = uAge;
	}
	public int getuPoint() {
		return uPoint;
	}
	public void setuPoint(int uPoint) {
		this.uPoint = uPoint;
	}
	public int getuAdmin() {
		return uAdmin;
	}
	public void setuAdmin(int uAdmin) {

		this.uAdmin = uAdmin;
	}
	
	@Override
	public String toString() {
		return "Users_VO [uPk=" + uPk + ", uPw=" + uPw + ", uName=" + uName + ", uAge=" + uAge + ", uPoint=" + uPoint
				+ ", uAdmin=" + uAdmin + "]";
	}
	
	
	
}
