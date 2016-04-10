package com.mirre.ou2.fx;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class MazeMain extends Application {
	
	private Pane rootLayout;
	private static Stage stage;
	
	
	@Override
	public void start(Stage stage) {
		MazeMain.stage = stage;
		loadController();
		Scene scene = new Scene(rootLayout);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}
	
	private void loadController(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MazeMain.class.getResource("StartView.fxml"));
			rootLayout = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getStage() {
		return stage;
	}
}
