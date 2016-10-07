
public abstract class TowerAttack {
	
	private int damage;
	private double range;
	private double timeBetweenAttacks;
	private Enemy[] enemies;
	
	public abstract void findTargets();
	public abstract void attackTargets();
}
