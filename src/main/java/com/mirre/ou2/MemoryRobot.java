package com.mirre.ou2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;


//TODO Instead of calculating just in time, Calculate where it should go during init?
public class MemoryRobot extends Robot {

	private Map<Position,Boolean> memory = new HashMap<Position,Boolean>();
	private Deque<BiValue<Position,Direction>> deque = new ArrayDeque<BiValue<Position,Direction>>();
	
	private Direction dir = Direction.NORTH;
	 
	public MemoryRobot(Maze maze) {
		super(maze);
		//Position pos = maze.getStartPosition();
		//TOODOO
	}

	@Override
	public void move() {
		if(maze.isGoal(this.getCurrentPosition())){
			System.out.println(this.getClass().getSimpleName() + " has already reached the goal!");
			return;
		}
		memory.put(this.getCurrentPosition(), true);
		
		Position dest = dir.toPosition(getCurrentPosition());
		
		if(maze.isMoveable(dest) ){
			setCurrentPosition(dest);
		}else{
			boolean foundDir = false;
			for(Direction d : Direction.directionsMinus(dir.getInverse())){
				if(isMoveable(d)){
					dir = d;
					foundDir = true;
				}
			}
			if(foundDir){
				
			}else{
				dir = dir.getInverse();
			}
			BiValue<Position,Direction> biValue = deque.pop();
			setCurrentPosition(biValue.getFirst());
			dir = biValue.getSecond();
		}
		
		if(maze.isGoal(dest)){
			System.out.println(this.getClass().getSimpleName() + " has reached the goal!");
		}
	}
	
	private boolean isMoveable(Direction d){
		return maze.isMoveable(dir.toPosition(getCurrentPosition()));
	}
	
	private void checkAndAddToDeque(Direction d){
		Position p = dir.toPosition(getCurrentPosition());
		if(maze.isMoveable(p)){
			if(!memory.containsKey(p)){
				
			}else{
				
			}
			BiValue<Position,Direction> biValue = new BiValue<Position,Direction>(p,d);
			deque.addFirst(biValue);
		}
	}
	
	private class BiValue<T,P> {
		private T t;
		private P p;
		
		protected BiValue(T t, P p){
			this.t = t;
			this.p = p;
		}
		
		private T getFirst(){
			return t;
		}
		private P getSecond(){
			return p;
		}
	}

}
