package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entite.InfosNutritionnelles;

public class InfosNutritionnellesDAO {

    private EntityManager entityManager;

    public InfosNutritionnellesDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(InfosNutritionnelles infosNutritionnelles) {
        entityManager.getTransaction().begin();
        entityManager.persist(infosNutritionnelles);
        entityManager.getTransaction().commit();
    }

    public InfosNutritionnelles findById(Long id) {
        return entityManager.find(InfosNutritionnelles.class, id);
    }

    public List<InfosNutritionnelles> findAll() {
        TypedQuery<InfosNutritionnelles> query = entityManager.createQuery("SELECT i FROM InfosNutritionnelles i", InfosNutritionnelles.class);
        return query.getResultList();
    }

    public void update(InfosNutritionnelles infosNutritionnelles) {
        entityManager.getTransaction().begin();
        entityManager.merge(infosNutritionnelles);
        entityManager.getTransaction().commit();
    }

    public void delete(Long id) {
        entityManager.getTransaction().begin();
        InfosNutritionnelles infosNutritionnelles = entityManager.find(InfosNutritionnelles.class, id);
        if (infosNutritionnelles != null) {
            entityManager.remove(infosNutritionnelles);
        }
        entityManager.getTransaction().commit();
    }
    
    // Vous pouvez ajouter d'autres méthodes de recherche personnalisées ici en fonction de vos besoins.
}

