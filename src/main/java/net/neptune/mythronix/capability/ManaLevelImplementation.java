package net.neptune.mythronix.capability;

public class ManaLevelImplementation implements IManaLevel{

    private int manaLevel;
    private int manaXp;

    @Override
    public int getManaLevel() {
        return this.manaLevel;
    }

    @Override
    public int getManaXp() {
        return this.manaXp;
    }

    @Override
    public void setManaLevel(int manaLevel) {
        this.manaLevel = manaLevel;
        this.manaXp = getXpForLevel(manaLevel);
    }

    @Override
    public void setManaXp(int manaXp) {
        this.manaXp = manaXp;
        this.manaLevel = getLevelForXp(manaXp);
    }

    @Override
    public void addManaXp(int manaXp) {
        setManaXp(this.manaXp + manaXp);
    }

    @Override
    public void addManaLevel(int manaLevel) {
        setManaLevel(this.manaLevel + manaLevel);
    }

    @Override
    public void set(IManaLevel oldManaLevel) {
        this.manaLevel = oldManaLevel.getManaLevel();
        this.manaXp = oldManaLevel.getManaXp();
    }

    @Override
    public int getManaXpToNextLevel() {
        return manaXp - getXpForLevel(manaLevel);
    }

    @Override
    public int getTotalXpToNextLevel() {
        return getXpForLevel(manaLevel + 1) - getXpForLevel(manaLevel);
    }

    /**
     * Returns the total amount of xp needed to reach the given level
     * @param level the level to reach
     * @return the total amount of xp needed to reach the given level
     */
    private int getXpForLevel(int level) {
        if(level >= 0 && level <= 16){
            return (int) (Math.pow(level, 2) + 6 * level);
        } else if(level > 16 && level <= 31){
            return (int) (2.5 * Math.pow(level, 2) - 40.5 * level + 360);
        } else if(level > 31){
            return (int) (4.5 * Math.pow(level, 2) - 162.5 * level + 2220);
        } else {
            return 0;
        }
    }

    /**
     * Returns the level for the given amount of xp
     * @param xp the amount of xp
     * @return the level for the given amount of xp
     */
    private int getLevelForXp(int xp) {
        if(xp >= 0 && xp <= 352){
            return (int) (Math.sqrt(xp + 9) - 3);
        } else if(xp > 352 && xp <= 1507) {
            return (int) ((81.0/10) + Math.sqrt((2.0/5) * (xp - (7839.0/40))));
        } else if(xp > 1507){
            return (int) ((325.0 / 18) + Math.sqrt((2.0 / 9) * (xp - (54215.0 / 72))));
        } else {
            return 0;
        }
    }
}
