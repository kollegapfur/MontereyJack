//package ru.slesarev.MontereyJack.Entity.internal;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import ru.slesarev.MontereyJack.Entity.User;
//
//import java.util.Collection;
//import java.util.List;
//
//@Data
//public class AuthUser implements UserDetails {
//
//    private Long id;
//    private String login;
//    private String password;
//    private String name;
//
//    private boolean enabled;
//
//    private List<GrantedAuthority> roles;
//
//    public AuthUser(Long id, String login, String password, boolean enabled, List<GrantedAuthority> roles) {
//        this.id = id;
//        this.login = login;
//        this.password = password;
//        this.enabled = enabled;
//        this.roles = roles;
//    }
//
//    public AuthUser(User user, List<GrantedAuthority> roles) {
//        this(user.getId(), user.getLogin(), user.getPassword(), user.isEnabled(), roles);
//    }
//
//    @JsonIgnore
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles;
//    }
//
//    @JsonIgnore
//    @Override
//    public String getUsername(){
//        return login;
//    }
//
//    @JsonIgnore
//    @Override
//    public String getPassword(){
//        return password;
//    }
//
//    @JsonIgnore
//    @Override
//    public boolean isEnabled(){
//        return enabled;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//}
