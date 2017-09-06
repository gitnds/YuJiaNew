package com.strong.yujiaapp.utils;

import com.strong.yujiaapp.data.ConfigInfo;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/8/24.
 */

public class WebServiceConnection {

    /**
     * 无参返回字符串结果
     *
     * @param METHOD_NAME
     *            方法名
     * @return
     */
    public static String GetString(String METHOD_NAME) {
        String result = "";
        try {
            // 指定 WebService的命名空间和调用方法
            SoapObject rpc = new SoapObject(ConfigInfo.NAMESPACE, METHOD_NAME);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyIn = rpc;
            envelope.bodyOut = rpc;
            envelope.dotNet = true;
            // 超时设置为5秒
            HttpTransportSE ht = new HttpTransportSE(ConfigInfo.URL
                    + ConfigInfo.WEBSERVICE, 5000);
            ht.call(ConfigInfo.NAMESPACE + METHOD_NAME, envelope);
            // envelope.setOutputSoapObject(rpc);
            // 使用call方法调用WebService方法
            // ht.call(NAMESPACE + METHOD_NAME, envelope);
            // SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            Object response = (Object) envelope.getResponse();
            result = response.toString();
            if (result.equals("anyType{}"))
                result = "";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return result;
    }

    /**
     * 有参返回字符串结果
     *
     * @param METHOD_NAME
     *            方法名
     * @param params
     *            参数
     * @return
     */
    public static String GetString(String METHOD_NAME,
                                   HashMap<String, String> params) {
        String result = "";
        try {
            // 指定 WebService的命名空间和调用方法
            SoapObject rpc = new SoapObject(ConfigInfo.NAMESPACE, METHOD_NAME);
            // 设置调用方法的参数值，如果没有参数，可以省略，设置方法的参数值的代码如下：
            for (String key : params.keySet()) {
                rpc.addProperty(key, params.get(key));
            }
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyIn = rpc;
            envelope.bodyOut = rpc;
            envelope.dotNet = true;
            HttpTransportSE ht = new HttpTransportSE(ConfigInfo.URL
                    + ConfigInfo.WEBSERVICE, 10000);
            ht.call(ConfigInfo.NAMESPACE + METHOD_NAME, envelope);
            // envelope.setOutputSoapObject(rpc);
            // 使用call方法调用WebService方法
            // ht.call(NAMESPACE + METHOD_NAME, envelope);
            // SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            Object response = (Object) envelope.getResponse();
            result = response.toString();
            if (result.equals("anyType{}"))
                result = "";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return result;
    }


}
