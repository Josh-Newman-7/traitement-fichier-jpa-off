package entite;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


//energies100g|graisse100g|sucre100g|fibres100g|proteines100g|sel100g|vitA100g|vitD100g|vitE100g|vitK100g|vitC100g|vitB1100g|vitB2100g|vitPP100g|vitB6100g|vitB9100g|vitB12100g|calcium100g|magnesium100g|iron100g|fer100g|betaCarotene100g|

//energies100g|graisse100g|sucre100g|fibres100g|proteines100g|sel100g|calcium100g|magnesium100g|iron100g|fer100g|

@Entity
@Table(name = "produit")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nom du produit
    @Column(name = "nom")
    private String nom;

    // Score nutritionnel (A, B, C, D, E, F)
    @Column(name = "score_nutritionnel")
    private char nutritionGradeFr;
    
    // Relation avec la catégorie du produit
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    // Relation avec la marque du produit
    @ManyToOne
    @JoinColumn(name = "marque_id")
    private Marque marque;
    
    // Liste d'ingrédients
    @ElementCollection
    @CollectionTable(name = "produit_ingredients", joinColumns = @JoinColumn(name = "produit_id"))
    @Column(name = "ingredient")
    private List<Ingredient> ingredients;

    // Liste d'allergènes
    @ElementCollection
    @CollectionTable(name = "produit_allergenes", joinColumns = @JoinColumn(name = "produit_id"))
    @Column(name = "allergene")
    private List<Allergene> allergenes;
    
    // Liste des additifs
    @ElementCollection
    @CollectionTable(name = "produit_additifs", joinColumns = @JoinColumn(name = "produit_id"))
    @Column(name = "additif")
    private List<Additif> additifs;

    @ManyToMany
    @JoinTable(name = "produit_vitamine", joinColumns = @JoinColumn(name = "produit_id"), inverseJoinColumns = @JoinColumn(name = "vitamine_id"))
    private Set<Vitamine> vitamines;

    // Présence d'huile de palme (vrai ou faux)
    @Column(name = "presence_huile_palme")
    private Boolean presenceHuilePalme;

    @Embedded
    private InfosNutritionnelles infosNutritionnelles;

    // Autres attributs et relations...
    
    
    // Constructeurs
	public Produit(String nom, char nutritionGradeFr, Categorie categorie, Marque marque,
			List<Ingredient> ingredients, List<Allergene> allergenes, List<Additif> additifs, Set<Vitamine> vitamines,
			Boolean presenceHuilePalme, InfosNutritionnelles infosNutritionnelles) {
		this.nom = nom;
		this.nutritionGradeFr = nutritionGradeFr;
		this.categorie = categorie;
		this.marque = marque;
		this.ingredients = ingredients;
		this.allergenes = allergenes;
		this.additifs = additifs;
		this.vitamines = vitamines;
		this.presenceHuilePalme = presenceHuilePalme;
		this.infosNutritionnelles = infosNutritionnelles;
	}
	
	public Produit() {}

    // Getters / setters
	public Long getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public char getNutritionGradeFr() {
		return nutritionGradeFr;
	}
	public void setNutritionGradeFr(char nutritionGradeFr) {
		this.nutritionGradeFr = nutritionGradeFr;
	}

	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Marque getMarque() {
		return marque;
	}
	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Allergene> getAllergenes() {
		return allergenes;
	}
	public void setAllergenes(List<Allergene> allergenes) {
		this.allergenes = allergenes;
	}

	public List<Additif> getAdditifs() {
		return additifs;
	}
	public void setAdditifs(List<Additif> additifs) {
		this.additifs = additifs;
	}

	public Set<Vitamine> getVitamines() {
		return vitamines;
	}
	public void setVitamines(Set<Vitamine> vitamines) {
		this.vitamines = vitamines;
	}

	public Boolean getPresenceHuilePalme() {
		return presenceHuilePalme;
	}
	public void setPresenceHuilePalme(Boolean presenceHuilePalme) {
		this.presenceHuilePalme = presenceHuilePalme;
	}

	public InfosNutritionnelles getInfosNutritionnelles() {
		return infosNutritionnelles;
	}
	public void setInfosNutritionnelles(InfosNutritionnelles infosNutritionnelles) {
		this.infosNutritionnelles = infosNutritionnelles;
	}

	public void addIngredient(Ingredient ingredient) {
	    // Vérifier que la liste d'ingrédients n'est pas nulle
	    if (this.ingredients == null) {
	        this.ingredients = new ArrayList<>();
	    }

	    this.ingredients.add(ingredient);

	    ingredient.setProduit(this);
	}

	
    // Méthodes equals()

    // Autres méthodes (CRUD, par exemple) pour la gestion des produits dans la base de données
}

