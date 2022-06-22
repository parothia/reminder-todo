package com.parothia.security

enum class UserRole(of: Set<UserPermissions>) {
    USER(setOf<UserPermissions>(UserPermissions.USER_READ)), ADMIN(
        setOf<UserPermissions>(
            UserPermissions.ADMIN_READ,
            UserPermissions.ADMIN_WRITE
        )
    );
}