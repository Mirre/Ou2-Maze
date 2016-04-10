package com.mirre.ou2;

//Starts with right facing and until you can't go in that facing anymore
//Then it getsLeft() and tries, if it can't move forward it repeats getsLeft() and turns around.

//(Fixed) Take note that the map is upside down. Y decreases when going north. But WEST and EAST are correct.
//Need to fix ^ Changed WEST and EAST so they do the reverse.
public class RightHandRuleRobot extends Robot {
	
	private Direction facing = Direction.NORTH;
	
	private Direction hand = facing.getRight(); 
	
	
	public RightHandRuleRobot(Maze maze) {
		super(maze);
	}

	@Override
	public void move() {
		if(maze.isGoal(this.getCurrentPosition())){
			System.out.println(this.getClass().getSuperclass().getSimpleName() + " has already reached the goal!");
			return;
		}
		simpleMove();
		if(maze.isGoal(this.getCurrentPosition())){
			System.out.println(this.getClass().getSuperclass().getSimpleName() + " has reached the goal!");
			return;
		}
		
	}
	
	private void simpleMove(){
		Position p;
		if(maze.isMoveable(p = hand.toPosition(getCurrentPosition()))){
			facing = hand;
			hand = facing.getRight();
			setCurrentPosition(p);
		}else if(maze.isMoveable(p = facing.toPosition(getCurrentPosition()))){
			setCurrentPosition(p);
		}else {
			facing = hand;
			hand = facing.getLeft();
			simpleMove();
		}
		
		System.out.println("Facing" + facing);
		System.out.println("Hand" + hand);
	}
}
