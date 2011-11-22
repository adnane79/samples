/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package boundary;

/**
 *
 * @author radityo
 */
public class HalamanDaftarPesanKosong extends HalamanDaftarPesan {

    

    @Override
    protected void process() {
       setTemplate("/WEB-INF/jsps/halaman_daftar_pesan_kosong.jsp");
    }

    



}
