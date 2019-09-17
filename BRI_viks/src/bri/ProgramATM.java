package bri;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ProgramATM {
	ArrNasabah[] arrNsh = new ArrNasabah[5];
	ArrTransfer[] arrTransfer = new ArrTransfer[5];
	ArrBank[] arrBank = new ArrBank[7];
	StringBuilder sb = new StringBuilder();
	String[] dump = new String[5];
	String[] dump1 = new String[7];
	Scanner sc = new Scanner(System.in);
	BufferedInputStream bis = null;
	FileInputStream fis = null;
	Timer timer = new Timer();
	char eof = '.';
    int pil;
    String pin;
    int nominal;
    int id = 3;
    String noRek;
    
    //membaca file apakah ada atau tidak
    public void bacaFile(String namaFile) {
    	try {
			fis = new FileInputStream(namaFile);
			bis = new BufferedInputStream(fis);
		} catch (Exception e) {
		
		}
    }
    
    //jika file ada maka akan di cek isinya dan di olah 
    public void loadFile() {
    	int data = -1;
    	char ch;
    	int index =0;
    	try {
			if (bis != null) {
				data = bis.read();
				ch = (char) data;
				sb = new StringBuilder();
				while (ch != eof && data != -1) {
					if (ch != ' ') {
						sb.append(ch);
					} else {
						dump[index] = sb.toString();
						index++;
						sb = new StringBuilder();
					}
					ch = (char) bis.read();
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Error");
		}
    }
    
    public void loadFileBank() {
    	int data = -1;
    	char ch;
    	int index =0;
    	try {
			if (bis != null) {
				data = bis.read();
				ch = (char) data;
				sb = new StringBuilder();
				while (ch != eof && data != -1) {
					if (ch != ' ') {
						sb.append(ch);
						
					} else {
						dump1[index] = sb.toString();
						index++;
						sb = new StringBuilder();
					}
					ch = (char) bis.read();
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Error");
		}
    }
    
    //ketika data sudah tidak tidak dipakai, maka tutup
    public void closeFile() {
    	try {
			bis.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    //masukan data ked alama array Of Record
    public void ambilData() {
    	bacaFile("dataNasabah.txt");
    	loadFile();
    	closeFile();
    	String[] temp;
    	for (int i = 0; i < arrNsh.length; i++) {
			temp = dump[i].split("~");
			arrNsh[i] = new ArrNasabah();
			arrNsh[i].setPin(temp[0]);
			arrNsh[i].setSaldo(Integer.parseInt(temp[1]));
			arrNsh[i].setNoRek(temp[2]);
			arrNsh[i].setNama(temp[3]);
		}
    }
    
    public void ambilDataBank() {
    	bacaFile("daftarBank.txt");
    	loadFileBank();
    	closeFile();
    	String[] temp;
    	for (int i = 0; i < arrBank.length; i++) {
			temp = dump1[i].split("~");
			arrBank[i] = new ArrBank();
			arrBank[i].setKodeBank(temp[0]);
			arrBank[i].setNamaBank(temp[1]);
    	}
    }
    public void ambilDataTransfer() {
    	bacaFile("transferBankLain.txt");
    	loadFile();
    	closeFile();
    	String[] temp;
    	for (int i = 0; i < arrTransfer.length; i++) {
			temp = dump[i].split("~");
			arrTransfer[i] = new ArrTransfer();
			arrTransfer[i].setKodeBank(temp[0]);
			arrTransfer[i].setNoRek(temp[1]);
			arrTransfer[i].setKesempatanLogin(Integer.parseInt(temp[2]));
    	}
    }
    
    public void cekNoRek() {
    	ambilDataTransfer();
    	System.out.println("          ===================================================");
        System.out.println("                       PT BANK RAKYAT INDONESIA          507          ");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.print("                 MASUKKAN NOMOR REKENING: "); noRek = sc.next();
        System.out.println("          ===================================================");
        for (int i = 0; i < arrTransfer.length; i++) {
			if (arrTransfer[i].getNoRek().equals(noRek)) {
				if (arrTransfer[i].getKesempatanLogin() == 0) {
					tampilSalah();
					System.out.println("                          KARTU ANDA DIBLOKIR");
					System.out.println("                            UNTUK SEMENTARA");
				} else {
					pilihanBahasa();
				}
			}
		}
        
    }
    //pilih bahasa
    public void pilihanBahasa() {
    	 System.out.println("          ===================================================");
		System.out.println("               	       PT BANK RAKYAT INDONESIA           006");
		System.out.println("                      SILAHKAN PILIH BAHASA ANDA");
		System.out.println("                ---------------------------------------");
		System.out.println("                      PLEASE SELECT YOUR LANGUAGE");
		System.out.println();
		System.out.println("                                        INDONESIA -> [1]");
		System.out.println();
		System.out.println("                                        ENGLISH   -> [2]");
		System.out.println();
		System.out.println("                      TO OBTAIN CARD PRESS CANCEL");
		System.out.println("                 ---------------------------------------");
		System.out.println("                    TEKAN 'CANCEL' UNTUK MEMBATALKAN");
		 System.out.println("          ===================================================");
        int pilihan = sc.nextInt();
        switch (pilihan) {
            case 1:
                infoBank();
                break;
            case 2:
            	System.out.println("Maintenance");
            	break;
        }
    }
    //info bank
    public void infoBank() {
    	 System.out.println("          ===================================================");
        System.out.println("                        PT BANK RAKYAT INDONESIA          521          ");
        System.out.println("                 NASABAH YTH,                                   ");
        System.out.print("                                                                  ");
        System.out.println();
        System.out.println("                 TIPS AMAN BERTRANSAKSI DI ATM:                 ");
        System.out.println("                 - JAGA KERAHASIAAN PIN                         ");
        System.out.println("                 - GANTI PIN ANDA SECARA BERKALA                ");
        System.out.println("                 - TUTUP DENGAN TANGAN SAAT                     ");
        System.out.println("                   MENEKAN NOMOR PIN"                            );
        System.out.println();
        System.out.println("                 CONTACT CENTER BRI:                            ");
        System.out.println("                         14017/1500017                          ");
        System.out.println("                 DIMOHON TIDAK MENGHUBUNGI NOMOR                ");
        System.out.println("                 LAIN SELAIN NOMOR DIATAS                       ");
        System.out.println("                                          LANJUTKAN =>[1]       ");
        System.out.println("          ===================================================");
        int pil = sc.nextInt();
       
        
        switch (pil) {
            case 1:
                login();
                break;
            default:
                System.err.println("\tPILIHAN YANG ANDA MASUKKAN SALAH");
                infoBank();
        }
        
    }
 
    // User Login
    
    public void login() {
    	ambilData();
    	ambilDataTransfer();
    	int masuk =0;
    	 System.out.println("          ===================================================");
        System.out.println("                       PT BANK RAKYAT INDONESIA           507          ");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("                          TUTUP TANGAN ANDA                    ");
        System.out.println("                      SAAT MEMASUKAN NOMOR PIN                 ");
        System.out.print("                     MASUKKAN PIN: "); pin = sc.next();
        for (int i = 0; i < arrNsh.length; i++) {
        	if (arrNsh[i].getPin().equals(pin)) {	
				if (arrNsh[i].getNoRek().equals(noRek)) {
					for (int j = 0; j < arrTransfer.length; j++) {
						if (arrTransfer[j].getNoRek().equals(noRek)) {
							arrTransfer[j].setKesempatanLogin(3);
							updateFileLogin();
							tarik();
							masuk =1;
						}
						
					}
				}
			}
		}
        if (masuk == 0) {
        	for (int j = 0; j < arrTransfer.length; j++) {
    			if (arrTransfer[j].getNoRek().equals(noRek)) {
    				arrTransfer[j].setKesempatanLogin(arrTransfer[j].getKesempatanLogin() -1);
    				updateFileLogin();
    				if (arrTransfer[j].getKesempatanLogin() > 0) {
    					tampilSalah();
    					System.out.println();
    					System.out.println("                       ANDA MEMASUKAN NOMOR PIN");
    					System.out.println("                              YANG SALAH");
    					System.out.println("                      KESEMPATAN ANDA TINGGAL " + arrTransfer[j].getKesempatanLogin());
    					transaksiLagi();			
    				} else {
    					tampilSalah();
    					System.out.println("                         KARTU ANDA DIBLOKIR");
    					System.out.println("                           UNTUK SEMENTARA");
    				}		
    			}
    		}
		}
    }
    //menu awal 
    public void menu() {
    	 System.out.println("          ===================================================");
        System.out.println("                        PT BANK RAKYAT INDONESIA          583               ");
        System.out.println();
        System.out.println("                      	  PILIH JENIS TRANSAKSI                   ");
        System.out.println();
        System.out.println("             	 [1] <= PENARIKAN   INFO REKENING => [5]           ");
        System.out.println();
        System.out.println("            	 [2] <= TRANSFER       PEMBAYARAN => [6]           ");
        System.out.println();
        System.out.println("             	 [3] <= PEMBELIAN        UBAH PIN => [7]           ");
        System.out.println();
        System.out.println("             	 [4] <= LAINNYA            KELUAR => [8]           ");
        System.out.println("          ===================================================");
        System.out.print("                       MASUKKAN PILIHAN ANDA : ");
	        pil = sc.nextInt();
	        switch (pil) {
	            case 1:
	                tampilPenarikanLain();
	                break;
	            case 7:
	            	ubahPin();
	            	break;
	            case 5:
	                cekSaldo();
	                break;
	                
	            case 2:
	                pilihBank();
	                break;
	            case 4:
	            	System.out.println("Maintance");
	                //pembayaran();
	                break;
	            case 8:
	                tampilSalah();
	                System.out.println("                           TERIMA KASIH");
	                System.out.println();
	                System.out.println("                   ANDA TELAH MENGGUNAKAN ATM BRI");
	                System.out.println();
		            System.out.println("                      SILAHKAN AMBIL KARTU ANDA ");
	                break;
	        }
    }
    
    public void ubahPin() {
    	tampilSalah();
    	System.out.println("                 MASUKAN NOMOR PIN LAMA");
    	System.out.print("                         ");
    	String pin2 = sc.next();
    	System.out.println();
    	System.out.println("                 MASUKAN NOMOR PIN BARU");
    	System.out.print("                         ");
    	String pinBaru = sc.next();
    	if (pin.equals(pin2)) {
    		if (pinBaru != pin) {
    			for (int i = 0; i < arrNsh.length; i++) {
    				if (arrNsh[i].getNoRek().equals(noRek)) {
    					arrNsh[i].setPin(pinBaru);
    					updateFile();
    					tampilSalah();
    					System.out.println("                       PIN ANDA BERHASIL DIUBAH");
    					transaksiLagi();
    				}
    			}
			} else {
				tampilSalah();
				System.out.println("                       ANDA MEMASUKAN NOMOR PIN");
				System.out.println("                   BARU YANG SAMA DENGAN YANG LAMA");
				transaksiLagi();
			}
			
		} else {
			tampilSalah();
			System.out.println("                       ANDA MEMASUKAN NOMOR PIN");
			System.out.println("                              YANG SALAH");
			transaksiLagi();
		}
    	
    }
    //cek saldo
    public void cekSaldo() {
    	 System.out.println("          ===================================================");
        System.out.println("                       PT BANK RAKYAT INDONESIA          590               ");
        System.out.println();
        System.out.println("                           INFORMASI SALDO                   ");
        System.out.println();
        for (int i = 0; i < arrNsh.length; i++) {
			if (arrNsh[i].getPin().equals(pin)) {
				System.out.println("                 SALDO: RP   " + arrNsh[i].getSaldo() + "                        ");
				
	        }
		}
        System.out.println();
        transaksiLagi();
        System.out.println("          ===================================================");
    }
    //penarikan uang secara manual
    public void tampilPenarikanLain() {
    	int pil;
    	System.out.println("          ===================================================");
        System.out.println("                      PT BANK RAKYAT INDONESIA           602               ");
        System.out.println();
        System.out.println("                      MASUKAN PENARIKAN TUNAI                   ");
        System.out.println("                    (DALAM KELIPATAN RP.50.000)"                );
        System.out.println();
        System.out.print("                        RP ");int penarikan = sc.nextInt();
        System.out.println();
        
        System.out.println();
        System.out.println("                                         BENAR => [1]");
    	System.out.println();
    	System.out.println("                                         SALAH => [2]");
    	pil = sc.nextInt();
    	switch (pil) {
			case 1:
				tarikNominal(penarikan);
				break;
			case 2:
				transaksiLagi();
				break;
		}
        
    }
    //tarik uang langsung
    public void tarik() {
        int penarikan;
 
        System.out.println("          ===================================================");
        System.out.println("                      PT BANK RAKYAT INDONESIA            601               ");
        System.out.println();
        System.out.println("                      PILIH JUMLAH PAKET TUNAI                   ");
        System.out.println();
        System.out.println("              [1] <= 50.000            500.000 => [5]           ");
        System.out.println();
        System.out.println("              [2] <= 100.000           750.000 => [6]           ");
        System.out.println();
        System.out.println("              [3] <= 200.000         1.000.000 => [7]           ");
        System.out.println();
        System.out.println("              [4] <= 300.000   TRANSAKSI LAIN => [8]           ");
        System.out.println("          ===================================================");
        System.out.print("                       MASUKKAN PILIHAN ANDA : ");
        pil = sc.nextInt();
        switch (pil) {
            case 1:
                tarikNominal(50000);
                break;
            case 2:
                tarikNominal(100000);
                break;
            case 3:
                tarikNominal(200000);
                break;
            case 4:
                tarikNominal(300000);
                break;
            case 5:
                tarikNominal(500000);
                break;
            case 6:
            	tarikNominal(750000);
            	break;
            case 7:
            	tarikNominal(1000000);
            case 8:
                menu();               
                break;
        }
    }
    
    public void tampilSalah() {
    	System.out.println("          ===================================================");
    	System.out.println("                       PT BANK RAKYAT INDONESIA          519               ");
        System.out.println();
         
    }
    
    public void tampilProses() {
    	System.out.println("          ===================================================");
    	System.out.println("                       PT BANK RAKYAT INDONESIA          009          ");
		System.out.println();
		System.out.println("                           SILAHKAN TUNGGU");
		System.out.println("                      TRANSAKSI SEDANG DIPROSES");
		System.out.println("                             --------");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("                          INFO CALL BRI");
		System.out.println("                    14017,500017, (021)57987400");
    }
    //
    public void tarikNominal(int nominal) {
    	TimerTask task = new TimeTask();
        for (int i = 0; i < arrNsh.length; i++) {
			if (arrNsh[i].getPin().equals(pin)) { 
				int saldo = arrNsh[i].getSaldo();
				if (nominal >= 50000) {
					if (nominal % 50000  == 0) {
			            if (saldo >= nominal) {
			            	saldo -= nominal;
			            	arrNsh[i].setSaldo(saldo);
			            	tampilProses();
			            	timer.schedule(task, 5000);
			                updateFile();
			            } else {
			            	tampilSalah();
			            	System.out.println("                    MAAF, SALDO ANDA TIDAK CUKUP");
			            	System.out.println();
			            	transaksiLagi();
			            }	
			        } else {
			        	tampilSalah();
			            System.out.println("     MAAF YANG ANDA MASUKAN HARUS KELIPATAN Rp. 50.000");
			            System.out.println();
			            transaksiLagi();
			        }
				} else {
					tampilSalah();
					System.out.println("       MAAF YANG ANDA MASUKAN HARUS KELIPATAN Rp. 50.000");
					System.out.println();
					transaksiLagi();
				}
				
	        }
		}
        
    }
    
    public void pilihBank() {
    	System.out.println("          ===================================================");
        System.out.println("                      PT BANK RAKYAT INDONESIA       591               ");
        System.out.println();
        System.out.println("                     PILIH BANK TUJUAN TRANSFER                   ");
        System.out.println();
        System.out.println("                                        B R I => [1]");
        System.out.println();
        System.out.println("                                    BANK LAIN => [2]");
        System.out.println();
        System.out.println();
        System.out.println("                   TEKAN CANCEL [3]  UNTUK BATAL");
        System.out.println("          ===================================================");
        System.out.print("                        MASUKKAN PILIHAN ANDA : ");
	        pil = sc.nextInt();
	        switch (pil) {
	            case 1:
	                transfer();
	                break;
	            case 2:
	            	transferBankNonBri();
	                break;
	            case 3:
	            	tampilSalah();
	                System.out.println("                           TERIMA KASIH");
	                System.out.println();
	                System.out.println("                   ANDA TELAH MENGGUNAKAN ATM BRI");
	                System.out.println();
		            System.out.println("                      SILAHKAN AMBIL KARTU ANDA ");
		            break;
	            
	        }
    }
    
    public void transfer() {
    	System.out.println("          ===================================================");
        System.out.println("                      PT BANK RAKYAT INDONESIA         591               ");
        System.out.println();
        System.out.println("                       MASUKKAN NOMOR REKENING                    ");
        System.out.println("                        TUJUAN TRANSFER ANDA    ");
        System.out.println();
        System.out.print("                            _");noRek = sc.next();
        System.out.println("                                        BENAR => [1]");
        System.out.println();
        System.out.println("                                        SALAH => [2]");
        System.out.println();
        System.out.println();
        System.out.println("                   TEKAN CANCEL [3]  UNTUK BATAL");
        System.out.println("          ===================================================");
        int pilihan = sc.nextInt();
        System.out.println();
        switch (pilihan) {
			case 1:
				tampilNominal();
				break;
			case 2:
				tampilSalah();
				System.out.println("                           TERIMA KASIH");
                System.out.println();
                System.out.println("                   ANDA TELAH MENGGUNAKAN ATM BRI");
                System.out.println();
	            System.out.println("                      SILAHKAN AMBIL KARTU ANDA ");
				break;
			case 3:
				tampilSalah();
				System.out.println("                           TERIMA KASIH");
                System.out.println();
                System.out.println("                   ANDA TELAH MENGGUNAKAN ATM BRI");
                System.out.println();
	            System.out.println("   ");
				break;
		}

    }
    public void transferBankNonBri(){
    	System.out.println("          ===================================================");
        System.out.println("                      PT BANK RAKYAT INDONESIA          591 ");
        System.out.println();
        System.out.println("                       MASUKKAN KODE BANK DAN");
        System.out.println("                       NOMOR REKENING TUJUAN ");
        System.out.println();
        System.out.println("                             DAFTAR KODE BANK => [1]");
        System.out.println();
        System.out.println("                                    LANJUTKAN => [2]");
        pil = sc.nextInt();
        switch (pil) {
			case 1:
				tampilDaftarBank();
				break;
			case 2:
				break;
	    }
        System.out.println();
        System.out.print("                            _");noRek = sc.next();
        System.out.println();
        System.out.println("                                        BENAR => [1]");
        System.out.println();
        System.out.println("                                        SALAH => [2]");
        System.out.println();
        System.out.println();
        pil = sc.nextInt();
        switch (pil) {
			case 1:
				tampilNominalNonBRI();
				break;
			case 2:
				tampilSalah();
				System.out.println("                           TERIMA KASIH");
	            System.out.println();
	            System.out.println("                   ANDA TELAH MENGGUNAKAN ATM BRI");
	            System.out.println();
	            System.out.println("                      SILAHKAN AMBIL KARTU ANDA ");
				break;
        }
    }
    
    public void tampilDaftarBank() {
    	 System.out.println("          ===================================================");
        System.out.println("                        KODE BANK TUJUAN TRANSFER             ");
        System.out.println();
        System.out.println("                    002: BRI           008: MANDIRI   ");
        System.out.println("                    009: BNI           200: BTN   ");
        System.out.println("                    014: BCA           110: BJB  ");
        System.out.println("                    420: MEGA          011: DANAMON  ");
        System.out.println("                    441: BUKOPIN       022: CIMB NIAGA ");
        System.out.println();
        System.out.println("                                        KELUAR => [1]");
        System.out.println();
        System.out.println();
        pil = sc.nextInt();
        switch (pil) {
			case 1:
				transferBankNonBri();
				break;
        }
        
    }
    
    public void tampilNominalNonBRI() {
    	 System.out.println("          ===================================================");
        System.out.println("                      PT BANK RAKYAT INDONESIA            591               ");
        System.out.println();
        System.out.println("                       MASUKAN JUMLAH TRANSFER                    ");
        System.out.println();
        System.out.print("                            RP ");nominal = sc.nextInt();
        System.out.println();
        System.out.println("                                        BENAR => [1]");
        System.out.println();
        System.out.println("                                        SALAH => [2]");
        System.out.println();
        System.out.println();
        System.out.println("                    TEKAN CANCEL [3]  UNTUK BATAL");
    	
    	pil = sc.nextInt();
    	switch (pil) {
			case 1:
				transferNominalNonBRI(nominal, noRek);
				break;
			case 2:
				tampilSalah();
				System.out.println("                           TERIMA KASIH");
                System.out.println();
                System.out.println("                   ANDA TELAH MENGGUNAKAN ATM BRI");
                System.out.println();
	            System.out.println("                      SILAHKAN AMBIL KARTU ANDA ");
				break;
			case 3:
				tampilSalah();
				System.out.println("                           TERIMA KASIH");
                System.out.println();
                System.out.println("                   ANDA TELAH MENGGUNAKAN ATM BRI");
                System.out.println();
	            System.out.println("                      SILAHKAN AMBIL KARTU ANDA ");
				break;
		}
    }
  
    public void tampilNominal() {
    	 System.out.println("          ===================================================");
        System.out.println("                      PT BANK RAKYAT INDONESIA             591               ");
        System.out.println();
        System.out.println("                       MASUKAN JUMLAH TRANSFER                    ");
        System.out.println();
        System.out.print("                            RP ");nominal = sc.nextInt();
        System.out.println();
        System.out.println("                                        BENAR => [1]");
        System.out.println();
        System.out.println("                                        SALAH => [2]");
        System.out.println();
        System.out.println();
        System.out.println("                    TEKAN CANCEL [3]  UNTUK BATAL");
    	
    	pil = sc.nextInt();
    	switch (pil) {
			case 1:
				transferNominal(nominal, noRek);
				break;
			case 2:
				tampilSalah();
				System.out.println("                           TERIMA KASIH");
                System.out.println();
                System.out.println("                   ANDA TELAH MENGGUNAKAN ATM BRI");
                System.out.println();
	            System.out.println("                      SILAHKAN AMBIL KARTU ANDA ");
				break;
		}
        
    }
    
    public void transferNominalNonBRI(int nominal, String noRek) {
    	ambilDataTransfer();
    	ambilDataBank();
    	TimerTask task = new TampilBerhasilTransaksi();
    	int saldo =0;
    	int nonBri =0;
    	String kodeBank ="";
    	for (int i = 0; i < arrNsh.length; i++) {
			if (arrNsh[i].getPin().equals(pin)) {
				saldo = arrNsh[i].getSaldo();
			}
			if ((arrTransfer[i].getKodeBank()+arrTransfer[i].getNoRek()).equals(noRek)) {
				if (arrTransfer[i].getKodeBank() != "002") {
					nonBri =1;
				}
			}
		}
    	 System.out.println("          ===================================================");
	    System.out.println("                      PT BANK RAKYAT INDONESIA            509");
	    System.out.println();
	    
		if (nonBri == 1) {
			if (saldo >= nominal+6500) {
				System.out.println("\t         INFORMASI TRANSFER");
			    System.out.println();
			    for (int k = 0; k < arrNsh.length; k++) {
		    		if (arrNsh[k].getPin().equals(pin)) {
			    		System.out.println("           DARI NOREK : " + arrNsh[k].getNoRek());
			    		break;
			    	}
			    }
			    for (int j = 0; j < arrNsh.length; j++) {
		    		if ((arrTransfer[j].getKodeBank()+arrTransfer[j].getNoRek()).equals(noRek)) {
		    			saldo = arrNsh[j].getSaldo();
		    			for (int j2 = 0; j2 < arrTransfer.length; j2++) {
							if ((arrTransfer[j2].getKodeBank()+arrTransfer[j2].getNoRek()).equals(noRek)) {
								for (int k = 0; k < arrBank.length; k++) {
									if (arrBank[k].getKodeBank().equals(arrTransfer[j2].getKodeBank())) {
										System.out.println("           BANK       : " + arrBank[k].getNamaBank());
										break;
									}
								}
							}
						}
		    			for (int j2 = 0; j2 < arrNsh.length; j2++) {
							if (arrTransfer[j].getNoRek().equals(arrNsh[j2].getNoRek())) {
								System.out.println("          KE NOREK   : " + arrNsh[j2].getNoRek());
								System.out.println("          NAMA       : " + arrNsh[j2].getNama());
							}
						}
	    	    		System.out.println("          JUMLAH     : RP "+ nominal);
	    	    		System.out.println();
	    	    		System.out.print("\t        PROSES TRANSAKSI?");
	    	            System.out.println("      [1] YA");
	    	            System.out.println();
	    	            System.out.println("                                       [2] TIDAK");
	    	        	pil = sc.nextInt();
	    	        	switch (pil) {
	    	    			case 1:
	    	    				for (int k = 0; k < arrNsh.length; k++) {
	    				    		if (arrTransfer[j].getNoRek().equals(arrNsh[k].getNoRek())) {
	    				    			saldo = arrNsh[k].getSaldo();
	    				    			saldo += nominal;
	    	    	    				arrNsh[k].setSaldo(saldo);
	    	    	    				tampilProses();
	    	    	    				updateFile();
	    					    		break;
	    					    	}
	    					    }
	    	    				
	    	    				for (int k = 0; k < arrNsh.length; k++) {
	    				    		if (arrNsh[k].getPin().equals(pin)) {
	    					    		saldo = arrNsh[k].getSaldo();
	    					        	saldo = saldo - (nominal+6500);
	    					        	arrNsh[k].setSaldo(saldo);
	    					        	updateFile();
	    					        	timer.schedule(task, 5000);
	    					    		break;
	    					    	}
	    					    }
	    	    				break;
	    	    			case 2:
	    	    				tampilSalah();
	    	    				System.out.println("\t             TERIMA KASIH");
	    	    				System.out.println();
	    	                    System.out.println("\t    ANDA TELAH MENGGUNAKAN ATM BRI");
	    	                    System.out.println();
	    	    	            System.out.println("\t      SILAHKAN AMBIL KARTU ANDA ");
	    	    				break;
	    	    		}
	    	        	break;
					}
				}
			 
			} else {
				System.out.println();
				System.out.println();
            	System.out.println("\t    MAAF, SALDO ANDA TIDAK CUKUP");
				transaksiLagi();
			}
		} else {
			System.out.println("\t           ANDA MEMASUKAN ");
			System.out.println("\t         	 KODE BANK ATAU");
			System.out.println("\t       NO REKENING YANG SALAH");
			System.out.println();
			transaksiLagi();
		}
    	
    }
 
    public void transferNominal(int nominal, String noRek) {
    	ambilDataTransfer();
    	TimerTask task = new TampilBerhasilTransaksi();
    	int saldo =0;
    	int bri =0;
    	for (int i = 0; i < arrNsh.length; i++) {
			if (arrNsh[i].getPin().equals(pin)) {
				saldo = arrNsh[i].getSaldo();
			}
			if (arrTransfer[i].getNoRek().equals(noRek)) {
				if (arrTransfer[i].getKodeBank().equals("002")) {
					bri =1;
				}
			}
			
			
		}
    	
    	 System.out.println("          ===================================================");
	    System.out.println("                        PT BANK RAKYAT INDONESIA          510");
	    System.out.println();
	    if (bri == 1) {
	    	for (int i = 0; i < arrNsh.length; i++) {
				if (arrNsh[i].getNoRek().equals(noRek)) {		
					if (saldo >= nominal) {
						System.out.println("\t         INFORMASI TRANSFER");
					    System.out.println();
					    for (int k = 0; k < arrNsh.length; k++) {
				    		if (arrNsh[k].getPin().equals(pin)) {
					    		System.out.println("          DARI NOREK : " + arrNsh[k].getNoRek());
					    		break;
					    	}
					    }
					}
					break;
				} else {
					if (i == arrNsh.length-1) {
						System.out.println("\t             ANDA MEMASUKAN ");
						System.out.println("\t         NO REKENING YANG SALAH");
						System.out.println();
						transaksiLagi();
					}		
				}
	    	} 
		} else {
			System.out.println("\t             ANDA MEMASUKAN ");
			System.out.println("\t         NO REKENING YANG SALAH");
			System.out.println();
			transaksiLagi();
				
		}
	    
	   
	    for (int i = 0; i < arrNsh.length; i++) {
	    	if (arrNsh[i].getPin().equals(pin)) {
				saldo = arrNsh[i].getSaldo();
				if (saldo >= nominal) {
		        	saldo -= nominal;
		        	arrNsh[i].setSaldo(saldo);
		        	updateFile();
		        	id = 1;
		        }
    		}
		}
    	for (int i = 0; i < arrNsh.length; i++) {
    		if (arrNsh[i].getNoRek().equals(noRek)) {
    			saldo = arrNsh[i].getSaldo();
    			if (id == 1) {
    				System.out.println("          KE NOREK   : " + arrNsh[i].getNoRek());
    	    		System.out.println("          NAMA       : " + arrNsh[i].getNama());
    	    		System.out.println("          JUMLAH     : RP "+ nominal);
    	    		System.out.println();
    	    		System.out.print("\t        PROSES TRANSAKSI?");
    	            System.out.println("      [1] YA");
    	            System.out.println();
    	            System.out.println("                                       [2] TIDAK");
    	        	pil = sc.nextInt();
    	        	switch (pil) {
    	    			case 1:
    	    				saldo += nominal;
    	    				arrNsh[i].setSaldo(saldo);
    	    				tampilProses();
    	    				updateFile();
    	    				timer.schedule(task, 5000);
    	    				break;
    	    			case 2:
    	    				tampilSalah();
    	    				System.out.println("\t             TERIMA KASIH");
    	    				System.out.println();
    	                    System.out.println("\t    ANDA TELAH MENGGUNAKAN ATM BRI");
    	                    System.out.println();
    	    	            System.out.println("\t      SILAHKAN AMBIL KARTU ANDA ");
    	    				break;
    	    		}
    	        	break;
				} else {
					System.out.println();
					System.out.println();
	            	System.out.println("\t    MAAF, SALDO ANDA TIDAK CUKUP");
					transaksiLagi();
				}
			}
		}
			
	}
    
    public void transferBerhasil() {
    	 System.out.println("          ===================================================");
    	System.out.println("                       PT BANK RAKYAT INDONESIA           591               ");
    	System.out.println();
    	System.out.println();
    	System.out.println("                           TRANSAKSI ANDA");
        System.out.println("                        BERHASIL DILAKSANAKAN");
        System.out.println("                            TERIMA KASIH");
        System.out.println("                        ATAS KEPERCAYAAN ANDA ");
    	
	   System.out.println();
	    transaksiLagi();
    }
        
    	
    public void updateFile() {
    	try {
			FileWriter fw = new FileWriter("dataNasabah.txt", false);
			
			String text;
			sb = new StringBuilder();
			int last = arrNsh.length;
			for (int i = 0; i < arrNsh.length; i++) {
				sb.append(arrNsh[i].getPin());
				sb.append("~");
				sb.append(arrNsh[i].getSaldo());
				sb.append("~");
				sb.append(arrNsh[i].getNoRek());
				sb.append("~");
				sb.append(arrNsh[i].getNama());
				if (i != last) {
					sb.append(" ");
				}
			}
			sb.append(".");
			text = sb.toString();
			
			fw.write(text);
			fw.flush();
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    public void updateFileLogin() {
    	try {
			FileWriter fw = new FileWriter("transferBankLain.txt", false);
			
			String text;
			sb = new StringBuilder();
			int last = arrTransfer.length;
			for (int i = 0; i < arrTransfer.length; i++) {
				sb.append(arrTransfer[i].getKodeBank());
				sb.append("~");
				sb.append(arrTransfer[i].getNoRek());
				sb.append("~");
				sb.append(arrTransfer[i].getKesempatanLogin());
				if (i != last) {
					sb.append(" ");
				}
			}
			sb.append(".");
			text = sb.toString();
			
			fw.write(text);
			fw.flush();
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    public void transaksiLagi() {
        int pilihan;
        System.out.print("                  TRANSAKSI LAGI?         YA => [1]");
        System.out.println();
        System.out.println("                                       TIDAK => [2]");
        System.out.println("          ===================================================");
        pilihan = sc.nextInt();
        System.out.println();
        switch (pilihan) {
			case 1:
				login();
				break;

			case 2:
				tampilSalah();
				System.out.println("\t             TERIMA KASIH");
				System.out.println();
                System.out.println("\t    ANDA TELAH MENGGUNAKAN ATM BRI");
                System.out.println();
	            System.out.println("\t      SILAHKAN AMBIL KARTU ANDA ");
				break;
		}
        
    }
    
    public static void main(String[] args) {
        ProgramATM program = new ProgramATM();
        program.cekNoRek();
    }
 
    
    
    
 
//    ---------------------------- Menu Transfer
	    
 
    //------------------------------------------ Menu penarikan
	    
	 
	    
//	 
//	    public void pembayaran() {
//	        String warn;
//	        double saldo = atm.getSaldo();
//	        Scanner scan = new Scanner(System.in);
//	        System.out.println("=======================================================");
//	        System.out.print("|");
//	        System.out.print("\t           Pembayaran                         |");
//	        System.out.println();
//	        System.out.println("=======================================================");
//	        System.out.print("|");
//	        System.out.print("\t       > Pilih Transaksi <                    |");
//	        System.out.println();
//	        System.out.print("|");
//	        System.out.print(" [1] Tagihan Listrik          [2] Tagihan Telefon    |");
//	        System.out.println();
//	        System.out.println("=======================================================");
//	        System.out.print("\t         Masukan pilihan Anda : ");
//	        pil = s.nextInt();
//	        switch (pil) {
//	            case 1:
//	                System.out.println("=======================================================");
//	                System.out.print("|");
//	                System.out.print("\t            Tagihan Listrik                   |"
//	                        + "                |");
//	                System.out.println();
//	                System.out.println("=======================================================");
//	                System.out.println("No. Pembayaran : 278889192828");
//	                System.out.println("Tanggal pembayaran : 20-22 November 2014");
//	                System.out.println("Jumlah Biaya : Rp.200.000");
//	                System.out.print("\t      Lakukan Pembayaran ? [y/n] ");
//	                warn = scan.nextLine();
//	                if (warn.equalsIgnoreCase("y")) {
//	                    if (atm.getSaldo() < 50000) {
//	                        System.out.println("\tMaaf Saldo Anda tidak mencukupi.");
//	                    } else {
//	                        saldo -= 200000;
//	                        if (saldo < minSaldo) {
//	                            System.out.println("\tMaaf, jumlah pembayaran terlalu besar");
//	                            System.out.println("\t    Sisa saldo tidak mencukupi");
//	                        } else {
//	                            atm.setSaldo(saldo);
//	                            System.out.println("Anda telah berhasil melakukan pembayaran sebesar : Rp. 200.000");
//	                            System.out.println("\t       Sisa Saldo adalah :" + saldo);
//	                        }
//	                        transaksiLagi();
//	                    }
//	                } else if (warn.equalsIgnoreCase("n")) {
//	                    System.out.println("=======================================================");
//	                    System.out.println("\tAnda Telah melakukan Pembayaran");
//	                    System.out.println("\t      Silahkan Ambil kartu Anda.");
//	                }
//	                break;
//	            case 2:
//	                System.out.println("=======================================================");
//	                System.out.print("|");
//	                System.out.print("\t            Tagihan Telefon                   |"
//	                        + "                |");
//	                System.out.println();
//	                System.out.println("=======================================================");
//	                System.out.println("No. Pembayaran : TL-9877767788");
//	                System.out.println("Tanggal pembayaran : 19-20 November 2014");
//	                System.out.println("Jumlah Biaya : Rp.347.000");
//	                System.out.print("\t      Lakukan Pembayaran ? [y/n] ");
//	                warn = scan.nextLine();
//	                if (warn.equalsIgnoreCase("y")) {
//	                    if (atm.getSaldo() < 50000) {
//	                        System.out.println("\tMaaf Saldo Anda tidak mencukupi.");
//	                    } else {
//	                        saldo -= 347000;
//	                        if (saldo < minSaldo) {
//	                            System.out.println("\tMaaf, jumlah pembayaran terlalu besar");
//	                            System.out.println("\t    Sisa saldo tidak mencukupi");
//	                        } else {
//	                            atm.setSaldo(saldo);
//	                            System.out.println("Anda telah berhasil melakukan pembayaran sebesar : Rp. 347.000");
//	                            System.out.println("\t       Sisa Saldo adalah :" + saldo);
//	                        }
//	                        transaksiLagi();
//	                    }
//	                } else if (warn.equalsIgnoreCase("n")) {
//	                    System.out.println("=======================================================");
//	                    System.out.println("\tAnda Telah melakukan Pembayaran");
//	                    System.out.println("\t      Silahkan Ambil kartu Anda.");
//	                }
//	            default:
//	                pembayaran();
//	                break;
//	        }
//	    }
 
    //----------------------------------------------- Transaksi lagi ?
}
