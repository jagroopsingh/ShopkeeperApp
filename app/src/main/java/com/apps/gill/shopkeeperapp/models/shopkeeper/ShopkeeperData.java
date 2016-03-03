package com.apps.gill.shopkeeperapp.models.shopkeeper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gill on 11-02-2016.
 */

public class ShopkeeperData {
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<ShopkeeperDetails> data = new ArrayList<ShopkeeperDetails>();

    /**
     *
     * @return
     * The statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     *
     * @param statusCode
     * The statusCode
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The data
     */
    public List<ShopkeeperDetails> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<ShopkeeperDetails> data) {
        this.data = data;
    }

}
