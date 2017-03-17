package CarRace3D;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.Random;

import CarRace3D.Xform;


public class Car {
	

	private int ID;
	private int type;
	private int color;
	private int speed;
	private Color[] colors;
	private Xform carGroup;
	private Timeline timeLine;
	//types 1 = car 2 = truck 3 = motor bike
	
	public Car()
	{
			timeLine = new Timeline();
			createRandomCar();
			activateTimeline();
			this.speed = 0;
	}
	
	public void createRandomCar()
	{
		carGroup = new Xform();
		createColors();
		createCar();
	}
	
	//getters
	

	public void setSpeed(int speed)
	{
		this.speed = speed;
		timeLine.setRate(speed);
	}
	
	public int getSpeed()
	{
		return this.speed;
	}
	
	public Xform getCarGroup()
	{
		return carGroup;
	}
	
	public int getType()
	{
		return type;
	}
	
	public int getColor()
	{
		return color;
	}
	
	public int getID()
	{
		return ID;
	}
	
	//create 10 random colors and set the color to be one of them
	
	public void createColors()
	{
		colors = new Color[10];
		for(int i = 0 ; i < colors.length ; i++)
		{
			Random rand = new Random();

			double r = rand.nextDouble();
			double g = rand.nextDouble();
			double b = rand.nextDouble();
			Color randomColor = new Color(r, g, b,1);
			colors[i] = randomColor;
		}
		
		Random randColor = new Random();
		color = randColor.nextInt(10);
	}
	
	private void createCar()
	{
		Random rand = new Random();
		int type = 1+(int)(Math.random()*(3));
		System.out.println(type);
		switch(type)
		{
			
			case 1:
				createCarGroup();
				break;
			case 2:
				createTruckGroup();
				break;
			case 3:
				createMotorBikeGroup();
				break;
		}
	
	}

	private void activateTimeline() {
	
		 timeLine.setCycleCount(Timeline.INDEFINITE);
		 timeLine.setAutoReverse(false);
	    	final KeyValue kv = new KeyValue(this.carGroup.layoutXProperty(), 2000);
	    	final KeyFrame kf = new KeyFrame(Duration.millis(3500), kv);
	    	timeLine.getKeyFrames().add(kf);
	    	timeLine.setRate(0);
	    	timeLine.play();
	    }

	private void createMotorBikeGroup() {
		  final PhongMaterial redMaterial = new PhongMaterial();
	      redMaterial.setDiffuseColor(colors[color]);
	      redMaterial.setSpecularColor(Color.RED);

	      final PhongMaterial whiteMaterial = new PhongMaterial();
	      whiteMaterial.setDiffuseColor(Color.WHITE);
	      whiteMaterial.setSpecularColor(Color.LIGHTBLUE);
	      
	      final PhongMaterial greyMaterial = new PhongMaterial();
	      greyMaterial.setDiffuseColor(Color.DARKGREY);
	      greyMaterial.setSpecularColor(Color.GREY);
	      

	      final PhongMaterial blackMaterial = new PhongMaterial();
	      blackMaterial.setDiffuseColor(Color.BLACK);
	      blackMaterial.setSpecularColor(Color.BLACK);
	      
	      Xform carXform = new Xform();
	     
	      Box carBody = new Box(15,7,7);
	      carBody.setMaterial(redMaterial);
	    //  carBody.setTranslateZ(100);
	      carBody.setTranslateY(0);
	      
	      Box backStick = new Box(10,3,3);
	      backStick.setMaterial(blackMaterial);
	      backStick.setTranslateZ(0);
	      backStick.setTranslateY(-1);
	      backStick.setTranslateX(12);
	      backStick.getTransforms().add(new Rotate(135,Rotate.Z_AXIS));
	      
	      Box frontStick = new Box(10,3,3);
	      frontStick.setMaterial(blackMaterial);
	      frontStick.setTranslateZ(0);
	      frontStick.setTranslateY(-1);
	      frontStick.setTranslateX(-10);
	      frontStick.getTransforms().add(new Rotate(-135,Rotate.Z_AXIS));
	      
	      Cylinder leftBackWheel = new Cylinder(2, 2);
	      leftBackWheel.setMaterial(greyMaterial);
	      leftBackWheel.setTranslateX(15);
	      leftBackWheel.setTranslateY(-7);
	      leftBackWheel.setTranslateZ(0);
	      leftBackWheel.setRotationAxis(Rotate.X_AXIS);
	      leftBackWheel.setRotate(90.0);
	     
	      Cylinder leftFrontWheel = new Cylinder(2, 2);
	      leftFrontWheel.setMaterial(greyMaterial);
	      leftFrontWheel.setTranslateX(-15);
	      leftFrontWheel.setTranslateY(-7);
	      leftFrontWheel.setTranslateZ(0);
	      leftFrontWheel.setRotationAxis(Rotate.X_AXIS);
	      leftFrontWheel.setRotate(90.0);
	      
	      carXform.getChildren().addAll(carBody,frontStick,backStick,leftBackWheel,leftFrontWheel);
	      
	      carGroup.getChildren().add(carXform);
	      
	      this.type = 3;
		
	}


