package com.animon.data.repository;

import com.animon.data.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity,Long> {

    Optional<RoleEntity> findByRoleName(String roleName) ;

//    @Query("select rol from Users user join user.roles rol where user.email=:emailParam")
//    RoleEntity userEmailFindRole(@Param("emailParam") String email);

}
