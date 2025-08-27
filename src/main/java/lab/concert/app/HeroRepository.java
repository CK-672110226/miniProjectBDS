package lab.concert.app;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import lab.concert.app.domain.Hero;

public interface HeroRepository extends CrudRepository<Hero, Long> {
    List<Hero> findByName(String name);
    List<Hero> findByRole(String role);
    List<Hero> findByTeamId(Long teamId);
}
