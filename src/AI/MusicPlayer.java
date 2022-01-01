package AI;

import Mode.DT;

public class MusicPlayer extends Thread {
    private String songName = "";
    private String nowSong = "";
    private PlayMusic T1;

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                switch (DT.musicFace) {
                    case none:
                        songName = "";
                        break;
                    case happy:
                        songName = "music/happy.mp3";
                        break;
                    default:
                        songName = "music/angry.mp3";
                }
                if (!nowSong.equals(songName)) {
                    //ªª∏Ë
                    System.out.println("\n«–∏Ë£∫" + songName);
                    if (!nowSong.equals(""))
                        T1.cancle();
                    nowSong = songName;
                    if (!songName.equals("")){
                        T1 = new PlayMusic(songName);
                        T1.start();
                    }
                } else {
                    //≤ª”√ªª
                }
                Thread.sleep(50);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
