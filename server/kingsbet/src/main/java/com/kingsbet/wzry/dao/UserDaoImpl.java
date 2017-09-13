package com.kingsbet.wzry.dao;

import com.kingsbet.wzry.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl extends BaseDao {
    //添加一条用户信息
    public int addUser(User user) {
        String update = "insert into t_user(str_nickname)values(?)";
        List<Object> params = new ArrayList<>();
//        params.add(user.getName());
        return this.executeUpdate(update, params);
    }

//    public List<User> findUsers() {
//        List<User> result = new ArrayList<User>();
//        String query = "select id,account,pwd,NAME,dept,role,phone,qq,email,remark from users";
//        ResultSet rs = this.executeQuery(query, null);
//        try {
//            while(rs.next()){
////                int id = rs.getInt("id");
////                String account = rs.getString("account");
////                String pwd = rs.getString("pwd");
//                String name = rs.getString("st");
//                String phone ="11";//+ rs.getInt("phone");
//                String qq = "22";//+rs.getInt("qq");
//                String email = rs.getString("email");
//                String mark = rs.getString("remark");
//                User user = new User();
//                result.add(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally{
//            this.close();
//        }
//        return result;
//    }


    public int delUserById(int id) {
        String query = "delete from users where id = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(id);
        return this.executeUpdate(query, params);
    }

    public int updateUserById(int id, User role) {
        // TODO Auto-generated method stub
        return 0;
    }

//    public boolean checkUser(String name) {
//        List<User> list = new ArrayList<User>();
//        list = this.findUsers();
//        for(User u:list){
//            if(u.getName().equals(name)){
//                return true;
//            }
//        }
//        return false;
//    }

    public List<User> findUsers(String name) {
        List<User> result = new ArrayList<User>();
        List<Object> params = new ArrayList<Object>();
        String query = "select id,account,pwd,NAME,dept,role,phone,qq,email,remark from users where 1=1";
        if(name!=null&&!"".equals(name)){
            query = query+" and NAME like ?";
            params.add("%"+name+"%");
        }
        ResultSet rs = this.executeQuery(query, params);
        try {
            while(rs.next()){
                int id = rs.getInt("id");
                String n = rs.getString("name");
                String desc = rs.getString("desc");
                User user = new User();
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.close();
        }
        return result;
    }

    public List<User> findUserById(String name) {
        List<User> result = new ArrayList<User>();
        List<Object> params = new ArrayList<Object>();
        String query = "select id,account,pwd,NAME,dept,role,phone,qq,email,remark from users where 1=1";
        if(name!=null&&!"".equals(name)){
            query = query+" and NAME like ?";
            params.add("%"+name+"%");
        }
        ResultSet rs = this.executeQuery(query, params);
        try {
            while(rs.next()){
                int id = rs.getInt("id");
                String n = rs.getString("name");
                String desc = rs.getString("desc");
                User user = new User();
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.close();
        }
        return result;
    }
}
