package com.java1824.happlyzwg.server;

import com.java1824.happlyzwg.cards.PlayCards;
import com.java1824.happlyzwg.cards.PlayCardsMake;
import com.java1824.happlyzwg.cards.PlayCardsRule;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayCardsServer{
    //服务端端口号
    private static final int PORT = 9999;
    //服务端套接字
    private ServerSocket socket;

    //装三个玩家
    List<PlayCardsClient> list = null;

    //用户手中的牌
    private PlayCardsMake pcm;

    //记录玩家抽牌的顺序
    private int countCards;
    //记录玩家
    private int playerCards;

//    //发牌前抽出的一张牌
//    private PlayCardsClient newList;

    public PlayCardsServer() {
        this.list = new ArrayList<>();
    }

    //开启服务
    public void startSocket(){
        try {
            socket = new ServerSocket(PORT);
            this.accept();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //监听用户连接
    public void accept(){
        if(socket != null) {
            System.out.println("等待用户连接....");
            while(true) {
                try {
                    Socket accept = socket.accept();
                    PlayCardsClient pcc = new PlayCardsClient(PlayCardsServer.this,accept);
                    list.add(pcc);
                    sendMessage("有"+list.size()+"用户连接上服务端了");
                    System.out.println("有"+list.size()+"用户连接上服务端了");
                    if(list.size() == 3){
                        sendMessage("可以开始游戏了,请准备(#REDY)");
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //判断用户发送过来的消息的正确性
    public void condition(PlayCardsClient pcc,String msg){
        System.out.println(msg);
        if(!PlayCardsCondition.conditionClient(msg)){
            return;
        }
        if(msg.equals(PlayCardsCondition.CARDS_REDY)){
            pcc.setSturt(PlayCardsClient.REDY);
            conditionCads();//todo
        }
    }
    //判断用户状态
    public void conditionCads(){
        String msg = "";
        for (PlayCardsClient pcc:list) {
            if(pcc.getSturt() != PlayCardsClient.REDY){
                msg += pcc.getIP()+"    ";
            }
        }
        if(msg.length() != 0){
            sendMessage("该"+msg+"用户没有准备");
            return;
        }
        //三个玩家准备好之后,发牌
        sendCards();   //todo

        //
        randomCards();

    }

    //发牌
    public void sendCards(){
        pcm = new PlayCardsMake();
        pcm.shuffle();
        List<List<PlayCards>> lists = pcm.fourCards();
        for (int i = 0; i < lists.size(); i++) {
            //将牌发给玩家
            list.get(i).setCardsList(lists.get(i));
            //将牌中的对子删除
            PlayCardsRule.pairsRemove(list.get(i).getCardsList());
            pcm.shuffle();
            //将牌展示给玩家
            list.get(i).lookCards();
        }
//        newList = list.get(1);
    }

    //给客户随机一个名字,再将其中一个名字定为第一个抽牌者
    public void randomCards(){
        Random ran = new Random();
        int a = ran.nextInt(3);
        for (int i = 0; i < list.size(); i++) {
            PlayCardsClient pcc = list.get(i);
            pcc.setName("你是玩家"+i);
            if(a == i){
               sendMessage("请先抽牌(#TAKE)");
            }
        }
    }


    //向所有客户发送信息
    public void sendMessage(String msg){
        for (PlayCardsClient pcc:list) {
            pcc.sendMsg(msg);
        }
    }

}
