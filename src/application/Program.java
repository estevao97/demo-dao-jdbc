package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department db = new Department(25,"Pharmacy");
		Seller seller = new Seller(2500,"Lores","lorenathalia100@gmail.com",new Date(),20000.00,db);
		System.out.println(seller);
	}

}
