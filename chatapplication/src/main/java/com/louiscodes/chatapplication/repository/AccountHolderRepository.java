package com.louiscodes.chatapplication.repository;

import com.louiscodes.chatapplication.entity.AccountHolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource  //this is to make the repo a sort of api in a sense. while @Repository is used to create a repository, @RepositoryRestResource is used to expose that repository as a RESTful endpoint.
public interface AccountHolderRepository extends JpaRepository<AccountHolderEntity, Long> {

    Optional<AccountHolderEntity> findByUsername(String username);

    Optional<AccountHolderEntity> findByEmail(String email);
}
