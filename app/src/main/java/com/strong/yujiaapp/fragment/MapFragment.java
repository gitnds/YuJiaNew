package com.strong.yujiaapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.strong.yujiaapp.R;
import com.strong.yujiaapp.ShareApplication;
import com.strong.yujiaapp.beanmodel.BaiduAuto;
import com.strong.yujiaapp.service.LocationService;
import com.strong.yujiaapp.service.ReceiveGoodsService;
import com.strong.yujiaapp.utils.PhoneService;
import com.strong.yujiaapp.utils.PointToDistance;
import com.strong.yujiaapp.utils.ThreadPoolManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/23.
 */

public class MapFragment extends Fragment {
    Activity activity;
    MapView mMapView = null;
    BaiduMap mBaiduMap;
    ImageButton ib_loc;
    TextView tv_near_detail_address, tv_near_group_name, tv_distance, tv_go_to;
    BitmapDescriptorFactory mCurrentMarker;
    LocationClient mLocClient;
    private LocationService locationService;
    public LocationClient mLocationClient = null;
    boolean isFirstLoc = true;
    int locationCount = 0;
    BitmapDescriptor bitmap, bitmapRED;
    List<Map<String, String>> lstGroup;
   public List<Map<String, String>> sort = new ArrayList<Map<String, String>>();
    BDLocation mylocation;
    double nearY;
    double nearX;
    String nearName;//zui//宇佳站点名称
    String detailName;//详细的到站地址
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mMapView = (MapView) view.findViewById(R.id.bmapView);
        ib_loc = (ImageButton) view.findViewById(R.id.ib_loc);
        tv_near_detail_address = (TextView) view.findViewById(R.id.tv_near_detail_address);
        tv_near_group_name = (TextView) view.findViewById(R.id.tv_near_group_name);
        tv_distance = (TextView) view.findViewById(R.id.tv_distance);
        tv_go_to = (TextView) view.findViewById(R.id.tv_go_to);
        ib_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFirstLoc = true;
            }
        });
        tv_go_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PhoneService.isAvilible(activity, "com.baidu.BaiduMap")) {
                    Intent i1 = new Intent();
// 驾车路线规划
                    i1.setData(Uri.parse("baidumap://map/direction?region=" + mylocation.getCity() + "&origin=" + mylocation.getLatitude() + "," + mylocation.getLongitude() + "&destination=" + detailName + "&mode=driving"));
                    startActivity(i1);

                }else{
                    Toast.makeText(activity, "您尚未安装百度地图", Toast.LENGTH_LONG).show();
                }
            }
        });

        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);

        // 开启定位图层
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        activity = (Activity) getContext();
        initLocation();
        super.onCreate(savedInstanceState);

    }

    private void initLocation() {
        locationService = ((ShareApplication) activity.getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        locationService.start();
    }


    public void onStart() {
        super.onStart();

    }

  /*  @Override 注释因为切换回来不调用oncreat就不能初始化定位，所以不用停止
    public void onStop() {
        // TODO Auto-generated method stub
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        mBaiduMap.setMyLocationEnabled(false);
        super.onStop();
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        mBaiduMap.setMyLocationEnabled(false);
        super.onStop();
    }

    /*****
     * 定位结果回调，重写onReceiveLocation方法
     */
    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            // 构造定位数据
            mylocation = location;
           /*  MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(0)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
                     // 设置定位数据

            mBaiduMap.setMyLocationData(locData);*/
            mMapView.getOverlay().clear();

            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                if (locationCount == 0) {
                    //设置缩放中心点；缩放比例；
                    builder.target(ll).zoom(12.0f);
                    locationCount++;
                } else {
                    double zoomLevel = mBaiduMap.getMapStatus().zoom;
                    builder.target(ll).zoom((float) zoomLevel);
                }

                //给地图设置状态
                // 构建MarkerOption，用于在地图上添加Marker
                bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.icon_openmap_focuse_mark); // 推算结果
                OverlayOptions option = new MarkerOptions().position(ll).icon(bitmap);
                // 在地图上添加Marker，并显示
                mBaiduMap.addOverlay(option);
                mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Bundle overlay = marker.getExtraInfo();
                        LatLng posison = marker.getPosition();
                        Double lat = posison.latitude;
                        Double longt = posison.longitude;
                        nearX = longt;
                        nearY = lat;
                        ThreadPoolManager.getInstance().addTask(addressRun);
                        Map<String, String> map = (Map<String, String>) overlay.getSerializable("info");
                        nearName = map.get("name");
                        String dist = map.get("dist");
                        tv_near_group_name.setText(nearName);
                        tv_distance.setText("距离" + Integer.parseInt(dist) / 1000 + "公里");
                        Toast.makeText(activity, "Marker被点击了！", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
                ThreadPoolManager.getInstance().addTask(searchRun);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

            }
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                sb.append("time : ");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
               /* Thread th_searchRun = new Thread(searchRun);
                th_searchRun.start();

                sb.append(location.getTime());
                sb.append("\nlocType : ");// 定位类型
                sb.append(location.getLocType());
                sb.append("\nlocType description : ");// *****对应的定位类型说明*****
                sb.append(location.getLocTypeDescription());
                sb.append("\nlatitude : ");// 纬度
                sb.append(location.getLatitude());
                sb.append("\nlontitude : ");// 经度
                sb.append(location.getLongitude());
                sb.append("\nradius : ");// 半径
                sb.append(location.getRadius());
                sb.append("\nCountryCode : ");// 国家码
                sb.append(location.getCountryCode());
                sb.append("\nCountry : ");// 国家名称
                sb.append(location.getCountry());
                sb.append("\ncitycode : ");// 城市编码
                sb.append(location.getCityCode());
                sb.append("\ncity : ");// 城市
                sb.append(location.getCity());
                sb.append("\nDistrict : ");// 区
                sb.append(location.getDistrict());
                sb.append("\nStreet : ");// 街道
                sb.append(location.getStreet());
                sb.append("\naddr : ");// 地址信息
                sb.append(location.getAddrStr());
                sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
                sb.append(location.getUserIndoorState());
                sb.append("\nDirection(not all devices have value): ");
                sb.append(location.getDirection());// 方向
                sb.append("\nlocationdescribe: ");
                sb.append(location.getLocationDescribe());// 位置语义化信息
                sb.append("\nPoi: ");// POI信息
                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        sb.append(poi.getName() + ";");
                    }
                }
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 速度 单位：km/h
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());// 卫星数目
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 海拔高度 单位：米
                    sb.append("\ngps status : ");
                    sb.append(location.getGpsAccuracyStatus());// *****gps质量判断*****
                    sb.append("\ndescribe : ");
                    sb.append("gps定位成功");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    // 运营商信息
                    if (location.hasAltitude()) {// *****如果有海拔高度*****
                        sb.append("\nheight : ");
                        sb.append(location.getAltitude());// 单位：米
                    }
                    sb.append("\noperationers : ");// 运营商信息
                    sb.append(location.getOperators());
                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    sb.append("\ndescribe : ");
                    sb.append("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }*/
            }
        }

    };
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            String result = "";
            switch (msg.what) {
                case 6:
                    List<OverlayOptions> options = new ArrayList<OverlayOptions>();
                    for (int i = 0; i < lstGroup.size(); i++) {
                        Map<String, String> map = new HashMap<String, String>();
                        double a = Double.parseDouble(lstGroup.get(i).get("tm_y"));
                        double b = Double.parseDouble(lstGroup.get(i).get("tm_x"));

                        // 在地图上添加Marker，并显示
                    /*
                     * LatLng p1 = new LatLng(a, b); LatLng p2 = new LatLng(c,
					 * d);
					 *
					 * int b2 = (int) DistanceUtil.getDistance(p1, p2);
					 */
                        int b1 = (int) PointToDistance.getDistanceFromTwoPoints(a,
                                b, mylocation.getLatitude(), mylocation.getLongitude());
                        map.put("name", lstGroup.get(i).get("tm_grname"));
                        map.put("dist", b1 + "");
                        map.put("jing", String.valueOf(a));
                        map.put("wei", String.valueOf(b));
                        sort.add((HashMap<String, String>) map);
                    }
                    for (int i = 0; i < sort.size(); i++) {
                        for (int j = 0; j < i; j++) {
                            if (Integer.parseInt(sort.get(i).get("dist")) < Integer
                                    .parseInt(sort.get(j).get("dist"))) {
                                sort.add(j, sort.get(i));
                                sort.remove(i + 1);
                            }
                        }
                    }
                    int size = 0;
                    if (sort.size() > 10) {
                        size = 10;
                    } else {
                        size = sort.size();
                    }
                    Marker marker = null;
                    for (int i = 0; i < size; i++) {
                        String jing = sort.get(i).get("jing");
                        String wei = sort.get(i).get("wei");
                        LatLng position = new LatLng(Double.parseDouble(jing), Double.parseDouble(wei));
                        bitmapRED = BitmapDescriptorFactory.fromResource(R.mipmap.icon_openmap_mark); // 推算结果
                        OverlayOptions option2 = new MarkerOptions().position(position).icon(bitmapRED);
                        //   options.add(option2);
                        marker = (Marker) (mBaiduMap.addOverlay(option2));
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("info", (Serializable) sort.get(i));//将自己宇佳站点信息带过去
                        marker.setExtraInfo(bundle);
                    }
                    nearY = Double.parseDouble(sort.get(0).get("jing"));
                    nearX = Double.parseDouble(sort.get(0).get("wei"));
                    String dist = sort.get(0).get("dist");
                    String name = sort.get(0).get("name");
                    nearName = name; //第一次把最近站点赋值给near防止下面调用赋值为空
                    tv_near_group_name.setText(name);//初始化最近收货站点名称
                    tv_distance.setText("距离" + Integer.parseInt(dist) / 1000 + "公里");//初始化最近收货站点距离
                    ThreadPoolManager.getInstance().addTask(addressRun);
                    //       mBaiduMap.addOverlays(options);//把所有地址标识添加到地图
                    break;
                case 7:
                    String detail = msg.getData().getString("detailAddress");
                    tv_near_detail_address.setText(detail);
                    tv_near_group_name.setText(nearName);//点击marker获取的收货站点名称
                    break;
            }
        }

    };
    Runnable searchRun = new Runnable() {
        @Override
        public void run() {
            try {

                HashMap<String, String> params = new HashMap<String, String>();
                params.put("cityname", "济南市");
                lstGroup = new ReceiveGoodsService().getGroupList(params);
                          /*  double c = lloc;
                            double d = llo;*/
                Message msg = new Message();
                msg.what = 6;
                handler.sendMessage(msg);

                // 排序

            } catch (Exception e) {
            }
        }
    };
    Runnable addressRun = new Runnable() {

        @Override
        public void run() {
            HttpURLConnection connection = null;
            try {
                                        /*
                                         * String strUTF8 = URLDecoder .decode(
										 * "http://api.map.baidu.com/place/v2/suggestion?query=天安门&region=北京市&city_limit=true&output=json&mcode=AB:40:A9:F3:B8:9C:3E:3D:49:94:50:DA:FC:27:7A:6B:55:49:C1:34;com.strong.yjwl&ak=McWV4f8UlALyHDfeRm1yGB0yCWpapig7"
										 * , "UTF-8");
										 */
                URL url = new URL(
                        "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=" + nearY + "," + nearX + "&output=xml&pois=1&ak=xe9uU8t5g4AdDpO3bWgUXhn8gUq3scRQ"
                                + "&output=json&mcode=72:8B:87:FF:4A:22:CF:39:BC:47:0B:C6:A6:55:0F:A1:6B:7E:A8:CE;com.strong.yujiaapp"

                );

                connection = (HttpURLConnection) url
                        .openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(8000);
                connection.setReadTimeout(8000);
                InputStream in = connection
                        .getInputStream(); // 下面对获取到的输入流进行读取
                BufferedReader bufr = new BufferedReader(
                        new InputStreamReader(in));
                StringBuilder response = new StringBuilder();
                String line = null;
                while ((line = bufr.readLine()) != null) {
                    response.append(line);
                }
                String jsonStr = response.toString();
                String addrJson = jsonStr.substring(29, jsonStr.length() - 1);
                Gson gson = new Gson();
                BaiduAuto baiduauto = gson.fromJson(
                        addrJson, BaiduAuto.class);
                Bundle data = new Bundle();
                detailName = baiduauto.result.formatted_address.toString().trim();
                data.putString("detailAddress",detailName );
                Message msg = new Message();
                msg.setData(data);
                msg.what = 7;
                handler.sendMessage(msg);
                // 解析

										/*
                                         * Message message = new Message();
										 * message.what = 6; //
										 * 将服务器返回的数据存放到Message中 message.obj =
										 * response.toString();
										 * handler.sendMessage(message);
										 */
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }
    };
    /**
     * 定位SDK监听函数
     *//*
    public class myListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null)
                return;
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                mBaiduMap.animateMapStatus(u);
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }

    }*/
}