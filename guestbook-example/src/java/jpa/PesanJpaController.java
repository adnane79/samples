/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package jpa;

import entity.Pesan;
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
public class PesanJpaController {

    public PesanJpaController() {
        emf = Persistence.createEntityManagerFactory("guestbook-examplePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pesan pesan) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pesan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pesan pesan) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pesan = em.merge(pesan);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pesan.getId();
                if (findPesan(id) == null) {
                    throw new NonexistentEntityException("The pesan with id " + id + " no longer exists.");
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
            Pesan pesan;
            try {
                pesan = em.getReference(Pesan.class, id);
                pesan.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pesan with id " + id + " no longer exists.", enfe);
            }
            em.remove(pesan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pesan> findPesanEntities() {
        return findPesanEntities(true, -1, -1);
    }

    public List<Pesan> findPesanEntities(int maxResults, int firstResult) {
        return findPesanEntities(false, maxResults, firstResult);
    }

    private List<Pesan> findPesanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pesan.class));
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

    public Pesan findPesan(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pesan.class, id);
        } finally {
            em.close();
        }
    }

    public int getPesanCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pesan> rt = cq.from(Pesan.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
