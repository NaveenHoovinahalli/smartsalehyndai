package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naveen on 26/2/15.
 */
public class StyleExteriorHotspot {

    @SerializedName("exterior_hotspot_id")
    String hotspotId;

    @SerializedName("style_exterior_hotspotdetails")
    StyleExteriorHotspotObject styleExteriorHotspotObject= new StyleExteriorHotspotObject();

    @SerializedName("X")
    String xValue;

    @SerializedName("Y")
    String yValue;

    public String getHotspotId() {
        return hotspotId;
    }

    public void setHotspotId(String hotspotId) {
        this.hotspotId = hotspotId;
    }

    public StyleExteriorHotspotObject getStyleExteriorHotspotObject() {
        return styleExteriorHotspotObject;
    }

    public void setStyleExteriorHotspotObject(StyleExteriorHotspotObject styleExteriorHotspotObject) {
        this.styleExteriorHotspotObject = styleExteriorHotspotObject;
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
}
