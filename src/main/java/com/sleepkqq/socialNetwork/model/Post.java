package com.sleepkqq.socialNetwork.model;

import jakarta.persistence.*;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    private User author;
    @ManyToOne
    private User userPage;
    private Date date;

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM HH:mm", Locale.ENGLISH);
        return sdf.format(date);
    }
}
