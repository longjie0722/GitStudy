package com.java1824.happlyzwg.server;

public class PlayCardsCondition {
    //准备
    public static final String CARDS_REDY = "#REDY";
    //抽牌
    public static final String CARDS_TAKE = "#TAKE";

    public static boolean conditionClient(String msg){
        if(msg.equals(CARDS_REDY)
                || msg.equals(CARDS_TAKE)){
            return true;
        }
        return false;
    }
}
