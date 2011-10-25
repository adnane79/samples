/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package readexceljava;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import object.OrangKeren;

/**
 *
 * @author radityo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            //mendefinisikan Workbook class

            Workbook workbook = Workbook.getWorkbook(new File("test.xls"));


            // menggunakan sheet ke 0 (di file test, yang saya isi yang cuman sheet 0)

            Sheet sheet = workbook.getSheet(0);

            ArrayList<OrangKeren> daftarOrangKeren = new ArrayList<OrangKeren>();

            
            // mengambil baris per baris dari excel dimulai dari index 1 , karena dalam test.xls
            // baris 0 merupakan header bukan data, jadi ini relative pada format xls anda..

            for(int i=1; i< sheet.getRows(); i++){
                
                OrangKeren keren = new OrangKeren();
                Cell[] row = sheet.getRow(i);

                keren.setNama(row[0].getContents());

                keren.setAlamat(row[1].getContents());

                keren.setTelp(row[2].getContents());

                daftarOrangKeren.add(keren);

            }
            //sekarang memprint orang keren yang ada di daftar orang keren.
            Iterator<OrangKeren> iterator = daftarOrangKeren.iterator();

            while(iterator.hasNext()){

                OrangKeren next = iterator.next();

                showOrangKeren(next);
                
            }

            

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void showOrangKeren(OrangKeren a){
        System.out.println("nama :");
        System.out.println(a.getNama());
        System.out.println("alamat : ");
        System.out.println(a.getAlamat());
        System.out.println("telp :");
        System.out.println(a.getTelp());
        System.out.println("");
        System.out.println("===============================");
    }

}
