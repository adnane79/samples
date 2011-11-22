/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package boundary;

import entity.DaftarPesan;
import entity.Pesan;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author radityo
 */
public class HalamanDaftarPesan extends Boundary {

    public HalamanDaftarPesan() {
        super();
        setTemplate("/WEB-INF/jsps/halaman_daftar_pesan.jsp");
    }

    @Override
    protected void process() {
        setMessage("");
        DaftarPesan daftarPesan = new DaftarPesan();
        if(validate_jumlah_pesan()){
            List<Pesan> sepuluhPesanTerbaru = daftarPesan.sepuluhPesanTerbaru();
            getRequest().setAttribute("daftar_pesan", sepuluhPesanTerbaru.iterator());
        }else{
            try {
                getResponse().sendRedirect("daftar_pesan_kosong");
            } catch (IOException ex) {
                Logger.getLogger(HalamanDaftarPesan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private boolean validate_jumlah_pesan(){
        DaftarPesan daftarPesan = new DaftarPesan();
        int jumlahPesan = daftarPesan.getJumlahPesan();
        
        if( jumlahPesan == 0){
            return false;
        }

        return true;
    }

}
