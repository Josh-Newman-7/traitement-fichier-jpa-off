package entite;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InfosNutritionnelles {
    @Column(name = "energie_100g")
    private Double energie100g;
    
    @Column(name = "graisse_100g")
    private Double graisse100g;
    
    @Column(name = "sucre_100g")
    private Double sucre100g;
    
    @Column(name = "fibres_100g")
    private Double fibres100g;
    
    @Column(name = "proteines_100g")
    private Double proteines100g;
    
    @Column(name = "sel_100g")
    private Double sel100g;
    
    @Column(name = "calcium_100g")
    private Double calcium100g;
    
    @Column(name = "magnesium_100g")
    private Double magnesium100g;
    
    @Column(name = "iron_100g")
    private Double iron100g;

    // Constructeurs
	public InfosNutritionnelles(Double energie100g, Double graisse100g, Double sucre100g, Double fibres100g,
			Double proteines100g, Double sel100g, Double calcium100g, Double magnesium100g, Double iron100g) {
		super();
		this.energie100g = energie100g;
		this.graisse100g = graisse100g;
		this.sucre100g = sucre100g;
		this.fibres100g = fibres100g;
		this.proteines100g = proteines100g;
		this.sel100g = sel100g;
		this.calcium100g = calcium100g;
		this.magnesium100g = magnesium100g;
		this.iron100g = iron100g;
	}
	
	public InfosNutritionnelles() {}

	
	//getters setters
	public Double getEnergie100g() {
		return energie100g;
	}
	public void setEnergie100g(Double energie100g) {
		this.energie100g = energie100g;
	}

	public Double getGraisse100g() {
		return graisse100g;
	}
	public void setGraisse100g(Double graisse100g) {
		this.graisse100g = graisse100g;
	}

	public Double getSucre100g() {
		return sucre100g;
	}
	public void setSucre100g(Double sucre100g) {
		this.sucre100g = sucre100g;
	}

	public Double getFibres100g() {
		return fibres100g;
	}
	public void setFibres100g(Double fibres100g) {
		this.fibres100g = fibres100g;
	}

	public Double getProteines100g() {
		return proteines100g;
	}
	public void setProteines100g(Double proteines100g) {
		this.proteines100g = proteines100g;
	}

	public Double getSel100g() {
		return sel100g;
	}
	public void setSel100g(Double sel100g) {
		this.sel100g = sel100g;
	}

	public Double getCalcium100g() {
		return calcium100g;
	}
	public void setCalcium100g(Double calcium100g) {
		this.calcium100g = calcium100g;
	}

	public Double getMagnesium100g() {
		return magnesium100g;
	}
	public void setMagnesium100g(Double magnesium100g) {
		this.magnesium100g = magnesium100g;
	}

	public Double getIron100g() {
		return iron100g;
	}
	public void setIron100g(Double iron100g) {
		this.iron100g = iron100g;
	}
}