	private void createTruckGroup() {
		  final PhongMaterial redMaterial = new PhongMaterial();
	        redMaterial.setDiffuseColor(colors[color]);
	        redMaterial.setSpecularColor(Color.RED);

	        final PhongMaterial whiteMaterial = new PhongMaterial();
	        whiteMaterial.setDiffuseColor(Color.WHITE);
	        whiteMaterial.setSpecularColor(Color.LIGHTBLUE);
	        
	        final PhongMaterial greyMaterial = new PhongMaterial();
	        greyMaterial.setDiffuseColor(Color.DARKGREY);
	        greyMaterial.setSpecularColor(Color.GREY);

	        Xform carXform = new Xform();
	       
	        
	        Box carBody = new Box(65,20,35);
	        carBody.setMaterial(redMaterial);
	      //  carBody.setTranslateZ(50);
	        carBody.setTranslateY(5);
	        
	        Cylinder rightBackWheel = new Cylinder(8, 8);
	        rightBackWheel.setMaterial(greyMaterial);
	        rightBackWheel.setTranslateX(15);
	        rightBackWheel.setTranslateY(-7);
	        rightBackWheel.setTranslateZ(-8);
	        rightBackWheel.setRotationAxis(Rotate.X_AXIS);
	        rightBackWheel.setRotate(90.0);
	       
	        
	        Cylinder leftBackWheel = new Cylinder(8, 8);
	        leftBackWheel.setMaterial(greyMaterial);
	        leftBackWheel.setTranslateX(15);
	        leftBackWheel.setTranslateY(-7);
	        leftBackWheel.setTranslateZ(15);
	        leftBackWheel.setRotationAxis(Rotate.X_AXIS);
	        leftBackWheel.setRotate(90.0);
	       
	        Cylinder leftFrontWheel = new Cylinder(8, 8);
	        leftFrontWheel.setMaterial(greyMaterial);
	        leftFrontWheel.setTranslateX(-15);
	        leftFrontWheel.setTranslateY(-7);
	        leftFrontWheel.setTranslateZ(0);
	        leftFrontWheel.setRotationAxis(Rotate.X_AXIS);
	        leftFrontWheel.setRotate(90.0);
	        
	        carXform.getChildren().addAll(carBody,rightBackWheel,leftBackWheel,leftFrontWheel);
	        
	        carGroup.getChildren().add(carXform);
		
	        this.type = 2;
	}


	private void createCarGroup() {
		  final PhongMaterial carColor = new PhongMaterial();
	        carColor.setDiffuseColor(colors[color]);
	        carColor.setSpecularColor(Color.RED);

	        final PhongMaterial whiteMaterial = new PhongMaterial();
	        whiteMaterial.setDiffuseColor(Color.WHITE);
	        whiteMaterial.setSpecularColor(Color.LIGHTBLUE);
	        
	        final PhongMaterial greyMaterial = new PhongMaterial();
	        greyMaterial.setDiffuseColor(Color.DARKGREY);
	        greyMaterial.setSpecularColor(Color.GREY);
	        
	        final PhongMaterial blackMaterial = new PhongMaterial();
	        blackMaterial.setDiffuseColor(Color.BLACK);
	        blackMaterial.setSpecularColor(Color.BLACK);
	        
	        
	        Xform carXform = new Xform();
	       
	        //car body
	        Box carBody = new Box(45,10,25);
	        carBody.setMaterial(carColor);
	        
	      
	        //4 wheels
	        Cylinder rightBackWheel = new Cylinder(5, 5);
	        rightBackWheel.setMaterial(greyMaterial);
	        rightBackWheel.setTranslateX(15);
	        rightBackWheel.setTranslateY(-10);
	        rightBackWheel.setTranslateZ(-10);
	        rightBackWheel.setRotationAxis(Rotate.X_AXIS);
	        rightBackWheel.setRotate(90.0);
	       
	        Cylinder rightFrontWheel = new Cylinder(5, 5);
	        rightFrontWheel.setMaterial(greyMaterial);
	        rightFrontWheel.setTranslateX(-15);
	        rightFrontWheel.setTranslateY(-10);
	        rightFrontWheel.setTranslateZ(-10);
	        rightFrontWheel.setRotationAxis(Rotate.X_AXIS);
	        rightFrontWheel.setRotate(90);
	        
	        Cylinder leftBackWheel = new Cylinder(5, 5);
	        leftBackWheel.setMaterial(greyMaterial);
	        leftBackWheel.setTranslateX(15);
	        leftBackWheel.setTranslateY(-10);
	        leftBackWheel.setTranslateZ(10);
	        leftBackWheel.setRotationAxis(Rotate.X_AXIS);
	        leftBackWheel.setRotate(90.0);
	       
	        Cylinder leftFrontWheel = new Cylinder(5, 5);
	        leftFrontWheel.setMaterial(greyMaterial);
	        leftFrontWheel.setTranslateX(-15);
	        leftFrontWheel.setTranslateY(-10);
	        leftFrontWheel.setTranslateZ(10);
	        leftFrontWheel.setRotationAxis(Rotate.X_AXIS);
	        leftFrontWheel.setRotate(90.0);
	        
	        //windshield
	        Box windShield = new Box(2,5,25);
	        windShield.setMaterial(blackMaterial);
	        windShield.setTranslateX(-22);
	        windShield.setTranslateY(7);
	        
	        carXform.getChildren().addAll(carBody,rightBackWheel,rightFrontWheel,leftBackWheel,leftFrontWheel,windShield);
	        
	        carGroup.getChildren().add(carXform);
	        
	        this.type = 1;
	}
}
