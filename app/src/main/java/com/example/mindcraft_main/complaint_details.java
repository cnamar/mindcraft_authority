package com.example.mindcraft_main;

public class complaint_details {
    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getLandmark() {
        return Landmark;
    }

    public void setLandmark(String landmark) {
        Landmark = landmark;
    }

    public String getPanchayat() {
        return Panchayat;
    }

    public void setPanchayat(String panchayat) {
        Panchayat = panchayat;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getWard_no() {
        return Ward_no;
    }

    public void setWard_no(String ward_no) {
        Ward_no = ward_no;
    }

    public String getProblem_description() {
        return problem_description;
    }

    public void setProblem_description(String problem_description) {
        this.problem_description = problem_description;
    }

    public String getProblem_type() {
        return problem_type;
    }

    public void setProblem_type(String problem_type) {
        this.problem_type = problem_type;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    int Status;
    String District;
    String Landmark;
    String Panchayat;
    String Time;
    String Ward_no;
    String problem_description;
    String problem_type;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    String Date;
    String UID;
    private complaint_details(){};
    private complaint_details(String District,String Landmark,String Panchayat,String Time,String Ward_no,String problem_description,String problem_type,int Status,String Date,String UID)
    {
        this.District=District;
        this.Landmark=Landmark;
        this.Panchayat=Panchayat;
        this.Time=Time;
        this.Ward_no=Ward_no;
        this.problem_description=problem_description;
        this.problem_type=problem_type;
        this.Status=Status;
        this.Date=Date;
        this.UID=UID;


    }

}
