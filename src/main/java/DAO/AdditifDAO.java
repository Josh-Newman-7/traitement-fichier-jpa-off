package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entite.Additif;

public class AdditifDAO {

    private EntityManager entityManager;

    public AdditifDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Additif additif) {
        entityManager.getTransaction().begin();
        entityManager.persist(additif);
        entityManager.getTransaction().commit();
    }

    public Additif findById(Long id) {
        return entityManager.find(Additif.class, id);
    }

    public List<Additif> findAll() {
        TypedQuery<Additif> query = entityManager.createQuery("SELECT a FROM Additif a", Additif.class);
        return query.getResultList();
    }

    public void update(Additif additif) {
        entityManager.getTransaction().begin();
        entityManager.merge(additif);
        entityManager.getTransaction().commit();
    }

    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Additif additif = entityManager.find(Additif.class, id);
        if (additif != null) {
            entityManager.remove(additif);
        }
        entityManager.getTransaction().commit();
    }
}

