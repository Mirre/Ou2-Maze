package com.mirre.ou2.fx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.mirre.ou2.Position;
import com.mirre.ou2.RightHandRuleRobot;
import com.mirre.ou2.Robot;
import com.mirre.ou2.Maze;
import com.mirre.ou2.Maze.PositionType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

public class MazeRepresentationController {

	@FXML
	private Pane root;
	
	@FXML
	private Rectangle mazeArea;
	
	private double nodeSizeX, nodeSizeY;
	
	private Maze maze;
	private Robot robot;
	
	private Button robotButton;
	
	@FXML
	public void initialize(){
		
		FileChooser fileChooser = new FileChooser();
		File f = fileChooser.showOpenDialog(MazeMain.getStage().getOwner());
		if(f == null){
			System.exit(-1);
		}
		
		List<String> nodes = new ArrayList<String>();
		
		int yNodes = 0;
		int xNodes = -1;
		
		try {
			FileReader fileReader = new FileReader(f);
			BufferedReader reader = new BufferedReader(fileReader);
			while(reader.ready()){
				String s = reader.readLine();
				if(xNodes == -1){
					xNodes = s.length();
				}
				nodes.add(s);
				yNodes++;
			}
			reader.close();
			fileReader = new FileReader(f);
			maze = new Maze(fileReader);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mazeArea.setHeight(20*yNodes);
		mazeArea.setWidth(20*xNodes);
		if(mazeArea.getHeight() > root.getPrefHeight() - 200){
			System.exit(-1);
			
		}if(mazeArea.getWidth() > root.getPrefWidth() - 200){
			System.exit(-1);
		}
		
		/*Left Corner of mazeArea rectangle*/
		double leftCornerX = 0;
		double leftCornerY = 0;
		
		mazeArea.setLayoutX(leftCornerX);
		mazeArea.setLayoutY(leftCornerY);
		root.setMaxSize(mazeArea.getWidth() - 10, mazeArea.getHeight() + 50);
		root.setPrefSize(mazeArea.getWidth()  - 10, mazeArea.getHeight() + 50);
		root.setMinSize(mazeArea.getWidth()  - 10, mazeArea.getHeight() + 50);
		
		Button moveButton = new Button("Move");
		moveButton.setLayoutX(mazeArea.getWidth()/2);
		moveButton.setLayoutY(mazeArea.getHeight() + 25);
		moveButton.setOnAction((event) -> {
			robot.move();
		});
		
		root.getChildren().add(moveButton);
		
		nodeSizeX = mazeArea.getWidth()/xNodes;
		nodeSizeY = mazeArea.getHeight()/yNodes;
		
		robotButton = new Button("");
		robotButton.setStyle("-fx-background-color: black;");
		
		robotButton.setMaxSize(nodeSizeX,nodeSizeY);
		robotButton.setMinSize(nodeSizeX,nodeSizeY);
		
		robot = new RightHandRuleRobot(maze) {
			@Override
			public void move(){
				Position oldPos = robot.getCurrentPosition();
				super.move();
				Position newPos = robot.getCurrentPosition();
				int xDiff = oldPos.getX() - newPos.getX();
				int yDiff = oldPos.getY() - newPos.getY();
				if(xDiff > 0){ //Right
					robotButton.setLayoutX(robotButton.getLayoutX() - nodeSizeX);
				}else if(xDiff < 0){ //Left
					robotButton.setLayoutX(robotButton.getLayoutX() + nodeSizeX);
				}if(yDiff > 0){ //Down
					robotButton.setLayoutY(robotButton.getLayoutY() - nodeSizeY);
				}else if(yDiff < 0){ //Up
					robotButton.setLayoutY(robotButton.getLayoutY() + nodeSizeY);
				}
			}
		};
		
		double destX = leftCornerX, destY = leftCornerY;
		for(String s : nodes){
			for(char c : s.toCharArray()){
				PositionType type = PositionType.fromChar(c);
				if(type == null){
					throw new IllegalArgumentException("Illegal Character in Maze file.");
				}
				
				Button b = new Button("");
				
				switch(type){
					case AIR:
						b = null;
						break;
					case GOAL:
						b.setStyle("-fx-background-color: green;");
						break;
					case WALL:
						break;
					case START:
						b.setStyle("-fx-background-color: red;");
						robotButton.setLayoutX(destX);
						robotButton.setLayoutY(destY);
						break;
					default:
						b = new Button("");
						break;
				}
				if(b != null){
					b.setMaxSize(nodeSizeX,nodeSizeY);
					b.setMinSize(nodeSizeX,nodeSizeY);
					b.setLayoutX(destX);
					b.setLayoutY(destY);
					root.getChildren().add(b);
				}
				destX += nodeSizeX;
			}
			destX = leftCornerX;
			destY += nodeSizeY;
		}
		root.getChildren().add(robotButton);
		System.out.println("Test");
	}
	
}