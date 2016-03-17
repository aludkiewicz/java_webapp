package com.hicollege.webapp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hicollege.webapp.dtos.User;

@Repository
@Component
public class UserDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public UserDao() {
    }
 
    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Transactional
    public List<User> list() {
        @SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listUser;
    }
 
    @Transactional
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }
    
    @Transactional
    public List<User> getAllUsers() {
        @SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) sessionFactory
                .getCurrentSession()
                .createQuery("from User")
                .list();
        return listUser;
    }
 
//    @Transactional
//    public void delete(int id) {
//        User userToDelete = new User();
//        userToDelete.setId(id);
//        sessionFactory.getCurrentSession().delete(userToDelete);
//    }
// 
//    @Transactional
//    public User get(int id) {
//        String hql = "from User where id=" + id;
//        Query query = sessionFactory.getCurrentSession().createQuery(hql);
//         
//        @SuppressWarnings("unchecked")
//        List<User> listUser = (List<User>) query.list();
//         
//        if (listUser != null && !listUser.isEmpty()) {
//            return listUser.get(0);
//        }
//         
//        return null;
//    }
}
