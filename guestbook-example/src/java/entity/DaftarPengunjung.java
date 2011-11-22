/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author radityo
 */
public class DaftarPengunjung {

    public DaftarPengunjung() {
        emf = Persistence.createEntityManagerFactory("guestbook-examplePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Pengunjung getPengunjung(String email){
        Pengunjung pengunjung = null;
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query q = em.createQuery("SELECT object(o) FROM Pengunjung as o WHERE o.email = :email");
            q.setParameter("email", email);
            pengunjung = (Pengunjung) q.getSingleResult();
        }catch(NoResultException e){
            
        }finally {
            if (em != null) {
                em.close();
            }
        }

        return pengunjung;
    }

    public void tambahPengunjung(Pengunjung pengunjung){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pengunjung);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
