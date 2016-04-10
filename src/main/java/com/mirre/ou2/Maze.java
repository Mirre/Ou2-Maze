package com.mirre.ou2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class Maze {
	private Map<Position,PositionType> mazeData = new HashMap<Position,PositionType>();
	private Position startPosition;
	
	public Maze(Reader reader) throws IllegalArgumentException {
		BufferedReader in = new BufferedReader(reader);
		
		//TODO y should start at the maximum Y and decrease to zero instead of increasing to maximum Y.
		try {
			int y = 0;
			while(in.ready()){
				char[] chars = in.readLine().toCharArray();
				for(int x = 0 ; x < chars.length ; x++){
					PositionType type = PositionType.fromChar(chars[x]);
					if(type == null){
						throw new IllegalArgumentException("Illegal Character in Maze file.");
					}
					switch(type){
						case AIR:
						case GOAL:
						case WALL:
							mazeData.put(new Position(x,y), type);
							break;
						case START:
							if(startPosition == null){
								startPosition = new Position(x,y);
								mazeData.put(startPosition, type);
							}else{
								throw new IllegalArgumentException("Multiple Start Locations in Maze File");
							}
							break;
						default:
							break;
					}
				}
				y++;
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isMoveable(Position pos){
		return mazeData.containsKey(pos) && !mazeData.get(pos).equals(PositionType.WALL);
	}
	
	public boolean isGoal(Position pos){
		return mazeData.containsKey(pos) && mazeData.get(pos).equals(PositionType.GOAL);
	}
	
	public Position getStartPosition(){
		return startPosition;
	}
	
	public enum PositionType {
		WALL,AIR,START,GOAL;
		
		public static PositionType fromChar(char c) {
			switch(c){
				case '*':
					return PositionType.WALL;
				case ' ':
					return PositionType.AIR;
				case 'S':
					return PositionType.START;
				case 'G':
					return PositionType.GOAL;
				default:
					return null;
			}
		}

	}
}
