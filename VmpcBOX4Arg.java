package com.eszproject;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JCheckBox;


public class VmpcBOX4Arg extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public static int dlBuf = 4096;
    private JButton btnSzyfrDoPliku, btnOdszDoPliku,btnOdszOkno, btnKoniec, btnInfo, btnCzysc, btnSzyfrWewPliku,btnOdSzyfrWewPliku,btnZaszyfrujPoleTekstowe,btnwymazPlik;
    AlgorytmVmpc4A vmpcAlg = new AlgorytmVmpc4A();
    private  JPasswordField passwordField;
	private static char[] haslo ;
	private JTextArea textArea;
   

    public VmpcBOX4Arg() {
        setTitle("Szyfr VMPC ver 4.1 © Sz. Panek");
        getContentPane().setBackground(new Color(0,0,65));
        createGui();
    }
    public void createGui() {
       
    	 btnSzyfrDoPliku = new JButton("<html>Szyfruj do pliku</html>");
         btnSzyfrDoPliku.setBounds(10, 10, 110, 36);
         btnSzyfrDoPliku.setForeground(Color.WHITE);
         btnSzyfrDoPliku.setBackground(Color.BLUE);
         btnSzyfrDoPliku.setFont(new Font("Tahoma", Font.PLAIN, 10));
         btnSzyfrDoPliku.addActionListener(this);
         getContentPane().setLayout(null);
         getContentPane().add(btnSzyfrDoPliku);

         btnSzyfrWewPliku = new JButton("<html>Szyfruj <br />wewnątrz pliku</html>");
         btnSzyfrWewPliku.setBounds(10, 50, 110, 36);
         btnSzyfrWewPliku.setForeground(Color.WHITE);
         btnSzyfrWewPliku.setBackground(Color.BLUE);
         btnSzyfrWewPliku.setFont(new Font("Tahoma", Font.PLAIN, 10));
         btnSzyfrWewPliku.addActionListener(this);
         getContentPane().add(btnSzyfrWewPliku);

         btnOdSzyfrWewPliku = new JButton("<html>Odszyfruj <br />wewnatrz pliku</html>");
         btnOdSzyfrWewPliku.setBounds(125, 50, 110, 36);
         btnOdSzyfrWewPliku.setBackground(Color.ORANGE);
         btnOdSzyfrWewPliku.setFont(new Font("Tahoma", Font.PLAIN, 10));
         btnOdSzyfrWewPliku.addActionListener(this);
         getContentPane().add(btnOdSzyfrWewPliku);

         btnCzysc = new JButton("Wyczyść okna");
         btnCzysc.setBounds(240, 91, 110, 36);
         btnCzysc.setFont(new Font("Tahoma", Font.PLAIN, 10));
         btnCzysc.addActionListener(this);
         getContentPane().add(btnCzysc);

         btnOdszOkno = new JButton("Odsz. w oknie");
         btnOdszOkno.setBounds(125, 91, 110, 36);
         btnOdszOkno.setBackground(Color.ORANGE);
         btnOdszOkno.setFont(new Font("Tahoma", Font.PLAIN, 10));
         btnOdszOkno.addActionListener(this);
         getContentPane().add(btnOdszOkno);

         btnKoniec = new JButton("X");
         btnKoniec.setBounds(297, 10, 53, 36);
         btnKoniec.setBackground(Color.RED);
         btnKoniec.setFont(new Font("Tahoma", Font.PLAIN, 15)); btnKoniec.addActionListener(this);
         getContentPane().add(btnKoniec);

         btnOdszDoPliku = new JButton("Odsz. do pliku");
         btnOdszDoPliku.setBounds(125, 10, 110, 36);
         btnOdszDoPliku.setBackground(Color.ORANGE);
         btnOdszDoPliku.setFont(new Font("Tahoma", Font.PLAIN, 12));
         btnOdszDoPliku.addActionListener(this);
         getContentPane().add(btnOdszDoPliku);

         btnInfo = new JButton("Info");
         btnInfo.setBounds(241, 10, 53, 36);
         btnInfo.setFont(new Font("Tahoma", Font.PLAIN, 10));
         btnInfo.addActionListener(this);
         getContentPane().add(btnInfo);
         
         btnZaszyfrujPoleTekstowe = new JButton("<html>Zaszyfruj <br />pole tekstowe</html>");
         btnZaszyfrujPoleTekstowe.setBounds(10, 91, 110, 36);
         btnZaszyfrujPoleTekstowe.setBackground(Color.BLUE);
         btnZaszyfrujPoleTekstowe.setForeground(Color.WHITE);
         btnZaszyfrujPoleTekstowe.setFont(new Font("Tahoma", Font.PLAIN, 10));
         btnZaszyfrujPoleTekstowe.addActionListener(this);
         getContentPane().add(btnZaszyfrujPoleTekstowe);
  
         btnwymazPlik = new JButton("Wymaż plik");
         btnwymazPlik.setFont(new Font("Tahoma", Font.PLAIN, 12));
         btnwymazPlik.setBackground(Color.MAGENTA);
         btnwymazPlik.setBounds(240, 51, 110, 36);
         btnwymazPlik.addActionListener(this);
         getContentPane().add(btnwymazPlik);
         
 
 passwordField = new JPasswordField();
 passwordField.addKeyListener(new KeyAdapter() {
 	@Override
 	public void keyTyped(KeyEvent e) {
 		if (String.valueOf(passwordField.getPassword()).trim().length() > 15) 
 			passwordField.setText(String.valueOf(passwordField.getPassword()).trim().substring(0, 15));
 	}
 });
 passwordField.setBackground(Color.LIGHT_GRAY);
 passwordField.setBounds(10, 134, 225, 24);
 passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
 passwordField.setForeground(Color.BLACK);
 passwordField.setToolTipText("podaj haslo");
 getContentPane().add(passwordField);
 
 JCheckBox chckbxNewCheckBox = new JCheckBox("pokaz haslo");
 chckbxNewCheckBox.setBounds(240, 134, 110, 25);

 chckbxNewCheckBox.addActionListener(new ActionListener() {
 	public void actionPerformed(ActionEvent e) {
 		if (chckbxNewCheckBox.isSelected())
 		{
 			passwordField.setEchoChar((char)0);

 	}else
 	{
 		passwordField.setEchoChar('*');
 	}
 	}
 });
 getContentPane().add(chckbxNewCheckBox);
 
 JScrollPane scrollPane = new JScrollPane();
 scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
 scrollPane.setBounds(10, 169, 340, 340);
 getContentPane().add(scrollPane);
 
 textArea = new JTextArea();
 scrollPane.setViewportView(textArea);
    }

    ////////////////////////////////////////////////////////////////      akcje przycisku
    public void actionPerformed(ActionEvent e)
    {
    	haslo = passwordField.getPassword();

        // #########################################################     // szyfrowanie wew pliku
        if (e.getSource() == btnSzyfrWewPliku )                              
        {
      	   JFileChooser chooser = new JFileChooser();
           chooser.setDialogTitle("Wybierz plik.");

           int result = chooser.showOpenDialog(null);
           if (result != JFileChooser.APPROVE_OPTION){
               return;
           }
       
    
           if (result != JFileChooser.APPROVE_OPTION){
               return;
           }
           String plik_wej   =  chooser.getSelectedFile().getName();
           String parentfile = chooser.getSelectedFile().getParent();
           String pelnyPlik = parentfile + "\\" + plik_wej;
    SzyfrujWewPliku(pelnyPlik);

        Toolkit.getDefaultToolkit().beep();

        //   JOptionPane.showMessageDialog(null, "Wynik w pliku *.szr");

        }
        // #########################################################     // szyfrowanie wew pliku

        // #########################################################     // szyfrowanie do pliku
        if (e.getSource() == btnSzyfrDoPliku )                            
        {
        	   JFileChooser chooser = new JFileChooser();
               chooser.setDialogTitle("Wybierz plik.");

               int result = chooser.showOpenDialog(null);
               if (result != JFileChooser.APPROVE_OPTION){
                   return;
               }
           
        
               if (result != JFileChooser.APPROVE_OPTION){
                   return;
               }
               String plik_wej   =  chooser.getSelectedFile().getName();
               String parentfile = chooser.getSelectedFile().getParent();
               String pelnyPlikWej = parentfile + "\\" + plik_wej;
               String PlikWyj = pelnyPlikWej + ".szr";
               
        SzyfrujDoPliku(pelnyPlikWej,PlikWyj);

            Toolkit.getDefaultToolkit().beep();

            //   JOptionPane.showMessageDialog(null, "Wynik w pliku *.szr");

        }
// ######################################################### szyfrowanie do pliku

        // ######################################################### // wymaz plik
        if (e.getSource() == btnwymazPlik )                                 
        {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Wybierz plik.");

            int result = chooser.showOpenDialog(null);
            if (result != JFileChooser.APPROVE_OPTION){
                return;
            }
        
     
            if (result != JFileChooser.APPROVE_OPTION){
                return;
            }
            String plik_wej   =  chooser.getSelectedFile().getName();
            String parentfile = chooser.getSelectedFile().getParent();
            String pelnyPlik = parentfile + "\\" + plik_wej;
            
            WymazPlik(pelnyPlik);
           
            Toolkit.getDefaultToolkit().beep();


        }
// ######################################################### // wymaz plik

// #########################################################  // Odszyfruj wew pliku
        if (e.getSource() == btnOdSzyfrWewPliku )                         
        {


            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "pliki zaszyfrowane VMPC", "szr", "SZR");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
             if (returnVal != JFileChooser.APPROVE_OPTION){
                return;
            }
            
        //    File in = chooser.getSelectedFile();             //
            String plik_wej   =  chooser.getSelectedFile().getName();
            String plik_wyj = null;
            String parentfile = chooser.getSelectedFile().getParent();

            if(plik_wej.length() >3) {
                String koncowka = plik_wej.substring(plik_wej.length() - 4);
                if (koncowka.equals(".szr"))
                    plik_wyj   = parentfile+ "\\Odsz-"+ plik_wej.substring(0, plik_wej.length()-4);  //odcina 4 ostatnie czyli .szr
                else
                    plik_wyj   = parentfile+ "\\Odsz-"+ plik_wej;
            }

            else
                plik_wyj   = parentfile+ "\\Odsz-"+ plik_wej;
            
            plik_wej   = parentfile + "\\"+  plik_wej;			// dopisujemy sciezke do pliku wejsciowego

           OdszyfrujWewPliku(plik_wej,plik_wyj);


        }
