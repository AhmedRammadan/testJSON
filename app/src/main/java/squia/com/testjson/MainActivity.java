package squia.com.testjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView tv_userId, tv_Id, tv_title, tv_body;
    private String userId, Id, title, body;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_userId = findViewById(R.id.userId);
        tv_Id = findViewById(R.id.id);
        tv_title = findViewById(R.id.title);
        tv_body = findViewById(R.id.body);

        //@TODO getData form website jsonplaceholder.typicode.com
        //getData();

        /**
         * TODO  setData form website jsonplaceholder.typicode.com
         * Just Test
         */
        try {
            setData();
        } catch (Exception e) {
            Log.e(TAG, "Error", e);

        }

    }

    private void getData() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray root = new JSONArray(response);
                            JSONObject post = root.getJSONObject(0);
                            userId = post.getString("userId");
                            Id = post.getString("id");
                            title = post.getString("title");
                            body = post.getString("body");
                            tv_userId.setText(userId);
                            tv_Id.setText(Id);
                            tv_title.setText(title);
                            tv_body.setText(body);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(request);
    }

    private void setData() {
        String url = "http://water.sitksahost.com/api/register";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("response", response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "ErrorResponse", error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("firstName", "ahmed");
                map.put("lastName", "Ramadan");
                map.put("email", "aaaa@gmail.com");
                map.put("password", "123456");
                map.put("addressDetails", "sdasdfad");
                map.put("area", "ddd");
                map.put("city", "ddd");
                map.put("country", "ddd");
                map.put("state", "ddd");
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<>();
                header.put("Content-Type", "application/json");
                header.put("Accept", "application/json");
                return header;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}
