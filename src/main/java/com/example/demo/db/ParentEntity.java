package com.example.demo.db;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ParentEntity {

    public ParentEntity(String value) {
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String value;

    @Column
    private String value2 = "we";

    @OneToMany(mappedBy = "parent", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<ChildEntity> children;
//
//    public void addChildren(ChildEntity children) {
//        this.children.contains()
//    }

    @Override
    public String toString() {
        return "ParentEntity{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", childrenSize='" + children.size() + '\'' +
                '}';
    }
}
