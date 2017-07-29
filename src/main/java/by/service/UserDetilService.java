package by.service;

import by.model.Roles;
import by.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Роман on 29.07.2017.
 */
@Service("userDetilService")
public class UserDetilService implements UserDetailsService{
    @Autowired
    private UserService userService;
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users users=userService.getUserByUsername(s);
        Set<GrantedAuthority> grantedAuthoritySet=new HashSet<GrantedAuthority>();
        for(Roles roles: users.getRolesSet()){
            grantedAuthoritySet.add(new SimpleGrantedAuthority(roles.getName()));
        }
        return new User(users.getUsername(),users.getPassword(),grantedAuthoritySet);
    }
}
