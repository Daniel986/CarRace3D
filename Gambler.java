package CarRace3D;

public class Gambler {

	private int gamblerId;
	private float wallet;
	private String name;
	
	public Gambler(String name, float deposit) {
		this.name = name;
		this.wallet = deposit;
	}
	public float getWallet() {
		return wallet;
	}
	public void setWallet(float wallet) {
		this.wallet = wallet;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
