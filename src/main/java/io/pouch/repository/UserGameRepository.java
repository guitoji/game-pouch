package io.pouch.repository;

import io.pouch.entities.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGameRepository extends JpaRepository<UserGame, Long>, JpaSpecificationExecutor<UserGame> {
}
