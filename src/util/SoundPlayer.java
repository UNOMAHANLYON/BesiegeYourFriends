package util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SoundPlayer {
    private LoopEvent soundLoop;
    private Boolean isSoundPlaying;

    private Boolean isDamageSound;

    private byte[] backgroundBytes;

    private byte[]  rollingCartBytes;

    private byte[]  catapultLaunchBytes;

    private byte[] cowBytes;

    private byte[] damageBytes;

    public SoundPlayer(){
        isSoundPlaying = false;
        isDamageSound = false;

        InputStream in = ResourceLoader.load(SoundPlayer.class,
                "./res/assets/sounds/cart_rolling.wav", "cr");
        rollingCartBytes = readBytes(in);

        in = ResourceLoader.load(SoundPlayer.class,
                "./res/assets/sounds/catapult_launch.wav", "cl");
        catapultLaunchBytes = readBytes(in);

        in = ResourceLoader.load(SoundPlayer.class,
                "./res/assets/sounds/destroyed.wav", "cl");
        damageBytes = readBytes(in);

        in = ResourceLoader.load(SoundPlayer.class,
                "./res/assets/sounds/cow_moo.wav", "cl");
        cowBytes = readBytes(in);

        in = ResourceLoader.load(SoundPlayer.class,
                "./res/assets/sounds/bg.wav", "bg");
        backgroundBytes = readBytes(in);
        loadFile(backgroundBytes);


    }

    public void PlaySoundLoop(){
        soundLoop.fire();
        if(isDamageSound)
            soundLoop.onAudioFinished();
        isSoundPlaying = true;
    }

    public void StopSoundLoop(){
        if(isSoundPlaying == true && isDamageSound == false){
            soundLoop.done();
            isSoundPlaying = false;
        }
    }

    public void PlayDamage(){
        isDamageSound = true;
        this.loadFile(damageBytes);
        this.PlaySoundLoop();
    }

    public void PlayCowMoo(){
        this.loadFile(cowBytes);
        this.PlaySoundLoop();
    }

    public void PlayRollingCart(){
        this.loadFile(rollingCartBytes);
        this.PlaySoundLoop();
    }

    public void PlayCatapultLaunch(){
        this.loadFile(catapultLaunchBytes);
        this.PlaySoundLoop();
    }

    public void PlayBG(){
        this.loadFile(backgroundBytes);
        soundLoop.audio.setGain(.001f);
        soundLoop.fire();
    }

    private byte[] readBytes(InputStream in) {
        try {
            BufferedInputStream buf = new BufferedInputStream(in);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int read;
            while ((read = buf.read()) != -1) {
                out.write(read);
            }
            in.close();
            return out.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void terminate(){
        shutDownClips();
    }

    private void loadFile(byte[] rawData) {
        soundLoop = new LoopEvent(new BlockingClip(rawData));
        soundLoop.initialize();
    }

    private void shutDownClips() {
        if (soundLoop != null) {
            soundLoop.shutDown();
            isSoundPlaying = false;
        }
    }
}
