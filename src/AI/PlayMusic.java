package AI;

import jmp123.demo.MiniPlayer;
import jmp123.output.*;
import jmp123.decoder.*;

import java.io.IOException;

public class PlayMusic extends Thread {
    private String threadName;
    private MiniPlayer player;

    /**
     * 需要传入音乐名字
     *
     * @param name
     */
    public PlayMusic(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    public void run() {
        System.out.println("Running " + threadName);

//        while (!Thread.currentThread().isInterrupted()) {
        try {
            player = new MiniPlayer(new Audio());
            player.open(threadName);
            player.run();
        } catch (Exception e) {
            e.printStackTrace();
//                break;
        }
//        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    public void cancle() {
        player.closeMusic();
//        interrupt();
    }

//    public void rePlayMusic(int musicId) throws IOException{
//        switch (musicId){
//            case 0:
//                songName = "gz.mp3";
//                break;
//            case 1:
//                songName = "2.mp3";
//                break;
//        }
//        player.open(songName);
////        player.pause();
//
//        interrupted();
//
//    }
}