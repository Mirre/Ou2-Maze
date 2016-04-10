package com.mirre.ou2;

public abstract class Robot {
	private Position position;
	protected Maze maze;
	
	public Robot(Maze maze){
		this.maze = maze;
		this.position = maze.getStartPosition();
	}
	
	public abstract void move();
	
	public Position getCurrentPosition(){
		return position;
	}
	
	protected void setCurrentPosition(Position pos){
		position = pos;
	}
}
