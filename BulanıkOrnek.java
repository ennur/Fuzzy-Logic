
import bulanikornek.Wine;
import java.net.URISyntaxException;
import java.util.Scanner;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;


public class BulanikOrnek {

    public static void main(String[] args) throws URISyntaxException {
        
        Scanner in=new Scanner(System.in);
        System.out.print("Alkol:");
        double alkol =in.nextDouble();
        System.out.print("flavanoitler:");
        double flavanoitler =in.nextDouble();
        System.out.print("magnezyum:");
        double magnezyum =in.nextDouble();
        System.out.print("toplam_fenoller:");
        double toplam_fenoller =in.nextDouble();
        System.out.print("renk_yogunlugu:");
        double renk_yogunlugu =in.nextDouble();
        System.out.print("od280_od315:");
        double Od_degerleri =in.nextDouble();
        
        Wine w = new Wine(alkol, flavanoitler, magnezyum ,toplam_fenoller,renk_yogunlugu,Od_degerleri);
        
        System.out.print(w);
        //uyelik fonksiyonlari gormek icin
        JFuzzyChart.get().chart(w.getModel());
        
        
        
    }
    
}
