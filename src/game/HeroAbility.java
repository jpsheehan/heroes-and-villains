package game;

/**
 * Represents the different kinds of abilities that Heroes can have.
 * TODO: The exact abilities will need to be discussed.
 *
 */
public enum HeroAbility implements Ability {
	CHEAPER_ITEMS, // for commerce students
	VILLAINS_LESS_20_HEALTH, // (20%) engineering students (body odour)
	INCREASED_RECOVERY_RATE, // (for team) science students
	WITTY_PHRASES, // (useless) arts students
	IMPROVED_ODDS, // (for dice game and guess the number) maths students
	PREVENTS_ROBBERY, // law students
	TELEPORT // (allows the team to teleport to the next city (single use)) compsoc students
}
