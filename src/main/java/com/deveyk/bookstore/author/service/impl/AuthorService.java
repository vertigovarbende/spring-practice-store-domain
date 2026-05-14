package com.deveyk.bookstore.author.service.impl;

import com.deveyk.bookstore.author.exception.AuthorAlreadyExistsException;
import com.deveyk.bookstore.author.exception.AuthorNotFoundException;
import com.deveyk.bookstore.author.model.mapper.AuthorApplicationMapper;
import com.deveyk.bookstore.author.repository.AuthorRepository;
import com.deveyk.bookstore.author.repository.entity.AuthorEntity;
import com.deveyk.bookstore.author.service.IAuthorService;
import com.deveyk.bookstore.author.service.command.AuthorChangeStatusCommand;
import com.deveyk.bookstore.author.service.command.AuthorContactUpdateCommand;
import com.deveyk.bookstore.author.service.command.AuthorCreateCommand;
import com.deveyk.bookstore.author.service.command.AuthorNameUpdateCommand;
import com.deveyk.bookstore.author.service.domain.Author;
import com.deveyk.bookstore.common.model.BsPage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorService implements IAuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorApplicationMapper authorApplicationMapper;

    @Override
    @Transactional
    public void create(AuthorCreateCommand command) {
        Objects.requireNonNull(command, "AuthorCreateCommand cannot be null");

        if (authorRepository.existsByContactEmailIgnoreCase(command.contact().email())) {
            throw new AuthorAlreadyExistsException(
                    "Author already exists with email: " + command.contact().email()
            );
        }

        Author author = Author.create(command.name(), command.contact());

        AuthorEntity entity = authorApplicationMapper.toEntity(author);
        authorRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public BsPage<Author> list(AuthorListCommand command) {
        Objects.requireNonNull(command, "AuthorListCommand cannot be null");

        Page<AuthorEntity> page = authorRepository.findAll(
                command.toSpecification(),
                command.toPageable()
        );

        List<Author> authors = page.getContent()
                .stream()
                .map(authorApplicationMapper::toDomain)
                .toList();

        return BsPage.<Author>builder()
                .content(authors)
                .page(page)
                .filteredBy(command.getFilter())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Author getById(String authorId) {
        return authorApplicationMapper.toDomain(getAuthorEntity(authorId));
    }

    @Override
    @Transactional
    public void updateName(AuthorNameUpdateCommand command) {
        Objects.requireNonNull(command, "AuthorNameUpdateCommand cannot be null");

        AuthorEntity entity = getAuthorEntity(command.authorId());
        Author author = authorApplicationMapper.toDomain(entity);

        author.updateName(command.name());

        authorRepository.save(authorApplicationMapper.toEntity(author));
    }

    @Override
    @Transactional
    public void updateContact(AuthorContactUpdateCommand command) {
        Objects.requireNonNull(command, "AuthorContactUpdateCommand cannot be null");

        AuthorEntity entity = getAuthorEntity(command.authorId());
        Author author = authorApplicationMapper.toDomain(entity);

        if (!author.getContact().getEmail().equalsIgnoreCase(command.contact().getEmail())
                && authorRepository.existsByContactEmailIgnoreCase(command.contact().getEmail())) {
            throw new AuthorAlreadyExistsException(
                    "Author already exists with email: " + command.contact().getEmail()
            );
        }

        author.updateContact(command.contact());

        authorRepository.save(authorApplicationMapper.toEntity(author));
    }

    @Override
    @Transactional
    public void changeStatus(AuthorChangeStatusCommand command) {
        Objects.requireNonNull(command, "AuthorChangeStatusCommand cannot be null");

        AuthorEntity entity = getAuthorEntity(command.authorId());
        Author author = authorApplicationMapper.toDomain(entity);

        author.changeStatus(command.targetStatus());

        authorRepository.save(authorApplicationMapper.toEntity(author));
    }

    @Override
    @Transactional
    public void verify(String authorId) {
        AuthorEntity entity = getAuthorEntity(authorId);
        Author author = authorApplicationMapper.toDomain(entity);

        author.verify();

        authorRepository.save(authorApplicationMapper.toEntity(author));
    }

    @Override
    @Transactional
    public void unverify(String authorId) {
        AuthorEntity entity = getAuthorEntity(authorId);
        Author author = authorApplicationMapper.toDomain(entity);

        author.unverify();

        authorRepository.save(authorApplicationMapper.toEntity(author));
    }

    private AuthorEntity getAuthorEntity(String authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(
                        "Author not found with id: " + authorId
                ));
    }

}
