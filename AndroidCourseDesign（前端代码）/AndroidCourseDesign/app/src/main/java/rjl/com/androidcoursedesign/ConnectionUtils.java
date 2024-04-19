package rjl.com.androidcoursedesign;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class ConnectionUtils {

    public static String postRequest(String url, Map<String, String> rawParams) {
        String result = null;
        try {

            // 创建HttpPost对象。
            HttpPost post = new HttpPost(url);
            // 如果传递参数个数比较多的话可以对传递的参数进行封装
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for (String key : rawParams.keySet()) {
                // 封装请求参数
                params.add(new BasicNameValuePair(key, rawParams.get(key)));
            }
            //Logger.i(TAG, "params------------------->" + params);
            // 设置请求参数
            post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
            HttpConnectionParams.setSoTimeout(httpParameters, 15000);



            SchemeRegistry registry=new SchemeRegistry();
            //让请求支持http  和https两种模式
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory( ), 8080));


            DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
            // 发送POST请求
            HttpResponse httpResponse = httpClient.execute(post);
            // 如果服务器成功地返回响应
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                // 获取服务器响应字符串
//                byte[] bytes = EntityUtils.toByteArray(httpResponse.getEntity());

                result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                Log.i(TAG, "result-------->" + result);

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
