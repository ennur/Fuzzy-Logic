
package bulanikornek;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import net.sourceforge.jFuzzyLogic.FIS;

public class Wine {
    
    private double flavanoitler;
    private double alkol;
    private double magnezyum;
    private double toplam_fenoller;
    private double renk_yogunlugu;
    private double od280_od315;
    private FIS fis;
    private ArrayList<String> siniflar = new ArrayList<String>();
    private ArrayList<String> alkoller = new ArrayList<String>();  
    private ArrayList<String> Flavanoitler = new ArrayList<String>();
    private ArrayList<String> Magnezyum = new ArrayList<String>();
    private ArrayList<String> Toplam_fenoller = new ArrayList<String>();
    private ArrayList<String> Renk_yogunlugu = new ArrayList<String>();
    private ArrayList<String> Od280_od315 = new ArrayList<String>();
    
    public Wine(double alkol, double flavanoitler,double magnezyum,double toplam_fenoller,double renk_yogunlugu,double od280_od315 ) throws URISyntaxException 
    {
        this.flavanoitler = flavanoitler;
        this.alkol=alkol;
        this.magnezyum= magnezyum;
        this.toplam_fenoller=toplam_fenoller;
        this.renk_yogunlugu=renk_yogunlugu;
        this.od280_od315=od280_od315;
        
        File dosya =new File(getClass().getResource("BulanikOrnek.Fcl").toURI());
        fis = FIS.load(dosya.getPath(),true);
        fis.setVariable("alkol", alkol);//1
        fis.setVariable("flavanoitler", flavanoitler); //7
        fis.setVariable("magnezyum", magnezyum); //5
        fis.setVariable("toplam_fenoller", toplam_fenoller); //6
        fis.setVariable("renk_yogunlugu", renk_yogunlugu);//10
        fis.setVariable("od280_od315", od280_od315); //12
        fis.evaluate();
    }

    public FIS getModel(){
     
      return fis;
    }
    
    @Override
    public String toString() {
        dosyaOku();
        String cikti; 
        
        
        int [] cevir = new int [siniflar.size()];
        double bulunan = (int) fis.getVariable("sarap_sinif").getValue();
        double beklenen;
        double toplam=0;
        
        
        
        // siniflar string arraylist integer dizisine cevriliyor
        for(int i=0;i<siniflar.size();i++){                    
            cevir[i] = Integer.parseInt(siniflar.get(i));           
        }
        
        for(int j=0;j<cevir.length;j++){
            beklenen=cevir[j];
            toplam+=Math.pow((beklenen-bulunan),2); 
            
            //System.out.println(Integer.parseInt(cevir[i]));
        }
        double mse =toplam/cevir.length;
        cikti= "sarap sinif..:"+Math.round(fis.getVariable("sarap_sinif").getValue())+'\n'+"MSE Degeri..:"+mse;
        return cikti;
    }
    

    public void dosyaOku() {
         
        //Dosyadan okuma islemleri
        String tumDosya = "";
        int satirSayisi=0;
        try (FileReader file = new FileReader("wine.txt")) {
            try (BufferedReader reader = new BufferedReader(file)) {
                String line = "";

                while ((line = reader.readLine()) != null) {
   
                        tumDosya += line;   
                        satirSayisi++;
                }        
            }
        } catch (IOException e) {
            System.out.print(e.toString());
        }
        
        //dosyadaki veriler virgüle göre ayiriliyor
        String[] veriler = null;
        for(int i=0;i<tumDosya.length();i++){
            veriler=tumDosya.split(",");                 
        }
        
        //virgulle ayrilan dosyadaki sarap_sinif degerleri aliniyor       
        for(int j=0;j<satirSayisi;j++){
              siniflar.add(veriler[7*j]);       
        }        
    } 
     
 }

 
    
    
 

    
    

