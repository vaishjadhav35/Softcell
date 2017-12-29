package in.vaishnavi.softcell;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        getData();


    }

    private void initViews() {

        mRecyclerView= (RecyclerView) findViewById(R.id.RecyclerView);
        mLinearLayoutManager=new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
         mRequestQueue= Volley.newRequestQueue(MainActivity.this);



    }
    private void getData() {

        String URL=getResources().getString(R.string.URL);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.POST, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d("Response","Response:"+response.toString());

                ArrayList<Data> mDataList=new ArrayList<>();

                for (int i=0;i<response.length();i++){

                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        String id=jsonObject.getString("id");
                        String name=jsonObject.getString("name");
                        String phone_number=jsonObject.getString("phone_number");
                        String subject=jsonObject.getString("subject");

                        Data data=new Data(id,name,phone_number,subject);
                        mDataList.add(data);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    DataAdapter dataAdapter=new DataAdapter(MainActivity.this,mDataList);
                    mRecyclerView.setAdapter(dataAdapter);
                    dataAdapter.notifyDataSetChanged();


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Error while fetching data",Toast.LENGTH_LONG).show();
                Log.d("Error","Error Message:"+error.toString());

            }
        });

        mRequestQueue.add(jsonArrayRequest);
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        super.onBackPressed();

    }
}
