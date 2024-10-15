package com.weathertracer.v1.util;

/*
 * 기상청 제공 소스 코드
 */
public class LamcProjection {
    private static final double PI = Math.PI;
    private static final double DEGRAD = PI / 180.0;
    private static final double RADDEG = 180.0 / PI;
    
    private static double re, olon, olat, sn, sf, ro;

    public static class LamcParameter {
        public double Re;
        public double grid;
        public double slat1;
        public double slat2;
        public double olon;
        public double olat;
        public double xo;
        public double yo;
        public int first;
    }

    public static class Result {
        public double x;
        public double y;
        public double lon;
        public double lat;
    }

    public static Result lamcproj(double lon, double lat, int code, LamcParameter map) {
        Result result = new Result();

        if (map.first == 0) {
            re = map.Re / map.grid;
            double slat1 = map.slat1 * DEGRAD;
            double slat2 = map.slat2 * DEGRAD;
            olon = map.olon * DEGRAD;
            olat = map.olat * DEGRAD;
            
            sn = Math.tan(PI * 0.25 + slat2 * 0.5) / Math.tan(PI * 0.25 + slat1 * 0.5);
            sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
            sf = Math.tan(PI * 0.25 + slat1 * 0.5);
            sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
            ro = Math.tan(PI * 0.25 + olat * 0.5);
            ro = re * sf / Math.pow(ro, sn);
            map.first = 1;
        }

        if (code == 0) {
            double ra = Math.tan(PI * 0.25 + lat * DEGRAD * 0.5);
            ra = re * sf / Math.pow(ra, sn);
            double theta = lon * DEGRAD - olon;
            if (theta > PI) theta -= 2.0 * PI;
            if (theta < -PI) theta += 2.0 * PI;
            theta *= sn;
            result.x = ra * Math.sin(theta) + map.xo;
            result.y = ro - ra * Math.cos(theta) + map.yo;
        } else {
            double xn = lon - map.xo;
            double yn = ro - lat + map.yo;
            double ra = Math.sqrt(xn * xn + yn * yn);
            if (sn < 0.0) ra = -ra;
            double alat = Math.pow((re * sf / ra), (1.0 / sn));
            alat = 2.0 * Math.atan(alat) - PI * 0.5;
            double theta;
            if (Math.abs(xn) <= 0.0) {
                theta = 0.0;
            } else {
                if (Math.abs(yn) <= 0.0) {
                    theta = PI * 0.5;
                    if (xn < 0.0) theta = -theta;
                } else {
                    theta = Math.atan2(xn, yn);
                }
            }
            double alon = theta / sn + olon;
            result.lat = alat * RADDEG;
            result.lon = alon * RADDEG;
        }

        return result;
    }
}