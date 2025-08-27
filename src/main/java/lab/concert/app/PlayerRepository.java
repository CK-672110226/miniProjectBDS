package lab.concert.app;

import org.springframework.data.repository.CrudRepository;
import lab.concert.app.domain.Player;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    Player findByName(String name);
    Player findByEmail(String email);
}
