package com.cml.newframe.activeandroid;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by cmlBeliever on 2016/7/29.
 */
@Table(name = "t_user")
public class Category extends Model {
    @Column(notNull = true)
    public String userName;
    @Column(notNull = false)
    public String nickName;
    @Column
    public int age;

    @Override
    public String toString() {
        return "Category{" +
                "userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", age=" + age +
                '}';
    }

    public static List<Category> getUsers() {
        return new Select().from(Category.class).orderBy("id desc").execute();
    }

    public List<Item> getModel() {
        return getMany(Item.class, "cccc");
    }
}
