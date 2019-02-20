package com.java1824.happlyzwg;

import com.java1824.happlyzwg.cards.PlayCards;
import com.java1824.happlyzwg.cards.PlayCardsMake;
import com.java1824.happlyzwg.cards.PlayCardsRule;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        PlayCardsMake cardsMake = new PlayCardsMake();

        System.out.println(cardsMake.getCardsList().size());
        cardsMake.shuffle();
        System.out.println(cardsMake.getCardsList());

        List<List<PlayCards>>lists = cardsMake.fourCards();
//        for (List<PlayCards>list:lists) {
//            cardsMake.sort(list);
//            System.out.println(list);
//        }

        //取出一组牌
        List<PlayCards> cards = lists.get(0);
        //将牌排序
        cardsMake.sort(cards);
        System.out.println("抽牌前的牌:"+cards);
//        System.out.println(cards.get(0).getCompare_value());
//        System.out.println(cards.get(1).getCompare_value());
        PlayCardsRule.pairsRemove(cards);
        System.out.println("删除牌中的对子之后的牌:"+cards);

//        //从这一组牌中抽出一张
//        List<PlayCards> cards1 = cardsMake.takeCards(cards, new int[]{0});
//        //将抽掉的牌删除掉
//        cardsMake.removeCards(cards,cards1);
//        System.out.println("抽牌后的牌:"+cards);
//        System.out.println("抽出的一张牌是:"+cards1);

        }
}
