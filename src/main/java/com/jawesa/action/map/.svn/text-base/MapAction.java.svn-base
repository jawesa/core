package com.jawesa.action.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jawesa.action.common.Action;
import com.jawesa.annotation.controller.ControllerQualifier;
import com.jawesa.controller.common.Controller;

@Named
@SessionScoped
@SuppressWarnings("serial")
public class MapAction extends Action {

	private Player player;
	private Square square;
	private List<Square> map;
	
	@Inject
	@ControllerQualifier
	private Controller controller;
	
	@PostConstruct
	public void init() {
		int mapSize = 15;
		
		setMap(new ArrayList<MapAction.Square>());
		
		Land plain = new Land();
		plain.setName("Plaine");
		plain.setColor("#FFFFFF");
		
		Land forest = new Land();
		forest.setName("Foret");
		forest.setColor("#00FF00");
		
		Land desert = new Land();
		desert.setName("Désert");
		desert.setColor("#FFFF00");
		
		for(int i = -mapSize; i <= mapSize; i++) {
			for(int j = -mapSize; j <= mapSize; j++) {
				Square s = new Square();
				s.setX(i);
				s.setY(j);
				
				if(i > 0 && j > -3) {
					s.setLand(desert);
				}
				else if(i < j) {
					s.setLand(forest);
				} else {
					s.setLand(plain);
				}
				
				map.add(s);
			}
		}
		
		Player p1 = new Player();
		p1.setName("Vous");
		p1.setPv(50);
		p1.setPvMax(50);
		
		getSquare(0, 0).setPlayer(p1);
		
		Player p2 = new Player();
		p2.setName("Mage inconnu");
		p2.setPv(40);
		p2.setPvMax(40);
		
		getSquare(1, 1).setPlayer(p2);
		
		Player p3 = new Player();
		p3.setName("Gaasdrik");
		p3.setPv(1);
		p3.setPvMax(1);
		
		getSquare(2, 2).setPlayer(p3);
		
		Player p4 = new Player();
		p4.setName("Iset");
		p4.setPv(150);
		p4.setPvMax(150);
		
		getSquare(-2, 1).setPlayer(p4);
		
		Player p5 = new Player();
		p5.setName("Firk");
		p5.setPv(150);
		p5.setPvMax(150);
		
		getSquare(-2, 2).setPlayer(p5);
		
		Player p6 = new Player();
		p6.setName("Ideo");
		p6.setPv(5000);
		p6.setPvMax(5000);
		
		getSquare(0, 3).setPlayer(p6);
		
		Player p7 = new Player();
		p7.setName("Puissant guerrier");
		p7.setPv(100);
		p7.setPvMax(100);
		
		getSquare(-2, -1).setPlayer(p7);
		
		setPlayer(p1);
	}
	
	public List<Square> getDisplaySquares() {
		Square center = getSquare(player);
		int range = 5;
		
		List<Square> result = new ArrayList<MapAction.Square>();
		for(Square s : map) {
			if(s.isInZone(center, range)) {
				result.add(s);
			}
		}
		
		Collections.sort(result);
		
		return result;
	}
	
	public Square getSquare(int x, int y) {
		for(Square s : map) {
			if(s.getX() == x && s.getY() == y) {
				return s;
			}
		}
		return null;
	}
	
	public Square getSquare(Player p) {
		for(Square s : map) {
			if(p == s.getPlayer()) {
				return s;
			}
		}
		return null;
	}
	
	public void attack() {
		if(square != null && square.isOccupied()) {
			System.out.println("Attack ! on "+square.toString());
			square.getPlayer().attack();
		}
	}
	
	public void strongAttack() {
		if(square != null && square.isOccupied()) {
			System.out.println("Strong Attack !! on "+square.toString());
			square.getPlayer().strongAttack();
		}
	}
	
	public void heal() {
		if(square != null && square.isOccupied()) {
			System.out.println("Heal :-) on "+square.toString());
			square.getPlayer().heal();
		}
	}
	
	public void move() {
		if(square != null) {
			if(square.isOccupied()) {
				// WARN
				controller.warn("Vous ne pouvez pas vous déplacer sur cette case car elle est déjà occupée.");
			} else {
				System.out.println("Move on "+square.toString());
				getSquare(player).setPlayer(null);
				square.setPlayer(player);
			}
		}
	}
	
	public class Player {
		private String name;
		private int pv;
		private int pvMax;
		
		public void changePv(int value) {
			pv = pv + value;
			
			if(pv > pvMax) {
				pv = pvMax;
			}
		}
		
		public void attack() {
			changePv(-10);
		}
		
		public void strongAttack() {
			changePv(-30);
		}
		
		public void heal() {
			changePv(20);
		}
		
		public boolean isDead() {
			return (pv <= 0);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getPv() {
			return pv;
		}

		public void setPv(int pv) {
			this.pv = pv;
		}

		public int getPvMax() {
			return pvMax;
		}

		public void setPvMax(int pvMax) {
			this.pvMax = pvMax;
		}
	}
	
	public class Land {
		private String name;
		private String color;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
	}
	
	public class Square implements Comparable<Square> {
		private int x;
		private int y;
		private Land land;
		private Player player;
		
		public boolean isOccupied() {
			return (player != null && !player.isDead());
		}
		
		public boolean isInZone(Square center, int range) {
			return ((Math.abs(x - center.x) <= range) && (Math.abs(y - center.y) <= range));
		}

		@Override
		public int compareTo(Square o) {
			if(o == null) {
				return -1;
			} else if(y == o.y) {
				return (x - o.x);
			} else {
				return (o.y - y);
			}
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("(").append(getX()).append(" x ").append(getY()).append(")");
			return sb.toString();
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public Land getLand() {
			return land;
		}

		public void setLand(Land land) {
			this.land = land;
		}

		public Player getPlayer() {
			return player;
		}

		public void setPlayer(Player player) {
			this.player = player;
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Square> getMap() {
		return map;
	}

	public void setMap(List<Square> map) {
		this.map = map;
	}

	public Square getSquare() {
		return square;
	}

	public void setSquare(Square square) {
		this.square = square;
	}
	
}
