package com.example.pass.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pass.PassProvider
import com.example.pass.R
import kotlinx.android.synthetic.main.fragment_menu.*


class MenuFragment : Fragment() {

    private val menuController = MenuController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menu_recyclerView.layoutManager = LinearLayoutManager(view.context)
        menu_recyclerView.adapter = menuController.adapter
        menuController.setPassList(PassProvider().getPassList())
    }

    companion object {
        fun newInstance() = MenuFragment()
    }
}