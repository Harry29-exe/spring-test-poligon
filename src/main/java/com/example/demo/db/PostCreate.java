package com.example.demo.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@Component
//@Transactional
@RestController
public class PostCreate {

    @Autowired
    private ChildRepository childRepository;
    @Autowired
    private ParentRepository parentRepository;
    private Logger logger = Logger.getLogger("PostCreate");

//    @PostConstruct
//    @Transactional
    @GetMapping("/we")
    public void addChildrenTest() {
        childRepository.deleteAll();
        parentRepository.deleteAll();

        saveParent();
        saveChild();
        print();


        var parent = parentRepository.findByValue("parent1");
        var children = parent.getChildren();
        children.get(0).setChildValue("modified value");
        parentRepository.save(parent);

        print();

//        parent = parentRepository.findByValue("parent1");
//        parentRepository.delete(parent);
//        print();

        saveChildWithParentId(parent.getId());
        print();

        childRepository.replaceFirst("ct", "XDD");
        print();
    }

    @GetMapping("we2")
    public void printAll() {
        this.print();
    }

    @Transactional
    public void saveParent() {
        var parent = new ParentEntity("parent1");
        var child = new ChildEntity("child0");
        child.setParent(parent);
        ArrayList<ChildEntity> children = new ArrayList<>();
        children.add(child);
        parent.setChildren(children);

        parentRepository.save(parent);

    }

    @Transactional
    void saveChild() {
        var parentId = parentRepository.findByValue("parent1").getId();
        var child = new ChildEntity("ctcthild1ct");

        child.setParent(parentRepository.getById(parentId));
        childRepository.save(child);
    }

    @Transactional
    void saveChildWithParentId(Long parentId) {
        var child = new ChildEntity("child2");
        var parent = parentRepository.getByValue("parent1");
//        var parent = parentRepository.getById(parentId);
        child.setParent(parent);

        childRepository.save(child);
    }

    private void print() {
        printChildren();
        printParents();
        System.out.println("----");
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void printChildren() {
//        childRepository.findAll()
//                .forEach(System.out::println);
        childRepository.findAll()
                .forEach(e -> logger.log(Level.INFO, e.toString()));
    }


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void printParents() {
//        parentRepository.findAll()
//                .forEach(System.out::println);
        parentRepository.findAll()
                .forEach(e -> logger.log(Level.INFO, e.toString()));
    }

}
