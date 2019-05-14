package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
	
	public void drawAllPoints(ArrayList<PointList> pList, GraphicsContext gc) {
		for (PointList item : pList) {
			double size = item.getSize();
			double color = item.getColor1();
			double color2 = item.getColor2();
			double color3 = item.getColor3();
			double x = item.getX();
			double y = item.getY();
			gc.setFill(Color.hsb(color, color2, color3, 0.5));
			gc.fillOval(x-size/2,y-size/2,size,size);		
		}
	}
	
	private static final int max_x = 800;
	private static final int max_y = 800;
	private static final int step = 20;
	
	public static int zufallsWert(int von, int bis) {
		int zufallszahl = (int) (Math.random() * bis) + von;
		return zufallszahl;
	}
	
	public static void zufallsPunkteZeichnen(GraphicsContext gc, int anzahl) {
		int color = 0;
		int x = 0;
		int y = 0;
		int r = 0;
		double s = 0;
		double v = 0;
		double t = 0;
		
		for (int i = 0; i < anzahl; i++) {
			color = zufallsWert(0, 255);
			x = zufallsWert(-100,800);
			y = zufallsWert(-100,800);
			r = zufallsWert(0,300);
			s = Math.random();
			v = Math.random();
			t = Math.random();
			gc.setFill(Color.hsb(color, s, v, t));
			gc.fillOval(x, y, r, r);
		}
	}
	
	public static void punkteZeichnen(GraphicsContext gc) {
		gc.setFill(Color.hsb(50, 1, 1, 0.5));
		gc.fillOval(max_x/2, max_y/2, 400, 400);

		gc.setFill(Color.hsb(50, 1, 1, 0.25));
		gc.fillOval(1, 1, 400, 400);

		gc.setFill(Color.hsb(150, 1, 1, 0.15));
		gc.fillOval(200, 200, 400, 400);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Canvas canvas = new Canvas(max_x, max_y);
			GraphicsContext gc = canvas.getGraphicsContext2D();
			
			GridPane gridPane = new GridPane();
			gridPane.add(canvas, 1, 0);

			// Koordinaten-Kreuz
			/*
			gc.setStroke(Color.RED);
			gc.strokeLine(max_x/2, 0, max_x/2, max_y);
			gc.strokeLine(0, max_y/2, max_x, max_y/2);

			gc.setStroke(Color.BLACK);
			// 1. Teil
			for (int i=0; i <= max_y; i+=step) {
				gc.strokeLine(1, 1, max_x, i);
			}
			// 2. Teil
			for (int i=0; i < max_x; i+=step) {
				gc.strokeLine(1, 1, i, max_y);
			}
			// 3. Teil
			for (int i=0; i <= max_y; i+=step) {
				gc.strokeLine(1, max_y, max_x, i);
			}
			// 4. Teil
			for (int i=0; i < max_x; i+=step) {
				gc.strokeLine(1, max_y, i, 1);
			}
			*/
			
			// Punkte zeichnen
			punkteZeichnen(gc);

			Button clear = new Button();
			clear.setText("clear Canvas");
			gridPane.add(clear, 1, 1);
			
			Button punkte = new Button();
			punkte.setText("draw points...");
			gridPane.add(punkte, 1, 2);
			
			canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, ev -> {
				double x = ev.getX();
				double y = ev.getY();
				gc.setFill(Color.hsb((x*y), 1, 0.5));
				gc.fillOval(x, y, 100, 100);
			});
			
			clear.setOnAction(ev -> {
				gc.clearRect(1, 1, max_x, max_y);
			});
			
			punkte.setOnAction(ev -> {
				gc.clearRect(1, 1, max_x, max_y);
				zufallsPunkteZeichnen(gc, 200);
			});
			
			Scene scene = new Scene(gridPane,800,900);	
			primaryStage.setTitle("JavaFX Demo");
			primaryStage.setScene(scene);
			primaryStage.show();


			
			
			
