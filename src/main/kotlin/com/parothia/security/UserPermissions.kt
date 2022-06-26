package com.parothia.security

enum class UserPermissions(permission: String) {
    USER_READ("user:read"), ADMIN_READ("admin:read"), ADMIN_WRITE("admin:write")
}