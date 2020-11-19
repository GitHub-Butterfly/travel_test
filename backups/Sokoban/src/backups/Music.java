package backups;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

public class Music {
    AudioClip clip = null;

    public AudioClip getAudioClip() {
        return this.clip;
    }

    public void setAudioClip(AudioClip clip) {
        this.clip = clip;
    }

    public void play() {//播放
        if (getAudioClip() != null) {
            getAudioClip().play();
        }
    }

    public void loop() {//循环
        if (getAudioClip() != null) {
            getAudioClip().loop();
        }
    }

    public void stop() {//停止
        if (getAudioClip() != null) {
            getAudioClip().stop();
        }
    }

}
