package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.HBox;

import sample.skaiciavimai.Anuitetinis;
import sample.skaiciavimai.Linijinis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Controller {


    @FXML
    private TextField procentai;
    @FXML
    private TextField suma;
    @FXML
    private TextField men;
    @FXML
    private LineChart line;
    @FXML
    private ToggleGroup grp1;
    @FXML
    private TableView<Grafikas> tbv;
    @FXML
    private TableColumn<Grafikas, String> clm1;
    @FXML
    private TableColumn<Grafikas, String> clm2;
    @FXML
    private TableColumn<Grafikas, String> clm3;
    @FXML
    private TableColumn<Grafikas, String> l;
    @FXML
    private ToggleButton atosto;
    @FXML
    private HBox atostogu;
    @FXML
    private TextField nuo;
    @FXML
    private TextField iki;
    @FXML
    private TableColumn<Grafikas, String> me;
    @FXML
    private TextField filtrnuo;
    @FXML
    private TextField filtriki;
    @FXML
    private HBox filtru;
    @FXML
    private ToggleButton filtruoti;



    private boolean rdbtn = false;
    private Linijinis linijinisSk;
    private Anuitetinis anuitetinis;

    private obs ob = new obs();
    private boolean atostogos = false;
    private boolean filtras = false;

    public void sav(ActionEvent event){
        try {
            File failas = new File("save.txt");
            if (failas.createNewFile()){
                System.out.println("created");
            }else{
                System.out.println("exists");
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        try{
            FileWriter fail = new FileWriter("save.txt");
            for(Grafikas grafikas: ob.getList()){
                fail.write(grafikas.getMen() + " " + grafikas.getLiko() + " " +
                        grafikas.getSuma() +" " + grafikas.getMoketi() +" " +
                        grafikas.getPalukanos() + "\n");
            }
            fail.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void send(ActionEvent event) {

        if(notEmpty()){
            ob.xyclear();
            ob.desandnew();


            int menesiu = Integer.parseInt(men.getText());

            boolean nope=false;


            if(!filtras){
                filtrnuo.setText("0");
                filtriki.setText(men.getText());
            }else{
                if((filtrnuo.getText().length()==0)){
                    filtrnuo.setStyle("-fx-background-color: red");
                    nope=true;
                }else{
                    filtrnuo.setStyle(null);
                }
                if((filtriki.getText().length()==0)){
                    filtriki.setStyle("-fx-background-color: red");
                    nope=true;
                }else{
                    filtriki.setStyle(null);
                }

            }



            if(atostogos){
                if((nuo.getText().length()!=0)?Integer.parseInt(nuo.getText())>=menesiu : true){
                    nuo.setStyle("-fx-background-color: red");
                    nope=true;
                }else{
                    nuo.setStyle(null);
                }
                if((iki.getText().length()!=0)? Integer.parseInt(iki.getText())<Integer.parseInt(nuo.getText()) : true){
                    iki.setStyle("-fx-background-color: red");
                    nope=true;
                }else if(Integer.parseInt(iki.getText())>menesiu) {
                    iki.setText(men.getText());
                    System.out.println("Cahnging " + iki.getText());
                }else{
                    iki.setStyle(null);
                }
                if((iki.getText().length()!=0 && nuo.getText().length()!=0)?Integer.parseInt(iki.getText())-Integer.parseInt(nuo.getText())+1 >= menesiu : true){
                    nuo.setStyle("-fx-background-color: red");
                    iki.setStyle("-fx-background-color: red");
                    nope=true;
                }
            }


            if(!nope) {
                if(rdbtn){
                    linijinisSk = new Linijinis (Double.parseDouble(suma.getText()), Double.parseDouble(procentai.getText()), menesiu, Integer.parseInt(nuo.getText()), Integer.parseInt(iki.getText()),
                    Integer.parseInt(filtrnuo.getText()), Integer.parseInt(filtriki.getText()));
                    linijinisSk.skaic(ob);
                }else{
                    anuitetinis = new Anuitetinis (Double.parseDouble(suma.getText()), Double.parseDouble(procentai.getText()), menesiu, Integer.parseInt(nuo.getText()), Integer.parseInt(iki.getText()),
                    Integer.parseInt(filtrnuo.getText()), Integer.parseInt(filtriki.getText()));
                    anuitetinis.skaic(ob);
                }
            }
        }
    }

    public boolean notEmpty(){
        if(suma.getText().length()!=0 && procentai.getText().length()!=0 && men.getText().length()!=0)
            return true;
        else
            return false;
    }

    public void initialize() {

        me.setCellValueFactory(new PropertyValueFactory<Grafikas, String>("men"));
        clm1.setCellValueFactory(new PropertyValueFactory<Grafikas, String>("suma"));
        clm2.setCellValueFactory(new PropertyValueFactory<Grafikas, String>("moketi"));
        clm3.setCellValueFactory(new PropertyValueFactory<Grafikas, String>("palukanos"));
        l.setCellValueFactory(new PropertyValueFactory<Grafikas, String>("liko"));

        tbv.setItems(ob.getList());


        line.getData().add(ob.getXY());

        tbv.setPlaceholder(new Label("Tuščia lentelė"));

        filtruoti.selectedProperty().addListener((observable, oldValue, newValue) -> {
            filtras=newValue;
            if(filtras){
                filtru.setVisible(true);

            }
            else{
                filtru.setVisible(false);

            }
        });

        atosto.selectedProperty().addListener((observable, oldValue, newValue) -> {
            atostogos=newValue;
            if(atostogos){
                atostogu.setVisible(true);
                nuo.setText("");
                iki.setText("");
            } else{
                atostogu.setVisible(false);
                nuo.setText("0");
                iki.setText("0");
            }
        });

        nuo.textProperty().addListener((observable, oldValue, newValue) -> {


            if(!(newValue.length()==0))
               { if (!newValue.matches("\\d{0,3}?") ) {
                    nuo.setText(oldValue);
                }

            }
        });

        iki.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!(newValue.length()==0)) {
                if (!newValue.matches("\\d{0,3}?")) {
                    iki.setText(oldValue);
                }
            }
        });

        filtriki.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!(newValue.length()==0))
            { if (!newValue.matches("\\d{0,3}?") ) {
                filtriki.setText(oldValue);
            }

            }
        });

        filtrnuo.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!(newValue.length()==0))
            { if (!newValue.matches("\\d{0,3}?") ) {
                filtrnuo.setText(oldValue);
            }

            }
        });

        grp1.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            if((((RadioButton)t1.getToggleGroup().getSelectedToggle()).getText()).equals("Linijinis")){
                rdbtn = true;
            }else{
                rdbtn = false;
            }
        });

        procentai.textProperty().addListener((observableValue, s, newValue) -> {
            if(!(newValue.length()==0))
                if (!newValue.matches("\\d{0,8}([\\.]\\d{0,8})?") ) {
                    procentai.setText(s);
                }

        });

        men.textProperty().addListener((observableValue, s, newValue) -> {
            if(!(newValue.length()==0))
                if (!newValue.matches("\\d{0,3}?") ) {
                    men.setText(s);
                }

        });

        suma.textProperty().addListener((observableValue, s, newValue) -> {
            if(!(newValue.length()==0))
                if (!newValue.matches("\\d+([\\.]\\d{0,2})?") ) {
                    suma.setText(s);
            }
        });



    }
}
