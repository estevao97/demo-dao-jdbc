package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDao sellerDao = DaoFactory.createSellerDao();
		Seller sl = sellerDao.findById(6);
		System.out.println("########## TESTE 1 ######### >> findById");
		System.out.println(sl + "\n");
		Department dep = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(dep);
		System.out.println("########## TESTE 2 ######### >> findByDepartment");
		for (Seller x : list) {
			System.out.println(x);
		}
		System.out.println();
		System.out.println("########## TESTE 3 ######### >> findAll");
		list = sellerDao.findAll();
		for (Seller x : list) {
			System.out.println(x);
		}
		//Seller seller = new Seller(null, "Gustavo", "gustadsg@gmail.com", new Date(), 30000.00, dep);
		//System.out.println("########## TESTE 4 ######### >> insert");
		//sellerDao.insert(seller);
		//System.out.println("Inserted!\nNew Seller: " + seller.getId());
		System.out.println("########## TESTE 5 ######### >> update");
		sl = sellerDao.findById(7);
		sl.setName("Gugusta");
		sellerDao.update(sl);
		System.out.println("Update completed!");
		

	}
}
