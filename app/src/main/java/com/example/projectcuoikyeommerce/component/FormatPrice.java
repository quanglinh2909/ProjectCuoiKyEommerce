package com.example.projectcuoikyeommerce.component;

import java.text.DecimalFormat;

public class FormatPrice {
    public static String formatPrice(double price){
        DecimalFormat formatter = new DecimalFormat("###,###,###");

       return  formatter.format(price)+" VNĐ";
    }
}
