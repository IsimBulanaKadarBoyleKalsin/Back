package com.animon.data.repository;

import com.animon.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUserEmail(String email);
    Optional<UserEntity> findByUserId(Long id);

}
