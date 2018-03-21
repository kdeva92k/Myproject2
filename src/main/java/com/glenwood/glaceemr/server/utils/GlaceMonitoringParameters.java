package com.glenwood.glaceemr.server.utils;

public class GlaceMonitoringParameters {

    int Shared = 0;
    int DB = 0;
    float DBRT = 0;
    int DBSP = 0;
    int SHSP = 0;
    int DBP = 0;
    long DBFS = 0;
    long SHFS = 0;
    long TFS = 0;
    long VARFS = 0;
    long USRFS = 0;
    long RFS = 0;
    long FMEM = 0;
    long FJVM = 0;
    public int getShared() {
        return Shared;
    }
    public void setShared(int shared) {
        Shared = shared;
    }
    public int getDB() {
        return DB;
    }
    public void setDB(int dB) {
        DB = dB;
    }
    public float getDBRT() {
        return DBRT;
    }
    public void setDBRT(float dBRT) {
        DBRT = dBRT;
    }
    public int getDBSP() {
        return DBSP;
    }
    public void setDBSP(int dBSP) {
        DBSP = dBSP;
    }
    public int getSHSP() {
        return SHSP;
    }
    public void setSHSP(int sHSP) {
        SHSP = sHSP;
    }
    public int getDBP() {
        return DBP;
    }
    public void setDBP(int dBP) {
        DBP = dBP;
    }
    public long getDBFS() {
        return DBFS;
    }
    public void setDBFS(long dBFS) {
        DBFS = dBFS;
    }
    public long getSHFS() {
        return SHFS;
    }
    public void setSHFS(long sHFS) {
        SHFS = sHFS;
    }
    public long getTFS() {
        return TFS;
    }
    public void setTFS(long tFS) {
        TFS = tFS;
    }
    public long getVARFS() {
        return VARFS;
    }
    public void setVARFS(long vARFS) {
        VARFS = vARFS;
    }
    public long getUSRFS() {
        return USRFS;
    }
    public void setUSRFS(long uSRFS) {
        USRFS = uSRFS;
    }
    public long getRFS() {
        return RFS;
    }
    public void setRFS(long rFS) {
        RFS = rFS;
    }
    public long getFMEM() {
        return FMEM;
    }
    public void setFMEM(long fMEM) {
        FMEM = fMEM;
    }
    public long getFJVM() {
        return FJVM;
    }
    public void setFJVM(long fJVM) {
        FJVM = fJVM;
    }
    @Override
    public String toString() {
        return "GlaceMonitoringParameters [Shared=" + Shared + ", DB=" + DB
                + ", DBRT=" + DBRT + ", DBSP=" + DBSP + ", SHSP=" + SHSP
                + ", DBP=" + DBP + ", DBFS=" + DBFS + ", SHFS=" + SHFS
                + ", TFS=" + TFS + ", VARFS=" + VARFS + ", USRFS=" + USRFS
                + ", RFS=" + RFS + ", FMEM=" + FMEM + ", FJVM=" + FJVM + "]";
    }

}

