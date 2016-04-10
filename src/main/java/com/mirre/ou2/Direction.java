package com.mirre.ou2;

import java.util.ArrayList;
import java.util.List;

public enum Direction {
	NORTH,SOUTH,WEST,EAST;
	
	public static Direction getDirection(Position from, Position to){
		if(from.getX() > to.getX()){
			return Direction.EAST;
		}else if(from.getX() < to.getX()){
			return Direction.WEST;
		}
		
		if(from.getY() > to.getY()){
			return Direction.NORTH;
		}else if(from.getY() < to.getY()){
			return Direction.SOUTH;
		}
		
		return null;
	}
	
	public Direction getRight(){
		switch(this){
			case EAST:
				return SOUTH;
			case NORTH:
				return EAST;
			case SOUTH:
				return WEST;
			case WEST:
				return NORTH;
			default:
				return null;
		}
	}
	
	public Direction getLeft(){
		switch(this){
			case EAST:
				return NORTH;
			case NORTH:
				return WEST;
			case SOUTH:
				return EAST;
			case WEST:
				return SOUTH;
			default:
				return null;
		}
	}
	
	public Direction getInverse(){
		switch(this){
			case EAST:
				return WEST;
			case NORTH:
				return SOUTH;
			case SOUTH:
				return NORTH;
			case WEST:
				return EAST;
			default:
				return null;
		}
	}
	
	public Position toPosition(Position from){
		switch(this){
			case EAST:
				return from.getPosToEast();
			case NORTH:
				return from.getPosToNorth();
			case SOUTH:
				return from.getPosToSouth();
			case WEST:
				return from.getPosToWest();
			default:
				return null;
		}
	}
	
	public static Direction[] directionsMinus(Direction... directions){
		List<Direction> dirs = new ArrayList<Direction>();
		for(Direction d : Direction.values()){
			dirs.add(d);
		}
		for(Direction d : directions){
			dirs.remove(d);
		}
		return dirs.toArray(new Direction[dirs.size()]);
	}
}
