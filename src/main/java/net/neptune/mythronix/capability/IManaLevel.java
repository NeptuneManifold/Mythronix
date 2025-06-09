package net.neptune.mythronix.capability;

public interface IManaLevel {
    int getManaLevel();
    int getManaXp();
    void setManaLevel(int level);
    void setManaXp(int cp);
    void addManaLevel(int level);
    void addManaXp(int xp);
    void set(IManaLevel oldManaLevel);
    int getManaXpToNextLevel();
    int getTotalXpToNextLevel();
}
