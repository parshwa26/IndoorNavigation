package io.mapwize.example;

public class CenterNames {
    private String place_name;
    private String floor;
    private int imgBackCol;
    private int pic;
    private Boolean isEnable;
    private String area;


    public CenterNames(String place_name, int imgBackCol, Boolean isEnable, String floor) {
        this.place_name = place_name;
        this.floor = floor;
        this.imgBackCol = imgBackCol;
        this.isEnable = isEnable;
    }
    public CenterNames(String place_name, int imgBackCol, Boolean isEnable, String floor, String area) {
        this.place_name = place_name;
        this.floor = floor;
        this.imgBackCol = imgBackCol;
        this.isEnable = isEnable;
        this.area = area;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public int getPic() {
        return pic;
    }

    public CenterNames(String place_name, int imgBackCol, Boolean isEnable, String floor, String area, int pic) {
        this.place_name = place_name;
        this.floor = floor;
        this.imgBackCol = imgBackCol;
        this.isEnable = isEnable;
        this.area = area;
        this.pic = pic;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setImgBackCol(int imgBackCol) {
        this.imgBackCol = imgBackCol;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

    public String getPlace_name() {
        return place_name;
    }

    public String getFloor() {
        return floor;
    }

    public int getImgBackCol() {
        return imgBackCol;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }
}