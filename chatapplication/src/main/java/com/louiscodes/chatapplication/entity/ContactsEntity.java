package com.louiscodes.chatapplication.entity;

import lombok.Data;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="contacts")
public class ContactsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="contact_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_holder_id")
    private AccountHolderEntity accountHolder;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "contact_relations",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "account_holder_id"))
    private Set<AccountHolderEntity> contacts = new HashSet<>();
}
