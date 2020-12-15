package ru.kirikomp.controller;

import ru.kirikomp.service.RoleRepr;
import ru.kirikomp.service.RoleService;
import ru.kirikomp.service.UserRepr;
import ru.kirikomp.service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class UserController implements Serializable {

    @EJB
    private UserService userService;

    @EJB
    private RoleService roleRepository;

    private UserRepr user;

    private List<RoleRepr> roles;

    private List<UserRepr> users;

    public void preLoad() {
        this.roles = roleRepository.getAllRoles();
        this.users = userService.getAllUsers();
    }

    public UserRepr getUser() {
        return user;
    }

    public void setUser(UserRepr user) {
        this.user = user;
    }

    public List<UserRepr> getAllUsers() {
        return users;
    }

    public String editUser(UserRepr user) {
        this.user = user;
        return "/admin/user.xhtml?faces-redirect=true";
    }

    public void deleteUser(UserRepr user) {
        userService.delete(user.getId());
    }

    public String createUser() {
        this.user = new UserRepr();
        return "/admin/user.xhtml?faces-redirect=true";
    }

    public String saveUser() {
        userService.merge(this.user);
        return "/admin/users.xhtml?faces-redirect=true";
    }

    public List<RoleRepr> getAllRoles() {
        return roles;
    }
}
