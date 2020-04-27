package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
					"SELECT seller.*,department.Name as DepName" + "  FROM seller INNER JOIN department"
							+ "  ON seller.DepartmentId = department.Id" + "  WHERE seller.Id = ?");
			pt.setInt(1, id);
			rs = pt.executeQuery();

			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				Seller sl = instantiateSeller(rs, dep);
				return sl;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pt);
			DB.closeResultSet(rs);
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller sl = new Seller();
		sl.setId(rs.getInt("Id"));
		sl.setName(rs.getString("Name"));
		sl.setEmail(rs.getNString("Email"));
		sl.setBaseSalary(rs.getDouble("BaseSalary"));
		sl.setBirthDate(rs.getDate("BirthDate"));
		sl.setDepartment(dep);
		return sl;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement pt = null;
		ResultSet rs = null;

		try {
			pt = con.prepareStatement(
					"SELECT seller.*,department.Name as DepName"
					+ "  FROM seller INNER JOIN department"
					+ "  ON seller.DepartmentId = department.Id"
					+ " WHERE DepartmentId = ? ORDER BY Name"); 
	
			pt.setInt(1, department.getId());
			rs = pt.executeQuery();

			List<Seller> lista = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				Seller sl = instantiateSeller(rs, dep);
				lista.add(sl);
			}
			return lista;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pt);
			DB.closeResultSet(rs);
		}
	}

}
