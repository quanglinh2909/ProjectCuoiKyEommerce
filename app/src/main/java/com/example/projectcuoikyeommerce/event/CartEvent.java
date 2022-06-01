package com.example.projectcuoikyeommerce.event;

public interface CartEvent {
    void clickIncrease(int position);
    void clickReduced(int position);
    void inputEditTex(int position, int value);
    void selectItem(int position, boolean isChecked);
}
