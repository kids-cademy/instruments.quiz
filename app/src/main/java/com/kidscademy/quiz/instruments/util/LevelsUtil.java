package com.kidscademy.quiz.instruments.util;

import com.kidscademy.quiz.instruments.App;
import com.kidscademy.quiz.instruments.model.Level;
import com.kidscademy.quiz.instruments.model.LevelState;

import java.util.List;

public class LevelsUtil {
    private final Storage storage;

    public LevelsUtil(Storage storage) {
        this.storage = storage;
    }

    public int getTotalLevels() {
        return storage.getLevels().length;
    }

    public int getUnlockedLevels() {
        int unlockedLevels = 0;
        for (LevelState levelState : storage.getLevelStates()) {
            if (levelState.isUnlocked()) {
                ++unlockedLevels;
            }
        }
        return unlockedLevels;
    }

    public int getCompletedLevels() {
        int completedLevels = 0;
        for (LevelState levelState : storage.getLevelStates()) {
            if (levelState.isComplete()) {
                ++completedLevels;
            }
        }
        return completedLevels;
    }

    public boolean areAllLevelsComplete() {
        for (LevelState levelState : storage.getLevelStates()) {
            if (!levelState.isComplete()) {
                return false;
            }
        }
        return true;
    }

    public int getTotalInstruments() {
        int totalInstruments = 0;
        for (Level level : storage.getLevels()) {
            totalInstruments += level.getInstrumentsCount();
        }
        return totalInstruments;
    }

    public int getSolvedInstruments() {
        int solvedInstruments = 0;
        for (LevelState level : storage.getLevelStates()) {
            solvedInstruments += level.getSolvedInstrumentsCount();
        }
        return solvedInstruments;
    }
}
