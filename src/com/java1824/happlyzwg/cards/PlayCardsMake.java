package com.java1824.happlyzwg.cards;

import com.java1824.happlyzwg.cards.PlayCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//牌的工具类
public class PlayCardsMake {
    //一副牌
    private List<PlayCards>cardsList;

    public PlayCardsMake() {
        this.cardsList = new ArrayList<>();
        this.makeCards();
    }

    //构建一副牌
    public void makeCards(){
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                    cardsList.add(new PlayCards(j, i));
            }
        }
    }

    //发牌
    public List<List<PlayCards>> fourCards(){
        List<List<PlayCards>> list = new ArrayList<>();

        List<PlayCards>one = new ArrayList<>();
        List<PlayCards>two = new ArrayList<>();
        List<PlayCards>three = new ArrayList<>();

        List<PlayCards>last = new ArrayList<>();

        for(int i = 0;i < 51;i++){
           List<PlayCards>listP = null;
           if(i % 3 == 0){
               listP = one;
           }else if(i % 3 == 1){
               listP = two;
           }else {
               listP = three;
           }
           listP.add(cardsList.get(i));
        }
        last.add(cardsList.get(51));

        list.add(one);
        list.add(two);
        list.add(three);
        list.add(last);

        return list;
    }

    //洗牌
    public void shuffle(){
        Collections.shuffle(this.cardsList);
    }

    //排序
    public static void sort(List<PlayCards>list){
        Collections.sort(list);
    }

    //抽牌
    public static List<PlayCards> takeCards(List<PlayCards>cardsList,int [] idx){
        List<PlayCards>cards = new ArrayList<>();
        for (int i:idx) {
            PlayCards pc = cardsList.get(i);
            cards.add(pc);
        }
        return cards;
    }

    //删除抽掉的牌
    public static void removeCards(List<PlayCards>cardsList,List<PlayCards>listCards){
        cardsList.removeAll(listCards);
    }

    public List<PlayCards> getCardsList() {
        return cardsList;
    }

    public void setCardsList(List<PlayCards> cardsList) {
        this.cardsList = cardsList;
    }
}
