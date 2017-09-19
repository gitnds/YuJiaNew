package com.strong.yujiaapp.utils;


public class PointToDistance {

    public static void main(String[] args) {
        getDistanceFromTwoPoints(23.5539530, 114.8903920, 23.5554550, 114.8868890);

       /* distanceOfTwoPoints(23.5539530, 114.8903920, 23.5554550, 114.8868890);  */
    }

    private static final Double PI = Math.PI;

    private static final Double PK = 180 / PI;

    /**
     * @Description: 第一种方法
     * @param lat_a
     * @param lng_a
     * @param lat_b
     * @param lng_b
     * @param @return
     * @return double
     * @author 钟志铖
     * @date 2014-9-7 上午10:11:35
     */
    public static double getDistanceFromTwoPoints(double lat_a, double lng_a, double lat_b, double lng_b) {
        double t1 = Math.cos(lat_a / PK) * Math.cos(lng_a / PK) * Math.cos(lat_b / PK) * Math.cos(lng_b / PK);
        double t2 = Math.cos(lat_a / PK) * Math.sin(lng_a / PK) * Math.cos(lat_b / PK) * Math.sin(lng_b / PK);
        double t3 = Math.sin(lat_a / PK) * Math.sin(lat_b / PK);

        double tt = Math.acos(t1 + t2 + t3);

        System.out.println("两点间的距离：" + 6366000 * tt + " 米");
        return 6366000 * tt;
    }
}