//#########################################################     // Odszyfruj wew pliku

// #########################################################     // Odszyfruj do pliku 
        if (e.getSource() == btnOdszDoPliku )                         
        {


            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "pliki zaszyfrowane VMPC", "szr", "SZR");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
             if (returnVal != JFileChooser.APPROVE_OPTION){
                return;
            }

            String plik_wej   =  chooser.getSelectedFile().getName();
            String plik_wyj = null;
            String parentfile = chooser.getSelectedFile().getParent();

            if(plik_wej.length() >3) {
                String koncowka = plik_wej.substring(plik_wej.length() - 4);
                if (koncowka.equals(".szr"))
                    plik_wyj   = parentfile+ "\\Odsz-"+ plik_wej.substring(0, plik_wej.length()-4);  //odcina 4 ostatnie czyli .szr
                else
                    plik_wyj   = parentfile+ "\\Odsz-"+ plik_wej;
            }

            else
                plik_wyj   = parentfile+ "\\Odsz-"+ plik_wej;
         
            plik_wej   = parentfile + "\\"+  plik_wej;			// dopisujemy sciezke do pliku wejsciowego
    		

           OdszyfrujDoPliku(plik_wej,plik_wyj);
  
        }
//#########################################################     //Odszyfruj do pliku
        
//#########################################################   //Odszyfruj w oknie
        if (e.getSource() == btnOdszOkno ) {

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "pliki zaszyfrowane VMPC", "szr", "SZR");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
             if (returnVal != JFileChooser.APPROVE_OPTION){
                return;
            }

            String plik_wej   =  chooser.getSelectedFile().getName();
            String parentfile = chooser.getSelectedFile().getParent(); 
            plik_wej   = parentfile + "\\"+  plik_wej;			// dopisujemy sciezke do pliku wejsciowego
    		
       
            try (   RandomAccessFile fin = new RandomAccessFile(plik_wej, "rw"))
            	{

            	int dlugoscOrg = (int) fin.length();
                int dlugosc = (int) fin.length() -16;        // minus vector
                //       System.out.println("dlug0 = " + dlugosc);
                int data;
                byte[] vector = new byte[16];
                byte[] bufor = new byte[dlBuf];
                  
                fin.seek(dlugosc);
                fin.read(vector, 0, 16);
                vmpcAlg.VzB2int(vector);       // zapis odczytanego vectora z pliku do tablicy z byte na int
                vmpcAlg.WczytajHaslo(haslo);	//wczytanie hasla
                vmpcAlg.KSA();
                QTimer t = new QTimer();        // Start pomiaru czasu
         
               fin.setLength(dlugosc);            // pomniejszamy o wektor
                fin.seek(0);   // pozycjonujemy plik na początek
                while ((data = fin.read(bufor)) != -1 ) {   //	
                	   String str = new String(vmpcAlg.VMPCODSZ(bufor),0,data);
                       textArea.setText( str);
                	
                                  }
                
                for (int i =0;i< 16 ;i++)			 // zapis wektora inicjującego V na koЯcu pliku 16 bajtów
                	fin.write(vmpcAlg.V[i]);
                
                fin.close();
               vmpcAlg.CzyscTablice();

                
                     JOptionPane.showMessageDialog(null,"Gotowe, czas: " +  t.getElapsed() + " ms");
             	} catch (IOException x) {
            			x.printStackTrace();
            		     System.out.println("jestem w dupie");	
            		}
                }

