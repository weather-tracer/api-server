package com.weathertracer.v1.util;

import com.weathertracer.v1.vo.Gps;
import com.weathertracer.v1.vo.Location;

/*
 * 기상청에서 제공한 C 소스 코드를 Java 소스 코드로 변환
 */
public class LambertProjection {

    private static class LamcParameter {
        double Re;
        double grid;
        double slat1, slat2, olon, olat, xo, yo;
        double first;

        // Projection variables
        double re, olon_, olat_, sn, sf, ro;

        LamcParameter() {
            Re = 6371.00877; // Earth radius in kilometers
            grid = 5.0; // Grid spacing in kilometers
            slat1 = 30.0; // Standard latitude 1
            slat2 = 60.0; // Standard latitude 2
            olon = 126.0; // Origin longitude
            olat = 38.0; // Origin latitude
            xo = 210 / grid; // Origin X
            yo = 675 / grid; // Origin Y
            first = 0; // First run flag
        }
    }

    private static final double PI = Math.PI;
    private static final double DEGRAD = PI / 180.0;
    private static final double RADDEG = 180.0 / PI;

    public static Location ConvertGpsToLocation(Gps gps) {
        LamcParameter map = new LamcParameter();
        double[] result = lamcproj(gps.lon(), gps.lat(), 0, map);
        return new Location((int) Math.floor(result[0] + 1.5), (int) Math.floor(result[1] + 1.5));
    }

    public static Gps ConvertLocationToGps(Location location) {
        LamcParameter map = new LamcParameter();
        double[] result = lamcproj(location.x() - 1, location.y() - 1, 1, map);
        return new Gps(result[1], result[0]);
    }

    private static double[] lamcproj(double lon, double lat, int code, LamcParameter map) {
        double[] result = new double[2];
        double theta, ra;

        if (map.first == 0) {
            map.re = map.Re / map.grid;
            map.slat1 *= DEGRAD;
            map.slat2 *= DEGRAD;
            map.olon_ = map.olon * DEGRAD;
            map.olat_ = map.olat * DEGRAD;

            map.sn = Math.tan(PI * 0.25 + map.slat2 * 0.5) / Math.tan(PI * 0.25 + map.slat1 * 0.5);
            map.sn = Math.log(Math.cos(map.slat1) / Math.cos(map.slat2)) / Math.log(map.sn);
            map.sf = Math.tan(PI * 0.25 + map.slat1 * 0.5);
            map.sf = Math.pow(map.sf, map.sn) * Math.cos(map.slat1) / map.sn;
            map.ro = Math.tan(PI * 0.25 + map.olat_ * 0.5);
            map.ro = map.re * map.sf / Math.pow(map.ro, map.sn);
            map.first = 1;
        }

        if (code == 0) {
            ra = Math.tan(PI * 0.25 + lat * DEGRAD * 0.5);
            ra = map.re * map.sf / Math.pow(ra, map.sn);
            theta = lon * DEGRAD - map.olon_;
            if (theta > PI)
                theta -= 2.0 * PI;
            if (theta < -PI)
                theta += 2.0 * PI;
            theta *= map.sn;
            result[0] = ra * Math.sin(theta) + map.xo;
            result[1] = map.ro - ra * Math.cos(theta) + map.yo;
        } else {
            double xn = lon - map.xo;
            double yn = map.ro - lat + map.yo;
            ra = Math.sqrt(xn * xn + yn * yn);
            if (map.sn < 0.0)
                ra = -ra;
            double no = Math.atan2(xn, yn);
            if (no < 0)
                no += 2 * PI;
            no = no / map.sn;
            ra = Math.pow((ra / (map.re * map.sf)), (1.0 / map.sn));
            lat = 2.0 * Math.atan(ra) - PI * 0.5;
            lon = no + map.olon_;
            result[0] = lon * RADDEG;
            result[1] = lat * RADDEG;
        }

        return result;
    }

    public static void main(String[] args) {
        double lat = 37.29518064456937;
        double lon = 127.04065801381438;

        Gps gps1 = new Gps(lat, lon);
        Location loc1 = ConvertGpsToLocation(gps1);
        System.out.println("GPS (" + gps1.lat() + ", " + gps1.lon() + ") to Location: " + loc1);

        Location loc2 = new Location(loc1.x(), loc1.y());
        Gps gps2 = ConvertLocationToGps(loc2);
        System.out.println("Location (" + loc2.x() + ", " + loc2.y() + ") to GPS: " + gps2);
    }
}