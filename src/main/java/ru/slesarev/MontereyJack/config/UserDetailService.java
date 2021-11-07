package ru.slesarev.MontereyJack.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.slesarev.MontereyJack.Entity.User;
import ru.slesarev.MontereyJack.Entity.internal.AuthUser;
import ru.slesarev.MontereyJack.Service.UserService;
import ru.slesarev.MontereyJack.inner.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getByLogin(username.toLowerCase());
        if( user == null){
            throw new UsernameNotFoundException(String.format("User with login [%s] not found.", username));
        }

        return getAuthUser(user);
    }

    public static AuthUser getAuthUser(User user){
        List<GrantedAuthority> roles = new ArrayList<>(getAuthorities(user.getRoles()));
        return new AuthUser(user, roles);
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private static List<String> getPrivileges(Collection<Role> roles) {
        List<String> collection = new ArrayList<>();
        for (Role role : roles){
            collection.add(role.name());
        }
        return collection;
    }

    private static List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    public static void innerSpringAuth(User user) {
        //primitive authentication
        AuthUser authUser = getAuthUser(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
