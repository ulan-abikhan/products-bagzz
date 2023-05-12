package com.example.first.repository;

import java.util.List;
import android.os.Parcelable;

import com.example.first.model.Todo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodoRepository
{

    @SerializedName("todos")
    @Expose
    private List<Todo> todos;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("skip")
    @Expose
    private Integer skip;
    @SerializedName("limit")
    @Expose
    private Integer limit;

    @SuppressWarnings({
            "unchecked"
    })
    public TodoRepository(android.os.Parcel in) {
        in.readList(this.todos, (Todo.class.getClassLoader()));
        this.total = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.skip = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.limit = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public TodoRepository() {
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }


}
