package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.chart.XYChart;

import java.text.DecimalFormat;

public class obs  {

    private ObservableList<Grafikas> grafikas = FXCollections.observableArrayList();
    private XYChart.Series series = new XYChart.Series();
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public obs(){
        series.setName("Liko");
    }

    public void addValue(float s, float m, float p, float l, Integer men) {
        grafikas.add(new Grafikas(
                Float.parseFloat(df2.format(s)),
                Float.parseFloat(df2.format(m)),
                Float.parseFloat(df2.format(p)),
                Float.parseFloat(df2.format(l)),
                men));

    }
    public void addValueLine(int month, float liko){
        series.getData().add(new XYChart.Data(month, liko));
    }

    public ObservableList<Grafikas> getList(){
        return grafikas;
    }

    public XYChart.Series getXY(){
        return series;
    }

    public void xyclear(){
        series.getData().clear();
    }
    public void desandnew(){
        grafikas.clear();

    }



}
