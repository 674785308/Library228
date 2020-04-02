package com.etc.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {
    private int bid;
    private String title;
    private String author;
    private String cover;
    private double price;
    private String publisher;
    private String pubdate;
    private int bnum;
    private String summary;
    private int tid;

    public Book(String title, String author, String cover, double price, String publisher, String pubdate, String summary, int tid) {
        this.title = title;
        this.author = author;
        this.cover = cover;
        this.price = price;
        this.publisher = publisher;
        this.pubdate = pubdate;
        this.summary = summary;
        this.tid = tid;
    }

    public Book(int bid, String title, String author, double price, String publisher, String pubdate, String summary, int tid) {
        this.title = title;
        this.author = author;
        this.bid = bid;
        this.price = price;
        this.publisher = publisher;
        this.pubdate = pubdate;
        this.summary = summary;
        this.tid = tid;
    }
}
