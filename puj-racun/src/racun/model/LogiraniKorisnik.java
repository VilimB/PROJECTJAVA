package racun.model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import racun.controller.PocetnaController;

public class LogiraniKorisnik {
     
    public static boolean logiraj (String kime, String lozinka) throws SQLException {
          Baza DB = new Baza();
          ResultSet rss = DB.select("SELECT *FROM korisnik"); 
           
          while(rss.next()) {
                       Integer id = rss.getInt("id");
                       String ime =rss.getString("korisnicko_ime");
                       String uloga = rss.getString("uloga");
                      
                       if(ime.equalsIgnoreCase(kime)){
                           
                          Racun k = new Racun();
                          k.setBr(id);
                          
                          PocetnaController p = new PocetnaController();
                          p.setUloga(uloga);
                         
                          System.out.println("_________________________________");
                          
                       }
                      }
        PreparedStatement ps = DB.exec("SELECT * FROM korisnik WHERE  korisnicko_ime =? AND lozinka=?");
        try {
            
            ps.setString(1, kime);
            ps.setString(2, lozinka);
         
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška: "+ex.getMessage());
            return false;
        }
    }
   
}
