/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */
package boundary;

import entity.DaftarPengunjung;
import entity.DaftarPesan;
import entity.Pengunjung;
import entity.Pesan;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author radityo
 */
public class HalamanTambahPesan extends Boundary {

    public HalamanTambahPesan(){
        super();
        setTemplate("/WEB-INF/jsps/halaman_tambah_pesan.jsp");
    }

    @Override
    protected void process() {

        setMessage("");

        if (getRequest().getParameter("act") != null && getRequest().getParameter("act").equals("add")) {
            try {
                if (validate_email()) {
                    if (validate_pesan()) {
                        DaftarPesan dp = new DaftarPesan();
                        DaftarPengunjung dpn = new DaftarPengunjung();
                        Pengunjung p = dpn.getPengunjung(getRequest().getParameter("email"));
                        if (p == null) {
                            p = new Pengunjung();
                            p.setEmail(getRequest().getParameter("email"));
                            dpn.tambahPengunjung(p);
                        }
                        Pesan pesan = new Pesan();
                        pesan.setPengunjung(p);
                        String pesanHtml = getRequest().getParameter("pesan");
                        pesanHtml = pesanHtml.replaceAll("\n", "<br>");
                        pesan.setPesan(pesanHtml);

                        dp.tambahPesan(pesan);
                    } else {
                        getResponse().sendRedirect("tambah_pesan_e2");
                    }
                } else {
                    getResponse().sendRedirect("tambah_pesan_e1");
                }
                getResponse().sendRedirect("daftar_pesan");
            } catch (IOException ex) {
                Logger.getLogger(HalamanTambahPesan.class.getName()).log(Level.SEVERE, null, ex);
            }



        }
    }

    boolean validate_email() {
        
        String email = getRequest().getParameter("email");
        if(email == null){
            return false;
        }
        if(email.trim().equals("")){
            return false;
        }

        return true;
    }

    public boolean validate_pesan() {

        String pesan = getRequest().getParameter("pesan");

        if(pesan == null){
            return false;
        }

        if(pesan.trim().equals("")){
            return false;
        }

        return true;
    }
}
