package com.yabcompany.dao;

import com.yabcompany.models.MainUser;
import com.yabcompany.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.Collection;

public class UserDAOImpl implements UserDAO {
    @Override
    public void addUser(MainUser mainUser) throws SQLException {
//        HibernateSessionFactoryUtil.getSessionFactory().openSession().get(MainUser.class, "id");
    }

    @Override
    public void updateUser(Long user_id, MainUser mainUser) throws SQLException {

    }

    @Override
    public MainUser getUserById(Long user_id) throws SQLException {
        return null;
    }

    @Override
    public Collection getAllUsers() throws SQLException {
        return null;
    }

    @Override
    public void deleteUser(MainUser mainUser) throws SQLException {

    }
}
