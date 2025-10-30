package com.library.management.system.libraryManagementSystem.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    // Admin permissions
    ADMIN_CREATE("admin:create"),
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),

    // Member permissions
    MEMBER_CREATE("member:create"),
    MEMBER_READ("member:read"),
    MEMBER_UPDATE("member:update"),
    MEMBER_DELETE("member:delete");
    @Getter
    private final String permissionName;
}