//######################################################### //Odszyfruj w oknie
      //btnZaszyfrujPoleTekstowe
      //#########################################################   //Zaszyfruj PoleTekstowe
        if (e.getSource() == btnZaszyfrujPoleTekstowe ) {
            JFileChooser fc = new JFileChooser();
           fc.showSaveDialog(this);
           String   	dir = "" +	fc.getSelectedFile();
           File out = new File(dir + ".szr");
           String tekst = textArea.getText();
           int data;
           
   
           byte [] bufor = new byte[dlBuf];
           InputStream is = new ByteArrayInputStream(tekst.getBytes());  

  
            try( FileOutputStream fo = new FileOutputStream(out))
            {
                         
                           
                vmpcAlg.Los(256,16);          //wektor inicjujący
                vmpcAlg.WczytajHaslo(haslo);	//wczytanie hasla
                vmpcAlg.KSA();
                QTimer t = new QTimer();        // Start pomiaru czasu
            //    bufferedReader.read(haslo);
                
                
                while ((data = is.read(bufor)) != -1 ) {   
             	 	fo.write(vmpcAlg.VMPCSZR(bufor), 0, data);
                                    }
                	
                           
             // zapis wektora inicjującego V na koncu pliku 16 bajtów
                for (int i =0;i< 16 ;i++)
                  fo.write(vmpcAlg.V[i]);

                vmpcAlg.CzyscTablice();
        
                fo.close();
                is.close();
               
                JOptionPane.showMessageDialog(null,"Gotowe, czas: " +  t.getElapsed() + " ms");
            }
            catch (IOException f) //Catch the IO error and print out the message
            {
            //   System.out.println("Error message: " + f.getMessage());
            }
            Toolkit.getDefaultToolkit().beep();

        }

