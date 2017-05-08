package com.sdajava.hibernate2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "book", schema = "ksiegarnia")
public class Book {
    private String title;
    private String author;
    private Date published;
    private String description;
    private int id;

    public Book(){}

    public Book(String title, String author, Date published, String description) {
        this.title = title;
        this.author = author;
        this.published = published;
        this.description = description;
    }

    @Column(name = "tytul", nullable = true, length = 128)
    public String getTitle () { return this.title; }
    public void setTitle (String title) { this.title = title; }

    @Column(name = "autor", nullable = true, length = 128)
    public String getAuthor() { return this.author; }
    public void setAuthor(String author){ this.author = author;}

    @Column(name = "rok_wydania", nullable = true)
    public Date getPublished (){ return this.published;}
    public void setPublished (Date published) {this.published = published;}

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {return this.id;}
    public void setId (int id) {this.id = id;}

    @Column(name = "opis", nullable = true, length = 256)
    public String getDescription() {return this.description;}
    public void setDescription(String description) {this.description = description;}
}
