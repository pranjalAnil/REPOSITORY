    package com.example.Project.entities;

    import jakarta.persistence.*;
    import lombok.Data;
    import org.modelmapper.internal.bytebuddy.dynamic.loading.InjectionClassLoader;

    import java.util.ArrayList;
    import java.util.List;

    @Entity
    @Table(name = "categories")
    @Data
    public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int categoryId;

        @Column(name = "title")
        private String categoryTitle;

        @Column (name = "description")
        private String categoryDescription;

        @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
        private List<Post> posts= new ArrayList<>();



    }
