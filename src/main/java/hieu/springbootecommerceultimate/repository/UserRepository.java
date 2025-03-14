package hieu.springbootecommerceultimate.repository;

import hieu.springbootecommerceultimate.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "select count (*) as cnt from users where email like :email and enabled = true", nativeQuery = true)
    int countByEmail(String email);

    @Query(value = "select * from users where email like :email and enabled = true limit :limit offset :offset", nativeQuery = true)
    List<UserEntity> findByEmail(String email, int limit, int offset);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByToken(String token);
}
