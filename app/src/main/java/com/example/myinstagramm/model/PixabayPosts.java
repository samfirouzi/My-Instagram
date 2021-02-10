package com.example.myinstagramm.model;

import java.util.List;

public class PixabayPosts {
    int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public List<Post> getHits() {
        return hits;
    }

    public void setHits(List<Post> hits) {
        this.hits = hits;
    }

    int totalHits;
    List<Post> hits;
}
