package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class ProgramTestDepartment {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

		System.out.println("########## TESTE 1 findById ##########");
		Department dep = departmentDao.findById(1);
		System.out.println(dep);
		System.out.println("########## TESTE 2 findAll ##########");
		List<Department> list = departmentDao.findAll();
		for (Department x : list) {
			System.out.println(x);
		}
		System.out.println("########## TESTE 3 insert ##########");
		Department dep1 = new Department(null, "Music");
		departmentDao.insert(dep1);
		System.out.println("Inserted! New id: " + dep1.getId());
		System.out.println("########## TESTE 4 update ##########");
		Department dep2 = departmentDao.findById(1);
		dep2.setName("Food");
		departmentDao.update(dep2);
		System.out.println("Update completed");
		System.out.println("########## TESTE 5 delete ##########");
		System.out.print("Enter whth Id for delete test: ");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Delete completed");
		sc.close();
	}
}
