package com.deveyk.bookstore.category.repository.entity;

import com.deveyk.bookstore.category.model.enums.CategoryStatus;
import com.deveyk.bookstore.common.repository.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BS_CATEGORIES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME", nullable = false, length = 255)
    private String name;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private CategoryStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryEntity that)) return false;
        return this.id != null && this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
