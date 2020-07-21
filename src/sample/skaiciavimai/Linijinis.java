package sample.skaiciavimai;

import sample.obs;

import java.text.DecimalFormat;

public class Linijinis {

    private double suma;
    private double proc;
    private int months;
    private double kreditas=0;
    private int nuo, iki;
    private int filtrnuo, filtriki;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public Linijinis(double suma, double proc, int men, int nuo, int iki, int filtrnuo, int filtriki){
        this.suma = suma;
        this.proc = proc;
        this.months = men;
        this.nuo = nuo;
        this.iki = iki;
        this.filtrnuo = filtrnuo;
        this.filtriki = filtriki;
        kreditas = this.suma / (double)(this.months-((iki!=0)? iki-nuo+1 : 0));

    }

    public void skaic(obs ob) {

        double palukanos, liko = suma;
        for(int i=0; i<months; i++) {
            ob.addValueLine(i, (float) liko);
            palukanos = liko * (proc / 100f / 12f);

            if(nuo-1<=i && iki-1>=i){
                if(filtrnuo-1<=i && filtriki-1>=i)
                ob.addValue((float) palukanos, 0, (float) palukanos, (float) liko, i);
            }else {
                if(filtrnuo-1<=i && filtriki-1>=i)
                ob.addValue((float) (kreditas + palukanos), (float) kreditas, (float) palukanos, (float) liko, i);
                liko -= kreditas;
            }

        }
        ob.addValueLine(months,(float)liko);
    }


}
