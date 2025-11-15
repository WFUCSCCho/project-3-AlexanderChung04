/*
        ∗ @file: Ramen.java
∗ @description: This program implements ramen class with comparable interface, initializes points for each piece of data, implements equals/compareto/get/toString methods
        ∗ @author: Alexander Chung
∗ @date: November 14, 2025
                                              */

public class Ramen implements Comparable<Ramen> {
    private int reviewNum;
    private String brand;
    private String variety;
    private String style;
    private String country;
    private double stars;

    public Ramen() {
        this.reviewNum = 0;
        this.brand = "N/A";
        this.variety = "N/A";
        this.style = "N/A";
        this.country = "N/A";
        this.stars = 0;
    }

    public Ramen(int reviewNum, String brand, String variety, String style, String country, double stars) {
        this.reviewNum = reviewNum;
        this.brand = brand;
        this.variety = variety;
        this.style = style;
        this.country = country;
        this.stars = stars;
    }

    Ramen(Ramen r) {
        reviewNum = r.reviewNum;
        brand = r.brand;
        variety = r.variety;
        style = r.style;
        country = r.country;
        stars = r.stars;
    }

    @Override //prints all datapoints
    public String toString() {
        return (reviewNum + ", " + brand + ", " + variety + ", " + style + ", " + country + ", " + stars);
    }

    public int getReviewNum() {
        return reviewNum;
    }

    public String getBrand() {
        return brand;
    }

    public String getVariety() {
        return variety;
    }

    public String getStyle() {
        return style;
    }

    public String getCountry() {
        return country;
    }

    public double getStars() {
        return stars;
    }

 /*   public boolean equals(Ramen p){
        if(p == null){return false;
        }(getClass() != obj.getClass())
        else{
            return(this.getReviewNum() == (p.getReviewNum())) && (this.getBrand().equals(p.getBrand())) && (this.getVariety().equals(p.getVariety())) && (this.getStyle().equals(p.getStyle())) && (this.getCountry().equals(p.getCountry())) && (this.getStars() == p.getStars());
        }
    }
*/

    @Override
    public boolean equals(Object obj) { //compares by review number
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Ramen)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Ramen p = (Ramen) obj;
        return (this.getReviewNum() == (p.getReviewNum())); // && (this.getBrand().equals(p.getBrand())) && (this.getVariety().equals(p.getVariety())) && (this.getStyle().equals(p.getStyle())) && (this.getCountry().equals(p.getCountry())) && (this.getStars() == p.getStars());
    }

    //compares by review number
    public int compareTo(Ramen obj) {
        return Integer.compare(this.getReviewNum(), obj.getReviewNum());
    }
}
