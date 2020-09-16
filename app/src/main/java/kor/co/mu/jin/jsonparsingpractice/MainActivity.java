package kor.co.mu.jin.jsonparsingpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.rv_recyclerview);

        try{

            InputStream is = getAssets().open("jsonData.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);

            Map<String,Object> boxOfficeResult= gson.fromJson( jsonObject.get("boxOfficeResult").toString(),new TypeToken<Map<String, Object>>(){}.getType());

            ArrayList<Map<String, Object>> jsonList = (ArrayList) boxOfficeResult.get("dailyBoxOfficeList");

            mAdapter=new MyAdapter(jsonList);

        }catch (Exception e){e.printStackTrace();}

    }//onCreate()..

    public void click_btn(View view) {
        recyclerView.setAdapter(mAdapter);
    }
}
