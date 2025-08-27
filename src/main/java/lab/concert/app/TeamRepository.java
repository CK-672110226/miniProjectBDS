package lab.concert.app;

import org.springframework.data.repository.CrudRepository;
import lab.concert.app.domain.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
    Team findByName(String name);
}
