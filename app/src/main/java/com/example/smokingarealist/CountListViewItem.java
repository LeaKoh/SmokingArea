package com.example.smokingarealist;

import java.util.ArrayList;
import java.util.Date;

public class CountListViewItem {
    private Date write_date;
    private Integer smoke_count;

    public Date getWrite_date(){
        return write_date;
    }

    public void setWrite_date(Date write_date){
        this.write_date = write_date;
    }

    public Integer getSmoke_count(){
        return smoke_count;
    }
    public void setSmoke_count(Integer smoke_count){
        this.smoke_count = smoke_count;
    }

    public String getString()
    {
        String date = write_date.toString();
        String count = smoke_count.toString();
        return "{" + "date: '" + date + "'," + "count:" + count + "}";
    }

    public CountListViewItem(Date write_date, Integer smoke_count){
        this.write_date = write_date;
        this.smoke_count = smoke_count;
    }

}
