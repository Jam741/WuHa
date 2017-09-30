package com.pisces.android.wuha

import com.mob.MobSDK
import com.pisces.android.framworkerlibrary.JBaseApplication
import com.pisces.android.locationlibrary.GDLocationUtil
import com.pisces.android.sharesdk.ShareSDK
import com.taobao.sophix.PatchStatus
import com.taobao.sophix.SophixManager

/**
 * Created by Jam on 2017/8/24.
 */
class App : JBaseApplication() {
    override fun onCreate() {
        super.onCreate()
        hotFixConfig()
        MobSDK.init(this, Config.MOB_APP_KEY, Config.MOB_APP_SECRET)
        GDLocationUtil.init(this)
        ShareSDK.initSDK(this)
    }


    /**
     * 热更新配置
     */
    fun hotFixConfig() {
        var appVersion: String
        try {
            appVersion = this.packageManager.getPackageInfo(this.packageName, 0).versionName
        } catch (e: Exception) {
            appVersion = "1.0.0"
        }

        SophixManager
                .getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAesKey(Config.ALI_HOTFIX_AES_KEY)
                .setEnableDebug(true)
                .setPatchLoadStatusStub { mode, code, info, handlePatchVersion ->
                    // 补丁加载回调通知
                    if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                        // 表明补丁加载成功
                    } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                        // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                        // 建议: 用户可以监听进入后台事件, 然后应用自杀
                    } else if (code == PatchStatus.CODE_LOAD_FAIL) {
                        // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                        // SophixManager.getInstance().cleanPatches();
                    } else {
                        // 其它错误信息, 查看PatchStatus类说明
                    }
                }.initialize()
        SophixManager.getInstance().queryAndLoadNewPatch()
    }
}