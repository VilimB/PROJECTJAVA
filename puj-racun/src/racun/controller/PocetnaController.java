package racun.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PocetnaController implements Initializable{
    
    @FXML
    private Button artikliBtn;

    @FXML
    private Button korisniciBtn;

    @FXML
    private Button racunBtn;
    private static String uloga;
    private String ulogica;
    
     public String getUloga() {
        return uloga;
    }

   
    public void setUloga(String uloga) { 
        this.uloga = uloga;
    }
    @FXML
        void artikliAkcija(ActionEvent event) throws IOException {
                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ArtikliRegister.fxml"));
                    Parent  root = (Parent) fxmlloader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Prikaz artikala");
                    stage.setScene(new Scene(root));
                    stage.show();
    }

    @FXML
    void korisniciAkcija(ActionEvent event) throws IOException {
                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("KorisnikView.fxml"));
                    Parent  root = (Parent) fxmlloader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Prikaz artikala");
                    stage.setScene(new Scene(root));
                    stage.show();

    }

    @FXML
    void racunAkcija(ActionEvent event) throws IOException {
                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("RacunView.fxml"));
                    Parent  root = (Parent) fxmlloader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Prikaz artikala");
                    stage.setScene(new Scene(root));
                    stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PocetnaController p = new PocetnaController();
        ulogica=p.getUloga();
       
            if(p.getUloga().equalsIgnoreCase("korisnik")){
                 artikliBtn.setVisible(false);
                 korisniciBtn.setVisible(false);
            }else if(p.getUloga().equalsIgnoreCase("admin")){
                artikliBtn.setVisible(true);
                korisniciBtn.setVisible(true);
            }
            else{
                System.out.println("GRESKA");
            }
    }
  
}
