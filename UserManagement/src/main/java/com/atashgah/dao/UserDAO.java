package com.atashgah.dao;

import java.sql.SQLException;
import java.util.List;

import com.atashgah.model.User;

public interface UserDAO {
	void insertUser(User user) throws SQLException;
	
	User selectUser(int id);
	
	List<User>selectUsers();
	
	boolean updateUser(User user) throws SQLException;
	
	boolean deleteUser(int id) throws SQLException;

}
