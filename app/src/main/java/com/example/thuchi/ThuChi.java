package com.example.thuchi;
public class ThuChi {
    private int id;
    private String tenKhoan;
    private String ngayThang;
    private double soTien;
    private boolean isThu;

    public ThuChi(int id, String tenKhoan, String ngayThang, double soTien, boolean isThu) {
        this.id = id;
        this.tenKhoan = tenKhoan;
        this.ngayThang = ngayThang;
        this.soTien = soTien;
        this.isThu = isThu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKhoan() {
        return tenKhoan;
    }

    public void setTenKhoan(String tenKhoan) {
        this.tenKhoan = tenKhoan;
    }

    public String getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(String ngayThang) {
        this.ngayThang = ngayThang;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public boolean isThu() {
        return isThu;
    }

    public void setThu(boolean thu) {
        isThu = thu;
    }
}

