# Xposed
-keepclassmembers class icu.andorid.yeepee.MyApp {
    boolean isHooked;
}

# Enum class
-keepclassmembers,allowoptimization enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class icu.andorid.yeepee.data.UpdateData { *; }
-keep class icu.andorid.yeepee.data.UpdateData$* { *; }

-keep,allowoptimization class * extends androidx.preference.PreferenceFragmentCompat
-keepclassmembers class com.fgol.yeepee.databinding.**  {
    public <methods>;
}
