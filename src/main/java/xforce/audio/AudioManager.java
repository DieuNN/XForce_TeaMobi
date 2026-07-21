package xforce.audio;

import java.io.IOException;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.control.VolumeControl;
import javax.microedition.media.Player;

public final class AudioManager implements Runnable {

    private static final int SFX_COUNT = 3;
    private static final int VOLUME_MULTIPLIER = 20;
    private static final int NO_SFX_PENDING = -1;
    private static final long SFX_POLL_MS = 50;
    private static final int LOOP_INDEFINITELY = -1;
    private static final int MAX_SFX_VOLUME = 100;
    private static final String soundPath = "/sound";

    private static boolean running;
    public static int musicVolume;
    public static int sfxVolume;
    private static Player[] sfxPlayers;
    private static Player musicPlayer;
    private static int pendingSfxVolume;
    private static AudioManager instance;
    private static int pendingSfxIndex = NO_SFX_PENDING;

    public static void loadSounds() {
        if (instance == null) {
            sfxPlayers = new Player[SFX_COUNT];
            try {
                sfxPlayers[0] = Manager.createPlayer("".getClass().getResourceAsStream(soundPath + "/shoot.wav"), "audio/x-wav");
                sfxPlayers[0].prefetch();
                sfxPlayers[1] = Manager.createPlayer("".getClass().getResourceAsStream(soundPath + "/explo.wav"), "audio/x-wav");
                sfxPlayers[1].prefetch();
                sfxPlayers[2] = Manager.createPlayer("".getClass().getResourceAsStream(soundPath + "/missile.wav"), "audio/x-wav");
                sfxPlayers[2].prefetch();
            } catch (MediaException | IOException e) {
                e.printStackTrace();
            }
            instance = new AudioManager();
            running = true;
            Thread audioThread = new Thread(instance);
            audioThread.start();
        }
    }

    public static void playSfx(int sfxIndex, int volume) {
        if (volume <= 0) {
            return;
        }
        pendingSfxVolume = volume;
        pendingSfxIndex = sfxIndex;
    }

    public static void playSfxMax(int sfxIndex) {
        pendingSfxVolume = MAX_SFX_VOLUME;
        pendingSfxIndex = sfxIndex;
    }

    public static void stopMusic() {
        if (musicPlayer == null || musicPlayer.getState() != Player.STARTED) {
            return;
        }
        try {
            musicPlayer.stop();
        } catch (MediaException e) {
            e.printStackTrace();
        }
    }

    public static void startMusic() {
        if (musicPlayer != null) {
            try {
                if (musicVolume > 0) {
                    musicPlayer.start();
                }
            } catch (MediaException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeMusic() {
        if (musicPlayer != null) {
            if (musicPlayer.getState() == Player.STARTED) {
                try {
                    musicPlayer.stop();
                } catch (MediaException e) {
                    e.printStackTrace();
                }
            }
            musicPlayer.close();
        }
    }

    public static void loadMusic(String filename) {
        closeMusic();
        try {
            musicPlayer = Manager.createPlayer("".getClass().getResourceAsStream(soundPath + filename), "audio/midi");
            musicPlayer.setLoopCount(LOOP_INDEFINITELY);
            if (musicVolume > 0) {
                musicPlayer.start();
                ((VolumeControl) musicPlayer.getControl("VolumeControl")).setLevel(musicVolume * VOLUME_MULTIPLIER);
            }
        } catch (MediaException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void setMusicVolume(int volume) {
        if (musicPlayer != null && musicPlayer.getState() != Player.UNREALIZED) {
            try {
                if (volume > 0) {
                    musicPlayer.start();
                    ((VolumeControl) musicPlayer.getControl("VolumeControl")).setLevel(volume * VOLUME_MULTIPLIER);
                } else {
                    musicPlayer.stop();
                }
            } catch (MediaException e) {
                e.printStackTrace();
            }
        }
        musicVolume = volume;
    }

    public static void setSfxVolume(int volume) {
        sfxVolume = volume;
    }

    @Override
    public void run() {
        while (running) {
            if (pendingSfxIndex > NO_SFX_PENDING && pendingSfxIndex < sfxPlayers.length) {
                int sfxIndex = pendingSfxIndex;
                pendingSfxIndex = NO_SFX_PENDING;
                try {
                    if (sfxPlayers[sfxIndex].getState() != Player.STARTED && sfxVolume > 0) {
                        VolumeControl vc = (VolumeControl) sfxPlayers[sfxIndex].getControl("VolumeControl");
                        if (vc != null) {
                            vc.setLevel(((sfxVolume * VOLUME_MULTIPLIER) * pendingSfxVolume) / MAX_SFX_VOLUME);
                        }
                        sfxPlayers[sfxIndex].start();
                    }
                } catch (MediaException ignored) {
                }
            }
            try {
                Thread.sleep(SFX_POLL_MS);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
