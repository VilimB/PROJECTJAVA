package racun.model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KorisniciModel {
    
    
 SimpleIntegerProperty id = new SimpleIntegerProperty();
 SimpleStringProperty ime = new SimpleStringProperty();
 SimpleStringProperty sifra = new SimpleStringProperty();
 SimpleStringProperty uloga = new SimpleStringProperty();
 
   public KorisniciModel (Integer id, String ime, String sifra,String uloga) {
            this.id = new SimpleIntegerProperty (id);
            this.ime = new SimpleStringProperty(ime);
            this.sifra = new SimpleStringProperty(sifra);
            this.uloga = new SimpleStringProperty (uloga);
 }

    public KorisniciModel() {
    }
    
       public Integer getId () {
             return id.get();
      }
      public String getIme () {
             return ime.get();
      }
      public String getSifra () {
             return sifra.get();
      }
       public String getUloga() {
             return uloga.get();
      }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public void setIme(String ime) {
           this.ime.set(ime);
    }

    public void setSifra(String sifra) {
           this.sifra.set(sifra);
    }
    public void setUloga(String uloga) {
        this.uloga.set(uloga);
    }
   public static ObservableList<KorisniciModel> listaKorisnika () throws SQLException {
         ObservableList<KorisniciModel> lista = FXCollections.observableArrayList();
                 Baza DB = new Baza();
            
                 String upit ="SELECT * FROM korisnik";
                 ResultSet rs = DB.select(upit);
       
        try {
            while (rs.next()) {
                lista.add(new KorisniciModel(
                        rs.getInt("id"),
                        rs.getString("korisnicko_ime"),
                        rs.getString("lozinka"),
                        rs.getString("uloga")));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
        return lista;
    }
   public void uredi () {
 try {
                 Baza DB = new Baza();
           PreparedStatement upit = DB.exec("UPDATE korisnik SET korisnicko_ime=?,lozinka=?,uloga=? WHERE id=?");
                  
        
            upit.setString(1, this.getIme());
            upit.setString(2, this.getSifra());
             upit.setString(3, this.getUloga());
            upit.setInt(4, this.getId());
           
            upit.executeUpdate();
            DB.close();
            } catch (SQLException ex) {
            System.out.println("Greška prilikom spasavanja korisnika u bazu:" + ex.getMessage());
 
     }
   }
  
   public void brisi() {
 try {
                 Baza DB = new Baza();
       PreparedStatement upit = DB.exec("DELETE FROM korisnik WHERE id=?");
        upit.setInt(1, this.getId());
        upit.executeUpdate();
        DB.close();
        } catch (SQLException ex) {
        System.out.println("Greška prilikom spasavanja korisnika u bazu:" + ex.getMessage());
 }
   }

   
}


 
   

