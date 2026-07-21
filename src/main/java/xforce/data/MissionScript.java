package xforce.data;

import xforce.entity.PlayerVehicle;
import xforce.entity.Sprite;
import xforce.game.GameCanvas;
import xforce.game.GameLevel;
import xforce.resource.Localization;
import xforce.screen.DialogBox;
public final class MissionScript {

    private static final int PHASE_NONE           = 0;
    private static final int PHASE_TRAINING       = 1;
    private static final int PHASE_BOSS_RUN       = 2;
    private static final int PHASE_MISSION_COMPLETE = 3;
    private static final int PHASE_DESTROY_BOSS   = 4;
    private static final int PHASE_DESTROY_AIRPORT = 5;
    private static final int PHASE_PROTECT_VIP    = 6;

    private static final int WAYPOINT_DESTINATION = 2;
    private static final int SCRIPT_COOLDOWN_TICKS = 1;

    public static int scriptPhase;
    private static int cooldownTicks;
    private static int scriptTimer;
    private static boolean scriptDone;
    public static void nextPhase(int i) {
        scriptPhase = i;
        scriptTimer = 0;
    }
    public static void checkTrigger() {
        if (cooldownTicks > 0) {
            cooldownTicks--;
            return;
        }
        if (!DialogBox.isVisible) {
            scriptDone = false;
        }
        if (scriptDone) {
            return;
        }
        switch (scriptPhase) {
            case PHASE_TRAINING:
                if (scriptTimer == 20) {
                    DialogBox.setText(Localization.trainDialogue[0], 3);
                    DialogBox.isVisible = true;
                } else if (scriptTimer == 40) {
                    GameLevel.cameraTarget = GameLevel.missionObjective;
                    scriptDone = true;
                } else if (scriptTimer == 41) {
                    DialogBox.setText(Localization.trainDialogue[1], GameLevel.currentVehicle);
                    scriptDone = true;
                } else if (scriptTimer == 42) {
                    DialogBox.setText(Localization.trainDialogue[2], 3);
                    GameLevel.missionY = 1272;
                    GameLevel.missionParam = 960;
                    GameLevel.missionX = 2;
                    GameLevel.setWaypoint(1272, 960);
                    scriptDone = true;
                } else if (scriptTimer == 43) {
                    DialogBox.setText(Localization.trainDialogue[3], 3);
                    GameLevel.missionY = 132;
                    GameLevel.missionParam = 132;
                    GameLevel.missionX = 2;
                    GameLevel.setWaypoint(132, 132);
                    scriptDone = true;
                } else if (scriptTimer == 44) {
                    DialogBox.setText(Localization.trainDialogue[4], 3);
                    GameLevel.cameraTarget = GameLevel.player;
                    GameLevel.missionX = 0;
                    GameLevel.setWaypoint(1272, 960);
                    scriptDone = true;
                } else if (scriptTimer == 45) {
                    scriptPhase = PHASE_NONE;
                }
                break;
            case PHASE_BOSS_RUN:
                if (scriptTimer == 20) {
                    DialogBox.setText(Localization.bossRunDialogue[0], GameLevel.currentVehicle);
                    scriptDone = true;
                } else if (scriptTimer == 40) {
                    DialogBox.setText(Localization.bossRunDialogue[1], 2);
                    scriptDone = true;
                } else if (scriptTimer == 60) {
                    PlayerVehicle fleeingJeep = new PlayerVehicle((byte) -3);
                    fleeingJeep.setPosition(1056, 960);
                    GameLevel.addEntity((Sprite) fleeingJeep);
                    GameLevel.cameraTarget = fleeingJeep;
                } else if (scriptTimer == 80) {
                    DialogBox.setText(Localization.bossRunDialogue[2], 3);
                    scriptDone = true;
                } else if (scriptTimer == 100) {
                    scriptPhase = PHASE_NONE;
                    GameLevel.currentVehicle = 2;
                    GameCanvas.gameLevel.loadMission(31);
                }
                break;
            case PHASE_MISSION_COMPLETE:
                if (scriptTimer == 20) {
                    DialogBox.setText(Localization.completeDialogue[0], 2);
                    scriptDone = true;
                } else if (scriptTimer == 21) {
                    DialogBox.setText(Localization.completeDialogue[1], 3);
                    scriptDone = true;
                } else if (scriptTimer == 22) {
                    DialogBox.setText(Localization.completeDialogue[2], 0);
                    scriptDone = true;
                } else if (scriptTimer == 23) {
                    DialogBox.setText(Localization.completeDialogue[3], 1);
                    scriptDone = true;
                } else if (scriptTimer == 24) {
                    DialogBox.setText(Localization.completeDialogue[4], 4);
                } else if (scriptTimer == 25) {
                    scriptPhase = PHASE_NONE;
                    GameLevel.triggerGameOver(true);
                }
                break;
            case PHASE_DESTROY_BOSS:
                if (scriptTimer == 0) {
                    GameLevel.cameraTarget = GameLevel.missionObjective;
                } else if (scriptTimer == 50) {
                    DialogBox.setText(Localization.txtDestroyBoss, 3);
                    scriptDone = true;
                } else if (scriptTimer == 52) {
                    GameLevel.cameraTarget = GameLevel.player;
                    scriptPhase = PHASE_NONE;
                }
                break;
            case PHASE_DESTROY_AIRPORT:
                if (scriptTimer == 20) {
                    DialogBox.setText(Localization.txtDestroyAirport, 3);
                    scriptDone = true;
                }
                if (scriptTimer == 21) {
                    GameLevel.missionY = 504;
                    GameLevel.missionParam = 624;
                    GameLevel.missionX = 2;
                } else if (scriptTimer == 50) {
                    GameLevel.missionY = 1056;
                    GameLevel.missionParam = 624;
                } else if (scriptTimer == 80) {
                    GameLevel.missionY = 504;
                    GameLevel.missionParam = 984;
                } else if (scriptTimer == 120) {
                    GameLevel.cameraTarget = GameLevel.player;
                    GameLevel.missionX = 0;
                    scriptPhase = PHASE_NONE;
                }
                break;
            case PHASE_PROTECT_VIP:
                if (scriptTimer == 20) {
                    GameLevel.cameraTarget = GameLevel.missionObjective;
                    DialogBox.setText(Localization.txtProtectVip, 3);
                    scriptDone = true;
                } else if (scriptTimer == 21) {
                    GameLevel.cameraTarget = GameLevel.player;
                    scriptPhase = PHASE_NONE;
                }
                break;
        }
        if (scriptPhase != PHASE_NONE) {
            scriptTimer++;
        }
    }
}
