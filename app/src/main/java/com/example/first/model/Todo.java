package com.example.first.model;

import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Todo implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("todo")
    @Expose
    private String todo;
    @SerializedName("completed")
    @Expose
    private Boolean completed;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    public final static Creator<Todo> CREATOR = new Creator<Todo>() {


        public Todo createFromParcel(android.os.Parcel in) {
            return new Todo(in);
        }

        public Todo[] newArray(int size) {
            return (new Todo[size]);
        }

    };

    @SuppressWarnings({
            "unchecked"
    })
    protected Todo(android.os.Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.todo = ((String) in.readValue((String.class.getClassLoader())));
        this.completed = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.userId = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Todo() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", todo='" + todo + '\'' +
                ", completed=" + completed +
                ", userId=" + userId +
                '}';
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(todo);
        dest.writeValue(completed);
        dest.writeValue(userId);
    }

    public int describeContents() {
        return 0;
    }

}