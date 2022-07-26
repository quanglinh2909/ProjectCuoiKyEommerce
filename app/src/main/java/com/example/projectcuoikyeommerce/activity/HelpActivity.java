package com.example.projectcuoikyeommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.admin.TermAdapter;
import com.example.projectcuoikyeommerce.model.Term;

import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends AppCompatActivity {
    RecyclerView termList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        termList=findViewById(R.id.list_term);
        loadView();
    }

    public void loadView(){
        List<Term> terms=createTerm();
        TermAdapter adapter=new TermAdapter(terms);
        termList.setLayoutManager(new LinearLayoutManager(this));
        termList.setAdapter(adapter);
    }
    public List<Term> createTerm(){
        List<Term> list=new ArrayList<Term>();
        list.add(new Term("ĐIỀU KHOẢN SỬ DỤNG",(String)getText(R.string.content_1)));
        list.add(new Term("HƯỚNG DẪN THANH TOÁN",(String)getText(R.string.content_2)));
        list.add(new Term("HƯỚNG DẪN MUA HÀNG",(String)getText(R.string.content_3)));
        list.add(new Term("CHÍNH SÁCH ĐỔI TRẢ",(String)getText(R.string.content_4)));
        list.add(new Term("BÁN HÀNG VÀ BẢO HÀNH",(String)getText(R.string.content_5)));
        list.add(new Term("CHÍNH SÁCH BẢO MẬT",(String)getText(R.string.content_6)));
        list.add(new Term("QUYỀN LỢI SINH NHẬT KHÁCH HÀNG",(String)getText(R.string.content_7)));
        return list;
    }
}