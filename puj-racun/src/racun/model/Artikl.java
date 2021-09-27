package racun.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;



public class Artikl implements Model {
    private int artikal_id;
    private String naziv;
    private int cijena;
    private String urll;
    
   
    private Artikl () {}

    public Artikl(int artikal_id, String naziv, int cijena, String urll) {
        this.artikal_id = artikal_id;
        this.naziv = naziv;
        this.cijena = cijena;
        this.urll = urll;
    
    }

    public int getArtikal_id() {
        return artikal_id;
    }

    public void setId(int artikal_id) {
        this.artikal_id = artikal_id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getCijena() {
        return cijena;
    }

    public void setCijena(Integer cijena) {
        this.cijena = cijena;
    }

    public String getUrll() {
        return urll;
    }

    public void setUrl(String urll) {
        this.urll = urll;
    }

    

    @Override
    public void create() {
        try {
            Baza DB = new Baza();
            PreparedStatement stmnt =DB.exec("INSERT INTO artikli VALUES (null, ?, ?, ?)");    
           
            stmnt.setString(1, this.naziv);
            stmnt.setInt(2, this.cijena);
            stmnt.setString(3, this.urll);
            
            stmnt.executeUpdate();
            DB.close();
        } catch (SQLException e) {
            System.out.println("Greska prilikom stvaranja korisnika u bazi:"
                    + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "artikal_id=" + artikal_id +
                ", naziv='" + naziv + '\'' +
                ", cijena='" + cijena + '\'' +
                ", url='" + urll + '\'' +
                '}';
    }

    @Override
    public void update() {
    }

    @Override
    public void delete() {
    }
}