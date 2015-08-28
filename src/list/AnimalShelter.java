package list;

import java.util.*;

abstract class Animal{
	
	protected String name;
	
	public Animal(String n){
		this.name = n;
	}
	
}

class Cat extends Animal{
	public Cat(String n){
		super(n);
	}
	
}

class Dog extends Animal{
	public Dog(String n){
		super(n);
	}
}

class AnimalQueue {
	AnimalQueue(){
		
	}
	public void enqueue(Animal a){
		
	}
	
	public Animal dequeueAnimal(){
		
		return null;
	}
	
	public Animal dequeueDogs(){
		
		return null;
	}
	
	public Animal dequeueCats(){
		
		return null;
	}
	
}

public class AnimalShelter {
	
	public static void main(String[] args) {
		AnimalQueue aq = new AnimalQueue();
		aq.enqueue(new Dog("d1"));
		aq.enqueue(new Dog("d2"));
		aq.enqueue(new Cat("c1"));
		aq.enqueue(new Cat("c2"));
	}
	
}
