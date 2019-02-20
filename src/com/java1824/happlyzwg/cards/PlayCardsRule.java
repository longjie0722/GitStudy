package com.java1824.happlyzwg.cards;

import com.java1824.happlyzwg.cards.PlayCards;
import com.java1824.happlyzwg.cards.PlayCardsMake;

import java.util.ArrayList;
import java.util.List;

//规则
public class PlayCardsRule {

    //将手中的牌里面的对子删掉
    public static void pairsRemove(List<PlayCards> listCards){
        PlayCardsMake.sort(listCards);
        int min = 0;
        for(int i = 1;i < listCards.size(); i++) {
            List<PlayCards> list = new ArrayList<>();
            PlayCards pc1 = listCards.get(min);
            PlayCards pc2 = listCards.get(i);
            if (pc1.getCompare_value() == pc2.getCompare_value()) {
                list.add(pc1);
                list.add(pc2);
                PlayCardsMake.sort(listCards);
                min = 0;
                i = 0;
            }else {
                min += 1;
            }
            if(min > listCards.size()){
                break;
            }
            PlayCardsMake.removeCards(listCards, list);
        }
    }


    //将抽出的牌与手中的牌比较是对子就消掉
    public static void takeRemoveCards(List<PlayCards> listCards,int [] idx){
        List<PlayCards> cards = PlayCardsMake.takeCards(listCards,new int[]{0});
        if(cards.size() != 1 || idx.length != 1){
            return;
        }
        for(int i = 0;i<listCards.size();i++){
            if(cards.get(0).getCompare_value() == listCards.get(i).getCompare_value()){
                PlayCardsMake.removeCards(listCards,cards);
            }

        }
    }

}
