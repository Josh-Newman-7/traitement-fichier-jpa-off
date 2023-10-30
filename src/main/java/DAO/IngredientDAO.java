package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entite.Ingredient;

public class IngredientDAO {

    private EntityManager entityManager;

    public IngredientDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Ingredient ingredient) {
        entityManager.getTransaction().begin();
        entityManager.persist(ingredient);
        entityManager.getTransaction().commit();
    }

    public Ingredient findById(Long id) {
        return entityManager.find(Ingredient.class, id);
    }

    public List<Ingredient> findAll() {
        TypedQuery<Ingredient> query = entityManager.createQuery("SELECT i FROM Ingredient i", Ingredient.class);
        return query.getResultList();
    }

    public void update(Ingredient ingredient) {
        entityManager.getTransaction().begin();
        entityManager.merge(ingredient);
        entityManager.getTransaction().commit();
    }

    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Ingredient ingredient = entityManager.find(Ingredient.class, id);
        if (ingredient != null) {
            entityManager.remove(ingredient);
        }
        entityManager.getTransaction().commit();
    }

    public List<Ingredient> createIngredients(String ingredientsString) {
        List<String> ingredientNames = splitIngredients(ingredientsString);
        List<Ingredient> ingredients = createIngredientsFromNames(ingredientNames);
        return ingredients;
    }

    private List<String> splitIngredients(String ingredientsString) {
    	// Divisez la chaîne en utilisant une virgule comme séparateur
        String[] ingredientsArray = ingredientsString.split(",");
        // Créez une liste pour stocker les noms d'ingrédients filtrés
        List<String> filteredIngredients = new ArrayList<>();

        for (String ingredient : ingredientsArray) {
        	 // Divisez chaque ingrédient potentiel en utilisant le point-virgule comme séparateur
            String[] ingredientsArrayMieux = ingredient.split(";");
            // Si la division a produit plus d'une partie, cela signifie que nous avons un nom d'ingrédient valide
            if (ingredientsArrayMieux.length > 1) {
                String ingredientsFiltre = ingredientsArrayMieux[1].trim();
                ingredientsFiltre = ingredientsFiltre.replaceAll("_", " ");
                ingredientsFiltre = ingredientsFiltre.replaceAll("\\(.*?\\)", ""); // Supprimer le contenu entre parenthèses
                ingredientsFiltre = ingredientsFiltre.replaceAll("\\[.*?\\]", ""); // Supprimer le contenu entre crochets
                ingredientsFiltre = ingredientsFiltre.replaceAll("\"", ""); // Supprimer les guillemets
                ingredientsFiltre = ingredientsFiltre.replaceAll("\\d", ""); // Supprimer les chiffres
                ingredientsFiltre = ingredientsFiltre.replaceAll("%", ""); // Supprimer les pourcentages
                ingredientsFiltre = ingredientsFiltre.replaceAll("\\.", ""); // Supprimer les points
                ingredientsFiltre = ingredientsFiltre.replaceAll("\\*", ""); // Supprimer les astérisques
                ingredientsFiltre = ingredientsFiltre.replaceAll("[\\[\\]\\{\\}]", ""); // Supprimer les caractères entre crochets, parenthèses ou accolades
                ingredientsFiltre = ingredientsFiltre.trim();
                ingredientsFiltre = ingredientsFiltre.toLowerCase(); // Convertir en minuscules

                if (!ingredientsFiltre.isEmpty()) {
                    filteredIngredients.add(ingredientsFiltre);
                }
            }
        }

        return filteredIngredients;
    }


    private List<Ingredient> createIngredientsFromNames(List<String> ingredientNames) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (String ingredientName : ingredientNames) {
            Ingredient ingredient = new Ingredient();
            ingredient.setNom(ingredientName);
            create(ingredient);
            ingredients.add(ingredient);
        }
        return ingredients;
    }
}
