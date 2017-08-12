package vo.cvcompany.com.myapplication.Module;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vo.cvcompany.com.myapplication.R;

/**
 * Created by kh on 8/9/2017.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {

    private ArrayList<Description> arrayList;
    private Context context;

    public Myadapter(Context context, ArrayList<Description> arrayList){
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Description  description = arrayList.get(position);
        holder.txtId.setText(description.getId());
        holder.txtDescription.setText(description.getDescription());
        holder.txtName.setText(description.getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtId)
        TextView txtId;
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.txtDescription)
        TextView txtDescription;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
