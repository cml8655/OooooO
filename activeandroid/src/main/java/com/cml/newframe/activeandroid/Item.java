package com.cml.newframe.activeandroid;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by cmlBeliever on 2016/7/29.
 */
@Table(name = "Items")
public class Item extends Model {
    @Column(notNull = true)
    public String coupoName;
    @Column(notNull = true)
    public int count;

    @Column(name = "cccc")
    public Category c;

    public static List<Item> getItems() {
        return new Select().from(Item.class).execute();
    }

    @Override
    public String toString() {
        return "Item{" +
                "coupoName='" + coupoName + '\'' +
                ", count=" + count +
                '}';
    }
}
