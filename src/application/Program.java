package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Seller sl = sellerDao.findById(6);
		System.out.println("########## TESTE 1 #########");
		System.out.println(sl + "\n");
		Department dep = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(dep);
		System.out.println("########## TESTE 2 #########");
		for(Seller x: list) {
			System.out.println(x);
		}
		System.out.println();
		System.out.println("########## TESTE 3 #########");
		list = sellerDao.findAll();
		for(Seller x: list) {
			System.out.println(x);
		}
		System.out.println();
	}

}
