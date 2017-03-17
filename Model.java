package CarRace3D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {

	
	//has all the cars, and the race information
	//changing the speed of all cars
	private String raceName;
	private int raceId;
	 private Car c1;
	 private Car c2;
	 private Car c3;
	 private Car c4;
	 private Car c5;
	 private List<Car> carList;
	 private List<Xform> xformList;
	 private double timeOfRace;
	 public Model(String raceName,int id)
	  {	
		 this.raceId = id;
		 this.raceName = raceName;
		 c1=new Car();
		 c2=new Car();
		 c3=new Car();	
		 c4=new Car();
		 c5=new Car();
	
		carList = new ArrayList<Car>();
		carList.add(c1);
		carList.add(c2);
		carList.add(c3);
		carList.add(c4);
		carList.add(c5);
		
		xformList = new ArrayList<Xform>();
		xformList.add(c1.getCarGroup());
		xformList.add(c2.getCarGroup());
		xformList.add(c3.getCarGroup());
		xformList.add(c4.getCarGroup());
		xformList.add(c5.getCarGroup());
		
	  }
	 
    public void changeSpeed()
	 {
		 Random r = new Random();
		 
		 for (Car car : carList) {
			 int speed = 2 + r.nextInt(8);
			 car.setSpeed(speed);
		}
	 }
	 
	public Car getCarByIndex(int index)
	 {
		 return carList.get(index);
	 }
	
	public void setTimeOfRace(double t)
	{
		timeOfRace = t;
	}
	
	public double getTimeOfRace()
	{
		return timeOfRace;
	}
	
	public String getRaceName()
	{
		return raceName;
	}
	
	public int getRaceId()
	{
		return raceId;
	}
	public List<Car> getCarList()
	{
		return this.carList;
	}
	
	public List<Xform> getXformList()
	{
		return this.xformList;
	}
}
