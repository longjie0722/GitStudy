package com.java1824.happlyzwg.server;

import com.java1824.happlyzwg.cards.PlayCards;
import com.java1824.happlyzwg.cards.PlayCardsMake;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class PlayCardsClient implements Runnable{
    //玩家未知状态
    public static final int UNKNOWN = 0;
    //玩家准备状态
    public static final int REDY = 1;
    //玩家抽牌状态
    public static final int TAKE = 2;

    private PlayCardsServer server;

    //客户端的套接字
    private Socket socketClient;

    //客户手中的牌
    List<PlayCards> cardsList;

    //给玩家定义一个名字
    private String name;

    //玩家现在的状态
    private int sturt = UNKNOWN;

    public PlayCardsClient(PlayCardsServer server,Socket socketClient) {
        this.socketClient = socketClient;
        this.server = server;
        new Thread(this).start();
    }

    public void sendMsg(String msg){
        try {
            OutputStream outputStream = socketClient.getOutputStream();
            outputStream.write(msg.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socketClient.getInputStream();
            while(true){
                byte [] bytes = new byte[1024];
                int read = inputStream.read(bytes);
                if(read < 0){
                    break;
                }
                String str = new String(bytes,0,read);
                server.condition(PlayCardsClient.this,str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //展示牌给自己看
    public void lookCards(){
        if(this.cardsList == null){
            return;
        }
        PlayCardsMake.sort(this.cardsList);
        String msg = "";
        for (int i = 0; i < this.cardsList.size(); i++) {
            PlayCards playCards = cardsList.get(i);
            msg += playCards.show()+"["+i+"]\t";
        }
        sendMsg(msg);
    }

    public List<PlayCards> getCardsList() {
        return cardsList;
    }

    public void setCardsList(List<PlayCards> cardsList) {
        this.cardsList = cardsList;
    }

    public int getSturt() {
        return sturt;
    }

    public void setSturt(int sturt) {
        this.sturt = sturt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 获取ip地址
    public String getIP() {
        if (this.socketClient != null) {
            return socketClient.getInetAddress().getHostAddress();
        }
        return "";
    }
}
