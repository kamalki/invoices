package com.example.kamal.incomingcallspecial;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kamal on 2/3/16.
 */
class CodeLearnAdapter extends BaseAdapter {

    private static JSONArray array;
    Context mcontext;
    LayoutInflater inflater;

    public CodeLearnAdapter(Context context, JSONArray arr) {
        this.mcontext = context;
        this.array = arr;
    }

    public int getCount() {
        return array == null ? 0 : array.length();
    }

    @Override
    public Object getItem(final int position) {
        if (array == null | array.length() < position) {
            return null;
        }
        try {
            return array.get(position);
        } catch (final JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    public JSONObject getObject(final int position) {
        return (JSONObject) getItem(position);
    }


    public void setData(final JSONArray data) {
        array = data;
        notifyDataSetChanged();
    }

    private static class ViewHolder {

        TextView phone_number;
        TextView remark;
        TextView currentDateandTime;

// public ViewHolder(TextView name, ImageView imgView){
//
//     this.name = name;
//     this.imgView = imgView;
//
// }

    }

    @Override
    public View getView(int position, final View convertView, final ViewGroup parent) {
        final View view;
        final ViewHolder holder;
        if (convertView != null) {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        } else {
            inflater = (LayoutInflater) mcontext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_main, parent, false);
            holder = new ViewHolder();
            holder.phone_number = (TextView) view.findViewById(R.id.textView11);
            holder.currentDateandTime = (TextView) view.findViewById(R.id.textView12);
            holder.remark = (TextView) view.findViewById(R.id.textView21);
            view.setTag(holder);
        }
        final JSONObject jsonObj = getObject(position);
        holder.phone_number.setText(jsonObj.optString("phone_number"));
        holder.currentDateandTime.setText(jsonObj.optString("call_date"));
        holder.remark.setText(jsonObj.optString("remark"));


        holder.phone_number.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                single_click(jsonObj);

            }
        });

        holder.currentDateandTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                single_click(jsonObj);

            }
        });

        holder.remark.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                single_click(jsonObj);

            }
        });


        return view;
    }

    public void single_click(JSONObject jsonObj) {
        Intent intent = new Intent(mcontext, SingleItemView.class);
        intent.putExtra("phone_number", jsonObj.optString("phone_number"));
        intent.putExtra("call_date", jsonObj.optString("call_date"));
        intent.putExtra("remark", jsonObj.optString("remark"));
        // Start SingleItemView Class
        mcontext.startActivity(intent);
    }
}


