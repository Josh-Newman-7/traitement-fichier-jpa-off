package main;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entite.Allergene;
import entite.Categorie;
import entite.Ingredient;
import entite.Marque;
import entite.Produit;


public class IntegrationOpenFoodFacts {

	public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
        EntityManager em = emf.createEntityManager();       
        
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Sélectionnez une option :");
            System.out.println("1. Affichez les 10 produits les mieux notés d'une catégorie");
            System.out.println("2. Affichez les 10 produits les mieux notés d'une marque");
            System.out.println("3. Affichez les 10 produits les mieux notés d'une catégorie sans un ingrédient");
            System.out.println("4. Affichez les 10 produits les mieux notés d'une marque sans un ingrédient");
            System.out.println("5. Affichez les 10 produits les mieux notés d'une catégorie sans un allergène");
            System.out.println("6. Affichez les 10 produits les mieux notés d'une marque sans un allergène");
            System.out.println("0. Quitter");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    // Option 1 : Affichez les 10 produits les mieux notés d'une catégorie
                    // Implémentez le code pour cette option.
                	top10ProductsByCat(em);
                    break;
                case 2:
                    // Option 2 : Affichez les 10 produits les mieux notés d'une marque
                    // Implémentez le code pour cette option.
                	top10ProductsByMarque(em);
                    break;
                case 3:
                    // Option 3 : Affichez les 10 produits les mieux notés d'une catégorie sans un ingrédient
                    // Implémentez le code pour cette option.
                	top10ProductsByCatWIng(em);
                    break;
                case 4:
                    // Option 4 : Affichez les 10 produits les mieux notés d'une marque sans un ingrédient
                    // Implémentez le code pour cette option.
                	top10ProductsByMarqueWIng(em);
                    break;
                case 5:
                    // Option 5 : Affichez les 10 produits les mieux notés d'une catégorie sans un allergène
                    // Implémentez le code pour cette option.
                	top10ProductsByCatWAll(em);
                    break;
                case 6:
                    // Option 6 : Affichez les 10 produits les mieux notés d'une marque sans un allergène
                    // Implémentez le code pour cette option.
                	top10ProductsByMarqueWAll(em);
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option non valide. Réessayez.");
                    break;
            }

        } while (choice != 0);
        
        // Fermeture des ressources et EntityManagerFactory.
        em.close();
        emf.close();
    }

	private static void top10ProductsByCat(EntityManager em) {
	    Scanner scanner = new Scanner(System.in);

	    System.out.println("Veuillez entrer le nom de la catégorie (par exemple : Catégorie1, Catégorie2) : ");
	    String categoryName = scanner.nextLine();

	    // Vérifier si la catégorie existe.
	    TypedQuery<Long> categoryCheckQuery = em.createQuery("SELECT COUNT(c) FROM Categorie c WHERE c.nom = :categoryName", Long.class);
	    categoryCheckQuery.setParameter("categoryName", categoryName);
	    Long categoryCount = categoryCheckQuery.getSingleResult();

	    if (categoryCount > 0) {
	        TypedQuery<Produit> query = em.createQuery("SELECT p FROM Produit p WHERE p.categorie.nom = :categoryName ORDER BY p.nutritionGradeFr ASC", Produit.class);
	        query.setParameter("categoryName", categoryName);
	        query.setMaxResults(10);

	        List<Produit> produits = query.getResultList();

	        // Affichez les produits.
	        for (Produit produit : produits) {
	            System.out.println("Nom du produit : " + produit.getNom());
	            System.out.println("Score nutritionnel : " + produit.getNutritionGradeFr());
	            System.out.println("------------------------------------");
	        }
	    } else {
	        System.out.println("La catégorie spécifiée n'existe pas.");
	    }

	    scanner.close();
	}

	private static void top10ProductsByMarque(EntityManager em) {
	    Scanner scanner = new Scanner(System.in);
	    
	    System.out.println("Veuillez entrer le nom de la marque (par exemple : Marque1, Marque2) : ");
	    String marqueName = scanner.nextLine().toLowerCase(); // Convertir en minuscules
	    
	    // Vérifier si la marque existe
	    TypedQuery<Marque> marqueQuery = em.createQuery("SELECT m FROM Marque m WHERE LOWER(m.nom) = :marqueName", Marque.class);
	    marqueQuery.setParameter("marqueName", marqueName);
	    
	    List<Marque> marques = marqueQuery.getResultList();
	    
	    if (marques.isEmpty()) {
	        System.out.println("La marque spécifiée n'existe pas.");
	        return; // Sortir de la méthode
	    }
	    
	    // La marque existe
	    TypedQuery<Produit> query = em.createQuery("SELECT p FROM Produit p WHERE LOWER(p.marque.nom) = :marqueName ORDER BY p.nutritionGradeFr ASC", Produit.class);
	    query.setParameter("marqueName", marqueName);
	    query.setMaxResults(10);

	    List<Produit> produits = query.getResultList();

	    // Affichez les produits.
	    for (Produit produit : produits) {
	        System.out.println("Nom du produit : " + produit.getNom());
	        System.out.println("Score nutritionnel : " + produit.getNutritionGradeFr());
	        System.out.println("------------------------------------");
	    }
	    
	    scanner.close();
	}

	private static void top10ProductsByCatWIng(EntityManager em) {
	    Scanner scanner = new Scanner(System.in);
	    
	    System.out.println("Veuillez entrer le nom de la catégorie (par exemple : Catégorie1, Catégorie2) : ");
	    String categoryName = scanner.nextLine().toLowerCase();
	    
	    // Vérifier si la catégorie existe
	    TypedQuery<Categorie> categorieQuery = em.createQuery("SELECT c FROM Categorie c WHERE LOWER(c.nom) = :categoryName", Categorie.class);
	    categorieQuery.setParameter("categoryName", categoryName);
	    
	    List<Categorie> categories = categorieQuery.getResultList();
	    
	    if (categories.isEmpty()) {
	        System.out.println("La catégorie spécifiée n'existe pas.");
	        return; // Sortir de la méthode
	    }
	    
	    // La catégorie existe
	    System.out.println("Veuillez entrer l'ingrédient à exclure : ");
	    String excludedIngredient = scanner.nextLine();
	    
	    TypedQuery<Produit> query = em.createQuery("SELECT p FROM Produit p WHERE LOWER(p.categorie.nom) = :categoryName AND NOT EXISTS (SELECT i FROM p.ingredients i WHERE i.nom = :excludedIngredient) ORDER BY p.nutritionGradeFr ASC", Produit.class);
	    query.setParameter("categoryName", categoryName);
	    query.setParameter("excludedIngredient", excludedIngredient);
	    query.setMaxResults(10);

	    List<Produit> produits = query.getResultList();

	    // Affichez les produits.
	    for (Produit produit : produits) {
	        System.out.println("Nom du produit : " + produit.getNom());
	        System.out.println("Score nutritionnel : " + produit.getNutritionGradeFr());
	        System.out.println("------------------------------------");
	    }
	    
	    scanner.close();
	}

	private static void top10ProductsByMarqueWIng(EntityManager em) {
	    Scanner scanner = new Scanner(System.in);
	    
	    System.out.println("Veuillez entrer le nom de la marque (par exemple : Marque1, Marque2) : ");
	    String marqueName = scanner.nextLine().toLowerCase(); // Convertir en minuscules
	    
	    // Vérifier si la marque existe
	    TypedQuery<Marque> marqueQuery = em.createQuery("SELECT m FROM Marque m WHERE LOWER(m.nom) = :marqueName", Marque.class);
	    marqueQuery.setParameter("marqueName", marqueName);
	    
	    List<Marque> marques = marqueQuery.getResultList();
	    
	    if (marques.isEmpty()) {
	        System.out.println("La marque spécifiée n'existe pas.");
	        return; // Sortir de la méthode
	    }
	    
	    // La marque existe
	    System.out.println("Veuillez entrer l'ingrédient à exclure : ");
	    String excludedIngredient = scanner.nextLine().toLowerCase(); // Convertir en minuscules
	    
	    // Vérifier si l'ingrédient existe
	    TypedQuery<Ingredient> ingredientQuery = em.createQuery("SELECT i FROM Ingredient i WHERE LOWER(i.nom) = :excludedIngredient", Ingredient.class);
	    ingredientQuery.setParameter("excludedIngredient", excludedIngredient);
	    
	    List<Ingredient> ingredients = ingredientQuery.getResultList();
	    
	    if (ingredients.isEmpty()) {
	        System.out.println("L'ingrédient spécifié n'existe pas.");
	        return; // Sortir de la méthode
	    }
	    
	    TypedQuery<Produit> query = em.createQuery("SELECT p FROM Produit p WHERE LOWER(p.marque.nom) = :marqueName AND NOT EXISTS (SELECT i FROM p.ingredients i WHERE LOWER(i.nom) = :excludedIngredient) ORDER BY p.nutritionGradeFr ASC", Produit.class);
	    query.setParameter("marqueName", marqueName);
	    query.setParameter("excludedIngredient", excludedIngredient);
	    query.setMaxResults(10);

	    List<Produit> produits = query.getResultList();

	    // Affichez les produits.
	    for (Produit produit : produits) {
	        System.out.println("Nom du produit : " + produit.getNom());
	        System.out.println("Score nutritionnel : " + produit.getNutritionGradeFr());
	        System.out.println("------------------------------------");
	    }
	    
	    scanner.close();
	}

	private static void top10ProductsByCatWAll(EntityManager em) {
	    Scanner scanner = new Scanner(System.in);
	    
	    System.out.println("Veuillez entrer le nom de la catégorie (par exemple : Catégorie1, Catégorie2) : ");
	    String categorieName = scanner.nextLine().toLowerCase(); // Convertir en minuscules
	    
	    // Vérifier si la catégorie existe
	    TypedQuery<Categorie> categorieQuery = em.createQuery("SELECT c FROM Categorie c WHERE LOWER(c.nom) = :categorieName", Categorie.class);
	    categorieQuery.setParameter("categorieName", categorieName);
	    
	    List<Categorie> categories = categorieQuery.getResultList();
	    
	    if (categories.isEmpty()) {
	        System.out.println("La catégorie spécifiée n'existe pas.");
	        return; // Sortir de la méthode
	    }
	    
	    // La catégorie existe
	    System.out.println("Veuillez entrer le nom de l'allergène à exclure : ");
	    String excludedAllergene = scanner.nextLine().toLowerCase(); // Convertir en minuscules
	    
	    // Vérifier si l'allergène existe
	    TypedQuery<Allergene> allergeneQuery = em.createQuery("SELECT a FROM Allergene a WHERE LOWER(a.nom) = :excludedAllergene", Allergene.class);
	    allergeneQuery.setParameter("excludedAllergene", excludedAllergene);
	    
	    List<Allergene> allergenes = allergeneQuery.getResultList();
	    
	    if (allergenes.isEmpty()) {
	        System.out.println("L'allergène spécifié n'existe pas.");
	        return; // Sortir de la méthode
	    }
	    
	    TypedQuery<Produit> query = em.createQuery("SELECT p FROM Produit p WHERE LOWER(p.categorie.nom) = :categorieName AND NOT EXISTS (SELECT a FROM p.allergenes a WHERE LOWER(a.nom) = :excludedAllergene) ORDER BY p.nutritionGradeFr ASC", Produit.class);
	    query.setParameter("categorieName", categorieName);
	    query.setParameter("excludedAllergene", excludedAllergene);
	    query.setMaxResults(10);

	    List<Produit> produits = query.getResultList();

	    // Affichez les produits.
	    for (Produit produit : produits) {
	        System.out.println("Nom du produit : " + produit.getNom());
	        System.out.println("Score nutritionnel : " + produit.getNutritionGradeFr());
	        // Affichez d'autres informations pertinentes du produit.
	        System.out.println("------------------------------------");
	    }
	    
	    scanner.close();
	}

	private static void top10ProductsByMarqueWAll(EntityManager em) {
	    Scanner scanner = new Scanner(System.in);
	    
	    System.out.println("Veuillez entrer le nom de la marque (par exemple : Marque1, Marque2) : ");
	    String marqueName = scanner.nextLine().toLowerCase(); // Convertir en minuscules
	    
	    // Vérifier si la marque existe
	    TypedQuery<Marque> marqueQuery = em.createQuery("SELECT m FROM Marque m WHERE LOWER(m.nom) = :marqueName", Marque.class);
	    marqueQuery.setParameter("marqueName", marqueName);
	    
	    List<Marque> marques = marqueQuery.getResultList();
	    
	    if (marques.isEmpty()) {
	        System.out.println("La marque spécifiée n'existe pas.");
	        return; // Sortir de la méthode
	    }
	    
	    // La marque existe
	    System.out.println("Veuillez entrer le nom de l'allergène à exclure : ");
	    String excludedAllergene = scanner.nextLine().toLowerCase(); // Convertir en minuscules
	    
	    // Vérifier si l'allergène existe
	    TypedQuery<Allergene> allergeneQuery = em.createQuery("SELECT a FROM Allergene a WHERE LOWER(a.nom) = :excludedAllergene", Allergene.class);
	    allergeneQuery.setParameter("excludedAllergene", excludedAllergene);
	    
	    List<Allergene> allergenes = allergeneQuery.getResultList();
	    
	    if (allergenes.isEmpty()) {
	        System.out.println("L'allergène spécifié n'existe pas.");
	        return; // Sortir de la méthode
	    }
	    
	    TypedQuery<Produit> query = em.createQuery("SELECT p FROM Produit p WHERE LOWER(p.marque.nom) = :marqueName AND NOT EXISTS (SELECT a FROM p.allergenes a WHERE LOWER(a.nom) = :excludedAllergene) ORDER BY p.nutritionGradeFr ASC", Produit.class);
	    query.setParameter("marqueName", marqueName);
	    query.setParameter("excludedAllergene", excludedAllergene);
	    query.setMaxResults(10);

	    List<Produit> produits = query.getResultList();

	    // Affichez les produits.
	    for (Produit produit : produits) {
	        System.out.println("Nom du produit : " + produit.getNom());
	        System.out.println("Score nutritionnel : " + produit.getNutritionGradeFr());
	        System.out.println("------------------------------------");
	    }
	    
	    scanner.close();
	}

}
