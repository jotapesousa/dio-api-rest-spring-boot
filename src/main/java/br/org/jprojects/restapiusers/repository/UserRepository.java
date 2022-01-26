package br.org.jprojects.restapiusers.repository;

import br.org.jprojects.restapiusers.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
