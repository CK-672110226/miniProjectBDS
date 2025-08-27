package lab.concert.app;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import lab.concert.app.domain.Hero;
import lab.concert.app.domain.Team;

@DataJpaTest
public class HeroControllerTests {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private HeroRepository heroRepository;

    @Test
    public void testCreateHero() {
        // Create a team first
        Team team = new Team("Team Alpha");
        team = em.persist(team);

        // Create a hero
        Hero hero = new Hero("Warrior", "Tank", 1000, "Shield Bash, Taunt", team);
        hero = em.persist(hero);

        // Verify hero creation
        assertNotNull(hero.getId());
        assertEquals("Warrior", hero.getName());
        assertEquals("Tank", hero.getRole());
        assertEquals(1000, hero.getHealthPoints());
        assertEquals("Team Alpha", hero.getTeam().getName());
    }

    @Test
    public void testListHeroes() {
        // Create teams
        Team team1 = new Team("Team Alpha");
        Team team2 = new Team("Team Beta");
        team1 = em.persist(team1);
        team2 = em.persist(team2);

        // Create heroes
        Hero hero1 = new Hero("Warrior", "Tank", 1000, "Shield Bash", team1);
        Hero hero2 = new Hero("Mage", "Support", 800, "Heal, Shield", team2);
        Hero hero3 = new Hero("Archer", "Damage", 700, "Arrow Shot, Rapid Fire", team1);

        em.persist(hero1);
        em.persist(hero2);
        em.persist(hero3);

        // Get all heroes
        Iterable<Hero> heroesIterable = heroRepository.findAll();
        List<Hero> heroes = (List<Hero>) heroesIterable;
        assertEquals(3, heroes.size());

        // Verify hero details
        Hero foundHero = heroRepository.findById(hero1.getId()).get();
        assertEquals("Warrior", foundHero.getName());
        assertEquals("Tank", foundHero.getRole());
    }

    @Test
    public void testUpdateHero() {
        // Create team and hero
        Team team = new Team("Team Alpha");
        team = em.persist(team);

        Hero hero = new Hero("Warrior", "Tank", 1000, "Shield Bash", team);
        hero = em.persist(hero);

        // Update hero
        hero.setHealthPoints(1200);
        hero.setAbilities("Shield Bash, Taunt, Charge");
        hero = em.persist(hero);

        // Verify update
        Hero updatedHero = em.find(Hero.class, hero.getId());
        assertEquals(1200, updatedHero.getHealthPoints());
        assertEquals("Shield Bash, Taunt, Charge", updatedHero.getAbilities());
    }

    @Test
    public void testDeleteHero() {
        // Create team and hero
        Team team = new Team("Team Alpha");
        team = em.persist(team);

        Hero hero = new Hero("Warrior", "Tank", 1000, "Shield Bash", team);
        hero = em.persist(hero);

        // Delete hero
        em.remove(hero);

        // Verify deletion
        Hero deletedHero = em.find(Hero.class, hero.getId());
        assertNull(deletedHero);
    }

    @Test
    public void testFindHeroByName() {
        // Create team and heroes
        Team team = new Team("Team Alpha");
        team = em.persist(team);

        Hero hero1 = new Hero("Warrior", "Tank", 1000, "Shield Bash", team);
        Hero hero2 = new Hero("Mage", "Support", 800, "Heal", team);
        em.persist(hero1);
        em.persist(hero2);

        // Find hero by name
        List<Hero> warriors = heroRepository.findByName("Warrior");
        assertEquals(1, warriors.size());
        assertEquals("Tank", warriors.get(0).getRole());
    }

    @Test
    public void testFindHeroByRole() {
        // Create team and heroes
        Team team = new Team("Team Alpha");
        team = em.persist(team);

        Hero hero1 = new Hero("Warrior", "Tank", 1000, "Shield Bash", team);
        Hero hero2 = new Hero("Paladin", "Tank", 1100, "Heal, Shield", team);
        em.persist(hero1);
        em.persist(hero2);

        // Find heroes by role
        List<Hero> tanks = heroRepository.findByRole("Tank");
        assertEquals(2, tanks.size());
        assertTrue(tanks.stream().allMatch(h -> h.getRole().equals("Tank")));
    }

    @Test
    public void testHeroTeamRelationship() {
        // Create teams
        Team team1 = new Team("Team Alpha");
        Team team2 = new Team("Team Beta");
        team1 = em.persist(team1);
        team2 = em.persist(team2);

        // Create heroes for different teams
        Hero hero1 = new Hero("Warrior", "Tank", 1000, "Shield Bash", team1);
        Hero hero2 = new Hero("Mage", "Support", 800, "Heal", team2);
        em.persist(hero1);
        em.persist(hero2);

        // Verify team relationships
        assertEquals("Team Alpha", hero1.getTeam().getName());
        assertEquals("Team Beta", hero2.getTeam().getName());
    }
    
    @Test
    public void testHeroHealthPointsValidation() {
        // Create team
        Team team = new Team("Team Alpha");
        team = em.persist(team);

        // Test valid health points
        Hero hero = new Hero("Warrior", "Tank", 1000, "Shield Bash", team);
        em.persist(hero);

        Hero foundHero = em.find(Hero.class, hero.getId());
        assertTrue(foundHero.getHealthPoints() > 0, "Health points should be positive");
        assertTrue(foundHero.getHealthPoints() <= 2000, "Health points should be reasonable");
    }
}
