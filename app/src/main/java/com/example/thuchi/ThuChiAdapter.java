package com.example.thuchi;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ThuChiAdapter extends ArrayAdapter<ThuChi> {

    private Context context;
    private int resource;
    private List<ThuChi> thuChiList;

    public ThuChiAdapter(Context context, int resource, List<ThuChi> thuChiList) {
        super(context, resource, thuChiList);
        this.context = context;
        this.resource = resource;
        this.thuChiList = thuChiList;
    }

    public void setData(List<ThuChi> thuChiList) {
        this.thuChiList = thuChiList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(resource, parent, false);

            holder = new ViewHolder();
            holder.tvLoai = row.findViewById(R.id.tv_loai);
            holder.tvNgayThang = row.findViewById(R.id.tv_ngay_thang);
            holder.tvTenKhoan = row.findViewById(R.id.tv_ten_khoan);
            holder.tvSoTien = row.findViewById(R.id.tv_so_tien);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        ThuChi thuChi = thuChiList.get(position);

        holder.tvTenKhoan.setText(thuChi.getTenKhoan());
        holder.tvNgayThang.setText(thuChi.getNgayThang());
        holder.tvSoTien.setText(String.valueOf(thuChi.getSoTien()));

        if (thuChi.isThu()) {
            holder.tvLoai.setText("Thu");
            holder.tvLoai.setTextColor(Color.GREEN);
        } else {
            holder.tvLoai.setText("Chi");
            holder.tvLoai.setTextColor(Color.RED);
        }

        return row;
    }

    static class ViewHolder {
        TextView tvLoai;
        TextView tvNgayThang;
        TextView tvTenKhoan;
        TextView tvSoTien;
    }
}

