package com.example.projectcuoikyeommerce.event;

import com.example.projectcuoikyeommerce.model.Province;

public interface AddressEvent {
    void selectProvince(int position, Province province);
}
