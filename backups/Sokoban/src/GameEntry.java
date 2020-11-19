import com.nsu.game.control.GameController;
import backups.Music;

import javax.sound.sampled.*;
import java.applet.Applet;
import java.io.File;
import java.net.MalformedURLException;

public class GameEntry {



    public static void main(String[] args) {
        /*Music mac = new Music();
        try {
            mac.setAudioClip(Applet.newAudioClip
                    ((new File("D:\\intelliJ IDEA\\Java\\test1\\src\\imgs\\yedegangqinqu.wav")).toURL()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        mac.loop();//循环播放
        //mac.stop();
        //mac.play();*/


        GameController game = new GameController();
        new Thread(()->{while(true) {
            playMusic();} //while中的true可换成参数来控制音乐的停止播放
        }).start();// Lambda表达式
        game.start();
        game.show();

    }

        public static void playMusic() {// 背景音乐播放
            try {

                //src/game/game_music.wav
                AudioInputStream ais = AudioSystem.getAudioInputStream(new File("D:\\intelliJ IDEA\\Java\\test1\\src\\music\\yedegangqinqu.wav"));
                //绝对路径
                AudioFormat aif = ais.getFormat();
                final SourceDataLine sdl;
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
                sdl = (SourceDataLine) AudioSystem.getLine(info);
                sdl.open(aif);
                sdl.start();
                FloatControl fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
                // value可以用来设置音量，从0-2.0
                double value = 2;
                float dB = (float) (Math.log(value == 0.0 ? 0.0001 : value) / Math.log(10.0) * 20.0);
                fc.setValue(dB);
                int nByte = 0;
                int SIZE = 1024 * 64*64;
                byte[] buffer = new byte[SIZE];
                while (nByte != -1) {
                    nByte = ais.read(buffer, 0, SIZE);
                    sdl.write(buffer, 0, nByte);
                }
                sdl.stop();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

