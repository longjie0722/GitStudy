package com.java1824.happlyzwg.cards;

//牌的类
public class PlayCards implements Comparable<PlayCards>{
    private static final String [] COLOR = {"红","黑","方","梅"};
    private static final String [] COMPARE = {
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "J",
            "Q",
            "K",
            "A",
            "2"
    };

    private int flower_color;  //花色 0~3
    private String show_value; //显示值
    private int compare_value; //比较值 0~12

    public PlayCards(int flower_color, int compare_value) {
        this.flower_color = flower_color;
        this.compare_value = compare_value;
        this.show_value = COLOR[flower_color] + COMPARE[compare_value];
    }

    public int getCompare_value() {
        return compare_value;
    }

    public void setCompare_value(int compare_value) {
        this.compare_value = compare_value;
    }

    public String show(){
        return show_value;
    }

    @Override
    public String toString() {
        return this.show();
    }

    //给牌定义一个排序的方法  Collections.sort(list); 里面的list是PlayCards类型继承Comparable
    public int compareTo(PlayCards pc){
        if(this == null){
            return 0;
        }
        if(this.getCompare_value() == pc.getCompare_value()){
            return pc.flower_color - this.flower_color;
        }
        return this.compare_value - pc.compare_value;
    }
}
