package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naveen on 26/2/15.
 */
public class StyleInteriorHotspot {

    @SerializedName("id")
    String hotspotId;

    @SerializedName("header")
    String hotspotheader;

    @SerializedName("description")
    String hotspotDescription;

    @SerializedName("X")
    String xValue;

    @SerializedName("Y")
    String yValue;

    @SerializedName("hotspot_image_file")
    String hotspotImage;

    public String getHotspotId() {
        return hotspotId;
    }

    public void setHotspotId(String hotspotId) {
        this.hotspotId = hotspotId;
    }

    public String getHotspotheader() {
        return hotspotheader;
    }

    public void setHotspotheader(String hotspotheader) {
        this.hotspotheader = hotspotheader;
    }

    public String getHotspotDescription() {
        return hotspotDescription;
    }

    public void setHotspotDescription(String hotspotDescription) {
        this.hotspotDescription = hotspotDescription;
    }

    public String getxValue() {
        return xValue;
    }

    public void setxValue(String xValue) {
        this.xValue = xValue;
    }

    public String getyValue() {
        return yValue;
    }

    public void setyValue(String yValue) {
        this.yValue = yValue;
    }

    public String getHotspotImage() {
        return hotspotImage;
    }

    public void setHotspotImage(String hotspotImage) {
        this.hotspotImage = hotspotImage;
    }
}
