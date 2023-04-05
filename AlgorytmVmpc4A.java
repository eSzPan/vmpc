package com.eszproject;




import java.util.Arrays;


class AlgorytmVmpc4A {

    /////////////////////////////////////////////////////////////////////////////////
    // KSA	//////////////////////////////////////////////////////////////////////
    public int[] P = new int[257];
    public int c = 16;
    public int K [] = new int[16];                   //has³o
    public int h,m,n,x,s;
    public int z = 16;
    public int V [] = new int[16];                          //  wektor inicjuj¹cy



    void CzyscTablice(){        
        Arrays.fill(K, 0x0);
        Arrays.fill(P, '0');
        Arrays.fill(V, '0');
    }

    void KSA(){
    
        s=0;
        for (int n =0;n<256 ;n++)
        {
            P[n]=  n;
        }
        P[256]=P[0]; // czyli 257 element bo liczymy od 0

        for ( m=0;m<768 ;m++)
        {
            n = m % 256;
            s = P[ (s + P[n] + K[m % c]) % 256];
            //System.out.println("--- " + m % c);

            x = P[n];
            P[n] = P[s];
            P[s] =  x;
        }

        for (int m =0;m<768 ;m++)
        {
            n = m % 257;
            s = P[ (s + P[n] + V[m % z])% 256 ];
            x = P[n];
            P[n] = P[s];
            P[s] =  x;
        }
    }
// KSA	//////////////////////////////////////////////////////////////////////


    public byte [ ] VMPCSZR(byte[] Wiad){

        byte [] Szyfrogram = new byte[Wiad.length];
      //  PasswordPanel.main(null);

        n = 0;
        for (int m =0;m<Wiad.length ;m++){
            s = P[ (s + P[n %257]) %257];

            Szyfrogram[m] = (byte) (Wiad[m] ^(P[P[P[s]]+1]%257));
            x    = P[n %257];
            P[n %257] = P[s];
            P[s] =  x;
            n = n + 1;
        }
      

        return Szyfrogram;
    }
   
    public byte [] VMPCODSZ(byte [] SzGram){

        byte [] WiadOdsz = new byte[SzGram.length];

        n = 0;
        for (int m =0;m<SzGram.length;m++){
            s = P[ (s + P[n %257]) %257];

            WiadOdsz[m] = (byte) (SzGram[m] ^(P[P[P[s]]+1]%257));
            x    = P[n %257];
            P[n %257] = P[s];
            P[s] =  x;
            n = n + 1;
        }

        return WiadOdsz;  // czyli odszyfrowana wiadomoœæ
    }


    //////////////////////////////////////////////////////	
	  public void WczytajHaslo( char[] pass) {
		
	  for (int i = 0; i < pass.length; i++) {
		K[i] = pass[i];
	  }
	 
	  }


    //////////////////////////////////////////////////////
    public void Wypisz(int [] tabx){
      //  System.out.println(tabx);
        for (int i =0;i<tabx.length ;i++)
          System.out.println(tabx[i]);
    }

    public void Los(int a , int b) {

        int[] liczby = new int[a];
        int[] wynik = new int[b];

        for (int i=0;i< a;i++){
            liczby[i]=i+1;
        }

        for (int i=0;i<b;i++){
            int l = (int) (Math.random() * a);
            wynik[i]= liczby[l];
            liczby[l]=liczby[a-1];
            a--;
        }

        for (int i=0;i<b;i++) {
               V[i] =  wynik[i];
        //   System.out.println(V[i]);
        }

    }

    public void VzB2int(byte Vzpliku []) {      //zmiana tablicy z byte do int
   //     System.out.println("jestem w Vzpliku");
        for (int i =0;i<Vzpliku.length ;i++)
            //System.out.println(Vzpliku[i] & 0xff);
            V[i]= Vzpliku[i] & 0xff;
    }

}

class QTimer {

    private final long start;

    public QTimer() {
        System.gc();
        start = System.currentTimeMillis();
    }
    public long getElapsed() {
        return System.currentTimeMillis() - start;
    }
}
