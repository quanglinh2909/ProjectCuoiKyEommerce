package com.example.projectcuoikyeommerce.event;

import com.example.projectcuoikyeommerce.model.UserInfo;

public interface EventGetAddress {
    void clickSelectAddress(int position, UserInfo userInfo);
    void clickEditAddress(int position, UserInfo userInfo);
}
