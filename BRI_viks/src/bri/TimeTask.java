package bri;

import java.util.TimerTask;

public class TimeTask extends TimerTask {

	public void run() {
		ProgramATM atm = new ProgramATM();
		System.out.println("=======================================================");
        System.out.println("\t       PT BANK RAKYAT INDONESIA");
        System.out.println();
        System.out.println();
        System.out.println("\t           TRANSAKSI ANDA");
        System.out.println("\t        BERHASIL DILAKSANAKAN");
        System.out.println("\t            TERIMA KASIH");
        System.out.println("\t        ATAS KEPERCAYAAN ANDA");
        System.out.println();
        atm.transaksiLagi();
		
	}

}
