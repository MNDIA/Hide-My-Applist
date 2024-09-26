package icu.nullptr.fgol.xposed.hook

interface IFrameworkHook {

    fun load()
    fun unload()
    fun onConfigChanged() {}
}
