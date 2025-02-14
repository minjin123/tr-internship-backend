package springbook.tr.user.model.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbook.tr.user.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
