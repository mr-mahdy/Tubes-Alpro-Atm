package bri;

public class ArrTransfer {
	private String kodeBank;
	private String noRek;
	private int kesempatanLogin;
	
	//--------------------------- setter
	public void setKodeBank(String kodeBank) {
		this.kodeBank = kodeBank;
	}
	
	public void setNoRek(String noRek) {
		this.noRek = noRek;
	}
	
	public void setKesempatanLogin(int kesempatanLogin) {
		this.kesempatanLogin = kesempatanLogin;
	}
	
	//------------------------------------ getter
	public String getKodeBank() {
		return kodeBank;
	}
	
	public String getNoRek() {
		return noRek;
	}
	
	public int getKesempatanLogin() {
		return kesempatanLogin;
	}
}
