package com.example.projectcuoikyeommerce.activity.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 *  Lớp hiển thị chi tiết người dùng đã xác thực cho giao diện người dùng.
 */
class LoggedInUserView {
    private String displayName;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }
}