# miniProjectBDS

## Domain Model Overview

### Hero
- **Attributes**: 
  - `name`: The name of the hero.
  - `role`: The role of the hero (e.g., Tank, Damage, Support).
  - `healthPoints`: The health points of the hero.
  - `abilities`: The abilities the hero can use in the game.

### Team
- **Attributes**: 
  - `name`: The name of the team.
- **Relationships**: 
  - Can have multiple heroes associated with it.

### Player
- **Attributes**: 
  - `name`: The name of the player.
  - `email`: The email of the player.
  - `level`: The current level of the player.
  - `experiencePoints`: The experience points accumulated by the player.

### Match
- **Attributes**: 
  - `status`: The current status of the match (e.g., InProgress, Completed).
  - `duration`: The duration of the match.
- **Relationships**: 
  - Involves two teams competing against each other.

### HeroDTO
- **Purpose**: 
  - A Data Transfer Object for transferring hero data between layers of the application.
