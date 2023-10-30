package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entite.Allergene;

public class AllergeneDAO {

    private EntityManager entityManager;

    public AllergeneDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Allergene allergene) {
        entityManager.getTransaction().begin();
        entityManager.persist(allergene);
        entityManager.getTransaction().commit();
    }

    public Allergene findById(Long id) {
        return entityManager.find(Allergene.class, id);
    }

    public List<Allergene> findAll() {
        TypedQuery<Allergene> query = entityManager.createQuery("SELECT a FROM Allergene a", Allergene.class);
        return query.getResultList();
    }

    public void update(Allergene allergene) {
        entityManager.getTransaction().begin();
        entityManager.merge(allergene);
        entityManager.getTransaction().commit();
    }

    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Allergene allergene = entityManager.find(Allergene.class, id);
        if (allergene != null) {
            entityManager.remove(allergene);
        }
        entityManager.getTransaction().commit();
    }
}
