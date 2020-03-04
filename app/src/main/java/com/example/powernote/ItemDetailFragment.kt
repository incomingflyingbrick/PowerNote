package com.example.powernote

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.powernote.view.PaintView
import kotlinx.android.synthetic.main.item_detail.view.*


/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    private lateinit var mPaintView:PaintView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)
        val metrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(metrics)
        mPaintView = rootView.paintview
        mPaintView.init(metrics)
        mPaintView.normal()
        return rootView
    }

    fun changeSizeFine(){

    }

    fun changeSizeMedium(){

    }

    fun changeSizeBold(){

    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
