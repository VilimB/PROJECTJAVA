package racun.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ArtikliModel {
      
  SimpleIntegerProperty id = new SimpleIntegerProperty();
  SimpleStringProperty ime = new SimpleStringProperty();
  SimpleIntegerProperty cijena = new SimpleIntegerProperty();
  SimpleStringProperty url = new SimpleStringProperty();
 
   public ArtikliModel (Integer id, String ime, Integer cijena,String url) {
            this.id = new SimpleIntegerProperty (id);
            this.ime = new SimpleStringProperty(ime);
            this.cijena = new SimpleIntegerProperty(cijena);
            this.url = new SimpleStringProperty(url);
 }
    
   public Integer getId () {
        return id.get();
    }
    public String getIme () {
           return ime.get();
    }
    public String getUrl () {
           return url.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public void setIme(String ime) {
           this.ime.set(ime);
    }

    public void setUrl(String url) {
           this.url.set(url);
    }

    public Integer getCijena() {
        return cijena.get();
    }

    public void setCijena(Integer cijena) {
        this.cijena.set(cijena);
    }
 
   public static ObservableList<ArtikliModel> listaArtikala () throws SQLException, Exception {
         ObservableList<ArtikliModel> lista = FXCollections.observableArrayList();
                Baza DB = new Baza();
                
                String upit ="SELECT * FROM artikli";
                ResultSet rs = DB.select(upit);
       
        try {
            while (rs.next()) {
                lista.add(new ArtikliModel(
                        rs.getInt("artikal_id"),
                        rs.getString("naziv"),
                        rs.getInt("cijena"),
                        rs.getString("slika")));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
        DB.close();
        return lista;
        
    }
   
 public void uredi () {
    try {
            Baza DB = new Baza(); 
           PreparedStatement upit =DB.exec("UPDATE artikli SET naziv=?,cijena=? ,slika=? WHERE artikal_id=?");
            
        
            upit.setString(1, this.getIme());
            upit.setInt(2, this.getCijena());
            upit.setString(3, this.getUrl());
            upit.setInt(4, this.getId());
           
            upit.executeUpdate();
            DB.close();
            } catch (SQLException ex) {
            System.out.println("Greška prilikom azuriranja artikla u bazu:" + ex.getMessage());
 
     }
   }
   public void brisi () {
 try {
            Baza DB = new Baza();      
       PreparedStatement upit = DB.exec("DELETE FROM artikli WHERE artikal_id=?");
        upit.setInt(1, this.getId());
        upit.executeUpdate();
        } catch (SQLException ex) {
        System.out.println("Greška prilikom brisanja artikla:" + ex.getMessage());
 }
 }

 
}
