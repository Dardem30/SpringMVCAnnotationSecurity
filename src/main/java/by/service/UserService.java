package by.service;

import by.model.Roles;
import by.model.Users;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Роман on 29.07.2017.
 */
@Service
public class UserService {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void saveUser(Users users){
        Roles roles= (Roles) sessionFactory.getCurrentSession().get(Roles.class,2);
        Set<Roles> rolesSet=new HashSet<Roles>();
        rolesSet.add(roles);
        users.setRolesSet(rolesSet);
        sessionFactory.getCurrentSession().persist(users);
    }
    @Transactional
    public Users getUserByUsername(String username){
        Users users= (Users) this.sessionFactory
                .getCurrentSession().createCriteria(Users.class).add(Restrictions.eq("username",username))
                .uniqueResult();
        return users;
    }
}
