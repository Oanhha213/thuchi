package com.example.thuchi;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText searchEditText;
    private TextView soDuTextView;
    private List<ThuChi> thuChiList;
    private ThuChiAdapter adapter;
    private double soDu = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.et_search);
        soDuTextView = findViewById(R.id.tv_so_du);
        listView = findViewById(R.id.list_view);

        // Khởi tạo danh sách khoản thu/chi
        thuChiList = new ArrayList<>();

        // Tạo CSDL SQLite và thêm dữ liệu mẫu
        Database database = new Database(MainActivity.this, "thuchi.sqlite", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS THUCHI(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TenKhoan VARCHAR(200), " +
                "NgayThang VARCHAR(200), " +
                "SoTien REAL, " +
                "IsThu INTEGER)");

        // Thêm dữ liệu mẫu
//        database.QueryData("INSERT INTO THUCHI VALUES(null, 'Thuê nhà', '2023-05-01', 1000000, 1)");
//        database.QueryData("INSERT INTO THUCHI VALUES(null, 'Mua ví', '2023-05-02', 500000, 0)");
//        database.QueryData("INSERT INTO THUCHI VALUES(null, 'Lương tháng 5', '2023-05-05', 2000000, 1)");

        // Lấy dữ liệu từ CSDL và cập nhật danh sách thuChiList
        Cursor cursor = database.GetData("SELECT * FROM THUCHI");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String tenKhoan = cursor.getString(1);
            String ngayThang = cursor.getString(2);
            double soTien = cursor.getDouble(3);
            boolean isThu = cursor.getInt(4) == 1;
            thuChiList.add(new ThuChi(id, tenKhoan, ngayThang, soTien, isThu));
            if (isThu) {
                soDu += soTien;
            } else {
                soDu -= soTien;
            }
        }

        // Sắp xếp danh sách thuChiList theo thứ tự giảm dần về số tiền
        Collections.sort(thuChiList, new Comparator<ThuChi>() {
            @Override
            public int compare(ThuChi thuChi1, ThuChi thuChi2) {
                if (thuChi1.getSoTien() > thuChi2.getSoTien()) {
                    return -1;
                } else if (thuChi1.getSoTien() < thuChi2.getSoTien()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        // Khởi tạo adapter và gắn kết với ListView
        adapter = new ThuChiAdapter(this, R.layout.layoutthuchi, thuChiList);
        listView.setAdapter(adapter);

        // Hiển thị số dư
        soDuTextView.setText("Số dư: " + soDu);

        // Xử lý sự kiện khi người dùng tìm kiếm
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String searchKeyword = searchEditText.getText().toString().trim();
                    search(searchKeyword);
                    return true;
                }
                return false;
            }
        });
    }


    private void search(String keyword) {
        List<ThuChi> searchResults = new ArrayList<>();

        for (ThuChi thuChi : thuChiList) {
            if (thuChi.getTenKhoan().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(thuChi);
            }
        }
        // Cập nhật danh sách hiển thị với kết quả tìm kiếm
        adapter.setData(searchResults);
    }
}


