package com.app.phoneticalphabet.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KClass

abstract class BaseView<in T : Recycler.RenderModel>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    abstract fun bind(viewModel: T, position: Int, tapListener: TapListener?)
}

interface TapListener {
    fun onTap(position: Int)
}

object Recycler {

    interface Renderer<T : RenderModel> {
        fun render(itemView: View, rm: T, pos: Int, tapListener: TapListener?)
    }

    interface SelectionRenderer<T : RenderModel> {
        fun render(itemView: View, rm: T, pos: Int, selectedPosition: Int, tapListener: TapListener?)
    }


    interface RenderModel

    class Type<T : RenderModel>(
        private val classType: KClass<T>,
        val viewType: Int,
        private val layout: Int,
        private val bind: (itemView: View, viewModel: T, position: Int, tapListener: TapListener?) -> Unit
    ) {

        fun makeView(context: Context, parent: ViewGroup): BaseView<T> {
            val view = LayoutInflater.from(context).inflate(layout, parent, false)
            return object : BaseView<T>(view) {
                override fun bind(viewModel: T, position: Int, tapListener: TapListener?) {
                    this@Type.bind(itemView, viewModel, position, tapListener)
                }
            }
        }

        fun matches(rm: RenderModel): Boolean {
            return classType.isInstance(rm)
        }
    }

    class Adapter(private var rows: List<RenderModel>) :
        RecyclerView.Adapter<BaseView<RenderModel>>() {

        fun updateData(rows: List<RenderModel>) {
            this.rows = rows
            this.notifyDataSetChanged()
        }

        var tapListener: TapListener? = null

        private val supportedTypes: MutableList<Type<*>> = mutableListOf()

        fun register(type: Type<*>) {
            supportedTypes.add(type)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseView<RenderModel> {
            val context = parent.context
            val index = supportedTypes.indexOfFirst { it.viewType == viewType }
            val currentType = supportedTypes[index]
            return currentType.makeView(context, parent) as? BaseView<RenderModel>
                ?: throw IllegalStateException("No match with BaseRenderModel")
        }

        override fun onBindViewHolder(viewHolder: BaseView<RenderModel>, position: Int) {
            val rowViewModel = rows[position]
            viewHolder.bind(rowViewModel, position, tapListener)
        }

        override fun getItemCount(): Int {
            return rows.count()
        }

        override fun getItemViewType(position: Int): Int {
            val rm = rows[position]
            return supportedTypes.firstOrNull { it.matches(rm) }?.viewType
                ?: throw java.lang.IllegalStateException("No match found in recyclerview")
        }
    }
}


