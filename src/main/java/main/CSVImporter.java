package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import DAO.AdditifDAO;
import DAO.AllergeneDAO;
import DAO.CategorieDAO;
import DAO.InfosNutritionnellesDAO;
import DAO.IngredientDAO;
import DAO.MarqueDAO;
import DAO.ProduitDAO;
import DAO.VitamineDAO;

import entite.Categorie;
import entite.Ingredient;
import entite.Marque;
import entite.Produit;

public class CSVImporter{
    private EntityManager entityManager;
    private AdditifDAO additifDAO;
    private AllergeneDAO allergeneDAO;
    private CategorieDAO categorieDAO;
    private InfosNutritionnellesDAO INDAO;
    private IngredientDAO ingredientDAO;
    private MarqueDAO marqueDAO;
    private ProduitDAO produitDAO;
    private VitamineDAO vitamineDAO;

    public CSVImporter(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    
    
    public void importCSVData(String filePath) throws IOException {
    	Path pathfile = Paths.get("");
        List<String> lines = Files.readAllLines(pathfile);
        
        for(int i=0; i<lines.size();i++) {
        	String line = lines.get(i);
        	String[] data = line.split("\\|", -1);
        	
        	if (data.length != 28) {
                // Gestion des erreurs : la ligne n'a pas le bon nombre de colonnes
                logError("Ligne incorrecte : " + line);
                continue;
            }

            if (!validateData(data)) {
                // Gestion des erreurs : les données de la ligne ne sont pas valides
                logError("Données non valides dans la ligne : " + line);
                continue;
            }

            //Dernière étape après toutes les vérification : on insert en base
            insertDataIntoDatabase(data);
        	
        }
    }

    
    
    
    private boolean validateData(String[] data) {
        // Champs obligatoires non vide
        if (isEmpty(data[0]) || isEmpty(data[1]) || isEmpty(data[2])) {
            return false;
        }

        // Vérification de la colonne "nutritionGradeFr" (a, b, c, d ou e)
        if (!isValidNutritionGrade(data[3])) {
            return false;
        }

        // Autres règles de validation

        return true;
    }

    
    
    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean isValidNutritionGrade(String grade) {
        return grade != null && grade.matches("[a-e]");
    }

    
    

    private void insertDataIntoDatabase(String[] data) {    	
    	// Recherche d'un doublon
        TypedQuery<Produit> query = entityManager.createQuery("SELECT p FROM Produit p WHERE p.nom = :nom AND p.marque.nom = :marque", Produit.class);
        query.setParameter("nom", data[2]);
        query.setParameter("marque", data[1]);

        List<Produit> existingProducts = query.getResultList();

        if (existingProducts.isEmpty()) {
            // Aucun doublon trouvé, insertion de la nouvelle entrée.
            Produit produit = produitDAO.createProductFromData(data[2]);

            // Gestion de la relation avec la catégorie
            Categorie categorie = categorieDAO.findOrCreateCategorie(data[0]); // Recherche ou création de la catégorie
            produit.setCategorie(categorie);

            // Gestion de la relation avec la marque
            Marque marque = marqueDAO.findOrCreateMarque(data[1]); // Recherche ou création de la marque
            produit.setMarque(marque);

            // Gestion des ingrédients, allergènes et additifs
            List<Ingredient> ingredients = ingredientDAO.createIngredients(data[5]); // Créez les ingrédients à partir des données du CSV
            for (Ingredient ingredient : ingredients) {
                produit.addIngredient(ingredient);
            }

            entityManager.getTransaction().begin();
            entityManager.persist(produit);
            entityManager.getTransaction().commit();
        } else {
            // Doublon trouvé
            // Gestion + information d'une erreur
            logError("Doublon trouvé : " + data[2] + " de la marque " + data[1]);
        }
    }

    
    
    
    private void logError(String message) {
        //Print d'erreur => A voir si il ne faut pas écrire le tout dans un fichier à part
//        try (FileWriter errorLog = new FileWriter("error.log", true)) {
//            errorLog.write(message + System.lineSeparator());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.err.println(message);
    }
}

