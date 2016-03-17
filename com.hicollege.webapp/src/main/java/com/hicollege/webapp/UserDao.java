package com.hicollege.webapp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hicollege.webapp.dtos.Album;
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
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }
    
    @Transactional
    public void saveAlbum(Album album) {
        sessionFactory.getCurrentSession().save(album);
    }
    
    @Transactional
    public void saveObject(Object obj) {
        sessionFactory.getCurrentSession().save(obj);
    }
    
    @Transactional
    public Album getAlbumByTitle(String title) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Album as album where album.title = :title");
        query.setParameter("title", title);
        List result = query.list();
        return result.size() == 0 ? null : (Album) result.get(0);
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
