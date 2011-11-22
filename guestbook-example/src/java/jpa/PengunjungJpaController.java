/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package jpa;

import entity.Pengunjung;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author radityo
 */
public class PengunjungJpaController {

    public PengunjungJpaController() {
        emf = Persistence.createEntityManagerFactory("guestbook-examplePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pengunjung pengunjung) {
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

    public void edit(Pengunjung pengunjung) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pengunjung = em.merge(pengunjung);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pengunjung.getId();
                if (findPengunjung(id) == null) {
                    throw new NonexistentEntityException("The pengunjung with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pengunjung pengunjung;
            try {
                pengunjung = em.getReference(Pengunjung.class, id);
                pengunjung.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pengunjung with id " + id + " no longer exists.", enfe);
            }
            em.remove(pengunjung);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pengunjung> findPengunjungEntities() {
        return findPengunjungEntities(true, -1, -1);
    }

    public List<Pengunjung> findPengunjungEntities(int maxResults, int firstResult) {
        return findPengunjungEntities(false, maxResults, firstResult);
    }

    private List<Pengunjung> findPengunjungEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pengunjung.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pengunjung findPengunjung(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pengunjung.class, id);
        } finally {
            em.close();
        }
    }

    public int getPengunjungCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pengunjung> rt = cq.from(Pengunjung.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
