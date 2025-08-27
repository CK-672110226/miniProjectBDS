package lab.concert.app;

import org.springframework.data.repository.CrudRepository;
import lab.concert.app.domain.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {
    java.util.List<Match> findByStatus(String status);
    java.util.List<Match> findByTeam1IdOrTeam2Id(Long team1Id, Long team2Id);
}
