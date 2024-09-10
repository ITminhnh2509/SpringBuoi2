package com.example.thu2_springio.model;

public enum XepLoai {
    GIOI("Giỏi"), Kha("Khá"), TRUNGBINH("Trung bình"), YEU("Yếu");
    private String xl;
    XepLoai(String xl) {
    this.xl = xl;
    }
    public String getXl() {
        return xl;
    }
    public static String fromString(String xl){
        for (XepLoai xepLoai : XepLoai.values()) {
            if(xepLoai.xl.equalsIgnoreCase(xl)){
                return xl;
            }
        }
        throw new IllegalArgumentException("Xep loai not found: " + xl);
    }
}
