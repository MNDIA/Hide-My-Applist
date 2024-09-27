package icu.andorid.yeepee.ui.fragment

import android.os.Bundle
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import icu.andorid.yeepee.service.ConfigManager
import icu.andorid.yeepee.ui.adapter.AppScopeAdapter
import icu.andorid.yeepee.ui.util.navController

class ScopeFragment : AppSelectFragment() {

    private lateinit var checked: MutableSet<String>

    override val firstComparator: Comparator<String> = Comparator.comparing { !checked.contains(it) }

    override val adapter by lazy {
        val args by navArgs<ScopeFragmentArgs>()
        checked = args.checked.toMutableSet()
        if (!args.filterOnlyEnabled) AppScopeAdapter(checked, null)
        else AppScopeAdapter(checked) { ConfigManager.getAppConfig(it)?.useWhitelist == args.isWhiteList }
    }

    override fun onBack() {
        setFragmentResult("app_select", Bundle().apply {
            putStringArrayList("checked", ArrayList(checked))
        })
        navController.navigateUp()
    }
}
