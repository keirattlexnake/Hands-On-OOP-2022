package NasabahArsip;


import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Functions {
	    Scanner sc = new Scanner(System.in);
	    
	    public String Masuk(){
			Nasabah R = new Nasabah();
			String norek, pin;
			Boolean ketemu=false;

			System.out.println("========== Masuk ATM ACBBA ======");     
			ObjectInputStream in = null;
			 // try dalam try
			try {
				// 1. buka file untuk dibaca	
				in=new ObjectInputStream(new FileInputStream
	    		   ("C:\\mydata/Nasabah.dat"));
	       Object curR = in.readObject();	
	       try {	
	    	   Scanner sc = new Scanner(System.in);
	       // 2. baca dan proses setiap record yang dibaca  
	    	   System.out.print("Selamat datang silahkan mengisi data berikut");
	    	   System.out.print("Masukan Norek :  "); norek=sc.next();
	    	   System.out.print("Masukan PIN :  "); pin=sc.next();
		
	    	   while (ketemu==false) {
	        	   R = (Nasabah) curR; //inputstream -> objek customer
	        	   // cek apakah norek ada?
	        	  if(R.getNorek().contentEquals(norek)){
	        		  ketemu=true;
	        	  }
	        	   // kalo ada masuk ke sini
	        	  if(ketemu==true) {
	  	        		int coba=1;
	  	        	  do {
	  	              	  if(R.getPin().contentEquals(pin)) {
	  	              		  return norek;
	  		        	  }else {
	  		        		  System.out.println("Password yang anda masukan salah,silahkan coba lagi!");
	  		        		  if(coba==1) {
	  		        			  System.out.println("anda mempunyai 3x percobaan, silahkan ulangi");		        			  
	  		        		  }
	  		        		  if(coba<=3) {
	  		        			  System.out.println("Percobaan ke-"+coba);
	  		        			  System.out.print("Masukan PIN :  "); pin=sc.next();
	  		        			  coba++;
	  		        		  }
	  		        	  }
	  	        	  }while(coba<=3);
	  	        	 System.out.println("=== Mohon maaf norek anda terblokir, hubungi cs segera ===");
	  	          }
	        	  
	        	  curR = in.readObject();
	         }    
	          
	      
	       } catch (EOFException e) {}
	             System.out.println("");	
	             System.out.println("NOREK tidak ditemukan");
	       } catch (ClassNotFoundException e) {
	             System.out.println("Class tidak ditemukan.");
	       } catch (IOException e) {
	             e.printStackTrace();
	      }   
		return "";	
	    }
	
	    // Cek Saldo
		public void MelihatSaldo(String norek){
		    Nasabah R = new Nasabah();
		    Boolean ketemu=false;
		    System.out.println("====== Cek Saldo ======");     
		    ObjectInputStream in = null;
		    try {
		     // 1. buka file untuk dibaca	
		       in=new ObjectInputStream(new FileInputStream
		    		   ("C:\\mydata/Nasabah.dat"));
		       Object curR = in.readObject();					
		       try {	
		       // 2. baca dan proses setiap record yang dibaca                 
		    	   while (ketemu==false) {
			        	   R = (Nasabah) curR; //inputstream -> objek customer
			        	  if(R.getNorek().contentEquals(norek)){
			        		  ketemu=true;
			        	  }
			        	  
			        	  if(ketemu==true) {
			        		  System.out.println("Saldo Anda : Rp."+ R.getSaldo());
			        	  }
			        	  
			        	  curR = in.readObject(); // baca lagi...
			         } 
		       } catch (EOFException e) {}
		             System.out.println("");				
		       } catch (ClassNotFoundException e) {
		             System.out.println("Class tidak ditemukan.");
		       } catch (IOException e) {
		             e.printStackTrace();
		      }           
		    }
		
		// Stor Saldo
		public void MengstorSaldo(String norek){
		    Nasabah R = new Nasabah();
		    Boolean ketemu=false;
		    float saldo;
		    System.out.println("====== Stor Saldo ======");     
		    ObjectInputStream in = null; // read
		    ObjectOutputStream out = null; // write
		    // TRY DI DALAM TRY APABILA IN OUT NYA MASING MASING
		    try {
		       try {
		    	// ISI FILE UTAMA DI BACKUP KE TEMP
		    	// 1. buka file untuk dibaca	
			       in=new ObjectInputStream(new FileInputStream
			    		   ("C:\\mydata/Nasabah.dat"));
			    		   Object curR = in.readObject();
			       // 2. buka file untuk ditulis	
			    	out=new ObjectOutputStream(new FileOutputStream
			    		              ("C:\\mydata/Temp.dat"+ ""));// nama direktori+file
		       // 2. baca dan proses setiap record yang dibaca    
		     	   while (true) {
		        	   R = (Nasabah) curR; //inputstream -> objek customer
		        	   R=new Nasabah(R.getNorek(),R.getPin(),R.getNama(),R.getSaldo());    
		               out.writeObject(R); 
		        	   curR = in.readObject(); // baca lagi...
		           }   
		       } catch (EOFException e) {}
		       
				try {
					// FILE TEMP DI UBAH LALU DI PINDAHKAN KEMBALI KE FILE UTAMA
				 	// 1. buka file untuk dibaca	
				    in=new ObjectInputStream(new FileInputStream
				    		   ("C:\\mydata/Temp.dat"));
				    		   Object curR = in.readObject();
				    // 2. buka file untuk ditulis	
				    out=new ObjectOutputStream(new FileOutputStream
				    		   ("C:\\mydata/Nasabah.dat"+ ""));// nama direktori+file
			       BufferedReader brInput= new BufferedReader
		                    (new InputStreamReader(System.in)); 
			       
			       System.out.print("1.Lanjut/0.Batal:  ");int x=sc.nextInt();
			       if(x!=0) { 
			    	   do {
				    	   System.out.print("Stor Saldo (min 50k) :  ");saldo=sc.nextFloat();
	        		   }while(saldo<50000);
					  // 2. baca dan proses setiap record yang dibaca    
			     		   while (true) {
				        	   R = (Nasabah) curR; //inputstream -> objek customer
				        	   
				        	   if(!R.getNorek().contentEquals(norek)) {
				        		   R=new Nasabah(R.getNorek(),R.getPin(),R.getNama(),R.getSaldo()); 
				        	   }else {
				        		   saldo=R.getSaldo()+saldo;
				        		   R=new Nasabah(R.getNorek(),R.getPin(),R.getNama(),saldo); 
				        		   
				        	   }
				               out.writeObject(R); 
				        	   curR = in.readObject(); // baca lagi...
			     		   }   
			       }else {
			    	   // 2. baca dan proses setiap record yang dibaca    
		     		   while (true) {
			        	   R = (Nasabah) curR; //inputstream -> objek customer
			        	   R=new Nasabah(R.getNorek(),R.getPin(),R.getNama(),R.getSaldo()); 
			               out.writeObject(R); 
			        	   curR = in.readObject(); // baca lagi...
		     		   }  
			       }
				}catch (EOFException e) {}
				 System.out.println("");	
		       } catch (ClassNotFoundException e) {
		             System.out.println("Class tidak ditemukan.");
		       } catch (IOException e) {
		             e.printStackTrace();
		      }  
		    }	
		
		// Tarik Saldo
		public void MenarikSaldo(String norek){
		    Nasabah R = new Nasabah();
		    Boolean ketemu;
		    float saldo;
		    System.out.println("====== Tarik Saldo ======");     
		    ObjectInputStream in = null; // read
		    ObjectOutputStream out = null; // write
		    // TRY DI DALAM TRY APABILA IN OUT NYA MASING MASING
		    try {
		       try {
		    	 // CEK SALDO NYA CUKUP ATAU ENGGA
		    	// 1. buka file untuk dibaca	
			       in=new ObjectInputStream(new FileInputStream
			    		   ("C:\\mydata/Nasabah.dat"));
			       // 2. buka file untuk ditulis	
			    	out=new ObjectOutputStream(new FileOutputStream
			    		              ("C:\\mydata/Temp.dat"+ ""));// nama direktori+file
		    	   Object curR = in.readObject();
		       // 2. baca dan proses setiap record yang dibaca  
			    	 while (true) {
			    		 ketemu=false;
			        	   R = (Nasabah) curR; //inputstream -> objek customer
			        	  if(R.getNorek().contentEquals(norek)){
			        		  ketemu=true;
			        	  }
			        	  
			        	  if(ketemu==true&&R.getSaldo()<50000) {
			        			 System.out.println("Mohon maaf saldo anda kurang!");
			        			 System.out.println("");
			        			 return;
			        	  }  
			        	  
			        	  curR = in.readObject(); // baca lagi...
			        } 
		       } catch (EOFException e) {}
		       System.out.print("1.Lanjut/0.Batal: ");int x=sc.nextInt();
		       if(x!=0) {
		       try {	
		    	// KALO CUKUP ISI FILE UTAMA DI BACKUP KE TEMP
		    	// 1. buka file untuk dibaca	
			       in=new ObjectInputStream(new FileInputStream
			    		   ("C:\\mydata/Nasabah.dat"));
			       // 2. buka file untuk ditulis	
			    	out=new ObjectOutputStream(new FileOutputStream
			    		              ("C:\\\\mydata/Temp.dat"+ ""));// nama direktori+file
				    Object curR = in.readObject();
			       // 2. baca dan proses setiap record yang dibaca  
			     	   while (true) {
			        	   R = (Nasabah) curR; //inputstream -> objek customer
			        	   R=new Nasabah(R.getNorek(),R.getPin(),R.getNama(),R.getSaldo());    
			               out.writeObject(R); 
			        	   curR = in.readObject(); // baca lagi...
			           }   
			       } catch (EOFException e) {}
				try {
					// FILE TEMP DI UBAH LALU DI PINDAHKAN KEMBALI KE FILE UTAMA					
				 	// 1. buka file untuk dibaca	
				     in=new ObjectInputStream(new FileInputStream
				    		   ("C:\\\\mydata/Temp.dat"));
				    		   Object curR = in.readObject();
				    // 2. buka file untuk ditulis	
				    out=new ObjectOutputStream(new FileOutputStream
				    		   ("C:\\mydata/Nasabah.dat"+ ""));// nama direktori+file
			       BufferedReader brInput= new BufferedReader
		                    (new InputStreamReader(System.in)); 
					 
					  // 2. baca dan proses setiap record yang dibaca    
			     		   while (true) {
				        	   R = (Nasabah) curR; //inputstream -> objek customer
				        	   if(!R.getNorek().contentEquals(norek)) {
				        		   R=new Nasabah(R.getNorek(),R.getPin(),R.getNama(),R.getSaldo()); 
				        	   }else {
				        		   do {
							    	   System.out.print("Tarik Saldo (min 50k) :  ");saldo=sc.nextFloat();
							    	   if(saldo>R.getSaldo()) {
							    		   System.out.println("Maaf saldo anda tidak mencukupi");
							    		   System.out.println("Sisa Saldo Anda : Rp."+ R.getSaldo());
							    		   System.out.println("----------");
							    		   System.out.print("1.Lanjut/0.Batal: ");x=sc.nextInt();
							    		   System.out.println("");
							    		   if(x==0) {
							    			   R=new Nasabah(R.getNorek(),R.getPin(),R.getNama(),R.getSaldo());
								    		   out.writeObject(R); 
								    		   return;
							    		   }
							    	   }
				        		   }while(saldo<50000||saldo>R.getSaldo());
				        		   saldo=R.getSaldo()-saldo;
				        		   R=new Nasabah(R.getNorek(),R.getPin(),R.getNama(),saldo);
				        		   System.out.println("Saldo berhasil ditarik");
				        		   System.out.println("Sisa Saldo Anda : Rp."+ R.getSaldo());
				        	   }
				               out.writeObject(R); 
				        	   curR = in.readObject(); // baca lagi...
			     		   }   
				}catch (EOFException e) {}   
		       }
		       // endif
		       
		  	 System.out.println("");
		       } catch (ClassNotFoundException e) {
		             System.out.println("Class tidak ditemukan.");
		       } catch (IOException e) {
		             e.printStackTrace();
		      }  
		    }	
		
}
// end functions

