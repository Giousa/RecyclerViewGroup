package com.example.z.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<tastInfo>list=new ArrayList<>();
    private RecyclerView tast_RecyclerView;
    private View mView;
    private GridLayoutManager manage;
    private TastAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView=LayoutInflater.from(this).inflate(R.layout.activity_main,null);
        setContentView(mView);
        initFindView();
        initData();
        setData();

    }
    private void setData() {
        Data();
        adapter=new TastAdapter(this,list);
        tast_RecyclerView.setAdapter(adapter);

        adapter.setOnItemListeren(new TastAdapter.OnItemListeren() {
            @Override
            public void OnItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(),position+"", Toast.LENGTH_SHORT).show();
            }
        });
        manage.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {//判断是否是0 如果为0获取布局显示的个数。否则显示一个
            @Override
            public int getSpanSize(int position) {
                return adapter.getItemViewType(position)==TastAdapter.TYPE_HEAD? manage.getSpanCount() : 1;
            }
        });
    }

    private void initData() {

        //需求必须使用GridLayoutManager布局管理器
        manage=new GridLayoutManager(getApplicationContext(),3, OrientationHelper.VERTICAL,false);
        tast_RecyclerView.setLayoutManager(manage);
//        tast_RecyclerView.addItemDecoration(new CircleImageView2(getContext()));

    }

    private void initFindView() {
        tast_RecyclerView= (RecyclerView) mView.findViewById(R.id.tast_RecyclerView);
    }

    /**
     * 假数据
     */
    /**
     * 假数据
     */
    private List<String>getList=null;
    private void Data(){
        for (int i=0;i<4;i++){
            getList=new ArrayList<>();
            tastInfo info=new tastInfo();
            for(int j=0;j<5;j++){
                getList.add("EXECL"+j);
            }
            if (i%2==0){
                info.setHeader("Execl测试"+i);
            }
            else
            {
                info.setHeader("考试"+i);
            }
            info.setDataList(getList);
            list.add(info);
        }
    }
}
