package bri;
import java.util.TimerTask;
public class TampilBerhasilTransaksi extends TimerTask {
	public void run() {
		ProgramATM atm = new ProgramATM();
		System.out.println("=======================================================");
		System.out.println("                    PT BANK RAKYAT INDONESIA 583        ");
        System.out.println();
        System.out.println();
        System.out.println("           				  TRANSAKSI ANDA                ");
        System.out.println("                       BERHASIL DILAKSANAKAN            ");
        System.out.println("                          TERIMA KASIH                  ");
        System.out.println("                       ATAS KEPERCAYAAN ANDA            ");
        System.out.println();
        atm.transaksiLagi();
		
	}
}
