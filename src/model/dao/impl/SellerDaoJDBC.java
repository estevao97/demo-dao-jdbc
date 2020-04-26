package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection con = null;
	
	public SellerDaoJDBC(Connection conn) {
		this.con = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement pt = null;
		ResultSet rs = null;
		
		try {
		pt = con.prepareStatement(
				"SELECT seller.*,department.Name as DepName"
				+ "  FROM seller INNER JOIN department"
				+ "  ON seller.DepartmentId = department.Id"
				+ "  WHERE seller.Id = ?");
		pt.setInt(1, id);
		rs = pt.executeQuery();
		
		if(rs.next()) {
			Department dep = new Department();
			dep.setId(rs.getInt("DepartmentId"));
			dep.setName(rs.getString("DepName"));
			Seller sl = new Seller();
			sl.setId(rs.getInt("Id"));
			sl.setName(rs.getString("Name"));
			sl.setEmail(rs.getNString("Email"));
			sl.setBaseSalary(rs.getDouble("BaseSalary"));
			sl.setBirthDate(rs.getDate("BirthDate"));
			sl.setDepartment(dep);
			return sl;
		}
		return null;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(pt);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
