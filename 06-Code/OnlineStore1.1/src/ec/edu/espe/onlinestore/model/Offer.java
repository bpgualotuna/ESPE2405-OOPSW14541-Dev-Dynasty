/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.onlinestore.model;

/**
 *
 * @author  Dev Dynasty, DCCO-ESPE
 */
public class Offer {
    private String description;
    private Category category;
    private final double discount;

    public Offer(String description, double discount) {
        this.description = description;
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Offer{" + "description=" + description + ", category=" + category + '}';
    }
    
    
}
    
    