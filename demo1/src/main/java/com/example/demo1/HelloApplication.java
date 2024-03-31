package com.example.demo1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group root=new Group();

        stage.setTitle("Reddit");




        Scene scene=new Scene(root,300,300,Color.rgb(120,100,0));

        Text text=new Text();
        text.setText("rouzbeh");
        text.setFill(Color.BLUE);
        text.setX(50);
        text.setY(50);

        root.getChildren().add(text);


        Line line=new Line();
        line.setStartX(50);
        line.setStartY(51);
        line.setEndX(90);
        line.setEndY(51);
        line.setStrokeWidth(2);
        line.setStroke(Color.BLUE);
        line.setRotate(45);
        line.setOpacity(0.4);

        root.getChildren().add(line);

        Rectangle rectangle=new Rectangle();
        rectangle.setX(100);
        rectangle.setY(100);
        rectangle.setHeight(50);
        rectangle.setWidth(50);
        rectangle.setFill(Color.BLUE);
        rectangle.setStroke(Color.BLANCHEDALMOND);

        Polygon triangle=new Polygon();
        triangle.getPoints().setAll(200.0,200.0);


//        ImageView image=new ImageView("C:\\Users\\Asus\\IdeaProjects\\demo1\\src\\reddit.png");
//
//        image.setX(150);
//        image.setY(150);

        root.getChildren().add(triangle);
        root.getChildren().add(rectangle);
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}