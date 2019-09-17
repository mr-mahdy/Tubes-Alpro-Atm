package bri;

public class ArrNasabah {
	String pin;
	int saldo;
	String norek;
	String nama;
	//--------------------------- setter
    public void setPin(String pin) {
        this.pin = pin;
    }
 
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    
    public void setNoRek(String norek) {
        this.norek = norek;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    
    
//------------------------------------ getter
    
 
    public String getPin() {
        return pin;
    }
 
    public int getSaldo() {
        return saldo;
    }
    
    public String getNoRek() {
        return norek;
    }
    
    public String getNama() {
        return nama;
    }  
}
