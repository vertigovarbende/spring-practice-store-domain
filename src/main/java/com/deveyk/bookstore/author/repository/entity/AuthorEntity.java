package com.deveyk.bookstore.author.repository.entity;

import com.deveyk.bookstore.author.model.enums.AuthorStatus;
import com.deveyk.bookstore.author.repository.entity.embeddable.AuthorContactEmbeddable;
import com.deveyk.bookstore.author.repository.entity.embeddable.AuthorNameEmbeddable;
import com.deveyk.bookstore.book.repository.entity.BookEntity;
import com.deveyk.bookstore.common.repository.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "BS_AUTHORS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Embedded
    AuthorNameEmbeddable name;

    @Embedded
    AuthorContactEmbeddable contact;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private AuthorStatus status;

    @Column(name = "IS_VERIFIED")
    private Boolean isVerified;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private Set<BookEntity> books;

    // Methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorEntity authorEntity)) return false;
        return id != null && id.equals(authorEntity.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
