package com.example.projectcuoikyeommerce.event;

import com.example.projectcuoikyeommerce.model.TagChild;
import com.example.projectcuoikyeommerce.model.TagParent;

public interface MenuEvent {
    void clickItemMenu(TagParent tagParent, TagChild tagChild);
}
