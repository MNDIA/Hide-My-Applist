package icu.andorid.yeepee.xposed.hook

import android.annotation.TargetApi
import android.os.Build
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import de.robv.android.xposed.XC_MethodHook
import icu.andorid.yeepee.common.CommonUtils
import icu.andorid.yeepee.common.Constants.*
import icu.andorid.yeepee.xposed.YEPService
import icu.andorid.yeepee.xposed.logE
import icu.andorid.yeepee.xposed.logI
import icu.andorid.yeepee.xposed.logW

@TargetApi(Build.VERSION_CODES.S)
class ZygoteArgsHook(private val service: YEPService) : IFrameworkHook {

    companion object {
        private const val TAG = "ZygoteArgsHook"
        private val sAppDataIsolationEnabled = CommonUtils.isAppDataIsolationEnabled
        private val sVoldAppDataIsolationEnabled = CommonUtils.isVoldAppDataIsolationEnabled
    }

    private var hook: XC_MethodHook.Unhook? = null

    override fun load() {
        if (!service.config.forceMountData) return
        logI(TAG, "Load hook")
        logI(TAG, "App data isolation enabled: $sAppDataIsolationEnabled")
        logI(TAG, "Vold app data isolation enabled: $sVoldAppDataIsolationEnabled")
        hook = findMethod("android.os.ZygoteProcess") {
            name == "startViaZygote"
        }.hookBefore { param ->
            runCatching {
                val uid = param.args[2] as Int
                if (uid == UID_SYSTEM) return@hookBefore
                val apps = service.pms.getPackagesForUid(uid) ?: return@hookBefore 
                for (app in apps) {
                    if (service.isHookEnabled(app)) {
                        if (sAppDataIsolationEnabled) param.args[20] = true // boolean bindMountAppsData

                        // if (sVoldAppDataIsolationEnabled) param.args[21] = true // boolean bindMountAppStorageDirs
                        //使用安卓系统判断这次bindMountAppStorageDirs的值，不强制true

                        // 获取 param.args[21] 的值并记录到日志中
                        val bindMountAppStorageDirs = param.args[21] as Boolean
                        val pkgDataInfoMap = if (param.args[18] == null) "null" else "IMap"
                        if (bindMountAppStorageDirs) {
                            logI(TAG, "@startViaZygote : $uid $app $number $bindMountAppStorageDirs \n $pkgDataInfoMap")
                        } else {
                            logW(TAG, "@startViaZygote : $uid $app $number $bindMountAppStorageDirs \n $pkgDataInfoMap")
                        }
                        return@hookBefore
                    }
                }
            }.onFailure {
                logE(TAG, "Fatal error occurred, disable hooks", it)
                unload()
            }
        }
    }

    override fun unload() {
        hook?.unhook()
        hook = null
    }

    override fun onConfigChanged() {
        if (service.config.forceMountData) {
            if (hook == null) load()
        } else {
            if (hook != null) unload()
        }
    }
}
