package sample;

public class Grafikas {
    private String suma;
    private String moketi;
    private String palukanos;
    private String liko;
    private String men;

    public Grafikas(float suma, float moketi, float palukanos, float liko, Integer men){
        this.suma = Float.toString(suma);
        this.moketi = Float.toString(moketi);
        this.palukanos = Float.toString(palukanos);
        this.liko = Float.toString(liko);
        this.men = Integer.toString(men+1);
    }


    public String getMen() {
        return men;
    }

    public void setMen(String men) {
        this.men = men;
    }

    public String getLiko() {
        return liko;
    }

    public void setLiko(String liko) {
        this.liko = liko;
    }

    public String getSuma() {
        return suma;
    }

    public void setSuma(String suma) {
        this.suma = suma;
    }

    public String getMoketi() {
        return moketi;
    }

    public void setMoketi(String moketi) {
        this.moketi = moketi;
    }

    public String getPalukanos() {
        return palukanos;
    }

    public void setPalukanos(String palukanos) {
        this.palukanos = palukanos;
    }
}
