package in.vaishnavi.softcell;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vaishnavi on 28/12/17.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Data> mDataList;

    public DataAdapter(Context mContext, ArrayList<Data> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_data_adapter,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Data data=mDataList.get(position);
        holder.mTextView_ID.append(data.getmID());
        holder.mTextView_Name.append(data.getmName());
        holder.mTextView_PhoneNumber.append(data.getmPhoneNumber());
        holder.mTextView_Subject.append(data.getmSubject());


    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView_ID,mTextView_Name,mTextView_PhoneNumber,mTextView_Subject;
        public ViewHolder(View view) {
            super(view);

            mTextView_ID=view.findViewById(R.id.textView_ID);
            mTextView_Name=view.findViewById(R.id.textView_Name);
            mTextView_PhoneNumber=view.findViewById(R.id.textView_PhoneNumber);
            mTextView_Subject=view.findViewById(R.id.textView_Subject);

        }
    }
}
