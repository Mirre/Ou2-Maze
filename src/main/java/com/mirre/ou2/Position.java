package com.mirre.ou2;

public class Position {
	private int x,y;
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Position getPosToSouth(){
		return new Position(x,y-1);
	}
	
	public Position getPosToNorth(){
		return new Position(x,y+1);
	}

	public Position getPosToWest(){
		return new Position(x+1,y);
	}

	public Position getPosToEast(){
		return new Position(x-1,y);
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Position){
			Position p = (Position) o;
			return p.x == x && p.y == y;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
	    int result = 137;
	    result = 31 * result + x;
	    result = 31 * result + y;
	    return result;
	}
	
	@Override
	public String toString(){
		return "X:" + x + " Y:" + y;
	}
}