/*

			gc.setFill(Color.hsb(100, 0.5, 0.5, 0.5));
			gc.fillOval(100,100,20,20);

			gridPane.setPadding(new Insets(10, 10, 10, 10)); 
			gridPane.setVgap(5); 
			gridPane.setHgap(5);
			gridPane.setAlignment(Pos.CENTER);
			// gridPane.add(node, 1, 0);

			
			
			
			Button ok = new Button();
			ok.setText("Clear Canvas");
	
			Button draw = new Button();
			draw.setText("Redraw Canvas");
			
	        
			Slider slider = new Slider();
			Slider slider2 = new Slider();
			Slider slider3 = new Slider();
			Slider sliderSize = new Slider();
			slider.setMin(0);
			slider.setMax(255);
			slider.setValue(125);
			slider.setShowTickLabels(true);
			slider.setShowTickMarks(true);
			slider.setMajorTickUnit(50);
			slider.setMinorTickCount(5);
			slider.setBlockIncrement(10);
			
			slider2.setMin(0);
			slider2.setMax(1);
			slider2.setValue(0.5);
			slider2.setShowTickLabels(true);
			slider2.setShowTickMarks(true);
			
			slider3.setMin(0);
			slider3.setMax(1);
			slider3.setValue(0.5);
			slider3.setShowTickLabels(true);
			slider3.setShowTickMarks(true);
			
			sliderSize.setMin(0);
			sliderSize.setMax(255);
			sliderSize.setValue(125);
			sliderSize.setShowTickLabels(true);
			sliderSize.setShowTickMarks(true);
			sliderSize.setMajorTickUnit(50);
			sliderSize.setMinorTickCount(5);
			sliderSize.setBlockIncrement(10);

			canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, ev -> {
				double size = sliderSize.getValue();
				double color = slider.getValue();
				double color2 = slider2.getValue();
				double color3 = slider3.getValue();
				double x = ev.getX();
				double y = ev.getY();
				gc.setFill(Color.hsb(color, color2, color3, 0.5));
				gc.fillOval(x-size/2,y-size/2,size,size);
				
				punkte.add(new PointList(x, y, size, color, color2, color3));
				
			});						

			ok.setOnAction(ev -> {
				gc.clearRect(0, 0, canvas.getWidth(),
				canvas.getHeight());				
			});

			draw.setOnAction(ev -> {
				drawAllPoints(punkte, gc);
				// punkte.clear();
			});

			gridPane.setPadding(new Insets(10, 10, 10, 10)); 
			gridPane.setVgap(5); 
			gridPane.setHgap(5);
			gridPane.setAlignment(Pos.CENTER);
			// gridPane.add(node, 1, 0);
			gridPane.add(canvas, 1, 0);
		
			FlowPane grid3 = new FlowPane();
			grid3.getChildren().add(ok);
			grid3.getChildren().add(draw);
			gridPane.add(grid3,  1, 1);
			FlowPane grid2 = new FlowPane();
			grid2.setVgap(5); 
			grid2.setHgap(5);
			Label sliderLabel = new Label("Farbwert");
			Label sliderSizeLabel = new Label("Größe");
			grid2.getChildren().add(sliderLabel);
			grid2.getChildren().add(slider);
			grid2.getChildren().add(slider2);
			grid2.getChildren().add(slider3);
			grid2.getChildren().add(sliderSizeLabel);
			grid2.getChildren().add(sliderSize);
			gridPane.add(grid2, 1, 3);
			
			ComboBox tasks = new ComboBox<String>();
			tasks.getItems().addAll("eins","zwei","drei","vier", "fünf");
			tasks.getItems().add(0, "bitte wählen 1");
			tasks.getItems().add(0, "bitte wählen 2");
			tasks.getItems().add(0, "bitte wählen 3");
			gridPane.add(tasks,  1, 4);
			tasks.setVisible(true);
			tasks.setEditable(false);
			*/
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Color getColor(int i) {
		i = i % 16;
		switch (i) {
			case 0: return Color.RED;
			case 1: return Color.BLUE;
			case 2: return Color.GREEN;
			default: return Color.AZURE;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
