package com.yabcompany.dao;

import com.yabcompany.models.MainUser;

import java.sql.SQLException;
import java.util.Collection;

public interface UserDAO {

    public void addUser(MainUser mainUser) throws SQLException;

    public void updateUser(Long user_id, MainUser mainUser) throws SQLException;

    public MainUser getUserById(Long user_id) throws SQLException;

    public Collection getAllUsers() throws SQLException;

    public void deleteUser(MainUser mainUser) throws SQLException;
}