//######################################################### //Zaszyfruj PoleTekstowe
        if (e.getSource() == btnKoniec)
        {
        	haslo=null;
         JOptionPane.showMessageDialog(null,"Autor Szczepan Panek,\nver 4.0 2021r.");
            System.exit(0);

        }

        if (e.getSource() == btnCzysc)
        {
           textArea.setText("");
           passwordField.setText("");
           haslo = null;
           vmpcAlg.CzyscTablice();
        }

        if (e.getSource() == btnInfo)
        {
            textArea.setText("Autor Szczepan Panek ver. 4.1 2022 r.\n\n" +
                    "Program szyfruje plik funkcją VMPC i zapisuje w tym samym\n" +
                    "katalogu z rozszerzeniem szr. Zaszyfrowany plik\n" +
                    "odszyfrowujemy w oknie lub w pliku zaczynającym\n" +
                    "się na Odsz-* w lini poleceń Vmpc.jar plik.txt \n" +
                    "otrzymujemy plik.txt.szr (zaszyfrowany) w lini \n" +
                    "poleceń Vmpc.jar plik.txt.szr otrzymujemy plik.txt\n" +
                    "(odszyfrowany)\n\n" +
                    "Funkcja VMPC - jak to działa\n" +
                    "Cecha jednokierunkowości funkcji VMPC jest niemal\n" +
                    "paradoksalna - przekształcenie jednego bajtu informacji\n" +
                    "wymaga jedynie trzech podstawowych instrukcji MOV\n " +
                    "procesora, zajmujących jeden cykl zegarowy każda,\n" +
                    "natomiast odwrócenie tak prostego przekształcenia wymaga\n" +
                    "wykonania średnio około 2^260 operacji, co jest liczbą \n" +
                    "niewyobrażalnie wielką. Uznany dziś za całkowicie\n" +
                    "wystarczający poziom bezpieczeństwa kryptograficznego\n" +
                    "to 2^128, a więc mniej niż pierwiastek z 2^260. \n" +
                    "Dla zilustrowania, jak duża jest to liczba, wyobraźmy\n" +
                    "sobie superprocesor, który ma rozmiary jednego atomu wodoru\n" +
                    "(ok. 10^-10m) i który pracuje z częstotliwością 1 mld GHz\n" +
                    "(10^18 operacji na sekundę).Wyobraźmy sobie superkomputer,\n" +
                    "który składa się z takich procesorów rozłożonych na całej\n" +
                    "powierzchni planety Ziemia (ok. 5,1*10^8 km^2) i który ma za\n" +
                    "zadanie odwrócić funkcję VMPC. Musiałby on pracować\n" +
                    "miliard miliardów lat (10^18), aby zakończyć swoje zadanie.\n" +
                    "Jednocześnie gdyby nasz superkomputer zajmował powierzchnię\n" +
                    "2 metrów kwadratowych, złamałby dzisiejsze standardowe algorytmy, \n" +
                    "jak AES (z 128 bitowym kluczem) w ciągu jednej sekundy. \n" +
                    "Oczywiście taki komputer nie istnieje i 128 bitów to wciąż \n" +
                    "bardzo wysoki poziom bezpieczeństwa, ale porównanie to daje \n" +
                    "pogląd na siłę funkcji VMPC. Z matematycznego punktu widzenia\n" +
                    "funkcja VMPC jest połączeniem podstawowych operacji na\n" +
                    "permutacjach z podstawowymi operacjami arytmetycznymi \n" +
                    "na liczbach całkowitych, na przykład z operacją dodawania.\n\n");
        }
    }
