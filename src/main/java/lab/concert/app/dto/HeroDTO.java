package lab.concert.app.dto;

public class HeroDTO {
    private Long id;
    private String name;
    private String role;
    private int healthPoints;
    private String abilities;
    private Long teamId;
    private String teamName;

    public HeroDTO() {
    }

    public HeroDTO(Long id, String name, String role, int healthPoints, String abilities, Long teamId, String teamName) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.healthPoints = healthPoints;
        this.abilities = abilities;
        this.teamId = teamId;
        this.teamName = teamName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
