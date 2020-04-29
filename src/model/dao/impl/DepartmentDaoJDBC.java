package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	private Connection con = null;

	public DepartmentDaoJDBC(Connection con) {
		this.con = con;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement pt = null;

		try {
			pt = con.prepareStatement("INSERT INTO department (Name) VALUES  (?)", Statement.RETURN_GENERATED_KEYS);
			pt.setString(1, obj.getName());

			if (pt.executeUpdate() > 0) {
				ResultSet rs = pt.getGeneratedKeys();
				if (rs.next()) {
					obj.setId(rs.getInt(1));
				}
				DB.closeResultSet(rs);

			} else {
				throw new DbException("Unexpected error! No rows affected.");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pt);
		}

	}

	@Override
	public void update(Department obj) {
		PreparedStatement pt = null;

		try {

			pt = con.prepareStatement("UPDATE department" + " SET Name = ? WHERE Id = ? ");

			pt.setString(1, obj.getName());
			pt.setInt(2, obj.getId());
			pt.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pt);

		}

	}

	@Override
	public void deleteById(Integer id) {

		PreparedStatement pt = null;

		try {

			pt = con.prepareStatement("DELETE FROM department  WHERE Id = ?");
			pt.setInt(1, id);

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pt);

		}

	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement pt = null;
		ResultSet rs = null;

		try {
			pt = con.prepareStatement("SELECT * FROM department WHERE department.Id = ?");
			pt.setInt(1, id);
			rs = pt.executeQuery();

			if (rs.next()) {
				Department dep = new Department(rs.getInt("Id"), rs.getString("Name"));
				return dep;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pt);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement pt = null;
		ResultSet rs = null;

		try {
			pt = con.prepareStatement("SELECT * FROM department ORDER BY NAME");

			rs = pt.executeQuery();

			List<Department> lista = new ArrayList<>();
			Map<String, String> map = new HashMap<>();

			while (rs.next()) {
				String fields = map.get(rs.getString("Name"));
				if (fields == null) {
					Department dep = new Department(rs.getInt("Id"), rs.getString("Name"));
					lista.add(dep);
					map.put(rs.getString("Name"), String.valueOf(rs.getInt("Id")));
				}
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
