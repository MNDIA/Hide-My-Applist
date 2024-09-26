package icu.nullptr.fgol.common;

interface IYPWService {

    void stopService(boolean cleanEnv) = 0;

    void syncConfig(String json) = 1;

    int getServiceVersion() = 2;

    int getFilterCount() = 3;

    String getLogs() = 4;

    void clearLogs() = 5;
}
