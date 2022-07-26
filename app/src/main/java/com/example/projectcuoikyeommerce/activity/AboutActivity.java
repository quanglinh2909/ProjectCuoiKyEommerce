package com.example.projectcuoikyeommerce.activity;
import com.example.projectcuoikyeommerce.adapter.admin.TimeLineAdapter;
import com.lriccardo.timelineview.TimelineDecorator;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.model.TimeLine;
import com.lriccardo.timelineview.TimelineView;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends AppCompatActivity {
    LinearLayout timelineView;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        loadView();
    }
    private void initUI(){
        timelineView=findViewById(R.id.timeLine);
        recyclerView=findViewById(R.id.time_line_list);
    }
    private void loadView(){

        List<TimeLine> list=createTimeLine();
        Log.d("SIZE", list.size()+"");
        TimeLineAdapter adapter=new TimeLineAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    private List<TimeLine> createTimeLine(){
        List<TimeLine> list=new ArrayList<TimeLine>();
        list.add(new TimeLine(R.drawable.foundation,"Thành lập-1999","Khởi nghiệp với nguồn vốn ít ỏi cùng cửa hàng nhỏ đi thuê"));
        list.add(new TimeLine(R.drawable.employee,"10 Nhân Viên-2000","Thời gian ngắn thu nhận được 10 nhân viên"));
        list.add(new TimeLine(R.drawable.shop,"Tân trang-2001","Mua lại và tân trang cửa hàng từ chủ thuê"));
        list.add(new TimeLine(R.drawable.expand,"Mở rộng-2004","Tình hình kinh doanh thuận lợi, mở rộng cửa hàng"));
        list.add(new TimeLine(R.drawable.grand_opening,"Mở chi nhánh-2015","Khai trương cửa hàng mới"));
        return list;
    }
}