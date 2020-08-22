package com.example.mindcraft_main;

public class detection_details {
    public String getWard_no() {
        return Ward_no;
    }

    public void setWard_no(String ward_no) {
        Ward_no = ward_no;
    }

    public String getDetected_classes() {
        return Detected_classes;
    }

    public void setDetected_classes(String detected_classes) {
        Detected_classes = detected_classes;
    }

    public String getTime_stamp() {
        return Time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        Time_stamp = time_stamp;
    }

    public String getNumber_plate() {
        return Number_plate;
    }

    public void setNumber_plate(String number_plate) {
        Number_plate = number_plate;
    }

    String Ward_no,Detected_classes,Time_stamp,Number_plate;
    private detection_details(){};
    private detection_details(String Ward_no,String Detected_classes,String Time_stamp,String Number_plate)
    {
        this.Ward_no=Ward_no;
        this.Detected_classes=Detected_classes;
        this.Time_stamp=Time_stamp;
        this.Number_plate=Number_plate;
    }

}
