package pw.janyo.fakeaccessibility;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Main implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        try {
            Class clazz = XposedHelpers.findClassIfExists("android.support.design.widget.BaseTransientBottomBar", lpparam.classLoader);
            if (clazz == null)
                return;
            XposedHelpers.findAndHookMethod(clazz, "shouldAnimate", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    return true;
                }
            });
        } catch (Exception e) {
            XposedBridge.log("hook失败，错误详情：" + e.getMessage());
        }
    }
}
