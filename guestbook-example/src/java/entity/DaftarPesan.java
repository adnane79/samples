/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */
package entity;

import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author radityo
 */
public class DaftarPesan {

    private int jumlahPesan = -1;

    public DaftarPesan() {
        emf = Persistence.createEntityManagerFactory("guestbook-examplePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * @return the jumlahPesan
     */
    public int getJumlahPesan() {

        if (jumlahPesan == -1) {
            EntityManager em = null;
            try {
                em = getEntityManager();
                Query q = em.createQuery("SELECT count(o) FROM Pesan as o");
                Number jumlah = (Number) q.getSingleResult();
                jumlahPesan = jumlah.intValue();

            } catch (javax.persistence.EntityNotFoundException e) {
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        }

        return jumlahPesan;
    }

    public List<Pesan> sepuluhPesanTerbaru() {
        List<Pesan> pesan = null;
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query q = em.createQuery("SELECT object(o) FROM Pesan as o ORDER BY o.id DESC");
            q.setMaxResults(10);
            pesan = q.getResultList();

        } catch (javax.persistence.EntityNotFoundException e) {
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return pesan;
    }

    public void tambahPesan(Pesan pesan) {

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pesan.setTanggal(Calendar.getInstance().getTime());
            em.persist(pesan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }
}
