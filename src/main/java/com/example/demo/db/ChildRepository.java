package com.example.demo.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ChildRepository extends JpaRepository<ChildEntity, Long> {

    ChildEntity findByChildValue(String childValue);

    ChildEntity getByChildValue(String value);

    @Transactional
    @Modifying
    @Query("""
            UPDATE ChildEntity c
            SET c.childValue = FUNCTION('regexp_replace', c.childValue, CONCAT('^', :from), :to)
            """
    )
    void replaceFirst(String from, String to);

}
