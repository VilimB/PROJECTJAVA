package racun.controller;


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import racun.model.Artikl;
import racun.model.ArtikliModel;


public class ArtikliController implements Initializable {
    @FXML Button dodajBtn;

    @FXML TextField nazivTxt;

    @FXML TextField cijenaTxt;

    @FXML TextField linkTxt;
    
    @FXML private TableView artiklTbl;

    @FXML private TableColumn nazivArtikla;

    @FXML private TableColumn cijenaArtikla;

    @FXML private TableColumn urlArtikla;

    @FXML private Button urediArtiklBtn;
    
    @FXML private Button konobarBtn;

    @FXML private Button izbrisiArtiklBtn;
    ArtikliModel odaberiArtikl;
   
  
    public void dodajAction (ActionEvent e) throws SQLException, Exception {
     
        String naziv = this.nazivTxt.getText();
        Integer cijena = Integer.parseInt(this.cijenaTxt.getText());
        String link = this.linkTxt.getText();
        
        System.out.println("Dodali ste uspjesno u bazu");
        
        Artikl a = new Artikl(0, naziv,cijena,link);
        a.create();
        ObservableList<ArtikliModel> data = ArtikliModel.listaArtikala();
                this.artiklTbl.setItems(data);

    }
    //Otvaranje artikala
    @FXML
    void artikliBtn(ActionEvent event) {

            try {
                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("RacunView.fxml"));
                    Parent  root = (Parent) fxmlloader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Prikaz artikala");
                    stage.setScene(new Scene(root));
                    stage.show();
        
            } catch (Exception ev) {
                    ev.printStackTrace();
            }

    }
    @FXML
    public void odaberiArtikl (Event e) {
            this.odaberiArtikl = (ArtikliModel)
            this.artiklTbl.getSelectionModel().getSelectedItem();
            this.nazivTxt.setText(this.odaberiArtikl.getIme());
            this.cijenaTxt.setText(String.valueOf(this.odaberiArtikl.getCijena()));
            this.linkTxt.setText(this.odaberiArtikl.getUrl());
            
    }
    
    //Brisanje artikala
     @FXML
    void deleteAkcija(ActionEvent event) throws Exception {
                if (this.odaberiArtikl != null) {
            try {
                this.odaberiArtikl.brisi();
                ObservableList<ArtikliModel> data = ArtikliModel.listaArtikala();
                this.artiklTbl.setItems(data);
            } catch (SQLException ex) {
                Logger.getLogger(KorisnikController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Urednjivanje artikala
    @FXML
    void urediAkcija(ActionEvent event) throws Exception {
        try {
            this.odaberiArtikl.setIme(this.nazivTxt.getText());
            this.odaberiArtikl.setCijena(Integer.parseInt(this.cijenaTxt.getText()));
            this.odaberiArtikl.setUrl(this.linkTxt.getText());
            
            this.odaberiArtikl.uredi();
            ObservableList<ArtikliModel> data = ArtikliModel.listaArtikala();
            this.artiklTbl.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(ArtikliController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Vracanje na popis konobara
    @FXML
    void konobarAkcija(ActionEvent event) throws IOException {
            
                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("KorisnikView.fxml"));
                    Parent  root = (Parent) fxmlloader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Prikaz konobara-korisnika");
                    stage.setScene(new Scene(root));
                    stage.show();
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
  
            ObservableList<ArtikliModel> data = null;
        try {
            data = ArtikliModel.listaArtikala();
        } catch (Exception ex) {
            Logger.getLogger(ArtikliController.class.getName()).log(Level.SEVERE, null, ex);
        }
            nazivArtikla.setCellValueFactory(new PropertyValueFactory<>("Ime"));
            cijenaArtikla.setCellValueFactory(new PropertyValueFactory<>("Cijena"));
            urlArtikla.setCellValueFactory(new PropertyValueFactory<>("Url"));
            
            artiklTbl.setItems(data);
       
    }
  
    
}
