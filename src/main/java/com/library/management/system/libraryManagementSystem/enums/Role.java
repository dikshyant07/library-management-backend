package com.library.management.system.libraryManagementSystem.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public enum Role {
    MEMBER(Set.of(
            Permission.MEMBER_CREATE,
            Permission.MEMBER_READ,
            Permission.MEMBER_UPDATE,
            Permission.MEMBER_DELETE
    )),

    ADMIN(Set.of(
            Permission.MEMBER_CREATE,
            Permission.MEMBER_READ,
            Permission.MEMBER_UPDATE,
            Permission.MEMBER_DELETE,
            Permission.ADMIN_CREATE,
            Permission.ADMIN_READ,
            Permission.ADMIN_UPDATE,
            Permission.ADMIN_DELETE
    ));
    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(permissions.stream().map(permission -> new SimpleGrantedAuthority(permission.getPermissionName())).toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
