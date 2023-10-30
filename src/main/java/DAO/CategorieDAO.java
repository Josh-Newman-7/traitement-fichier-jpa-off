package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entite.Categorie;

public class CategorieDAO {

    private EntityManager entityManager;

    public CategorieDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Categorie categorie) {
        entityManager.getTransaction().begin();
        entityManager.persist(categorie);
        entityManager.getTransaction().commit();
    }

    public Categorie findById(Long id) {
        return entityManager.find(Categorie.class, id);
    }

    public List<Categorie> findAll() {
        TypedQuery<Categorie> query = entityManager.createQuery("SELECT c FROM Categorie c", Categorie.class);
        return query.getResultList();
    }

    public void update(Categorie categorie) {
        entityManager.getTransaction().begin();
        entityManager.merge(categorie);
        entityManager.getTransaction().commit();
    }

    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Categorie categorie = entityManager.find(Categorie.class, id);
        if (categorie != null) {
            entityManager.remove(categorie);
        }
        entityManager.getTransaction().commit();
    }

    public Categorie findOrCreateCategorie(String nom) {
        // Recherchez la catégorie par nom
        TypedQuery<Categorie> query = entityManager.createQuery("SELECT c FROM Categorie c WHERE c.nom = :nom", Categorie.class);
        query.setParameter("nom", nom);

        List<Categorie> categories = query.getResultList();

        if (!categories.isEmpty()) {
            // La catégorie existe déjà
            return categories.get(0);
        } else {
            // La catégorie n'existe pas
            Categorie nouvelleCategorie = new Categorie();
            nouvelleCategorie.setNom(nom);

            // Insérer la nouvelle catégorie dans la base de données
            entityManager.getTransaction().begin();
            entityManager.persist(nouvelleCategorie);
            entityManager.getTransaction().commit();

            // Retourner la nouvelle catégorie
            return nouvelleCategorie;
        }
    }

}

