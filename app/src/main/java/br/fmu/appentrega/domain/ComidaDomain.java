package br.fmu.appentrega.domain;

import java.io.Serializable;

public class ComidaDomain implements Serializable {
    private String title;
    private String pic;
    private String description;
    private Double preco;
    private int numberInCart;

    public ComidaDomain(String title, String pic, String description, Double preco) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.preco = preco;
    }

    public ComidaDomain(String title, String pic, String description, Double preco, int numberInCart) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.preco = preco;
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
