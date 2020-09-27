package io.oddlot.ledger.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.oddlot.ledger.R
import io.oddlot.ledger.db
import io.oddlot.ledger.adapters.TabsAdapter
//import kotlinx.android.synthetic.main.fragment_main.*
//import kotlinx.android.synthetic.main.fragment_main.tabsRecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TabsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tabs, container, false)

        val rv = view.findViewById<RecyclerView>(R.id.rvTabs)

        rv.apply {
            layoutManager = LinearLayoutManager(activity)

            CoroutineScope(Dispatchers.IO).launch {
                val tabs = db.tabDao().allTabs()
                CoroutineScope(Dispatchers.Main).launch {
                    adapter = TabsAdapter(tabs)
                }
            }
        }

        return view
    }
}