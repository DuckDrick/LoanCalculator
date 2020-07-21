package sample.skaiciavimai;

import sample.obs;

import javax.security.auth.Destroyable;
import java.text.DecimalFormat;

public class Anuitetinis {

    private double suma;
    private double proc;
    private double kred;
    private int nuo, iki;
    private int months;
    private int filtrnuo, filtriki;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    public Anuitetinis(double suma, double proc, int men, int nuo, int iki, int filtrnuo, int filtriki){
        this.suma = suma;
        this.proc = proc;
        this.months = men;
        this.nuo = nuo;
        this.iki = iki;
        this.filtrnuo = filtrnuo;
        this.filtriki = filtriki;
        kred =  (((proc/100d/12d)*Math.pow(1+(proc/100d/12d),months-((iki!=0)? iki-nuo+1 : 0)))/(Math.pow(1+(proc/100d/12d),months-((iki!=0)? iki-nuo+1 : 0))-1));

        kred*= suma;


    }

    public void skaic(obs ob){

        double paluk;
        double aha = suma;
        for(int i=0; i<months; i++){
            paluk = (aha)*proc/100d/12d;
            ob.addValueLine(i,(float)aha);
            if(nuo-1<=i && iki-1>=i){
                if(filtrnuo-1<=i && filtriki-1>=i)
                ob.addValue((float) paluk, 0, (float) paluk, (float) aha, i);
            }else {
                if(filtrnuo-1<=i && filtriki-1>=i)
                ob.addValue((float) kred, (float) (kred - paluk), (float) paluk, (float) aha, i);
                aha-=kred-paluk;
            }

        }

        ob.addValueLine(months,(float)aha);
    }


}
