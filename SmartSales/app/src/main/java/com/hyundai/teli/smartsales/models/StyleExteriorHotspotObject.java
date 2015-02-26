package com.hyundai.teli.smartsales.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naveen on 26/2/15.
 */
public class StyleExteriorHotspotObject {

    @SerializedName("hotspotdetails_id")
    String hotspotId;

    @SerializedName("header")
    String header;

    @SerializedName("description")
    String description;

    @SerializedName("hotspotdetails_image_file")
    String hotspotImageFile;

    public String getHotspotId() {
        return hotspotId;
    }

    public void setHotspotId(String hotspotId) {
        this.hotspotId = hotspotId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHotspotImageFile() {
        return hotspotImageFile;
    }

    public void setHotspotImageFile(String hotspotImageFile) {
        this.hotspotImageFile = hotspotImageFile;
    }
}
