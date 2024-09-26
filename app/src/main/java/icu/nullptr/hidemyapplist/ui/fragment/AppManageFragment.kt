package icu.nullptr.fgol.ui.fragment

import android.os.Bundle
import com.google.android.material.transition.MaterialSharedAxis
import com.ss.fgol.R
import icu.nullptr.fgol.service.ConfigManager
import icu.nullptr.fgol.ui.adapter.AppManageAdapter
import icu.nullptr.fgol.ui.util.navController

class AppManageFragment : AppSelectFragment() {

    override val firstComparator: Comparator<String> = Comparator.comparing(ConfigManager::isHideEnabled).reversed()

    override val adapter = AppManageAdapter {
        val args = AppSettingsFragmentArgs(it)
        navController.navigate(R.id.nav_app_settings, args.toBundle())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
    }
}
