package com.apps.gill.shopkeeperapp.models.GetCustomer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gill on 12-02-2016.
 */
public class CustomerPicURL {
    @SerializedName("thumbnail")
    @Expose
    private Object thumbnail;
    @SerializedName("original")
    @Expose
    private Object original;

    /**
     *
     * @return
     * The thumbnail
     */
    public Object getThumbnail() {
        return thumbnail;
    }

    /**
     *
     * @param thumbnail
     * The thumbnail
     */
    public void setThumbnail(Object thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     *
     * @return
     * The original
     */
    public Object getOriginal() {
        return original;
    }

    /**
     *
     * @param original
     * The original
     */
    public void setOriginal(Object original) {
        this.original = original;
    }

}


