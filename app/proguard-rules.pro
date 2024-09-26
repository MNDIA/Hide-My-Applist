# Xposed
-keepclassmembers class icu.nullptr.fgol.MyApp {
    boolean isHooked;
}

# Enum class
-keepclassmembers,allowoptimization enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class icu.nullptr.fgol.data.UpdateData { *; }
-keep class icu.nullptr.fgol.data.UpdateData$* { *; }

-keep,allowoptimization class * extends androidx.preference.PreferenceFragmentCompat
-keepclassmembers class com.ss.fgol.databinding.**  {
    public <methods>;
}
