package com.strong.yujiaapp.service;

import com.strong.yujiaapp.utils.StringToXml;
import com.strong.yujiaapp.utils.WebServiceConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-09-15.
 */

public class ReceiveGoodsService {
    /**
     * 站点集合
     *
     * @param params
     *            查询条件
     * @return
     */
    public List<Map<String, String>> getGroupList(HashMap<String, String> params) {
        List<Map<String, String>> lstGroup = new ArrayList<Map<String, String>>();
        String strresult = WebServiceConnection.GetString("SelectMapPoint",
                params);
        if (strresult != null && !strresult.equals("error")) {
            try {
                if (!strresult.trim().equals("")
                        && !strresult.trim().equals("anyType{}")) {
                    lstGroup = StringToXml.getList(strresult, "MapPoint");
                }
            } catch (Exception e) {
                return null;
            }
        }
        return lstGroup;
    }
}
