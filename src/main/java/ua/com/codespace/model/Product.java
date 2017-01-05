package ua.com.codespace.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by devT on 26.12.2016.
 */
@Entity
public class Product {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userProduct;

    @OneToMany(mappedBy = "product")
    private Set<Bid> bids = new HashSet<Bid>();
}
