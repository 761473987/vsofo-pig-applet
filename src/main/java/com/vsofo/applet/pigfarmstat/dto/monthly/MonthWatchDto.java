package com.vsofo.applet.pigfarmstat.dto.monthly;

public class MonthWatchDto {

    private double npd = 0;
    private double avgPregnancyDay = 0; //平均妊娠天数 21
    private double avgBreastDay = 0;  //平均哺乳天数  52
    private double totalChildbirth = 0;   //配种对应分娩头数  89
    private double totalDueDate = 0;  //对应预产期记录数   13
    private double totalWeaning = 0;  //断奶仔猪数  44
    private double totalWeaningLive = 0;    //断奶对应的出生活仔数  45
    private double totalWeaningSow = 0;     //断奶母猪头数  42
    private double totaldeathChildcare = 0;  //保育仔猪死亡数 77
    private double avgNurseryPiglets = 0;  //保育仔猪平均存栏 67
    private double totaldeathFatteningPig = 0;  //育肥猪死亡数 78
    private double avgFatteningPig = 0; //育肥猪平均存栏 68

    public MonthWatchDto() {
    }

    public MonthWatchDto(double npd, double avgPregnancyDay, double avgBreastDay, double totalChildbirth, double totalDueDate, double totalWeaning, double totalWeaningLive, double totalWeaningSow, double totaldeathChildcare, double avgNurseryPiglets, double totaldeathFatteningPig, double avgFatteningPig) {
        this.npd = npd;
        this.avgPregnancyDay = avgPregnancyDay;
        this.avgBreastDay = avgBreastDay;
        this.totalChildbirth = totalChildbirth;
        this.totalDueDate = totalDueDate;
        this.totalWeaning = totalWeaning;
        this.totalWeaningLive = totalWeaningLive;
        this.totalWeaningSow = totalWeaningSow;
        this.totaldeathChildcare = totaldeathChildcare;
        this.avgNurseryPiglets = avgNurseryPiglets;
        this.totaldeathFatteningPig = totaldeathFatteningPig;
        this.avgFatteningPig = avgFatteningPig;
    }

    public double getNpd() {
        return npd;
    }

    public void setNpd(double npd) {
        this.npd = npd;
    }

    public double getAvgPregnancyDay() {
        return avgPregnancyDay;
    }

    public void setAvgPregnancyDay(double avgPregnancyDay) {
        this.avgPregnancyDay = avgPregnancyDay;
    }

    public double getAvgBreastDay() {
        return avgBreastDay;
    }

    public void setAvgBreastDay(double avgBreastDay) {
        this.avgBreastDay = avgBreastDay;
    }

    public double getTotalChildbirth() {
        return totalChildbirth;
    }

    public void setTotalChildbirth(double totalChildbirth) {
        this.totalChildbirth = totalChildbirth;
    }

    public double getTotalDueDate() {
        return totalDueDate;
    }

    public void setTotalDueDate(double totalDueDate) {
        this.totalDueDate = totalDueDate;
    }

    public double getTotalWeaning() {
        return totalWeaning;
    }

    public void setTotalWeaning(double totalWeaning) {
        this.totalWeaning = totalWeaning;
    }

    public double getTotalWeaningLive() {
        return totalWeaningLive;
    }

    public void setTotalWeaningLive(double totalWeaningLive) {
        this.totalWeaningLive = totalWeaningLive;
    }

    public double getTotalWeaningSow() {
        return totalWeaningSow;
    }

    public void setTotalWeaningSow(double totalWeaningSow) {
        this.totalWeaningSow = totalWeaningSow;
    }

    public double getTotaldeathChildcare() {
        return totaldeathChildcare;
    }

    public void setTotaldeathChildcare(double totaldeathChildcare) {
        this.totaldeathChildcare = totaldeathChildcare;
    }

    public double getAvgNurseryPiglets() {
        return avgNurseryPiglets;
    }

    public void setAvgNurseryPiglets(double avgNurseryPiglets) {
        this.avgNurseryPiglets = avgNurseryPiglets;
    }

    public double getTotaldeathFatteningPig() {
        return totaldeathFatteningPig;
    }

    public void setTotaldeathFatteningPig(double totaldeathFatteningPig) {
        this.totaldeathFatteningPig = totaldeathFatteningPig;
    }

    public double getAvgFatteningPig() {
        return avgFatteningPig;
    }

    public void setAvgFatteningPig(double avgFatteningPig) {
        this.avgFatteningPig = avgFatteningPig;
    }
}
