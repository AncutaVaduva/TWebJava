package model;

import com.sun.scenario.effect.impl.prism.PrImage;

public class Book {
    private String title;
    private String author;
    private String category;
    private Integer numberOfCopies;
    private Integer numberOfAvailableCopies;

    public Book(String title, String author, String category, Integer numberOfCopies) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.numberOfCopies = numberOfCopies;
        this.numberOfAvailableCopies = numberOfCopies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumberOfAvailableCopies() {
        return numberOfAvailableCopies;
    }

    public void setNumberOfAvailableCopies(Integer numberOfAvailableCopies) {
        this.numberOfAvailableCopies = numberOfAvailableCopies;
    }

    public Integer getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(Integer numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                ", numberOfAvailableCopies=" + numberOfAvailableCopies +
                '}';
    }
}
