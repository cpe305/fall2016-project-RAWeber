package com.rawprogramming.games.Towers;

import java.util.ArrayList;
import com.rawprogramming.games.*;

public class TowerStore extends Grid {
	
	private int money;
	private ArrayList<Tower> towers;
	private Tower selectedTower;
	
	public TowerStore(int money, int offsetX, int offsetY, GameApp game){
		super(2, 5, offsetX, offsetY, game);
		towers = new ArrayList<Tower>();
		generateTowers();
		this.money = money;
	}
	
	public void selectTower(float x, float y){
		GridSquare square = getTouchedSquare(x, y);
		selectedTower = square.getTower();
	}
	
	public void deselectTower(){
		selectedTower = null;
	}
	
	public Tower buyTower(){
		money -= selectedTower.getCost();
		return selectedTower;
	}
	
	public void generateTowers(){
		towers.add(new Tower("Basic Tower", 50));
		for(int i = 0; i < towers.size(); i++){
			if(i % 2 == 0){
				getSquare(1, i/2).setTower(towers.get(i));
			}
			else{
				getSquare(0, (int)Math.ceil(i/2.0)).setTower(towers.get(i));
			}
		}
	}
	
	public boolean hasTowerSelected(){
		return selectedTower != null;
	}
	
	public void addMoney(int money){
		this.money += money;
	}
}
