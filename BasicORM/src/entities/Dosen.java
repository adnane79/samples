/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author radityo
 */
@Entity
public class Dosen implements Serializable {
    @OneToMany(mappedBy = "dosenWali")
    private List<Mahasiswa> mahasiswaWali;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nama;

    private String nip;

    private String telp;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dosen)) {
            return false;
        }
        Dosen other = (Dosen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Dosen[id=" + id + "]";
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the nip
     */
    public String getNip() {
        return nip;
    }

    /**
     * @param nip the nip to set
     */
    public void setNip(String nip) {
        this.nip = nip;
    }

    /**
     * @return the telp
     */
    public String getTelp() {
        return telp;
    }

    /**
     * @param telp the telp to set
     */
    public void setTelp(String telp) {
        this.telp = telp;
    }

    /**
     * @return the mahasiswaWali
     */
    public List<Mahasiswa> getMahasiswaWali() {

        if(mahasiswaWali == null){
            mahasiswaWali = new ArrayList<Mahasiswa>();
        }

        return mahasiswaWali;
    }

    /**
     * @param mahasiswaWali the mahasiswaWali to set
     */
    public void setMahasiswaWali(List<Mahasiswa> mahasiswaWali) {
        this.mahasiswaWali = mahasiswaWali;
    }

    

}