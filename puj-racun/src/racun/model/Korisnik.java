package racun.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;




public class Korisnik implements Model {
    
      
    private int id;
    private String naziv;
    private String sifra;
    private String uloga;
  
    

    public Korisnik(int id, String naziv, String sifra,String uloga) {
        this.id = id;
        this.naziv = naziv;
        this.sifra = sifra;
        this.uloga=uloga;
 
    }

    public Korisnik() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }
    
    public String getUloga() {
        return uloga;
    }
    public void setUloga(String uloga) {
        this.uloga = uloga;
    }
    
    
    @Override
    public void create() {
        try {
            Baza DB = new Baza();
            PreparedStatement stmnt = DB.exec("INSERT INTO korisnik VALUES (null, ?, ?,?)");  
          
            stmnt.setString(1, this.naziv);
            stmnt.setString(2, this.sifra); 
            stmnt.setString(3, this.uloga); 
            stmnt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Greska prilikom stvaranja korisnika u bazi:"
                    + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", sifra='" + sifra + '\'' +
                ", uloga='" + uloga + '\'' +
               
                '}';
    }

    @Override
    public void update() {
    }

    @Override
    public void delete() {
    }
}