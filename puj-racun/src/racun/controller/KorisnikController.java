package racun.controller;


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;


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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import racun.model.KorisniciModel;
import racun.model.Korisnik;


public class KorisnikController implements Initializable {
    @FXML
    Label statusLbl;
    @FXML
    Button dodajBtn;

    @FXML
    private TextField nazivKonobara;

    @FXML
    private TextField sifraK;
    
    @FXML
    private TableView konobariTbl;

    @FXML
    private TableColumn imeKonobara;

    @FXML
    private TableColumn sifraKonobara;
    
    @FXML
    private TableColumn ulogaKonobara;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button urediBtn;
    
    @FXML
    private Button popisArtikalaBtn;
    
    @FXML
    private RadioButton adminUloga;

    @FXML
    private RadioButton korisnikUloga;
    
    ToggleGroup right;
    
    KorisniciModel odaberiKontakt;
    String ulogaa;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button artikliBtn;
  
    @FXML
    public void dodajKorisnikaAction (ActionEvent e) {
      
        
        try {
            
            ToggleGroup group = new ToggleGroup();
            adminUloga.setToggleGroup(group);
            korisnikUloga.setToggleGroup(group);
            
           if(adminUloga.isSelected()){
               ulogaa="admin";
               System.out.println("Uloga je: "+ulogaa);
               
           }else{
               ulogaa="korisnik";
               System.out.println("Uloga je: "+ulogaa);
             
           }
            
            String naziv = this.nazivKonobara.getText();
            String sifra = this.sifraK.getText();
            
           
            System.out.println("Dodali ste uspjesno konobara-korisnika u bazu");
            
            Korisnik k = new Korisnik(0, naziv,sifra,ulogaa);
            k.create();
            ObservableList<KorisniciModel> data = KorisniciModel.listaKorisnika();
            this.konobariTbl.setItems(data);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(KorisnikController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void radioAdmin(ActionEvent event) {
        
               this.korisnikUloga.setSelected(false);
               this.adminUloga.setSelected(true);
    }

    @FXML
    void radioKorisnik(ActionEvent event) {
                this.korisnikUloga.setSelected(true);
                this.adminUloga.setSelected(false);
    }
    
    
    
    
    
    //Otvaranje racuna
    @FXML
    void artikliBtn(ActionEvent event) throws IOException {
        
                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("RacunView.fxml"));
                    Parent  root = (Parent) fxmlloader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Prikaz artikala");
                    stage.setScene(new Scene(root));
                    stage.show();
    }
    
    @FXML
    public void odaberiKorisnika (Event e) {
        
        
            this.odaberiKontakt = (KorisniciModel)
            this.konobariTbl.getSelectionModel().getSelectedItem();
            
            this.nazivKonobara.setText(this.odaberiKontakt.getIme());
            this.sifraK.setText(this.odaberiKontakt.getSifra());
            
             if(this.odaberiKontakt.getUloga().equalsIgnoreCase("admin")){
                 this.adminUloga.setSelected(true);
                 this.korisnikUloga.setSelected(false);
             }else{
                 this.korisnikUloga.setSelected(true);
                 this.adminUloga.setSelected(false);
             }
         
    }
  

    //Brisanje konobara(korisnika)
    @FXML
    void deleteAkcija(ActionEvent event) {
        if (this.odaberiKontakt != null) {
            try {
                this.odaberiKontakt.brisi();
                ObservableList<KorisniciModel> data = KorisniciModel.listaKorisnika();
                this.konobariTbl.setItems(data);
            } catch (SQLException ex) {
                Logger.getLogger(KorisnikController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    //Urednjivanje konobara(korisnika)
    @FXML
    void urediAkcija(ActionEvent event) throws SQLException {
       
             this.odaberiKontakt.setIme(this.nazivKonobara.getText());
             this.odaberiKontakt.setSifra(this.sifraK.getText());
             
             
             if(adminUloga.isSelected()){
              this.odaberiKontakt.setUloga(this.adminUloga.getText());
               
           }else{
              this.odaberiKontakt.setUloga(this.korisnikUloga.getText());
              
           }
             this.odaberiKontakt.uredi();
             ObservableList<KorisniciModel> data = KorisniciModel.listaKorisnika();
             this.konobariTbl.setItems(data);

    }
    //Vracanje na popis artikala 
     @FXML
    void popisArtikalaAkcija(ActionEvent event) {
             try {
                
                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ArtikliRegister.fxml"));
                    Parent  root = (Parent) fxmlloader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Prikaz artikala");
                    stage.setScene(new Scene(root));
                    stage.show();
        
            } catch (Exception ev) {
                    ev.printStackTrace();
            }
    }

     @Override
public void initialize(URL url, ResourceBundle rb) {
   
        try {
            ObservableList<KorisniciModel> data = KorisniciModel.listaKorisnika();
            imeKonobara.setCellValueFactory(new PropertyValueFactory<>("Ime"));
            sifraKonobara.setCellValueFactory(new PropertyValueFactory<>("Sifra"));
            ulogaKonobara.setCellValueFactory(new PropertyValueFactory<>("Uloga"));
            konobariTbl.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(KorisnikController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
}
}
