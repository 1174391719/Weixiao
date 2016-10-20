package maxi_160514.network;


import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/*
 * 功能：提供http服务
 * 作者：马西
 * 时间：2014.7.15*/
public class HttpServer {



  /*  public JSONArray httpJsonPost(String value,// ****http上传
                                  String url) {// 用post的方式发送数据
        JSONArray jsonArray;
        String result = null;
        InputStream is = null;
        StringBuilder sb = null;
        ArrayList<NameValuePair> nameValuePairs = null;

        nameValuePairs.add(new BasicNameValuePair("key", value));

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,
                    HTTP.UTF_8));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "utf-8"), 8);
            sb = new StringBuilder();

            sb.append(reader.readLine());
            String line = "";
            while ((line = reader.readLine()) != null) {

                sb.append(line + "\n");

            }
            is.close();

            result = sb.toString();
            // System.out.println("result---------" + result);
            result = constants.tool.replaceString(0, result); // System.out.println("result---------"
            // + result);
            jsonArray = new JSONArray(result);
            System.out.println("jsonArray---------" + jsonArray);

            return jsonArray;

        } catch (Exception e) {
            System.out.println("httpServer Exception" + e);

        }
        return null;
    }*/


    public JSONObject httpPost(String url,Map<String,String> requestParams) {


        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        // BufferedReader bufferedReader = null;
        StringBuffer responseResult = new StringBuffer();
        StringBuffer params = mapToStringBuffer(requestParams);
        HttpURLConnection httpURLConnection = null;


        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            httpURLConnection = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            httpURLConnection.setRequestProperty("accept", "/*");
            httpURLConnection.setRequestProperty("connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Content-Length", String
                    .valueOf(params.length()));
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            // Post 请求不能使用缓存
            httpURLConnection.setUseCaches(false);


            httpURLConnection.setRequestMethod("POST");
            // 获取URLConnection对象对应的输出流
        /*    printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            printWriter.write(params.toString().getBytes());
            // flush输出流的缓冲
            printWriter.flush();*/

            OutputStream os = httpURLConnection.getOutputStream();
            os.write(params.toString().getBytes());
            os.flush();




            Log.d("kk", "params===:" + params);

            // 根据ResponseCode判断连接是否成功
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                Log.d("kk", " Error===" + responseCode);
            } else {
                Log.d("kk", "Post Success!");
            }
            // 定义BufferedReader输入流来读取URL的ResponseData
            bufferedReader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                responseResult.append(line);
            }


            Log.d("kk","toString:" + responseResult.toString());
            return new JSONObject(responseResult.toString());
         /*   Log.d("kk", "Constants.json:" +Constants.json);
            if (Constants.json == 0) {
                return new JSONObject(readFile("login.json"));
            }else if(Constants.json==1){
                return new JSONObject(readFile("bookshelf.json"));
            }*/

        } catch (Exception e) {
            Log.d("kk","send post request error!" + e);
        } finally {
            httpURLConnection.disconnect();
            try {
                if (printWriter != null) {
                    printWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }



        return null;
    }

    private StringBuffer mapToStringBuffer(Map map){

        StringBuffer buffer = new StringBuffer();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry element = (Map.Entry) it.next();
            buffer.append(element.getKey());
            buffer.append("=");
            buffer.append(element.getValue());
            buffer.append("&");
        }
        if (buffer.length() > 0) {
            buffer.deleteCharAt(buffer.length() - 1);
        }
        return buffer;
    }

    private  String readFile(String Path){



        this.getClass().getClassLoader().getResourceAsStream("assets/" + "Text.json");
        BufferedReader reader = null;
        String laststr = "";
        try{
        //    FileInputStream fileInputStream = new FileInputStream(Path);

            InputStream in=this.getClass().getClassLoader().getResourceAsStream("assets/" + Path);

            InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                laststr += tempString;
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }

}
