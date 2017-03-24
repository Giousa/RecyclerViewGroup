package com.example.z.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import android.widget.TextView;

import java.io.File;
import java.util.List;
import java.util.Map;
/**
 *
 */
public class TastAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private File cacheDir;
    public static final int TYPE_HEAD=0;
    public static final int  TYPE_CONTENT=1;
    private List<tastInfo>list=null;

    private Context mContext;
    private OnItemListeren onItemListeren;
    public TastAdapter(Context context,List<tastInfo>list){
        this.mContext=context;
        this.list=list;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==TYPE_HEAD){
            View view= LayoutInflater.from(mContext).inflate(R.layout.item_tast_head,parent,false);
            HeadView headView=new HeadView(view);

            return headView;
        }
        else if (viewType==TYPE_CONTENT){
            View view= LayoutInflater.from(mContext).inflate(R.layout.item_tast,parent,false);
            MyHoldView contentHold=new MyHoldView(view);
            return contentHold;
        }
        return null;
    }

    /*
    判断位置
     */
    public boolean isHeader(int position) {
        return position == 0;
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        int count=0;
        for (int i=0;i<list.size();i++){
            tastInfo info=list.get(i);
            List<String>list1=info.getDataList();
            if (position==count){
                ((HeadView) holder).item_tast_head_title.setText(info.getHeader());
            }
            count++;
            for (int j = 0; j < list1.size(); j++) {
                if (position==count){
                    ((MyHoldView) holder).item_tast_title.setText(list1.get(j)+"");
                    ((MyHoldView) holder).item_tast_circleImageView.setTag(position);
                    ((MyHoldView) holder).item_tast_linearlayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int position=holder.getLayoutPosition();
                            onItemListeren.OnItemClick(view,position);
                        }
                    });
                }

                count++;
            }
        }

    }


    /**
     * 获取类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        //循环遍历所有的数据判断position==0执行头部布局。否则内容
        int count=0;
        for (int i=0;i<list.size();i++){
            tastInfo info=list.get(i);
            List<String>list1=info.getDataList();
            if (position==count){
                return TYPE_HEAD;
            }
            count++;

            for (int j=0;j<list1.size();j++){
                if (position==count){
                    return TYPE_CONTENT;
                }
                count++;
            }
        }
        return 0;
    }

    //   通过循环实体类中的数据获取所有的长度和
    @Override
    public int getItemCount() {//通过获取的list长度判断
        int count = list.size();
        for (int i = 0; i < list.size(); i++) {
            tastInfo bean = list.get(i);
            List<String> dataList = bean.getDataList();
            count += dataList.size();
        }
        return count;
    }

    class MyHoldView extends RecyclerView.ViewHolder{
        private CircleImageView item_tast_circleImageView;
        private TextView item_tast_title;
        private LinearLayout item_tast_linearlayout;
        public MyHoldView(View itemView) {
            super(itemView);
            item_tast_circleImageView= (CircleImageView) itemView.findViewById(R.id.item_tast_circleImageView);
            item_tast_title= (TextView) itemView.findViewById(R.id.item_tast_title);
            item_tast_linearlayout= (LinearLayout) itemView.findViewById(R.id.item_tast_linearlayout);
        }
    }

    class HeadView extends RecyclerView.ViewHolder{
        private TextView item_tast_head_title;
        private LinearLayout item_tast_head_linearlayout;
        public HeadView(View itemView) {
            super(itemView);
            item_tast_head_title= (TextView) itemView.findViewById(R.id.item_tast_head_title);
            item_tast_head_linearlayout= (LinearLayout) itemView.findViewById(R.id.item_tast_head_linearlayout);
        }
    }
    public interface OnItemListeren{
        void OnItemClick(View view,int position);
    }
    public void setOnItemListeren(OnItemListeren onItemListeren){
        this.onItemListeren=onItemListeren;
    }

}
