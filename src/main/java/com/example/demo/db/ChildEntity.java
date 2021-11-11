package com.example.demo.db;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ChildEntity {

    public ChildEntity(String childValue) {
        this.childValue = childValue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String childValue;

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private ParentEntity parent;

}
