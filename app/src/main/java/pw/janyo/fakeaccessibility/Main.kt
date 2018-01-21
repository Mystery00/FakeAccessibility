package pw.janyo.fakeaccessibility

import de.robv.android.xposed.*
import de.robv.android.xposed.callbacks.XC_LoadPackage

class Main : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        try {
            val clazz = XposedHelpers.findClassIfExists("android.support.design.widget.BaseTransientBottomBar", lpparam.classLoader) ?: return
            XposedHelpers.findAndHookMethod(clazz, "shouldAnimate", object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: XC_MethodHook.MethodHookParam): Any {
                    return true
                }
            })
        } catch (e: Exception) {
            XposedBridge.log("hook失败，错误详情：" + e.message)
        }
    }
}
