/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practic2_2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Practic2_2 {

    public static void main(String[] args) {
        System.out.println("Start programm!");
        String server = "https://android-for-students.ru";
        String serverPath = "/materials/practical/hello.php";
        HashMap<String, String> map = new HashMap();
        map.put("name","Korzhavina");
        map.put("group", "RIBO-03-22");
        HTTPRunnable httpRunnable = new HTTPRunnable(server+ serverPath, map);
        Thread th = new Thread(httpRunnable);
        th.start();
        try{
            th.join();
        }catch(InterruptedException ex) {
            
        }catch (JSONException e) {
            System.out.println("Error parsing JSON response: " + e.getMessage());
        } catch (Exception ex) {
            System.out.println("Error processing server response: " + ex.getMessage());
        }
        finally{
            JSONObject jSONObject = new JSONObject(httpRunnable.getResponseBody());
            int result = jSONObject.getInt("result_code");
            System.out.println("Result"+ result);
            System.out.println("Type: "+ jSONObject.getString("message_type"));
            System.out.println("Text"+ jSONObject.getString("message_text"));
            switch(result){
                case 1: 
                    JSONArray jSONArray = jSONObject.getJSONArray("task_list");
                    System.out.println("Task list: ");
                    for(int i = 0; i < jSONArray.length(); i++){
                        System.out.println((i+1) +") " +jSONArray.get(i));  
                    }
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        }
    }
}