////////////////////////////////////////////////////////////////akcje przycisku


  static void  OdszyfrujDoPliku(String sin, String sout ){
      AlgorytmVmpc4A vmpcAlg = new AlgorytmVmpc4A();

 	 try (   RandomAccessFile  fin = new RandomAccessFile(sin, "r"); 
 			 RandomAccessFile fout = new RandomAccessFile(sout, "rw") ){
	
 	    int dlugosc = (int) fin.length();
        int data;
        byte[] vector = new byte[16];
        byte[] bufor = new byte[dlBuf];
        
        fin.seek(dlugosc - 16);		// dlugosc pomniejszona o vector z konca pliku
        fin.read(vector, 0, 16);
        vmpcAlg.VzB2int(vector);       // zapis odczytanego vectora z pliku do tablicy z byte na int
        vmpcAlg.WczytajHaslo(haslo);	//wczytanie hasla
        vmpcAlg.KSA();
        QTimer t = new QTimer();        // Start pomiaru czasu
        
        fin.seek(0);   // pozycjonujemy plik na początek
        while ((data = fin.read(bufor)) != -1 ) {   
 	  	fout.write(vmpcAlg.VMPCODSZ(bufor), 0, data);
                 }
    fout.setLength(fin.length() -16);    // obcinamy 16 znakow wektora :)
    
    fin.close();
    fout.close();
    vmpcAlg.CzyscTablice();

    
         JOptionPane.showMessageDialog(null,"Gotowe, czas: " +  t.getElapsed() + " ms");
 	} catch (IOException e) {
			e.printStackTrace();
		}
    }
  
  static void  SzyfrujDoPliku(String sin, String sout ){
      AlgorytmVmpc4A vmpcAlg = new AlgorytmVmpc4A();

 	 try (   RandomAccessFile  fin = new RandomAccessFile(sin, "r"); 
 			 RandomAccessFile fout = new RandomAccessFile(sout, "rw") ){
	
 		   int data;
 	       int dlBuf = 4096;
 	       byte[] bufor = new byte[dlBuf];
 	       
 	       vmpcAlg.Los(256,16);          //wektor inicjujący
 	       vmpcAlg.WczytajHaslo(haslo);	//wczytanie hasla
 	       vmpcAlg.KSA();
 	       QTimer t = new QTimer();        // Start pomiaru czasu

        while ((data = fin.read(bufor)) != -1 ) {   
 	  	fout.write(vmpcAlg.VMPCSZR(bufor), 0, data);
                 }
              
    for (int i =0;i< 16 ;i++)			 // zapis wektora inicjującego V na koЯcu pliku 16 bajtów
    	fout.write(vmpcAlg.V[i]);
    
    fin.close();
    fout.close();
    vmpcAlg.CzyscTablice();

    
         JOptionPane.showMessageDialog(null,"Gotowe, czas: " +  t.getElapsed() + " ms");
 	} catch (IOException e) {
			e.printStackTrace();
		}
    }
  
  
  static void  OdszyfrujWewPliku(String sin,String sout){
      AlgorytmVmpc4A vmpcAlg = new AlgorytmVmpc4A();

 	 try (   RandomAccessFile  fin = new RandomAccessFile(sin, "rw")
 			 ){
	
 	    int dlugosc = (int) fin.length();
        int data;
        byte[] vector = new byte[16];
        byte[] bufor = new byte[dlBuf];
        
        fin.seek(dlugosc - 16);		// dlugosc pomniejszona o vector z konca pliku
        fin.read(vector, 0, 16);
        vmpcAlg.VzB2int(vector);       // zapis odczytanego vectora z pliku do tablicy z byte na int
        vmpcAlg.WczytajHaslo(haslo);	//wczytanie hasla
        vmpcAlg.KSA();
        QTimer t = new QTimer();        // Start pomiaru czasu
        
        fin.seek(0);   // pozycjonujemy plik na początek
        while ((data = fin.read(bufor)) != -1 ) {   
        	fin.seek(fin.getChannel().position() - data);
       	fin.write(vmpcAlg.VMPCODSZ(bufor), 0, data);

                 }
       fin.setLength(fin.length() -16);    // obcinamy 16 znakow wektora :)
    
    fin.close();
    File old = new File(sin);
    File newfile =new File(sout);
    old.renameTo(newfile);
    vmpcAlg.CzyscTablice();

    
         JOptionPane.showMessageDialog(null,"Gotowe, czas: " +  t.getElapsed() + " ms");
 	} catch (IOException e) {
			e.printStackTrace();
		}
    }
  
  
   
    static void  WymazPlik(String sin){
        //######################################################### // WymazPlik
       
        	QTimer t = new QTimer();        // Start pomiaru czasu
        	 try (   RandomAccessFile file = new RandomAccessFile(sin, "rw")){
    	
            int data;
            int dlBuf = 4096;
            byte[] bufor = new byte[dlBuf];
            byte[] buforpusty = new byte[dlBuf];

            Arrays.fill(buforpusty,(byte)0xff);	// wypelnia ff, czyli jedynkami

           
                while ((data = file.read(bufor)) != -1 ) {   
             	   file.seek(file.getChannel().position() - data);
                   file.write(buforpusty, 0, data);
                  
                       }
                
                 file.close();
               JOptionPane.showMessageDialog(null,"Gotowe, czas: " +  t.getElapsed() + " ms");
        	} catch (IOException e) {
    			e.printStackTrace();
    		}
        
        }
    //######################################################### // WymazPlik
    static void  SzyfrujWewPliku(String sin){
        AlgorytmVmpc4A vmpcAlg = new AlgorytmVmpc4A();

   	 try (   RandomAccessFile file = new RandomAccessFile(sin, "rw") ){
	
       int data;
       int dlBuf = 4096;
       byte[] bufor = new byte[dlBuf];
       
       vmpcAlg.Los(256,16);          //wektor inicjujący
       vmpcAlg.WczytajHaslo(haslo);	//wczytanie hasla
       vmpcAlg.KSA();
       QTimer t = new QTimer();        // Start pomiaru czasu

     //  Arrays.fill(buforpusty,(byte)0xff);	// wypelnia ff, czyli jedynkami

      
           while ((data = file.read(bufor)) != -1 ) {   
        	   file.seek(file.getChannel().position() - data);
               file.write(vmpcAlg.VMPCSZR(bufor), 0, data);
                   }
           // zapis wektora inicjującego V na końcu pliku 16 bajtów
           for (int i =0;i< 16 ;i++)
               file.write(vmpcAlg.V[i]);

           vmpcAlg.CzyscTablice();
           file.close();
       
 
          File old = new File(sin);
          File newfile =new File(sin + ".szr");
          old.renameTo(newfile);
           
           JOptionPane.showMessageDialog(null,"Gotowe, czas: " +  t.getElapsed() + " ms");
   	} catch (IOException e) {
			e.printStackTrace();
		}
    }
     
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) throws IOException {


        //######################################################### 2
        if (args.length == 2 && args[1].equals("x")) {     // zamazywanie pliku
        	QTimer t = new QTimer();        // Start pomiaru czasu
        	try {
    		String filePath = args[0];
        	RandomAccessFile file = new RandomAccessFile(filePath, "rw");
            int data;
            int dlBuf = 4096;
            byte[] bufor = new byte[dlBuf];
            byte[] buforpusty = new byte[dlBuf];

            Arrays.fill(buforpusty,(byte)0xff);	// wypelnia ff, czyli jedynkami

           
                while ((data = file.read(bufor)) != -1 ) {   
             	   file.seek(file.getChannel().position() - data);
                   file.write(buforpusty, 0, data);
                  
                       }
                
                 file.close();
               JOptionPane.showMessageDialog(null,"Gotowe, czas: " +  t.getElapsed() + " ms");
        	} catch (IOException e) {
    			e.printStackTrace();
    		}
        
        }
      //######################################################### 2


//######################################################### 0
        if (args.length == 0){

            VmpcBOX4Arg okno = new VmpcBOX4Arg();
            okno.pack();
            okno.addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
           okno.setSize(380,560);
            okno.setLocation(200,200);
            okno.setVisible(true);
        }
//######################################################### 0
    }
}